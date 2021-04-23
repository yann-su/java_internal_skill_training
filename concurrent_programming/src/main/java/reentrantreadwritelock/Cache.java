package reentrantreadwritelock;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class Cache {

    static HashMap<String, Object> map = new HashMap();


    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = reentrantReadWriteLock.readLock();
    static Lock writeLock = reentrantReadWriteLock.writeLock();


    //get value according to key
    public Object get(String key) {
        readLock.lock();
        Object o;
        try {
            o = map.get(key);
            try {
                log.info("sleep");
                Thread.sleep(10000);
                log.info("wake up");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        } finally {
            readLock.unlock();
        }
        return o;
    }


    public void put(String key,Object o){
        writeLock.lock();
        try {
            map.put(key,o);
            try {
                log.info("sleep");
                Thread.sleep(10000);
                log.info("wake up");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }finally {
            writeLock.unlock();
        }
    }

    public void clear(){
        writeLock.lock();
        try {
            map.clear();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        Cache cache = new Cache();


        Thread thread1 = new Thread(()->{
//            cache.put("k1","11");
            cache.get("k1");
        },"t1") {};


        Thread thread2 = new Thread(()->{
            cache.get("k1");
        },"t2") {};


        thread1.start();
        thread2.start();


    }



}
