package jvm.mock;

import obj.Student;

import java.util.ArrayList;
import java.util.List;


/**
 * Jvisualvm 使用Jvisualvm工具进行查看
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
