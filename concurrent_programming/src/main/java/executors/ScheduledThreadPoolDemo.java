package executors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
//并行执行，不会影响各自任务
public class ScheduledThreadPoolDemo {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        //两个是同时运行的
        scheduledThreadPool.schedule(()->{
            log.info("task1 start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            log.info("task1 end");
        },1, TimeUnit.SECONDS);

        scheduledThreadPool.schedule(()->{
            log.info("task2 start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            log.info("task1 end");
        },1, TimeUnit.SECONDS);






    }

}
