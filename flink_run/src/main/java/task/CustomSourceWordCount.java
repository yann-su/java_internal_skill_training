package task;

import Source.OrderSource;
import entity.Order;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import static org.apache.flink.table.api.Expressions.$;

public class CustomSourceWordCount {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        //使用自定义的source
        DataStreamSource<Order> orderDataStreamSource = env.addSource(new OrderSource());
        //转化成Flink sql
        tableEnv.createTemporaryView("orderB",orderDataStreamSource,$("id"),$("userId").as("user_id"),$("money"),$("createTime").as("create_time"));

        //flink sql转化成dataStream
        tableEnv.toAppendStream(tableEnv.sqlQuery("select * from orderB"), Row.class).print();
        tableEnv.executeSql("select * from orderB").print();

        env.execute();



    }

}
