package cas;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;


/**
 * 操作底层类，因为是底层类，所以被命名为unsafe对象
 */
public class UnsafeTest {


    static Unsafe unsafe;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        //获取unsafe
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe)theUnsafe.get(null);

        System.out.println(unsafe);

        //1、获取域的偏移地址
        long idOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("id"));
        long nameOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("name"));


        Teacher teacher = new Teacher();
        //2、执行cas操作
        unsafe.compareAndSwapInt(teacher, idOffset, 0, 1);
        unsafe.compareAndSwapObject(teacher, nameOffset, null, "张三");

        //验证
        System.out.println(teacher);


    }

}

@Data
class Teacher{

    volatile int id;
    volatile String name;

}
