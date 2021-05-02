package pool.custom;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Test {

    public static void main(String[] args) {

        ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MICROSECONDS, 10);
        for (int i = 0; i < 5; i++) {
            int j = i;
            threadPool.execute(()->{
                log.debug("{}",j);
            });
        }



    }

}
