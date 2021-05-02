package pool.custom;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class BlockingQueue<T> {

    //1 任务队列
    private Deque<T>  queue = new ArrayDeque<>();
    //2 锁
    private ReentrantLock lock = new ReentrantLock();
    //3 生产者条件变量
    private Condition fullWaitSet = lock.newCondition();
    //4 消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();
    //5 容量
    private int capcity;

    public BlockingQueue(){

    }

    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    //带超时的阻塞获取
    public T poll(long timeout, TimeUnit unit){
        lock.lock();
        try {
            //将timeout统一转换成 纳秒
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()){
                try {
                    //返回的剩余的时间
                    if (nanos <= 0){
                        nanos = emptyWaitSet.awaitNanos(nanos);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //阻塞获取 消费者生产者模式
    public T take(){
        lock.lock();
        try {
            while (queue.isEmpty()){
                try {
                    log.info("等待");
                    emptyWaitSet.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    public void put(T task){
        lock.lock();
        try {
            while (queue.size() == capcity){
                try {
                    log.info("进入等待状态");
                    fullWaitSet.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            emptyWaitSet.signal();
        }finally {
            lock.unlock();
        }
    }


    public boolean offer(T task,long timeout,TimeUnit timeUnit){
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capcity){
                try {
                    log.info("进入等待状态");
                    if (nanos <= 0){
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        }finally {
            lock.unlock();
        }
    }

    public int size(){
        lock.lock();
        try {
            return queue.size();
        }finally {
            lock.unlock();
        }
    }











}
