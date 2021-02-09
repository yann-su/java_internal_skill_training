package algorithm;

public class Factorial {
    static RecursiveFactorial recursiveFactorial;
    public static void main(String[] args) {

        int i = factorialSolve(4);

        //递归调用使用函数式表达
        recursiveFactorial = n -> n <= 2 ? n : n * recursiveFactorial.factorial(n-1);
        System.out.println(recursiveFactorial.factorial(4));

        System.out.println(i);

        System.out.println(fibonacai(8));

    }

    /**
     *  以f(6)为例子
     *  f(6)
     *   => (6 * f(5))
     *   => 6 * (5 * f(4))
     *   => 6 * (5 (* 4 * f(3)))
     *   => 6 * (5 * (4 * (3 * f(2))))
     *   => 6 * (5 * (4 * (3 * (2 * f(1)))))
     *   => 6 * (5 * (4 * (3 * (2 * 1))))
     *   => 6 * (5 * (4 * (3 * 2)))
     *   => 6 * (5 * (4 * 6))
     *   => 6 * (5 * 24))
     *   => 6 * 120
     *   => 720
     *   找本质：第一明确函数的功能是什么
     *          第二寻找递归终点
     *          第三寻找等式
     *
     *
     * @param order
     * @return
     */
    public static int  factorialSolve(int order){
//        if (order == 1){
//            return 1;
//        }
        //确定边界,以防止递归无线循环,当知道递归的边界即可终止
        if (order <= 2){
           return order;
        }
        return order * factorialSolve(order-1);
    }

    /**
     * 1、1、2、3、5、8、13、21、34....
     *  f(1) = f(2) = 1 即终点可以为n =< 2
     *  斐波那契
     * @param n
     */
    public static int fibonacai(int n){
        if ( n <= 2 )
            return 1;
        return fibonacai(n-1) + fibonacai(n-2);
    }





}
