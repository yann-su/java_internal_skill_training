package exections;

public class TestException {

    void testFunc() throws CustomerException{
        throw new CustomerException("this is msg");
    }

    void testFunc2(){
        testFunc();
    }

    public static void main(String[] args) {
        try {
            new TestException().testFunc();
        }catch (CustomerException customerException){
            System.out.println(customerException.getDescription());
        }
    }
}
