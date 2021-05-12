package task.windows;

import entity.Order;
import entity.Student;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class SocketCountWindow {

    public static void main(String[] args) throws Exception {


         final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
         env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
         EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
         StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

         DataStreamSource<Order> orderDataStreamSource = env.addSource(new RichParallelSourceFunction<Order>() {

             private boolean flag = true;

             @Override
             public void run(SourceContext<Order> sourceContext) throws Exception {
                 Random random = new Random();
                 while (flag) {
                     String orderId = UUID.randomUUID().toString();
                     int userId = random.nextInt(2);
                     int money = random.nextInt(100);
                     long eventTime = System.currentTimeMillis() - random.nextInt(5) * 1000;
                     Order order = new Order(orderId, userId, money, eventTime);
                     sourceContext.collect(order);
                     System.out.println(order);
                     Thread.sleep(2000);
                 }
             }

             @Override
             public void cancel() {
                 flag = false;
             }
         });


         SingleOutputStreamOperator<Order> money = orderDataStreamSource.keyBy(Order::getUserId).countWindow(10).sum("money");
         SingleOutputStreamOperator<Order> money2 = orderDataStreamSource.keyBy(Order::getUserId).countWindow(10,5).sum("money");

//         money.print();
         money2.print();

        env.execute("SocketCountWindow");


    }
}
