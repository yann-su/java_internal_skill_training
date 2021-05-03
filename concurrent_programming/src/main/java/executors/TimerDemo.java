package executors;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
//此方法不需要掌握,了解即可
public class TimerDemo {

    public static void main(String[] args) {

        Timer timer = new Timer();

        TimerTask timerTask1 = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                log.info("task1");
                //任务一直接让线程直接终止
                int i = 1/0;
                Thread.sleep(1000);
                log.info("task1 end");
            }
        };

        TimerTask timerTask2 = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                log.info("task2");
                Thread.sleep(3000);
                log.info("task2 end");
            }
        };

        log.info("start...");
        timer.schedule(timerTask1,1000);
        timer.schedule(timerTask2,1000);




    }

}
