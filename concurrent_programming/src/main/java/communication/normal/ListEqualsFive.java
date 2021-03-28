package communication.normal;

import communication.trans.MyList;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListEqualsFive implements Runnable {

    private final MyList myList;

   public ListEqualsFive(MyList myList){
       this.myList = myList;
   }


    @Override
    public void run() {

       synchronized (myList){
           try {
               log.info("waiting");
               myList.wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           if (myList.size() == 5){
               log.info("myList size == ",myList.size());
               log.info("ListEqualsFive is launch");
           }else {
               log.info("myList size == {} ",myList.size());
               log.info("NO!!!");
           }

       }

    }



}
