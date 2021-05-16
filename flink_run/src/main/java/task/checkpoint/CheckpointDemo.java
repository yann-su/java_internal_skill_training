package task.checkpoint;

import entity.Order;
import lombok.val;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class CheckpointDemo {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        Configuration conf = new Configuration();
        conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER, true);
        conf.setString(ConfigConstants.METRICS_REPORTER_PREFIX+".influxdb.clas","org.apache.flink.metrics.influxdb.InfluxdbReporter");
        conf.setString(ConfigConstants.METRICS_REPORTER_PREFIX+".influxdb.host","localhost");
        conf.setString(ConfigConstants.METRICS_REPORTER_PREFIX+".influxdb.port","8086");
        conf.setString(ConfigConstants.METRICS_REPORTER_PREFIX+".influxdb.db","flink");
        conf.setString(ConfigConstants.METRICS_REPORTER_PREFIX+".influxdb.username","flink-metrics");
        conf.setString(ConfigConstants.METRICS_REPORTER_PREFIX+".influxdb.password","qwerty");
        conf.setString(ConfigConstants.METRICS_REPORTER_PREFIX+".influxdb.retentionPolicy","one_hour");
//        conf.setString("execution.savepoint.path","");
        //自定义端口
        conf.setInteger(RestOptions.PORT, 8050);
        //本地env
        env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        //开发中经常用
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3, Time.of(10, TimeUnit.SECONDS)));
//        env.setRestartStrategy(RestartStrategies.noRestart());
//        env.setRestartStrategy(RestartStrategies.failureRateRestart(
//                3,
//                Time.of(5, TimeUnit.SECONDS),
//                Time.of(3, TimeUnit.SECONDS)));


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
                CheckpointConfig.ExternalizedCheckpointCleanup.DELETE_ON_CANCELLATION);

// enables the experimental unaligned checkpoints
        env.getCheckpointConfig().enableUnalignedCheckpoints();

// sets the checkpoint storage where checkpoint snapshots will be written
        env.getCheckpointConfig().setCheckpointStorage(new RocksDBStateBackend("file:///H:\\baidu\\视频-flink1.12入门到精通\\ck"));
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
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

        orderDataStreamSource.print();
        env.execute();






    }


}
