package jvm.mock;

import java.util.ArrayList;
import java.util.List;

/**
 *  演示堆内存溢出 java.lang.OutOfMemoryError
 *  根据-Xmx8m 修改，同java stacks -xms1024k
 *  Xmx 默认是 4294967296
 *
 *
 */
public class HeapMockOverError {

    public static void main(String[] args) {

        int i = 0;
        System.out.println(Runtime.getRuntime().maxMemory());

        try {
            List list = new ArrayList();
            String s = "hello heap for mock";
            while (true){
                list.add(s);
                s = s + s;
                i ++ ;
            }
        }catch (OutOfMemoryError out){
            System.out.println(i);
            out.printStackTrace();
        }


    }

}
