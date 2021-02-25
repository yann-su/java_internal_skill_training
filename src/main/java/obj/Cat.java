package obj;

public class Cat implements Animal{
    String name  = "kefei";

    @Override
    public void run() {
        System.out.println("Cat run");
    }
}
