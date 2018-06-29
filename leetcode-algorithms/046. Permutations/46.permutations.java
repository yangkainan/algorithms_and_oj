/*
 * [46] Permutations
 *
 * https://leetcode-cn.com/problems/permutations/description/
 *
 * algorithms
 * Medium (61.65%)
 * Total Accepted:    2.2K
 * Total Submissions: 3.5K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,2,3]
 * 输出:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 * 
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        return recursivePermute(nums);
    }

    private List<List<Integer>> recursivePermute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> subResult = recursivePermute(subArrayByRemoveElement(nums, i));
            if (subResult.size() == 0) {
                subResult.add(new ArrayList<Integer>());
            } 
            for (List<Integer> onePermute : subResult) {
                onePermute.add(0, nums[i]);
                result.add(onePermute);
            }
        }
        return result;
    }

    private int[] subArrayByRemoveElement(int[] nums, int toRemoveIndex) {
        int[] result = new int[nums.length -1];
        for(int i = 0; i < result.length; i++) {
            if (i < toRemoveIndex) {
                result[i] = nums[i];
            } else {
                result[i] = nums[i + 1];
            }
        }
        return result;
    }
}
