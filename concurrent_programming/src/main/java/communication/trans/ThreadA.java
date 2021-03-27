package communication.trans;

public class ThreadA extends Thread{

    private MyList myList;

    public ThreadA(MyList list){
        super();
        this.myList = list;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                myList.add();
                System.out.println("added " + (i + 1) + " element");
                Thread.sleep(1000);
            }
        }catch (InterruptedException ignored) {
            ignored.printStackTrace();
        }
    }
}
