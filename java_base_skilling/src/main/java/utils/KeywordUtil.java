package utils;

import com.csvreader.CsvReader;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.dic.Dictionary;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class KeywordUtil {
    static Configuration cfg;
    static List<String> expandWords = new ArrayList<>();

    /**
     * 每个词的最小长度
     */
    private static final int MIN_LEN = 2;

    static  {
        cfg = DefaultConfig.getInstance();
        cfg.setUseSmart(true); //设置useSmart标志位 true-智能切分 false-细粒度切分
        boolean flag = loadDictionaries("keywords.dic");
        if (!flag) {
            throw new RuntimeException("读取失败");
        }
        Dictionary.initial(cfg);
        Dictionary.getSingleton().addWords(expandWords); //词典中加入自定义单词
    }

    /**
     * 加载自定义词典，若无想要添加的词则无需调用，使用默认的词典
     * @param filenames
     * @return
     */
    private static boolean loadDictionaries(String... filenames) {
        try {
            for (String filename : filenames) {
                expandWords.addAll(IOUtils.readLines(KeywordUtil.class.getClassLoader().getResourceAsStream(filename), StandardCharsets.UTF_8));
                expandWords.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 提取词语，结果将按频率排序
     * @param text 待提取的文本
     * @return 提取出的词
     */
    public static List<String> extract(String text) {
        StringReader reader = new StringReader(text);
        IKSegmenter ikSegmenter = new IKSegmenter(reader, cfg);
        Lexeme lex;
        Map<String, Integer> countMap = new HashMap<>();
        try {
            while ((lex = ikSegmenter.next()) != null) {
                String word = lex.getLexemeText();
                if (word.length() >= MIN_LEN) { //取出的词至少#{MIN_LEN}个字
                    countMap.put(word, countMap.getOrDefault(word, 0) + 1);
                }
            }
            List<String> result = new ArrayList<>(countMap.keySet());
            //根据词出现频率从大到小排序
            result.sort((w1, w2) -> countMap.get(w2) - countMap.get(w1));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 提取存在于我扩充词典的词
     * @param num 需要提取的词个数
     * @return
     */
    public static List<String> getKeywords(String text, Integer num) {
        List<String> words = extract(text);
        List<String> result = new ArrayList<>();
        int count = 0;
        for (String word : words) {
            if (expandWords.contains(word)) {
                result.add(word);
                if (++count == num) {
                    break;
                }
            }
        }
        return result;
    }


    public static List<Cell[]> readExcel(String pathSource, String sourceName, List<Integer> colIndex) {
        try {
			/*// 如果需要通过URL获取资源的加上以下的代码，不需要的省略就行
			URL url = new URL(strURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			// 设置超时间为3秒
			conn.setConnectTimeout(3*1000);
			// 防止屏蔽程序抓取而返回403错误
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 获取输入流
			InputStream inputStream = conn.getInputStream();
			Workbook workbook  = Workbook.getWorkbook(inputStream);
			......*/

            // 解析路径的file文件
            Workbook workbook = Workbook.getWorkbook(new File(pathSource+sourceName));
            // 获取第一张工作表
            Sheet sheet = workbook.getSheet(0);
            // 循环获取每一行数据 因为默认第一行为标题行，我们可以从 1 开始循环，如果需要读取标题行，从 0 开始

            List<Cell[]> cells = new ArrayList<>();
            cells.add(sheet.getRow(0));
            // sheet.getRows() 获取总行数
            for (int i = 1; i < sheet.getRows(); i++) {
                // 获取第一列的第 i 行信息 sheet.getCell(列，行)，下标从0开始
//                String id = sheet.getCell(0, i).getContents();
                Cell[] row = sheet.getRow(i);
                StringBuilder contents = new StringBuilder();
                for (Integer index : colIndex) {
                    contents.append(row[index].getContents()).append("||||");
                }
                List<String> keywords = getKeywords(String.valueOf(contents), 20);
                if (keywords.size() > 0){
                    log.info("当前匹配到的数据是:{},匹配到的关键词是{},匹配到行是{}",contents,keywords,i+1);
                    cells.add(row);
                }
            }

            return cells;

        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createSheet(String path,String name,List<Cell[]> cells) throws IOException, WriteException {
        //创建关联磁盘文件
        File excel = new File(path +name);
        //创建一个excel
        WritableWorkbook workbook = Workbook.createWorkbook(excel);
        WritableSheet sheet = workbook.createSheet("result", 0);
        log.info("当前的数据获取大小为:{}",cells.size()-1);
        Label label =null ;
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).length; j++) {
                label=new Label(j,i,cells.get(i)[j].getContents());
                sheet.addCell(label);
            }
        }
        log.info("开始写数据");
        //写入数据
        workbook.write();
        workbook.close();

    }



    public static void main(String[] args) throws IOException {
//        String text = "新一代移动通信基站设备 qe131数字程控交换机";

        List<Integer> colIndex = new ArrayList<>();
        colIndex.add(23);
        colIndex.add(24);
        colIndex.add(25);

        List<Cell[]> cells = readExcel("/Users/yann/IdeaProjects/java_internal_skill_training/java_base_skilling/src/main/resources/", "2014.xls", colIndex);

        try {
            createSheet("/Users/yann/IdeaProjects/java_internal_skill_training/java_base_skilling/src/main/resources/","res_2014.xls",cells);
        } catch (WriteException e) {
            throw new RuntimeException(e);
        }





    }
}