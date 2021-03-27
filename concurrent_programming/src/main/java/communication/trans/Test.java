package communication.trans;

public class Test {

    public static void main(String[] args) {

        MyList myList = new MyList();
        ThreadA threadA = new ThreadA(myList);
        threadA.setName("A");
        ThreadB threadB = new ThreadB(myList);
        threadB.setName("B");
        threadA.start();
        threadB.start();

    }
}
