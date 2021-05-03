package exections;

public class TestException {

    public boolean testFunc(boolean flag) throws CustomerException {
        if (flag == true) return true;
        throw new CustomerException("this is msg");
    }

    void testFunc2() throws CustomerException {
        testFunc(true);
    }

    public static void main(String[] args) throws CustomerException {
        boolean b = new TestException().testFunc(false);
        System.out.println(b);

    }
}
