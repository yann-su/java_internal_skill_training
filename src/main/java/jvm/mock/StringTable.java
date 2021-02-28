package jvm.mock;

public class StringTable {


    // StringTable hashTable ['a','b','ab']
    // 使用javap -v   StringTable 查看反编译的内容
    public static void main(String[] args) {

        String s1 = "a";

        String s2 = "b";

        String s3 = "ab";
        //注释中是s4编译中实际的执行代码
        //1.8 执行的方式 new StringBuilder().append("a").append("b").toString(); //newString(value, 0, count)
        //1.9以后使用了InvokeDynamic 来进行链接，但是实际上都不是指向一个位置， (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;反编译结果
        String s4 = s1 + s2; //是一个变量，拼接需要在对象里卖弄

        String s5 = "a"+"b"; //常量在编译期间已经知道

        //1.8中 在s4中是在堆中创建了一个新的对象 newString对象，故实际上是是不一样的
        //1.9以后使用了InvokeDynamic
        System.out.println(s4 == s3);
        //在使用的编译Javac的时候，使用"a"+"b"时候，已经知道是是一个常量，执行了“ab”
        System.out.println(s5 == s3);


        // new String("ab") //new StringBuilder().append("a").append("b").toString();
        String s = new String("a") +  new String("b");
        //将s的对象尝试放入串池中，
        String sIntern = s.intern();
        //返回的sIntern是放在了串池中
        System.out.println(sIntern == s3);


    }


}
