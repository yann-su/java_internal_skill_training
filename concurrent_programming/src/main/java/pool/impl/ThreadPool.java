package pool.impl;


//学 https://blog.csdn.net/jisuanjiguoba/article/details/80548045
public interface ThreadPool<Job extends Runnable> {
    //执行一个Job,这个Job需要实现Runnable
    void execute(Job job);
    //关闭线程池
    void shutdown();
    //添加工作者线程
    void addWorkers(int num);
    //增加工作者线程
    void removeWorker(int num);
    //得到正在等待执行任务数量
    int getJobSize();

}
