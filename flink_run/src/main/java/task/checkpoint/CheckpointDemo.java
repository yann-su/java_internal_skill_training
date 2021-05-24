package task.checkpoint;

import entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.contrib.streaming.state.EmbeddedRocksDBStateBackend;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

import static java.lang.Thread.sleep;

@Slf4j
public class CheckpointDemo {


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        log.info("CheckpointDemo开始");

        //开发中经常用
//        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3, Time.of(10, TimeUnit.SECONDS)));
//        env.setRestartStrategy(RestartStrategies.noRestart());
//        env.setRestartStrategy(RestartStrategies.failureRateRestart(
//                3,
//                Time.of(5, TimeUnit.SECONDS),
//                Time.of(3, TimeUnit.SECONDS)));

        //设置允许同时ck的数量
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        // start a checkpoint every 1000 ms
        env.enableCheckpointing(10000);

// advanced options:

// set mode to exactly-once (this is the default)
        //默认为仅此一次
//        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

// make sure 500 ms of progress happen between checkpoints
//        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);

// checkpoints have to complete within one minute, or are discarded
        env.getCheckpointConfig().setCheckpointTimeout(60000);

// allow only one checkpoint to be in progress at the same time
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);

// enable externalized checkpoints which are retained
// after job cancellation
        env.getCheckpointConfig().enableExternalizedCheckpoints(
                CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

// enables the experimental unaligned checkpoints
        env.getCheckpointConfig().enableUnalignedCheckpoints();

// sets the checkpoint storage where checkpoint snapshots will be written
//        env.getCheckpointConfig().setCheckpointStorage(new RocksDBStateBackend("file:///Users/backbook/data/ck"));

        env.setStateBackend(new EmbeddedRocksDBStateBackend()); //开启RocksDB的新的方法
        env.getCheckpointConfig().setCheckpointStorage("file:///Users/backbook/data/ck");

        //配置重启策略


        //产生数据
        DataStreamSource<Order> orderDataStreamSource = env.addSource(new RichParallelSourceFunction<Order>() {

            private boolean flag = true;

            @Override
            public void run(SourceContext<Order> sourceContext) throws Exception {
                Random random = new Random();
                while (flag) {
                    String orderId = UUID.randomUUID().toString();
                    int userId = random.nextInt(2);
                    int money = random.nextInt(100);
                    long eventTime = System.currentTimeMillis() - random.nextInt(20) * 1000;
                    sourceContext.collect(new Order(orderId, userId, money, eventTime));
                    sleep(1000);
                }
            }
            @Override
            public void cancel() {
                flag = false;
            }
        });

        SingleOutputStreamOperator<Order> orderWithWater = orderDataStreamSource.
                assignTimestampsAndWatermarks(
//                        WatermarkStrategy.<Order>forMonotonousTimestamps() //指定最大的允许乱序时间
//                        WatermarkStrategy.<Order>forBoundedOutOfOrderness(Duration.ofSeconds(0)) 这个例子和上面的是一致的
                        WatermarkStrategy.<Order>forBoundedOutOfOrderness(Duration.ofSeconds(3)) //指定最大的允许乱序时间
                                .withTimestampAssigner((order, timestamp) -> order.getCreateTime())//指定事件时间
                );

        orderWithWater.print();
        env.execute(CheckpointDemo.class.getName());






    }


}
