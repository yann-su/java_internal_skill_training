package task;

import entity.LoginEvent;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CEPTask {


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        executionEnvironment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        executionEnvironment.setParallelism(1);

        DataStreamSource<LoginEvent> loginEventDataStreamSource = executionEnvironment.fromElements(
                new LoginEvent("1", "TMD", 1618498576),
                new LoginEvent("1", "TMD", 1618498577),
                new LoginEvent("1", "TMD", 1618498579),
                new LoginEvent("1", "TMD", 1618498582),
                new LoginEvent("2", "TMD", 1618498583),
                new LoginEvent("1", "TMD", 1618498585));


        SingleOutputStreamOperator<LoginEvent> loginEventSingleOutputStreamOperator = loginEventDataStreamSource.
                assignTimestampsAndWatermarks(
//                        WatermarkStrategy.<Order>forMonotonousTimestamps() //指定最大的允许乱序时间
//                        WatermarkStrategy.<Order>forBoundedOutOfOrderness(Duration.ofSeconds(0)) 这个例子和上面的是一致的
                        WatermarkStrategy.<LoginEvent>forBoundedOutOfOrderness(Duration.ofSeconds(10)) //指定最大的允许乱序时间
                                .withTimestampAssigner((order, timestamp) -> order.getTimestamp()  * 1000)//指定事件时间
                );


        loginEventDataStreamSource.print();


        Pattern<LoginEvent, LoginEvent> within =
                Pattern.<LoginEvent>begin("begin").
                where(new SimpleCondition<LoginEvent>() {
                    @Override
                    public boolean filter(LoginEvent loginEvent) throws Exception {
                        return !loginEvent.getMessage().equals("TMD");
                    }
                }).optional()
                .next("second").where(new SimpleCondition<LoginEvent>() {
                            @Override
                            public boolean filter(LoginEvent loginEvent) throws Exception {
                                return false;
                            }
                 }).next("")
                .times(3)
                .within(Time.seconds(10));


        PatternStream<LoginEvent> pattern = CEP.pattern(loginEventSingleOutputStreamOperator.keyBy(LoginEvent::getUserId), within);


        SingleOutputStreamOperator<String> select = pattern.select((PatternSelectFunction<LoginEvent, String>) map -> {
            LoginEvent begin = map.getOrDefault("begin", null).iterator().next();
            return begin.getUserId();
        });


        select.print();

        executionEnvironment.execute("BarrageBehavior01");

    }


}
