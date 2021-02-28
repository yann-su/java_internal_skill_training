package jvm.mock;

import java.util.ArrayList;
import java.util.List;

//-Xmx10m设置，跑的时候出现如下的错误.在1.6在永久代（方法区）中，在1.8以后在堆内存中，原因是为了更好的垃圾回收，
//StringTable 垃圾回收机制
//-XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
//调整-XX:StringTableSize=10000 桶个数，因为StringTable是一个HashTable 桶数越多碰撞次数越少
//intern 判断字符串对象是否需要进入StringTable中减少对象的开销
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
