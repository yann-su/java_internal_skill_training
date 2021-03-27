package communication.trans;

public class ThreadB extends Thread{

    private MyList myList;

    public ThreadB(MyList myList){
        this.myList = myList;
    }

    @Override
    public void run() {
        try {
            //这种方式TheadB会一直去轮训，导致线程会一直使用CPU资源
        while (true) {
            if (myList.size() == 5) {
                System.out.println("eeeeeeeeeexit");
                throw new InterruptedException();
            }
        }
        }catch(InterruptedException e){
            e.printStackTrace();
        }



    }
}
