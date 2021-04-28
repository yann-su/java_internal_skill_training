package pool.custom;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    //任务队列，
    private BlockingQueue<Runnable> taskQueue;
    //线程集合
    private HashSet<Worker> workers = new HashSet();
    //核心线程数
    private int coreSize;
    //超时时间
    private long timeout;
    //时间工具类
    private TimeUnit timeUnit;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int queueCapacity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
    }

    class Worker{



    }


}
