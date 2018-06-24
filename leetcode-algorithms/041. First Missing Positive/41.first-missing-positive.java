/*
 * [41] First Missing Positive
 *
 * https://leetcode-cn.com/problems/first-missing-positive/description/
 *
 * algorithms
 * Hard (28.26%)
 * Total Accepted:    1.3K
 * Total Submissions: 4.2K
 * Testcase Example:  '[1,2,0]'
 *
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * 
 * 示例 1:
 * 
 * 输入: [1,2,0]
 * 输出: 3
 * 
 * 
 * 示例 2:
 * 
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 
 * 
 * 示例 3:
 * 
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 
 * 
 * 说明:
 * 
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 * 
 */
class FirstMissingPositive041 {
    public int firstMissingPositive(int[] nums) {
        if (nums == null) {
            return 1;
        }

        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            while(cur > 0 && cur <= nums.length && cur != (i + 1)){

                int tmp = nums[cur - 1];

                if (tmp == (cur)) {
                    break;
                }

                nums[cur - 1] = cur;
                nums[i] = tmp;
                cur = tmp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != (i + 1)) {
                return ( i + 1);
            }
        }

        return nums.length + 1;
    }

    public static void main(String[] args) {
        FirstMissingPositive041 solution = new FirstMissingPositive041();
        int i = solution.firstMissingPositive(new int[]{1, 1});
        System.out.println(i);
    }
}
