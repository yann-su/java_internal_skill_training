package utils;

import com.csvreader.CsvReader;
import org.apache.commons.io.IOUtils;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.dic.Dictionary;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class KeywordUtil {
    Configuration cfg;
    List<String> expandWords = new ArrayList<>();

    /**
     * 每个词的最小长度
     */
    private static final int MIN_LEN = 2;

    KeywordUtil() {
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
    private boolean loadDictionaries(String... filenames) {
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
    public List<String> extract(String text) {
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
    public List<String> getKeywords(String text, Integer num) {
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



    public static void main(String[] args) throws IOException {
//        String text = "新一代移动通信基站设备 qe131数字程控交换机";


        CsvReader newCsvReader = new CsvReader("/Users/yann/IdeaProjects/java_internal_skill_training/java_base_skilling/src/main/resources/1024.txt",'\t', Charset.forName("UTF-8"));
        //读取表头
        newCsvReader.readHeaders();

        //读取到的行数内容存入到集合中
        ArrayList<String> newCsvReaderList = new ArrayList<>();

        //讲数据存储到集合中
        while (newCsvReader.readRecord()) {
            //读取一整行
            newCsvReaderList.add(newCsvReader.getRawRecord());
            KeywordUtil keywordUtil = new KeywordUtil();
            List<String> keywords = keywordUtil.getKeywords(newCsvReader.getRawRecord(), 1);
            keywords.forEach(System.out::println);
        }







    }
}