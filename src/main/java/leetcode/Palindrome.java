package leetcode;

public class Palindrome {
    public static void main(String[] args) {
        boolean palindrome = isPalindrome(0);
        System.out.println(palindrome);
        System.out.println(-7%-3);
    }

    /**
     * 判断是不是回文数
     * @param number
     * @return
     */
    public static boolean  isPalindrome(int number){
        if(number < 0) return false;
        if(number == 0) return true;
        int palindrome = 0;
        int expect = number;
        while (number != 0){
            palindrome = palindrome * 10 + number % 10;
            number /= 10;
        }
        return  palindrome == expect;

    }


}
