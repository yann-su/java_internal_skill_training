package jvm.mock;

import obj.Student;

import java.util.ArrayList;
import java.util.List;


/**
 *- jconsole：是一个内置 Java 性能分析器，分析堆栈溢出、多线程死锁等效果俱佳
 *- jvisualvm：监控内存泄露，跟踪垃圾回收，执行时内存、cpu分析，线程分析
 */
public class JvisualvmHeap {

    public static void main(String[] args) throws InterruptedException {


        List list = new ArrayList();
        for (int i = 0 ; i < 50; i ++ ){
            Student student = new Student();
            list.add(student) ;
        }
        Thread.sleep(1000000L);

    }

}
