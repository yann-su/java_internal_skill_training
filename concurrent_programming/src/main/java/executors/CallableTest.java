package executors;

import java.util.concurrent.*;

public class CallableTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        try {

            String a = "return String";
            return a;
        }catch (Exception e){
            e.printStackTrace();
            return "exception";
        }
    }

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        Future<String> feature = threadPoolExecutor.submit(new CallableTest());

        try {
            System.out.println(feature.get());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }


    }
}
