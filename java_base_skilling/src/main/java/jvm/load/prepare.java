package jvm.load;

public class prepare {

    public static void main(String[] args) {
        System.out.println(H.a);

    }

}
class H{
    public static int a = 13;


   static   {
        System.out.println("静态代码块");
    }
    public H() {
        System.out.println("初始化");
    }



}