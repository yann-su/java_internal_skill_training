package leetcode;

import entity.ListNode;

public class Palindrome {




    public static void main(String[] args) {
        boolean palindrome = isPalindrome(11011);
        System.out.println(palindrome);
        String number = "11011";
        boolean palindrome1 = isPalindrome(number);
        System.out.println(palindrome1);

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, null)));

        System.out.println(head.next.val);
        boolean b = isPalindrome(head);
        System.out.println(b);


    }

    private static ListNode left;

    public static boolean isPalindrome(ListNode head){
        left = head;
        return reversePalindrome(head);
    }

    public static boolean reversePalindrome(ListNode right){
        if (right == null) return true;
        boolean res = reversePalindrome(right.next);
        res = res && (left.val == right.val);
        left = left.next;
        return res;
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
