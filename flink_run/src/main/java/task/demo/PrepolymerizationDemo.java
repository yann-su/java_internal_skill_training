package task.demo;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.ContinuousProcessingTimeTrigger;

import java.util.Random;

public class PrepolymerizationDemo {

    public static void main(String[] args) throws Exception {

        //创建env
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
        //读取数据
        DataStreamSource<Tuple2<String, Double>> tuple2DataStreamSource = env.addSource(new MySource());
        //
        tuple2DataStreamSource
                .keyBy(k->k.f0)
//                .window(TumblingEventTimeWindows.of(Time.days(1)));
//                .window(SlidingProcessingTimeWindows.of(Time.days(1),Time.seconds(1)));
                //从当天的时间
                .window(TumblingProcessingTimeWindows.of(Time.days(1),Time.hours(-8)))
                .trigger(ContinuousProcessingTimeTrigger.of(Time.seconds(1)));
//                .sum() //简单聚合
//                .aggregate() //复杂的自定义聚合


        env.execute("PrepolymerizationDemo");




    }

    public static class MySource implements SourceFunction<Tuple2<String,Double>>{

        private boolean flag = true;
        private Random random = new Random();
        private String[] categories = {"女装","男装","图书","家电","洗护","美妆","运动","游戏","户外","家具","乐器","办公"};

        @Override
        public void run(SourceContext<Tuple2<String, Double>> ctx) throws Exception {
            while (flag){
                //随机生成分类和金额
                int index = random.nextInt(categories.length); //[0~length) => [0~length-1]
                String category = categories[index]; //获取随机分类
                double price =  random.nextDouble() * 100; //nextDouble()生成(0-1)的随机小数，*100后表示为[0~100)的随机小数
                ctx.collect(Tuple2.of(category,price));
                //休眠二十毫秒
                Thread.sleep(20);
            }
        }

        @Override
        public void cancel() {
            flag = false;
        }
    }







}
