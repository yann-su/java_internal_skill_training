package communication.normal;

import communication.trans.MyList;

public class WaitFiveTest {

    public static void main(String[] args) throws InterruptedException {


            MyList myList = new MyList();
            Thread thread1 = new Thread(new ListEqualsFive(myList));
            Thread thread2 = new Thread(new NotifyListFive(myList));

            Thread.sleep(1000);

            thread1.start();
            Thread.sleep(1000);
            thread2.start();






    }


}
