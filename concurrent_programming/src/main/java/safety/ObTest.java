package safety;

public class ObTest {


    public static void main(String[] args) throws InterruptedException {
        //room 实现和 AtomicInteger 是一致的，保护修改的代码临界区的原子性
        Room room = new Room();

        ObRunnable01 obRunnable01 = new ObRunnable01(room);
        ObRunnable02 obRunnable02 = new ObRunnable02(room);
        Thread thread1 = new Thread(obRunnable01);
        Thread thread2 = new Thread(obRunnable02);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(room.getCounter());


    }
}
