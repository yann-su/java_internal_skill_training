package jvm.mock;

public class JavaStringDeferConstans {

    //验证常量值是延迟加载的，debug得出,常量查看String对象
    public static void main(String[] args) {

        //每执行一行不同的输出放在常量池里的多+1
        System.out.println("0");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        //以下重复，可以debug看出来是否是出现变化
        System.out.println("0");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        //当程序加载到这里会会创建常量到常量池中
        System.out.println("10");
        System.out.println("11");
        System.out.println("12");





    }
}
