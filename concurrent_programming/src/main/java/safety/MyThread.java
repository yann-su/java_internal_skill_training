package safety;

public class MyThread extends Thread{

    private int count = 5;

    public MyThread(String name){
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (count  > 0){
            count --;
            System.out.println("由"+this.currentThread().getName()+"计算，count="+count);
        }
    }

    //此案例展示了线程在线程独占的内存区域
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread("A");
        MyThread myThread2 = new MyThread("B");
        MyThread myThread3 = new MyThread("C");

        myThread1.start();
        myThread2.start();
        myThread3.start();

        System.out.println(myThread1.count);
        System.out.println(myThread2.count);
        System.out.println(myThread3.count);


    }
}
