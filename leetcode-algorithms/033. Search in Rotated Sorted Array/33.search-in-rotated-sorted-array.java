/*
 * [33] Search in Rotated Sorted Array
 *
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (31.07%)
 * Total Accepted:    1.4K
 * Total Submissions: 4.4K
 * Testcase Example:  '[4,5,6,7,0,1,2]\n0'
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 
 * 你可以假设数组中不存在重复的元素。
 * 
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 * 示例 1:
 * 
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 
 * 
 * 示例 2:
 * 
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * 
 */
class Solution {
    public static boolean debug = true;
    public int search(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int rotatedPointIndex = findRotatedPointIndex(nums, 0, nums.length - 1);

        int startIndex = 0 + rotatedPointIndex;
        int endIndex = nums.length - 1 + rotatedPointIndex;
        return binarySearch( nums, target, startIndex, endIndex);

    }


    private int binarySearch( int[] nums, int target, int startIndex, int endIndex) {

        if (startIndex > endIndex) {
            return  -1;
        }

        int midIndex = (startIndex +  endIndex) / 2;

        int midValue = nums[midIndex % nums.length];
        if (midValue == target) {
            return midIndex % nums.length;
        }

        if (midValue > target) {
            return binarySearch( nums, target, startIndex, midIndex - 1);
        } else {
            return binarySearch( nums, target, midIndex + 1, endIndex);
        }


    }

    private int findRotatedPointIndex(int[] nums, int startIndex, int endIndex) {

        if (isInAscendingOrder(nums, startIndex, endIndex)) {
            return startIndex;
        }

        if ((endIndex == startIndex + 1) && (nums[endIndex] <= nums[startIndex])) {
            return endIndex;
        }

        int midIndex = (startIndex + endIndex)/2;

        if (isInAscendingOrder(nums, midIndex, endIndex)) {
            return findRotatedPointIndex(nums, startIndex, midIndex);
        } else {
            return findRotatedPointIndex(nums, midIndex, endIndex);

        }

    }

    private boolean isInAscendingOrder(int[] nums, int startIndex, int endIndex) {
        return (nums[startIndex] <= nums[endIndex]);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution, new int[]{4, 5, 6, 7, 0, 1, 2}, 4);

        test(solution, new int[]{4, 5, 6, 7, 0, 1, 2}, 3);

        test(solution, new int[]{1,3}, 0);


    }

    private static void test(Solution solution, int[] nums, int target) {
        int targetIndex = solution.search(nums, target);

        int rotatedPointIndex = solution.findRotatedPointIndex(nums, 0, nums.length - 1);

        debugOutput(nums, target, targetIndex, rotatedPointIndex);
    }

    private static void debugOutput(int[] nums, int target, int targetIndex, int rotatedPointIndex) {
        if (debug) {
            String array = "[";
            for (int i = 0; i < nums.length; i++) {
                array += nums[i] +",";
            }
            array = array.substring(0, array.length() - 1);
            array += "]";
            System.out.println(String.format("nums: %s, rotatedPointIndex: %s, target: %s, targetIndex: %s",
                                             array, rotatedPointIndex, target, targetIndex));
        }
    }
}
