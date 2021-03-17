package design;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TwoPhaseTermination {

    private Thread monitor;

    public void start(){
        monitor = new Thread(()->{
            while (true){
                Thread current = Thread.currentThread();
                if (current.isInterrupted()){
                    log.debug("after handler");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("monitor");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    current.interrupt();
                }
            }
        } );
        monitor.start();

    }

    public void stop(){
        monitor.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();
        TimeUnit.SECONDS.sleep(5);
        twoPhaseTermination.stop();
    }

}
