package base;

import org.slf4j.LoggerFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class MyCallable implements Callable {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MyCallable.class);
    @Override
    public Integer call() throws Exception {
        System.out.println("call is running");
//        log.debug("call is running");
        long startTime = System.currentTimeMillis();
        Thread.sleep(10000);
        System.out.println((System.currentTimeMillis() - startTime)/1000);
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
