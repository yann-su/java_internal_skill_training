package pool.custom;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Test {

    public static void main(String[] args) {

        ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MICROSECONDS, 10);
        for (int i = 0; i < 15; i++) {
            int j = i;
            threadPool.execute(()->{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                log.debug("{}",j);
            });
        }



    }

}
