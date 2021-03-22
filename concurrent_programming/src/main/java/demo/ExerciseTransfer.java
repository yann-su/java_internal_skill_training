package demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

@Slf4j
//经典多线程卖票
public class ExerciseTransfer {

    static Random random = new Random();
    public static int randomInt(){
        return random.nextInt(5)+1;
    }

    public static void sellFortest() throws InterruptedException {

        //模拟多人买票
        TicketWindow ticketWindow = new TicketWindow(1000);

        ArrayList<Thread> list = new ArrayList<>();
        Vector<Integer> amountList = new Vector<>();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(()->{
                //买票
                int sell = ticketWindow.sell(1);
                amountList.add(sell);
            });
            list.add(thread);
            thread.start();
        }
        for (Thread thread : list) {
            thread.join();
        }

        log.info("余票：{}",ticketWindow.getCount());
        log.info("卖出的票是：{}",amountList.stream().mapToInt(i -> i).sum());
    }


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            sellFortest();
        }

    }



}
