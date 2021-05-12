package task.watermark;

import entity.Order;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.util.Collector;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class WaterMarkDemo1 {


    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        DataStreamSource<Order> orderDataStreamSource = env.addSource(new RichParallelSourceFunction<Order>() {

            private boolean flag = true;

            @Override
            public void run(SourceContext<Order> sourceContext) throws Exception {
                Random random = new Random();
                while (flag) {
                    String orderId = UUID.randomUUID().toString();
                    int userId = random.nextInt(2);
                    int money = random.nextInt(100);
                    long eventTime = System.currentTimeMillis() - random.nextInt(5) * 1000;
                    sourceContext.collect(new Order(orderId, userId, money, eventTime));
                    Thread.sleep(1000);
                }
            }

            @Override
            public void cancel() {
                flag = false;
            }
        });


        //基于事件时间进行计算
//        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime); //在新版本是按照EventTime
        //设置watermark
        //在flink1.12以后api的生成发生了变化，要看老版本的api也是一样的生成逻辑，只是api发生了变化
        SingleOutputStreamOperator<Order> orderWithWater = orderDataStreamSource.
                assignTimestampsAndWatermarks(
//                        WatermarkStrategy.<Order>forMonotonousTimestamps() //指定最大的允许乱序时间
//                        WatermarkStrategy.<Order>forBoundedOutOfOrderness(Duration.ofSeconds(0)) 这个例子和上面的是一致的
                        WatermarkStrategy.<Order>forBoundedOutOfOrderness(Duration.ofSeconds(3)) //指定最大的允许乱序时间
                                .withTimestampAssigner((order, timestamp) -> order.getCreateTime())//指定事件时间
                );

//        SingleOutputStreamOperator<Order> money = orderWithWater.keyBy(Order::getUserId).
//                window(TumblingEventTimeWindows.of(Time.seconds(5))).sum("money");


        SingleOutputStreamOperator<Object> result = orderWithWater.keyBy(Order::getUserId).
                window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .apply(new WindowFunction<Order, Object, Integer, TimeWindow>() {
            @Override
            //使用原始api进行计算
            public void apply(Integer integer, TimeWindow window, Iterable<Order> input, Collector<Object> out) throws Exception {
                ArrayList<String> strings = new ArrayList<>();
                HashMap<String, Integer> keysSum = new HashMap<>();
                for (Order order : input) {
                    keysSum.put(order.getId(),order.getMoney());
                    Long createTime = order.getCreateTime();
                    strings.add(createTime+"");
                }
                out.collect(keysSum);
            }
        });


        result.print();

        env.execute("");


    }

}
