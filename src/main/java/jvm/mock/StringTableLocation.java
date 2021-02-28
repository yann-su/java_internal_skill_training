package jvm.mock;

import java.util.ArrayList;
import java.util.List;

//-Xmx10m设置，跑的时候出现如下的错误.在1.6在永久代（方法区）中，在1.8以后在堆内存中，原因是为了更好的垃圾回收，

/**
 *  java.lang.OutOfMemoryError: Java heap space
 * 	at java.base/java.util.Arrays.copyOf(Arrays.java:3511)
 * 	at java.base/java.util.Arrays.copyOf(Arrays.java:3480)
 * 	at java.base/java.util.ArrayList.grow(ArrayList.java:237)
 * 	at java.base/java.util.ArrayList.grow(ArrayList.java:244)
 * 	at java.base/java.util.ArrayList.add(ArrayList.java:454)
 * 	at java.base/java.util.ArrayList.add(ArrayList.java:467)
 * 	at jvm.mock.StringTableLocation.main(StringTableLocation.java:12)
 *
 */
public class StringTableLocation {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        try {
            for (int j=0;j< 2600000;j++){
                list.add(String.valueOf(j).intern());
                i++;
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }finally {
            System.out.println(i);
        }



    }
}
