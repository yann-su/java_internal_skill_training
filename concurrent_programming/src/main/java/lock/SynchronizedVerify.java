package lock;

import demo.TicketWindow;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

@Slf4j
public class SynchronizedVerify {

    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();
        log.info(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){

        }
        Thread.sleep(4000);
        log.info(ClassLayout.parseInstance(o).toPrintable());



    }

}
