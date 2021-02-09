package leetcode;

import org.junit.Test;

public class IntegerRevese {

    public int reverse(int x) {
        //使用long
        long expect = 0;
        while (x != 0) {
            expect = expect * 10 + x % 10;
            x /= 10;
        }
        return (int) expect == expect ? (int) expect : 0;
    }

    public int reverseWithZeros(int x) {
        //使用long
        int expect = 0;
        while (x != 0) {
            int tmp = x % 10;
            //判断是否 大于 最大32位整数
            if (expect > 214748364 || (expect == 214748364 && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (expect < -214748364 || (expect == -214748364 && tmp < -8)) {
                return 0;
            }
            expect = expect * 10 + tmp;
            x /= 10;
        }
        return expect;
    }

    @Test
    public void revese() {
        int reverse = reverseWithZeros(-2147483642);
        System.out.println(reverse);
    }

}
