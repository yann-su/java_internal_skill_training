package base;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


@Slf4j(topic = "c.MyCallable")
public class MyCallable implements Callable {

    @Override
    public Integer call() throws Exception {
        log.info("call is running");
        long startTime = System.currentTimeMillis();
        Thread.sleep(10000);
        log.info("{}",(System.currentTimeMillis() - startTime));
        return 1000;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask(new MyCallable());

        Thread call = new Thread(futureTask, "call");
        call.start();
        System.out.println("main running");
        Integer integer = futureTask.get();
        System.out.println(integer);


    }

}
