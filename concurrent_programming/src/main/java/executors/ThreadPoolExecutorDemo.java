package executors;

import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 100, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(5));
        Future<String> feature = threadPoolExecutor.submit(()->"return String");
        threadPoolExecutor.execute(() -> System.out.println("Runnable"));

        try {
            System.out.println(feature.get());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }


    }
}
