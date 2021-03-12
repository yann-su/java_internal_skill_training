package task;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import java.util.Arrays;

/**
 * 由官方版本改成Stream的流，
 */
public class WordCount {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);


        DataStream<String> dataStream = env.fromElements("hello word he", "li hua from Canada", "where true print false","he he he");
        /***
         * 默认情况下，flink可以从方法签名中获取范型信息，但是 void flatMap(IN value, Collector<OUT> out)
         * 由javac编译后，会变成 void flatMap(IN value, Collector out),导致范型丢失，
         * 导致flink无法自动推断出类型，flink会认为是Object类型，从而导致无效的序列化类型。
         */
        DataStream<Tuple2<String,Integer>> flatMapStream = dataStream.flatMap((String a, Collector<Tuple2<String,Integer>> out) ->
                Arrays.stream(a.split(" ")).forEach(x -> out.collect(Tuple2.of(x,1)))
        ).returns(Types.TUPLE(Types.STRING,Types.INT));

        KeyedStream<Tuple2<String, Integer>, String> tuple2StringKeyedStream = flatMapStream.keyBy(x -> x.f0);

        DataStream<Tuple2<String, Integer>> sumStream = tuple2StringKeyedStream.sum(1);

        sumStream.print();

        env.execute("张三");


    }

}
