package jvm.load;

public class AppLoad {

    /**
     * 本地直接运行，然后可以查看
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("jvm.load.entity.A");
        System.out.println(aClass.getClassLoader());
    }
}
