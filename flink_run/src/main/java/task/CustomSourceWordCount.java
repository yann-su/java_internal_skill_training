package task;

import Source.OrderSource;
import entity.Order;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class CustomSourceWordCount {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

        DataStreamSource<Order> orderDataStreamSource = env.addSource(new OrderSource());


        orderDataStreamSource.print();

        env.execute();




    }

}
