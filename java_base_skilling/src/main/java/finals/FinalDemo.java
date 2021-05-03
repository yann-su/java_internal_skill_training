package finals;

/**
 * final 修饰类，修饰方法，修饰变量
 * 类： 不能被继承
 * 方法： 不能被重写
 * 变量： 变量有且只能被赋值一次
 * 扩展：abstract 和 final 的关系，两者互斥关系，不能同时出现，
 *     理解：abstract是一定要被重写和继承，不能同时使用，如方法
 *          final是不允许被继承
 */

public class FinalDemo {

    //全局
    //看log4j源码看别人如何使用final在实际生产中
    public final static String NAME;
    //可以在静态代码块进行赋值
    static {
        NAME = "王五";
    }

    //示例代码块，是可以在实例代码创造的时候的进行赋值
    //每次创建FinalDemo的时候，就会进行
    //如果没有赋值的情况下，需要在构造器中进行赋值
    public final int age;
    {
        age = 19;
    }



    //不能直接重写
    public final void run(){
        //局部方法内不允许被修改
        final double rate = 3.14;
//        rate = 1;
    }

    public static void main(String[] args) {
        //不允许被再次赋值
//        NAME = "李四";
        System.out.println(NAME);
    }




}
