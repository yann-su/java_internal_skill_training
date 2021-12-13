package task;

import entity.LoginEvent;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.time.Duration;

public class CEPTask1 {


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();



        DataStream<String> dataStream = executionEnvironment.fromElements(("a"), ("c"), ("b1"), ("b2"));


        Pattern strictPattern = Pattern.begin("start").where(new IterativeCondition<Object>() {
            @Override
            public boolean filter(Object s, Context<Object> context) {
                return s.toString().equalsIgnoreCase("a");
            }
        }).next("middle").where(new IterativeCondition<Object>() {
            @Override
            public boolean filter(Object o, Context<Object> context) {
                return o.toString().contains("b");
            }
        });


        CEP.pattern(dataStream, strictPattern).select(map -> {
            System.out.println("strictPattern:" + map.get("start").toString());
            System.out.println("strictPattern:" + map.get("middle").toString());
            return map;
        }).print();


        /*---------松散连续----------------------*/
        Pattern relaxedPattern = Pattern.begin("start").where(new IterativeCondition<Object>() {
            @Override
            public boolean filter(Object s, Context<Object> context) {
                return s.toString().equalsIgnoreCase("a");
            }
        }).followedBy("middle").where(new IterativeCondition<Object>() {
            @Override
            public boolean filter(Object o, Context<Object> context) {
                return o.toString().contains("b");
            }
        });

        CEP.pattern(dataStream, relaxedPattern).select(map -> {
            System.out.println("relaxedPattern:" + map.get("start").toString());
            System.out.println("relaxedPattern:" + map.get("middle").toString());
            return map;
        }).print();


        /*---------不确定的松散连续----------------------*/
        Pattern nonDeterminPattern = Pattern.begin("start").where(new IterativeCondition<Object>() {
            @Override
            public boolean filter(Object s, Context<Object> context) {
                return s.toString().equalsIgnoreCase("a");
            }
        }).followedByAny("middle").where(new IterativeCondition<Object>() {
            @Override
            public boolean filter(Object o, Context<Object> context) {
                return o.toString().contains("b");
            }
        });

        CEP.pattern(dataStream, nonDeterminPattern).select(map -> {
            System.out.println("nonDeterminPattern:" + map.get("start").toString());
            System.out.println("nonDeterminPattern:" + map.get("middle").toString());
            return map;
        }).print();


        executionEnvironment.execute("BarrageBehavior01");

    }


}
