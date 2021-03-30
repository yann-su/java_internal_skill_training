package Source;

import entity.Order;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.util.Random;
import java.util.UUID;

public class OrderSource extends RichParallelSourceFunction<Order> {
    private boolean flag = true;
    @Override
    public void run(SourceContext<Order> sourceContext) throws Exception {


        Random random = new Random();
        while (flag){
            String oid = UUID.randomUUID().toString();
            int userid = random.nextInt(3);
            int money = random.nextInt(102);
            long createTime = System.currentTimeMillis();
            sourceContext.collect(new Order(oid,userid,money,createTime));
            Thread.sleep(1000);
        }
    }

    @Override
    public void cancel() {
        flag = false;
    }
}
