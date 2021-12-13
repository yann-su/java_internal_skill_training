package Source;

import entity.Order;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class MapConfigSource extends RichParallelSourceFunction<Map<String,String>> {


    private boolean flag = true;
    @Override
    public void run(SourceFunction.SourceContext<Map<String,String>> sourceContext) throws Exception {


        while (flag){

            Map<String, String> map = new HashMap<>();
            map.put("source","1");
            map.put("uin","11529213");
            map.put("strBak1","0:100891-328271854 0:100892-3282718234");
            map.put("__tid","music_dc0970");
            map.put("order_songid","97744");
            map.put("tid","1158186802");

            String s = "0:100891-328271854 0:100892-3282718234";



            sourceContext.collect(map);
            Thread.sleep(2000031131);



        }
    }

    @Override
    public void cancel() {
        flag = false;
    }

}
