package task;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

public class FileWordCount {


    public static void main(String[] args) throws Exception {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);


        DataStreamSource<String> stringDataStreamSource = env.readTextFile("/Users/backbook/IdeaProjects/java_internal_skill_training/flink_run/src/main/resources/WordCount.txt");

        DataStream<Tuple2<String,Integer>> returns = stringDataStreamSource
                //和java的Stream filter是一致的操作，为了演示
                .filter(x->!x.equals("nihao"))
                //flatm
                .flatMap((String s, Collector<Tuple2<String, Integer>> o) ->
                Arrays.stream(s.split(" ")).forEach(e -> o.collect(Tuple2.of(e, 1)))).returns(Types.TUPLE(Types.STRING,Types.INT));


        KeyedStream<Tuple2<String, Integer>, String> tuple2StringKeyedStream = returns.keyBy(t -> t.f0);

        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = tuple2StringKeyedStream.sum(1);

        //以reduce来看打扁平后的数据怎么进行sum计算
        SingleOutputStreamOperator<Tuple2<String, Integer>> reduce = tuple2StringKeyedStream.reduce(
                (Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) -> Tuple2.of(value1.f0, value1.f1 + value2.f1));

        sum.print();

        env.execute();


    }

}
