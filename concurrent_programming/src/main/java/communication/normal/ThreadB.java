package communication.normal;

public class ThreadB implements Runnable {

    private Object lock;

    public ThreadB(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock){
                System.out.println("start wait time = "+System.currentTimeMillis());
                lock.wait();
                System.out.println("end wait time ="+System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
