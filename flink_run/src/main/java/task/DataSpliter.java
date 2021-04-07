package task;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;


@Slf4j
public class DataSpliter {


    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        DataStreamSource<Integer> dataStreamSource = env.fromElements(1, 2, 3, 4, 5, 6, 7, 8);


        //分流的方式
        OutputTag<Integer> oddTag = new OutputTag<>("奇数", TypeInformation.of(Integer.class));
        OutputTag<Integer> evenTag = new OutputTag<>("偶数", TypeInformation.of(Integer.class));

        SingleOutputStreamOperator<Integer> process = dataStreamSource.process(new ProcessFunction<Integer, Integer>() {
            @Override
            public void processElement(Integer value, Context context, Collector<Integer> collector) throws Exception {
                if (value % 2 == 0){
                    context.output(evenTag,value);
//                    log.info(value+"");
                }else {
                    context.output(oddTag,value);
                }
            }
        });

        DataStream<Integer> oddOutputStream = process.getSideOutput(oddTag);
        DataStream<Integer> evenOutputStream = process.getSideOutput(evenTag);

        oddOutputStream.print("odd").setParallelism(1);
        evenOutputStream.print("even").setParallelism(1);


        env.execute("");



    }


}
