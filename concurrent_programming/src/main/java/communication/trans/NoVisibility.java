package communication.trans;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoVisibility {


    //修改可以让线程停止下来
//    volatile static boolean isRun = true;
    static boolean isRun = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            //线程会有一份缓存数据,减少对主存的读取
            //但是如果添加了volatile会从主存中获取，可见性
            while (isRun){

            }
        }).start();

        Thread.sleep(1000);
        log.info("thread run to main,the next change");
        //在没有添加volatile时候，isRun的数据是在主存中，
        isRun = false;

    }

}
