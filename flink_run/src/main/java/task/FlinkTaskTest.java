package task;

import entity.SensorReading;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlinkTaskTest {

    public static void main(String[] args) throws Exception {
        // 使用Blink解析器
        StreamExecutionEnvironment streamExecEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment streamTableEnv = StreamTableEnvironment.create(streamExecEnv, settings);

        // 设置重启策略：Job连续重启失败5次，则任务失败，需手动恢复
        streamExecEnv.setRestartStrategy(RestartStrategies.fixedDelayRestart(5, Time.of(1, TimeUnit.MINUTES)));


        DataStreamSource<SensorReading> sensorReadingDataStreamSource = streamExecEnv.fromCollection(Arrays.asList(
                new SensorReading("sen01", 1314141L, 131.9),
                new SensorReading("sen02", 1314161L, 131.9),
                new SensorReading("sen03", 1314181L, 131.9),
                new SensorReading("sen04", 1314191L, 131.9),
                new SensorReading("sen05", 13141918L, 131.9)
        ));


        DataStreamSource<Integer> integerDataStreamSource = streamExecEnv.fromElements(1, 2, 4, 5, 6, 67, 189);

//        integerDataStreamSource.print("data");

        sensorReadingDataStreamSource.print("data").setParallelism(1);

        streamExecEnv.execute();


    }

}
