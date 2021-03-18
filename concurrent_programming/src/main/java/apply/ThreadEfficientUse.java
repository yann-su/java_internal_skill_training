package apply;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadEfficientUse {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            log.info("洗水壶");
            sleep(2);
            log.info("烧水");
            sleep(10);
            log.info("水开了");
        },"老王");

        Thread thread1 = new Thread(() -> {
            log.info("洗茶壶");
            sleep(2);
            log.info("拿茶叶");
            sleep(1);
            try {
                log.info("等待泡茶");
                //使用了同步等地线程一结束了，才进行下一步

                log.info(thread.getState().toString());
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("泡茶");

        },"小王");

        sleep(1);
        thread.start();
        thread1.start();
        log.info("完成泡茶");

    }


    public static void sleep(int sec){
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
