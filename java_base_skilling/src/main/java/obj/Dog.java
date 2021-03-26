package obj;

public class Dog implements Animal{
    String name  = "wangcai";
    @Override
    public void run() {
        System.out.println("Dog run");
    }
}
