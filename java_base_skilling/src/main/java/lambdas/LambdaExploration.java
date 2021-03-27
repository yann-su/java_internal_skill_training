package lambdas;

//学习lambda表达式
// 英语单词备注：Exploration n. 探测；探究；踏勘
public class LambdaExploration {

    public static void main(String[] args) {
        //函数式表达式，()->{} 必须使用接口并且只有一个方法
        //目的是为了简化匿名内部类的写法
        Runnable runnable =  () ->{
            System.out.println("hello lambda");
         };
        new Thread(runnable).start();

        // ==> 代码简化
        new Thread(()-> {
            // 单词备注：simplify vt. 简化；使单纯；使简易
            System.out.println("hello simplify lambda");
        }).start();

    }
}
