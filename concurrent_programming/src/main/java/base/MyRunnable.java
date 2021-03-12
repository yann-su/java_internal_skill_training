package base;

public class MyRunnable implements Runnable{


    @Override
    public void run() {
        System.out.println("hello Runnable");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        //交给底层cpu调度
        thread.start();
        System.out.println("hello world");

    }

}
