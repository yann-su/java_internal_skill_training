package task.feature;

import entity.FactOrderItem;
import entity.Goods;
import entity.OrderItem;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class WindowJoinDemo {


    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);


        DataStreamSource<Goods> goodsStream = env.addSource(new GoodsSource());
        DataStreamSource<OrderItem> orderStream = env.addSource(new OrderItemSource());


        //可以使用ProcessingTime进行示例
        DataStream<FactOrderItem> apply = goodsStream.join(orderStream)
                .where(Goods::getGoodsId)
                .equalTo(OrderItem::getGoodsId)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .apply((JoinFunction<Goods, OrderItem, FactOrderItem>) (first, second) -> {
                    FactOrderItem factOrderItem = new FactOrderItem();
                    factOrderItem.setGoodsId(first.getGoodsId());
                    factOrderItem.setGoodsName(first.getGoodsName());
                    factOrderItem.setCount(second.getCount());
                    factOrderItem.setTotalMoney(new BigDecimal(second.getCount()).multiply(first.getGoodsPrice()));
                    return factOrderItem;
                });


        apply.print();

        env.execute("");


    }





    public static class GoodsSource extends RichSourceFunction<Goods>{

        private boolean flags = false;

        @Override
        public void run(SourceContext<Goods> ctx) throws Exception {
            while (!flags){
                Goods.GOODS_LIST.forEach(ctx::collect);
                TimeUnit.SECONDS.sleep(1);
            }
        }

        @Override
        public void cancel() {
            flags = true;
        }
    }

    public static class OrderItemSource extends RichSourceFunction<OrderItem>{

        private boolean flags = false;
        private Random random;

        @Override
        public void open(Configuration parameters) throws Exception {
            random = new Random();
            super.open(parameters);
        }

        @Override
        public void run(SourceContext<OrderItem> ctx) throws Exception {
            while (!flags){
                Goods goods = Goods.randomGoods();
                OrderItem orderItem = new OrderItem();
                orderItem.setGoodsId(goods.getGoodsId());
                orderItem.setCount(random.nextInt(10)+1);
                orderItem.setItemId(UUID.randomUUID().toString());
                ctx.collect(orderItem);
                orderItem.setItemId("111");
                ctx.collect(orderItem);
                TimeUnit.SECONDS.sleep(1);
            }
        }

        @Override
        public void cancel() {
            flags = true;
        }
    }




}


