package jvm.mock;

/**
 * 此代码是用来查看递归调用本地虚拟机栈的一个栈帧的次数
 * 使用-Xss256k 可以修改栈帧的大小，一般使用默认即可
 */
public class RecursiveForJvmStack {

   static int counter = 0;

    public static void main(String[] args) {

        try {
            mockRecursive();
        }catch (StackOverflowError stackOverflowError){
            stackOverflowError.printStackTrace();
            System.out.println(counter);
        }



    }

    public static void mockRecursive(){
        counter++;
        mockRecursive();
    }
}
