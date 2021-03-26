package leetcode;

import data.structure.linked.LinkedList;
import entity.ListNode;

import java.util.HashMap;
import java.util.List;

public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        /**
         * 暴力枚举法
         */
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }


    /**
     * 两个链表相加,问题出在链表赋值问题，思路没问题，但是面试时候看的是对错，记住这点
     * 
     * @param l1
     * @param l2
     * @return
     */
    // TODO: 2021/2/25 学习链表赋值
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = null,tail = null;
        int divisor = 0;
        while (l1 != null || l2 != null){
            //巧用三目运算符
            int l1data = l1 != null ? l1.val : 0;
            int l2data = l2 != null ? l2.val : 0;
            int data =  l1data + l2data + divisor;
            if (head == null){
                head = tail =  new ListNode(data % 10,null);
            }
            else{
                tail.next = new ListNode(data % 10,null);
                tail = tail.next;
            }
            divisor =  data / 10;
            if ( l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
        }
        return head;
    }


    public static int[] twoSumWithHashMap(int[] nums, int target) {
        /**
         *
         */
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (integerIntegerHashMap.containsKey(integerIntegerHashMap.get(target - nums[i]))) {
                return new int[]{integerIntegerHashMap.get(target - nums[i]), i};
            }
            integerIntegerHashMap.put(nums[i], i);
        }
        return new int[]{0, 0};
    }

    public static void print(ListNode temp) {
        while (temp != null) {
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
        ListNode head = new ListNode(9, new ListNode(9, new ListNode(9, null)));
        ListNode head1 = new ListNode(9, new ListNode(9, null));
        ListNode listNode = addTwoNumbers(head, head1);
        print(listNode);


    }


}
