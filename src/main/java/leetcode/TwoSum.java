package leetcode;

import java.util.HashMap;

public class TwoSum {

    public static int [] twoSum(int[] nums, int target){
        /**
         * 暴力枚举法
         */
        for (int i = 0; i < nums.length;i ++ ){
            for (int j = i + 1; j < nums.length; j++){
                if (target == nums[i] + nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0,0};
    }

    public static int [] twoSumWithHashMap(int[] nums, int target){
        /**
         *
         */
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int i = 0; i < nums.length;i ++ ){
            if (integerIntegerHashMap.containsKey(integerIntegerHashMap.get(target - nums[i]))){
                return new int[]{integerIntegerHashMap.get(target - nums[i]),i};
            }
            integerIntegerHashMap.put(nums[i],i);
        }
        return new int[]{0,0};
    }



    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{2,7,11,15},9);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }


}
