package task;

import Source.MapBussinessSource;
import Source.MapConfigSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import entity.ListeningDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.util.Collector;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class TTLState {

    public static String MapNull(Map<String, String> map,String field,String defaultValue){
        String orDefault = map.getOrDefault(field, defaultValue).trim();
        if (orDefault.equals("")){
            return "-1";
        }
        return orDefault;
    }

    public static void main(String[] args) throws Exception {


        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);


        DataStreamSource<Map<String, String>> mapDataStreamSource1 = env.addSource(new MapConfigSource()).setParallelism(1);
        DataStreamSource<Map<String, String>> mapDataStreamSource2 = env.addSource(new MapBussinessSource()).setParallelism(1);

        DataStream<Map<String, String>> tidStream = mapDataStreamSource1.union(mapDataStreamSource2);

        SingleOutputStreamOperator<Map<String, String>> filterStream = tidStream.filter(m -> {
            if (m.get("__tid").equals("ec00050")){
                long cmd = Long.parseLong(MapNull(m,"cmd","-1"));
                long uuid = Long.parseLong(MapNull(m,"uuid","-1"));
                long playtime = Long.parseLong(MapNull(m,"playtime","-1"));
                long int24 = Long.parseLong(MapNull(m,"int24","-1"));
                //int11要求不为空，但是避免流数据可能会有int11="",转换异常行为
                long int11 = Long.parseLong(MapNull(m,"int11","-1"));


                String string1 = m.getOrDefault("string1","");
                List<Long> integerArrayList = new ArrayList<>(Arrays.asList(10015L,10074L,10075L,10077L,10080L,10081L));
                String pattern = "^1,13,.*";
                /**
                 *
                 * select author_uin, a.playlist_id, order_id, order_songid, uin, nvl(play_cnt,0)play_cnt, nvl(playtime,0)playtime
                 * from
                 * (
                 *   -- 接单往歌单加歌的操作: tme_music_app::t_dw_music_dc0970 歌单运营触发写操作流水
                 *   SELECT distinct author_uin, playlist_id,
                 *       split(split(op_record, ':')[1],'-')[0] order_id, split(split(op_record, ':')[1],'-')[1] order_songid
                 *   from
                 *   (
                 *       SELECT uin author_uin, tid playlist_id, strBak1
                 *       from
                 *       tme_music_app::t_dw_music_dc0970
                 *       where tdbank_imp_date between 2021102400 and 2021102423
                 *         and `source`=1
                 *   )t LATERAL VIEW explode(split(strBak1,' ')) nt AS op_record
                 * )a
                 * left join
                 * (
                 *   select int11 playlist_id, songid, uin, playtime, 1 play_cnt
                 *   from hlw::t_dw_ec00050
                 *   where imp_date = 20211024
                 *       and cmd=1 and uuid>0
                 *       and playtime between 0 and 3600
                 *       and int24 in (10014,10015,10074,10075,10077,10080,10081)
                 *       and int11 is not null and int11 > 10000
                 *       and ((int24=10014 and string1 not like '1,13,%' and string1 != '2,20,305,') or int24 != 10014)
                 *   -- group by int11, songid, uin
                 * )b on a.playlist_id = b.playlist_id and a.order_songid = b.songid
                 */
                return cmd == 1
                        && uuid > 0
                        && ( playtime >= 0 && playtime <= 3600 )
                        && int11 > 10000
                        && ( integerArrayList.contains(int24) || ( int24 == 10014 && !Pattern.matches(pattern, string1) && !string1.equals("2,20,305,")) );
            }else if (m.get("__tid").equals("music_dc0970")){
                int sourceTid = Integer.parseInt(m.getOrDefault("source","-1"));
                //配置流： 实时流是2定时流是1，离线业务采用定时1，改造实时使用2
                return sourceTid == 1 || sourceTid == 2 ;
            }
            return false;
        });


        DataStream<ListeningDetails> flatMapStream= filterStream.flatMap((FlatMapFunction<Map<String, String>, ListeningDetails>) (map, out) -> {
            if (map.get("__tid").equals("ec00050")){

                int os_type = Integer.parseInt(map.getOrDefault("os_type", "-1"));
                int int20 = Integer.parseInt(map.getOrDefault("int20", "-1"));
                long uin =  Long.parseLong(MapNull(map,"uin", "-1"));
                long playtime = Long.parseLong(MapNull(map,"playtime", "-1"));
                long int11 = Long.parseLong(MapNull(map,"int11", "-1"));
                long songid = Long.parseLong(MapNull(map,"songid", "-1"));
                String reporttime = map.getOrDefault("reporttime", "-1");

                ListeningDetails listeningDetails = new ListeningDetails();
                listeningDetails.setBusiness("ec00050");
                listeningDetails.setPlaylistId(int11);
                listeningDetails.setOrderSongId(songid);
                if (uin != -1){
                    listeningDetails.setUin(uin);
                }
                listeningDetails.setPlaytime(playtime);
                listeningDetails.setReportTime(reporttime);
                //判断是否为安卓，安卓的播放时长为
                //int20从罗盘中看
                if (os_type == 11){
                    listeningDetails.setAudioDuration((int20/1000));
                    out.collect(listeningDetails);
                }else if (os_type == 1){
                    listeningDetails.setAudioDuration((int20));
                    out.collect(listeningDetails);
                }
            }else if (map.get("__tid").equals("music_dc0970")){
                //前面已经过滤""数据，所以strBak1，必定有值
                String strBak1 = map.getOrDefault("strBak1", "");
                String[] strBakArray = strBak1.split(" ");
                long uin = Long.parseLong(map.getOrDefault("uin", "-1"));
                //业务和命名重名，避免冲突重新命名
                long tid_dc0970 = Long.parseLong(map.getOrDefault("tid", "-1"));
                ListeningDetails listeningDetails = new ListeningDetails();
                if (tid_dc0970 != -1) {
                    for (String opRecord : strBakArray) {
                        long order_id = Long.parseLong(!opRecord.split(":")[1].split("-")[0].trim().equals("") ? opRecord.split(":")[1].split("-")[0].trim() : "-1");
                        long order_song_id = Long.parseLong(!opRecord.split(":")[1].split("-")[1].trim().equals("") ? opRecord.split(":")[1].split("-")[1].trim() : "-1");
                        listeningDetails.setBusiness("music_dc0970");
                        listeningDetails.setPlaylistId(tid_dc0970);
                        listeningDetails.setAuthorUin(uin);
                        listeningDetails.setOrderId(order_id);
                        listeningDetails.setOrderSongId(order_song_id);
                        out.collect(listeningDetails);
                    }
                }
            }
        }).returns(ListeningDetails.class);


        DataStream<String> JsonStream =
                flatMapStream.keyBy("playlistId", "orderSongId").process(new TTLStateFilter()).returns(String.class);



        JsonStream.print();


        String kafkaTopic="listening_list";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "11.154.134.110:9092,11.154.134.73:9092,9.146.222.153:9092,11.154.131.122:9092,11.154.130.140:9092,9.146.222.156:9092");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


//        FlinkKafkaProducer011<String> producer011 = new FlinkKafkaProducer011<>(kafkaTopic, new SimpleStringSchema(), properties);

//        JsonStream.addSink(producer011);




        env.execute("AutoFilterETL");


    }

    public static class TTLStateFilter extends KeyedProcessFunction<Tuple, ListeningDetails, String> {

        ValueState<String> configState;
        ValueState<Long> timerTime;
        ValueState<Long> start;

        @Override
        public void open(Configuration parameters) throws Exception {
            configState = getRuntimeContext().getState(new ValueStateDescriptor<>("configState", String.class)); //配置json
            timerTime = getRuntimeContext().getState(new ValueStateDescriptor<>("timerTime", Long.class)); //
            start = getRuntimeContext().getState(new ValueStateDescriptor<>("start", Long.class)); //
        }

        @Override
        public void processElement(ListeningDetails listeningDetails, Context ctx, Collector<String> out) throws Exception {;
            SerializeConfig serializeConfig=new SerializeConfig();
            serializeConfig.propertyNamingStrategy= PropertyNamingStrategy.SnakeCase;
            if (listeningDetails.getBusiness().equals("music_dc0970"))
            {

                out.collect(listeningDetails.toString());
                //去除不必要的字段，
                listeningDetails.setPlaylistId(null);
                listeningDetails.setOrderSongId(null);
                listeningDetails.setBusiness(null);

                String configJson = JSON.toJSONString(listeningDetails,serializeConfig);
                //状态的注册和管理
                if (configState.value() == null){
                    configState.update(configJson);
                    start.update(ctx.timerService().currentProcessingTime());
//                    long timerTimestamps = ctx.timerService().currentProcessingTime()+ 30L *1000;
                    long timerTimestamps = ctx.timerService().currentProcessingTime()+ 30L *24*60*60*1000;
                    ctx.timerService().registerProcessingTimeTimer(timerTimestamps);
                    timerTime.update(timerTimestamps);
                }else {
                    configState.update(configJson);
                    //获取上次定时器的时间，并且删除
                    ctx.timerService().deleteProcessingTimeTimer(timerTime.value());
//                    long timerTimestamps = ctx.timerService().currentProcessingTime()+ 30L *1000;
                    long timerTimestamps = ctx.timerService().currentProcessingTime()+ 30L *24*60*60*1000;
                    //重新定义一个定时器
                    ctx.timerService().registerProcessingTimeTimer(timerTimestamps);
                    timerTime.update(timerTimestamps);
                }
            }
            if ( listeningDetails.getBusiness() != null && listeningDetails.getBusiness().equals("ec00050") && configState.value() != null) {
                String jsonSate = configState.value();
                ListeningDetails listeningDetailsState = JSON.parseObject(jsonSate, ListeningDetails.class);
                listeningDetails.setBusiness(null);
                listeningDetails.setAuthorUin(listeningDetailsState.getAuthorUin());
                listeningDetails.setOrderId(listeningDetailsState.getOrderId());
                out.collect(JSON.toJSONString(listeningDetails,serializeConfig));
            }
        }

        @Override
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {
//            out.collect("清除状态" );
            log.info("状态在整个周期到时间 "+ ctx.getCurrentKey().getField(0) + " " + ctx.getCurrentKey().getField(1)  + "   "+(ctx.timerService().currentProcessingTime()+ - start.value()) / 1000);
            configState.clear();
            start.clear();
            timerTime.clear();
        }
    }





}
