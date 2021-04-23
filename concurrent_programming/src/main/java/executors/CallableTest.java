package executors;

import java.util.concurrent.Callable;

public class CallableTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        try {


        }catch (Exception e){
            e.printStackTrace();
            return "exception";
        }

        return null;
    }
}
