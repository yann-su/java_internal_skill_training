package lock;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyLockTest {


    public static void main(String[] args) {

        MyLock myLock = new MyLock();

        new Thread(()->{
            myLock.lock();
            //t1 myLock 不存在锁重入的功能
//            myLock.lock();
            try {
                log.info("t1 my log show");
                Thread.sleep(2000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } finally {
                log.info("unlocking");
                myLock.unlock();
            }
        },"t1").start();

        new Thread(()->{
            myLock.lock();
            try {
                log.info("t2 my log show");
                Thread.sleep(4000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } finally {
                log.info("unlocking");
                myLock.unlock();
            }
        },"t2").start();







    }

}
