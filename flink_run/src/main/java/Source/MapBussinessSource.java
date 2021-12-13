package Source;

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapBussinessSource extends RichParallelSourceFunction<Map<String,String>> {


    private boolean flag = true;
    @Override
    public void run(SourceContext<Map<String,String>> sourceContext) throws Exception {


        Random random = new Random();
        while (flag){
            Thread.sleep(10000);
            Map<String, String> map = new HashMap<>();
            map.put("int11","1158186802");
            map.put("songid","328271854");
            map.put("uin","1152921337743");
            map.put("playtime","131");
            map.put("cmd","1");
            map.put("uuid","1227787015");
            map.put("int24","10015");
            map.put("string1","1,13,");
            map.put("os_type","11");
            map.put("reporttime","2021-10-29 14:27:57");
            map.put("__tid","ec00050");

            sourceContext.collect(map);
            Thread.sleep(10000);
        }
    }

    @Override
    public void cancel() {
        flag = false;
    }

}
