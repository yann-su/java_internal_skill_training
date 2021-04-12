package jvm.load;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomLoader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String path = "G:\\"+name+".class";
        ByteArrayOutputStream os = new ByteArrayOutputStream();


        try {
            Files.copy(Paths.get(path),os);
            //得到字节数组
            byte[] bytes = os.toByteArray();
            //byte[] -> *.class
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException("类文件未找到",e);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        CustomLoader customLoader = new CustomLoader();
        Class<?> test1 = customLoader.loadClass("Person1");
        Class<?> test2 = customLoader.loadClass("Person1");



        System.out.println(test1 == test2); //true



        for (Method method : test1.getMethods()) {
            System.out.println(method.getName());
        }

        String name = test1.getName();
        System.out.println(name);



        //不一样的加载对象是可以再次加载的
        CustomLoader customLoader1 = new CustomLoader();
        Class<?> test11 = customLoader1.loadClass("Person1");
        System.out.println(test11 == test1); //false
    }
}
