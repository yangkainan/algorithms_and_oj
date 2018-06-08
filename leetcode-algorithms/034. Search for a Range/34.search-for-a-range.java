/*
 * [34] Search for a Range
 *
 * https://leetcode-cn.com/problems/search-for-a-range/description/
 *
 * algorithms
 * Medium (32.23%)
 * Total Accepted:    1.4K
 * Total Submissions: 4.4K
 * Testcase Example:  '[5,7,7,8,8,10]\n8'
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 
 * 示例 1:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 
 * 示例 2:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * 
 */
class SearchForARange034 {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return new int[]{-1, -1};
        }

        int matchIndex = binarySearch(nums, target);
        if (matchIndex == -1) {
            return new int[]{-1, -1};
        }

        return binarySearchRange(nums, target, matchIndex);
        
    }

    private int[] binarySearchRange(int[] nums, int target, int matchIndex) {
        
        int index = -1;

        int startIndex = 0;
        int endIndex = matchIndex -1;
        int smallestIndex = matchIndex;
        while ((index = binarySearch(nums, 0, endIndex, target)) != -1){
            endIndex = index -1;
            if (index < smallestIndex){
                smallestIndex = index;
            }
        }

        startIndex = matchIndex + 1;
        endIndex = nums.length -1;
        int biggestIndex = matchIndex;
        while ((index = binarySearch(nums, startIndex, endIndex, target)) != -1){
            startIndex = index + 1;
            if (index > biggestIndex ){
                biggestIndex = index;
            }
        }


        return new int[] {smallestIndex,  biggestIndex};
    }

    private int binarySearch(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length -1, target);
    }

    private int binarySearch(int[] nums, int startIndex, int endIndex, int target) {
        if (startIndex > endIndex) {
            return -1;
        }

        int midIndex = (startIndex + endIndex) / 2;

        int midValue = nums[midIndex];
        if (midValue == target) {
            return midIndex;
        }

        if (midValue > target) {
            return binarySearch(nums, startIndex, midIndex - 1, target);
        } else {
            return binarySearch(nums, midIndex + 1, endIndex, target);
        }



    }

    public static void main(String[] args) {
        SearchForARange034 solution = new SearchForARange034();

        solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);

        solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6);
    }
}
