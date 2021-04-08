package task;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class RebalanceTest {


    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);

        SingleOutputStreamOperator<Long> filter = env.fromSequence(0, 300).filter(x -> x > 10);

//        filter.print();


        SingleOutputStreamOperator<Tuple2<Integer, Integer>> sum = filter.map(new RichMapFunction<Long, Tuple2<Integer, Integer>>() {
            @Override
            public Tuple2<Integer, Integer> map(Long value) throws Exception {
                int indexOfThisSubtask = getRuntimeContext().getIndexOfThisSubtask();
                return new Tuple2<>(indexOfThisSubtask, 1);
            }
        }).keyBy(f -> f.f0).sum(1);

        sum.print();

        SingleOutputStreamOperator<Tuple2<Integer, Integer>> sum1 = filter.rebalance().map(new RichMapFunction<Long, Tuple2<Integer, Integer>>() {
            @Override
            public Tuple2<Integer, Integer> map(Long value) throws Exception {
                int indexOfThisSubtask = getRuntimeContext().getIndexOfThisSubtask();
                return new Tuple2<>(indexOfThisSubtask, 1);
            }
        }).keyBy(f -> f.f0).sum(1);



        //观察加rebalance的方法的数据变化，可以修正数据倾斜问题
        sum1.print();

        env.execute("");

    }


}
