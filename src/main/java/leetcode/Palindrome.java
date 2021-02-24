package leetcode;

public class Palindrome {
    public static void main(String[] args) {
        boolean palindrome = isPalindrome(0);
        System.out.println(palindrome);
        String number = "1111";
        boolean palindrome1 = isPalindrome(number);
        System.out.println(palindrome1);
    }

    /**
     * 判断是不是回文数
     *
     * @param number
     * @return
     */
    public static boolean isPalindrome(int number) {
        if (number < 0) return false;
        if (number == 0) return true;
        int palindrome = 0;
        int expect = number;
        while (number != 0) {
            palindrome = palindrome * 10 + number % 10;
            number /= 10;
        }
        return palindrome == expect;

    }

    public static boolean isPalindrome(String number){
        int left = 0, right = number.length() - 1;
        while (left < right){
            if (number.charAt(left) != number.charAt(right))
                return false;
            left ++;
            right --;
        }
        return true;
    }


}
