package executors;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class NewFixedThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            //可以省略使用默认的
            private AtomicInteger atomicInteger = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                 return new Thread(r,"mypool"+atomicInteger.getAndIncrement());
            }
        });
        executorService.execute(()->{
            log.info("1");
        });
        executorService.execute(()->{log.info("2"); });
        executorService.execute(()->{log.info("3"); });
        executorService.execute(()->{log.info("1"); });


        //提交多个任务
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(
                () -> {
                    log.info("begin");
                    Thread.sleep(2000);
                    return "a";
                },
                () -> {
                    log.info("begin");
                    Thread.sleep(3000);
                    return "b";
                },
                () -> {
                    log.info("begin");
                    Thread.sleep(5000);
                    return "c";
                }
        ));
        futures.forEach(f->
        {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException interruptedException) {
                interruptedException.printStackTrace();
            }
        });



    }



}
