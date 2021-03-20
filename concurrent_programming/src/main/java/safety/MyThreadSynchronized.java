package safety;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyThreadSynchronized {

    static int count = 0;
    static Object lock = new Object();
    static Object lock1 = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(()->{

            //
            for (int i = 0; i < 5000000;i++){
                //使用对象锁临界区保证，这段代码是一个原子性
                synchronized (lock){
                    //count ++ 在jvm指令中 为在使用单线程时候，运行代码为指针计数器来一行行运行，不会出现cpu资源问题，故使用synchronized
                    // get count
                    // count = count + 1，
                    // put count
                    count ++;
                }
            }
        });

        Thread thread2 = new Thread(()->{

            //
            for (int i = 0; i < 5000000;i++){
                synchronized (lock){
                    count --;
                }
            }
        });



        thread1.start();
        thread2.start();
        log.info("count:"+count);
        thread1.join();
        thread2.join();

    }



}
