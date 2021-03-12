package base;

public class MyThread extends Thread{

    @Override
    //
    public void run() {
        System.out.println("hello thread");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("hello world");


    }


}
