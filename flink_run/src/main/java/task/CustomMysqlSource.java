package task;

import Source.MySQLSource;
import entity.Student;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import static org.apache.flink.table.api.Expressions.$;

public class CustomMysqlSource {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        DataStreamSource<Student> studentDataStreamSource = env.addSource(new MySQLSource()).setParallelism(1);

        //转化成Flink sql
        tableEnv.createTemporaryView("orderB",studentDataStreamSource,$("Id"),$("age"),$("name"));

        tableEnv.executeSql("select * from orderB").print();

        env.execute("");


    }
}
