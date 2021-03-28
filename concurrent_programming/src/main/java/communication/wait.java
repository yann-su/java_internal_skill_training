package communication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class wait {

    //加final是为了初始化的时候只能一次，不能被修改引用
    final static Object lock = new Object();

    /**
     * 线程1和线程2 进入monitor的wait队列中，然后进入等待队列，当主线程使用lock.notifyAll()
     * notify 和 wait 只在对象上，并且要持有相同的锁
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            synchronized (lock){
                log.info("t1 running");
                try {
                    log.info("be about to waiting");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("t1 wake up");
            }
        },"t1");
        Thread thread2 = new Thread(()->{
            synchronized (lock){
                log.info("t2 running");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("t2 wake up");
            }
        },"t2");
        thread1.start();
        thread2.start();
        Thread.sleep(10000);
        log.info("main run");
        synchronized (lock){
            lock.notifyAll();
        }


    }




}
