package task.sql;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.contrib.streaming.state.EmbeddedRocksDBStateBackend;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;


public class SqlDemo {
    public static void main(String[] args) throws Exception {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

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
                CheckpointConfig.ExternalizedCheckpointCleanup.DELETE_ON_CANCELLATION);

// enables the experimental unaligned checkpoints
        env.getCheckpointConfig().enableUnalignedCheckpoints();

// sets the checkpoint storage where checkpoint snapshots will be written
//        env.getCheckpointConfig().setCheckpointStorage(new RocksDBStateBackend("file:///Users/backbook/data/ck"));

        env.setStateBackend(new EmbeddedRocksDBStateBackend()); //开启RocksDB的新的方法
        env.getCheckpointConfig().setCheckpointStorage("file:///Users/backbook/data/ck");

        ParameterTool sourceProperties = ParameterTool.fromPropertiesFile(SqlDemo.class.getClassLoader().getResourceAsStream("kafka.properties"));
        String dwdAfiLoanRepay = sourceProperties.get("a");
        tableEnv.executeSql(dwdAfiLoanRepay);
        tableEnv.executeSql("select * from a").print();


        env.execute(SqlDemo.class.getName());
    }
}
