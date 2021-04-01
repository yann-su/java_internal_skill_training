package reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class reenterable {

    static ReentrantLock reentrantLock = new ReentrantLock();


    public static void main(String[] args) {
        try {
            reentrantLock.lock();
            log.info("mian获取锁");
            m1();

        }finally {
            log.info("释放main");
            reentrantLock.unlock();
        }


    }

    public static void m1(){
        try {
            reentrantLock.lock();
            log.info("进入m1");
            m2();
        }finally {
            log.info("释放m1");
            reentrantLock.unlock();
        }
    }

    public static void m2(){
        try {
            reentrantLock.lock();
            log.info("进入m2");
        }finally {
            //释放锁
            log.info("释放m2");
            reentrantLock.unlock();
        }
    }



}
