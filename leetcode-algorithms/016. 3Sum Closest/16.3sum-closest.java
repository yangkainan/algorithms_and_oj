/*
 * [16] 3Sum Closest
 *
 * https://leetcode-cn.com/problems/3sum-closest/description/
 *
 * algorithms
 * Medium (32.59%)
 * Total Accepted:    1.4K
 * Total Submissions: 4.3K
 * Testcase Example:  '[-1,2,1,-4]\n1'
 *
 * Given an array nums of n integers and an integer target, find three integers
 * in nums such that the sum is closest to target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution.
 * 
 * Example:
 * 
 * 
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * 
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * 
 */
class ThreeSumClosest016 {
    public int threeSumClosest(int[] nums, int target) {

        int result = Integer.MAX_VALUE;

        int attemptSum = 0;
        if (nums.length <= 3) {
            for (int i = 0; i < nums.length; i++){
                attemptSum +=nums[i];
            }
            return attemptSum;
        }


        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int b = nums[j];

                for (int k = j + 1; k < nums.length; k++) {
                    int c = nums[k];

                    attemptSum = a + b + c;
//                    System.out.println(String.format("%s + %s +%s = %s, target: %s, before diff: %s, current diff: %s",
//                                                     a, b, c, attemptSum, target, result, (attemptSum - target)));

                    if (result == Integer.MAX_VALUE) {
                        result = attemptSum;
                    } else if (Math.abs(attemptSum - target) < Math.abs(result - target)) {
                        result = attemptSum;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSumClosest016 solution = new ThreeSumClosest016();
        int[] nums = new int[] {1,1,-1,-1,3};
        int target = -1;
        System.out.println(solution.threeSumClosest(nums, target));
    }
}
