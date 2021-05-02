package pool.custom;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Slf4j
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

    public void execute(Runnable task){
        synchronized (workers){
            if (workers.size() < coreSize){
                //
                log.debug("新增worker{}",workers);
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
                return;
            }
            log.debug("");
            taskQueue.put(task);
        }
    }


    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int queueCapacity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
    }

    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            //当 task 不为空，执行任务
            //当task执行完毕，在接着从任务队列中获取任务并执行
            while (task != null || (task = taskQueue.take()) != null){
                try{
                    task.run();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    task = null;
                }

            }
            synchronized (workers){
                workers.remove(this);
            }


        }
    }


}
