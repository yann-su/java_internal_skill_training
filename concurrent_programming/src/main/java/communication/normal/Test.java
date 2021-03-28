package communication.normal;

public class Test {
    public static void main(String[] args) {
        final Object lock = new Object();
        try {

            ThreadB threadB = new ThreadB(lock);
            Thread r2 = new Thread(threadB);
            r2.start();
            //保证线程持有锁进入monitor中wait队列中,进行等待
            Thread.sleep(1000);
            ThreadA threadA = new ThreadA(lock);
            Thread r1 = new Thread(threadA);
            r1.start();

        }catch (InterruptedException e){

        }


    }
}
