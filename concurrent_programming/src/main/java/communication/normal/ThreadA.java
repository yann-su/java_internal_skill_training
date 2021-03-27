package communication.normal;

public class ThreadA implements Runnable{

    private Object lock;

    public ThreadA(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("start notify time "+System.currentTimeMillis());
            //notify()方法不会有异常发生
            lock.notify();
            System.out.println("end notify time "+System.currentTimeMillis());
        }
    }

}
