package leetcode;

import static sort.QuickSort.QuickSort;

public class MaxProduct {


    public static int maximumProduct(int[] nums) {

        QuickSort(nums, 0, nums.length - 1);
        if (nums.length >= 3){
            return nums[nums.length - 1] *  nums[nums.length -2] * nums[nums.length - 3] ;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] ints = {-100,-98,-1,2,3,4};
        int i = maximumProduct(ints);
        System.out.println(i);

    }

}
