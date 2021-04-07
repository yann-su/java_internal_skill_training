package communication.normal;

import communication.trans.MyList;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotifyListFive implements Runnable {


    private final MyList myList ;

    public NotifyListFive(MyList myList){
        this.myList = myList;
    }


    @SneakyThrows
    @Override
    public void run() {
        synchronized (myList){
        for (int i = 0; i < 10; i++) {
            myList.add();
            if (myList.size() == 5){
               myList.notifyAll();
                log.info("e is {}",myList.size());
//                Thread.sleep(10000);10000
                myList.wait();
            }
            log.info("add element {}",i+1);
        }
        }

    }



}
