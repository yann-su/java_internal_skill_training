package task.state;

import groovy.lang.Tuple;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * keyState 中的value state 实际可以使用maxBy
 */
public class KeyStateDemo {
    public static void main(String[] args) throws Exception {


        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        //模拟数据
        DataStreamSource<Tuple2<String, Long>> tuple2DataStreamSource = env.fromElements(
                Tuple2.of("北京", 1L),
                Tuple2.of("上海", 1L),
                Tuple2.of("天津", 1L),
                Tuple2.of("上海", 11L),
                Tuple2.of("北京", 6L)
                );

        //使用的MaxBy即可
        SingleOutputStreamOperator<Tuple2<String, Long>> tuple2SingleOutputStreamOperator = tuple2DataStreamSource.keyBy(t -> t.f0).maxBy(1);
        //学习时候可以keyState valueState实现maxBy


        SingleOutputStreamOperator<Tuple3<String, Long, Long>> map = tuple2DataStreamSource.
                keyBy(t -> t.f0).
                map(new RichMapFunction<Tuple2<String, Long>, Tuple3<String, Long, Long>>() {
                    //定义个状态来存放最大值
                    private ValueState<Long> maxValueState;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        //创建状态描述器
                        ValueStateDescriptor stateDescriptor = new ValueStateDescriptor("maxValueState",Long.class);
                        //根据状态描述器获取初始化状态
                        maxValueState = getRuntimeContext().getState(stateDescriptor);
                    }

            @Override
            public Tuple3<String, Long, Long> map(Tuple2<String, Long> value) throws Exception {
                Long historyValue = maxValueState.value();
                Long currentValue = value.f1;
                if (historyValue == null || currentValue > historyValue){
                    historyValue = currentValue;
                    maxValueState.update(historyValue);
                    return Tuple3.of(value.f0,currentValue,historyValue);
                }else {
                    maxValueState.update(historyValue);
                    return Tuple3.of(value.f0,currentValue,historyValue);
                }
            }
        });

        tuple2SingleOutputStreamOperator.print();
        map.print();





        env.execute("");


    }
}
