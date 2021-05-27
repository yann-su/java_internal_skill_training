package task;

import Source.MySQLSource;
import entity.Student;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import java.time.Duration;

import static org.apache.flink.table.api.Expressions.$;

public class CustomMysqlSource {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        DataStreamSource<Student> studentDataStreamSource = env.addSource(new MySQLSource()).setParallelism(1);


        SingleOutputStreamOperator<Student> studentSingleOutputStreamOperator = studentDataStreamSource.
                assignTimestampsAndWatermarks(
//                        WatermarkStrategy.<Order>forMonotonousTimestamps() //指定最大的允许乱序时间
//                        WatermarkStrategy.<Order>forBoundedOutOfOrderness(Duration.ofSeconds(0)) 这个例子和上面的是一致的
                        WatermarkStrategy.<Student>forBoundedOutOfOrderness(Duration.ofSeconds(1)) //指定最大的允许乱序时间
                                .withTimestampAssigner((order, timestamp) -> order.getCreateTimestamp())//指定事件时间
                );
        //转化成Flink sql
        tableEnv.createTemporaryView("orderB",studentSingleOutputStreamOperator,$("Id"),$("age"),$("name"),$("createTimestamp").rowtime());

        tableEnv.executeSql("select * from orderB").print();

        env.execute("");


    }
}
