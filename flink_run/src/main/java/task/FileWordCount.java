package task;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

public class FileWordCount {


    public static void main(String[] args) throws Exception {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);


        DataStreamSource<String> stringDataStreamSource = env.readTextFile("/Users/backbook/IdeaProjects/java_internal_skill_training/flink_run/src/main/resources/WordCount.txt");

        DataStream<Tuple2<String,Integer>> returns = stringDataStreamSource.flatMap((String s, Collector<Tuple2<String, Integer>> o) ->
                Arrays.stream(s.split(" ")).forEach(e -> o.collect(Tuple2.of(e, 1)))).returns(Types.TUPLE(Types.STRING,Types.INT));


        returns.print();

        env.execute();


    }

}
