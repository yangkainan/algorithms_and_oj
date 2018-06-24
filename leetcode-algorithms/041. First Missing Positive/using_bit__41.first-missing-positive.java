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
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 1;
        }
        int mask  = makeMask(n);
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur > 0 && cur <= n) {
                mask = setNthBitToZero(mask, cur - 1);
            }
        }
        int firstZeroBit = firstZeroBit(mask, nums.length);
        if (firstZeroBit == -1) {
            return n + 1;
        } else {
            return firstZeroBit;
        }
    }

    private int makeMask(int length) {
        int mask = 0x1 << (length + 1);
        return mask -1;
    }

    private int setNthBitToZero(int mask, int nthBit) {
        int toMask = 0x1 << nthBit;
        toMask = ~toMask;
        return mask & toMask;
    }

    private int firstZeroBit(int mask, int length) {
        for (int i = 0; i < length; i++) {
            int toMask = 0x1 << i;
            if ( (mask & toMask) != 0) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.firstMissingPositive(new int[]{1});

        System.out.println(i);
    }
}
