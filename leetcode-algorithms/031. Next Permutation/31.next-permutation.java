import java.util.Arrays;

/*
 * [31] Next Permutation
 *
 * https://leetcode-cn.com/problems/next-permutation/description/
 *
 * algorithms
 * Medium (27.55%)
 * Total Accepted:    934
 * Total Submissions: 3.4K
 * Testcase Example:  '[1,2,3]'
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 
 * 必须原地修改，只允许使用额外常数空间。
 * 
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 */
class NextPermutation031 {
    public void nextPermutation(int[] nums) {

        if (nums == null | nums.length <= 1) {
            return;
        }

        if (isInDescendingOrder(nums, 0, nums.length)) {
            changeToMin(nums, 0, nums.length);
            return;
        }

        int startIndex = nums.length - 2;

        while (isInDescendingOrder(nums, startIndex, nums.length)) {
            startIndex --;
        }



        for (int i = nums.length - 1; i > startIndex; i--) {
            if (nums[i] > nums[startIndex]) {
                int tmp = nums[startIndex];
                nums[startIndex] = nums[i];
                nums[i] = tmp;
                break;
            }
        }

        changeToMin(nums, startIndex +1, nums.length);

    }

    private void changeToMin(int[] nums, int startIndex, int endIndex) {
        Arrays.sort(nums, startIndex, endIndex);
    }

    private boolean isInDescendingOrder(int[] nums, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NextPermutation031 solution = new NextPermutation031();
        int[] nums = new int[]{3, 2, 1};

        solution.nextPermutation(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] +",");
        }
        System.out.println();


         nums = new int[]{1, 2, 3};

        solution.nextPermutation(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] +",");
        }
        System.out.println();

        nums = new int[]{1, 1, 5};

        solution.nextPermutation(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] +",");
        }
        System.out.println();
    }

}
