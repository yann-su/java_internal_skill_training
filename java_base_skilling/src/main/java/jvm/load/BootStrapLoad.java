package jvm.load;

public class BootStrapLoad {

    //到这里去执行代码
    //\java_base_skilling\target\classes> java -Xbootclasspath/a:. jvm.load.BootStrapLoad
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("jvm.load.entity.A");
        System.out.println(aClass.getClassLoader());
    }


}
