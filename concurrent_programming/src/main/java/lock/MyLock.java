package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {


    //独占锁,同步器类
    class MySync extends AbstractQueuedLongSynchronizer{
        @Override
        //尝试获取锁
        protected boolean tryAcquire(long arg) {
            if (compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }


        @Override
        protected boolean tryRelease(long arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        //是否持有独占锁
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition(){
            return new ConditionObject();
        }

    }

    private MySync sync = new MySync();

    @Override
    //加锁（不成功进入等待队列）
    public void lock() {
        sync.acquire(1);
    }

    @Override
    //加锁 可打断
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    //尝试加锁
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    //尝试加锁带超时
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    //解锁
    public void unlock() {
        sync.release(1);
    }

    @Override
    //
    public Condition newCondition() {
        return null;
    }
}
