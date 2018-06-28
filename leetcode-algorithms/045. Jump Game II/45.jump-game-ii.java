import java.util.HashMap;

/*
 * [45] Jump Game II
 *
 * https://leetcode-cn.com/problems/jump-game-ii/description/
 *
 * algorithms
 * Hard (23.64%)
 * Total Accepted:    1K
 * Total Submissions: 4.2K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 
 * 示例:
 * 
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 
 * 
 * 说明:
 * 
 * 假设你总是可以到达数组的最后一个位置。
 * 
 */
class JumpGameII045 {
    private HashMap<Integer, Integer> cacheStartPoinToMinStepsMap;
    public int jump(int[] nums) {
        cacheStartPoinToMinStepsMap = new HashMap<>();
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        return recursiveJump(nums, 0, nums.length - 1);
    }

    private int recursiveJump(int[] nums, int curIndex, int recursiveLimits){

        if (curIndex == (nums.length - 1)){
            return 0;
        }

        if (recursiveLimits <= 0) {
            return Integer.MAX_VALUE;
        }

        if (cacheStartPoinToMinStepsMap.keySet().contains(curIndex)) {
            return cacheStartPoinToMinStepsMap.get(curIndex);
        }

        int candidateJump = nums[curIndex];
        if (candidateJump == 0) {
            return Integer.MAX_VALUE;
        }


        int leftSteps = nums.length - 1 - curIndex;

        candidateJump = Math.min(leftSteps, candidateJump);

        int minSteps = Integer.MAX_VALUE;

        for (int i = candidateJump; i > 0; i--) {
            if (minSteps == 1) {
                return minSteps;
            }

            int steps = recursiveJump(nums, curIndex + i, Math.min(minSteps - 1, recursiveLimits - 1));
            if (steps == Integer.MAX_VALUE) {
                continue;
            }
            int totalSteps = steps + 1;
            if (totalSteps< minSteps) {
                minSteps = totalSteps;
            }
        }
        cacheStartPoinToMinStepsMap.put(curIndex, minSteps);
        return minSteps;
    }

    public static void main(String[] args) {
        JumpGameII045 solution = new JumpGameII045();

        int[] nums = new int[25001];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = 25000 - i;
        }
        System.out.println(solution.jump(new int[] {5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5}));
        System.out.println(solution.jump(nums));
        System.out.println(solution.jump(new int[] {1,2}));
    }
}
