/*
 * [26] Remove Duplicates from Sorted Array
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/description/
 *
 * algorithms
 * Easy (37.61%)
 * Total Accepted:    16.2K
 * Total Submissions: 42.8K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 
 * 示例 1:
 * 
 * 给定数组 nums = [1,1,2], 
 * 
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 
 * 
 * 你不需要考虑数组中超出新长度后面的元素。
 * 
 * 示例 2:
 * 
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 
 * 你不需要考虑数组中超出新长度后面的元素。
 * 
 * 
 * 说明:
 * 
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 
 * 你可以想象内部操作如下:
 * 
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * 
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * 
 * 
 */
class RemoveDuplicatesFromSortedArray026 {
    public int removeDuplicates(int[] nums) {
        if (nums == null ) {
            return 0;
        }

        if (nums.length <= 1) {
            return nums.length;
        }

        int numOfUniqElements = 0;

        int startIndex = 0;
        int endIndex = nums.length - 1;


        while(startIndex <= endIndex) {
            int occurrence = 1;

            while((startIndex + occurrence) <= endIndex &&
                    nums[startIndex] == nums[startIndex + occurrence]) {
                occurrence ++;
            }

            int shiftSteps = occurrence -1;
            if (shiftSteps > 0) {
                shiftLeft(nums, (startIndex + occurrence), endIndex, shiftSteps);
            }
            startIndex ++;
            endIndex -= shiftSteps;

            numOfUniqElements ++;

        }
        return numOfUniqElements;
    }

    private void shiftLeft(int[] nums, int startIndex, int endIndex, int steps) {
        while (startIndex <= endIndex) {
            int newIndex = startIndex - steps;
            nums[newIndex] = nums[startIndex];
            startIndex ++;
        }
    }


    public static void main(String[] args) {

        RemoveDuplicatesFromSortedArray026 solution = new RemoveDuplicatesFromSortedArray026();
        int[] nums = new int[] {1};

        int numOfUniqElements = solution.removeDuplicates(nums);

        System.out.println("Uniq Elements: " + numOfUniqElements);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }

}
