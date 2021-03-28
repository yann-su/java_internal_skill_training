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
                //wait是Object sleep() 是线程类独有
                //wait是进入了等待队列，而sleep()是占有锁不释放
                //共同点：都是TIMEW
                lock.wait();
                System.out.println("end wait time ="+System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
