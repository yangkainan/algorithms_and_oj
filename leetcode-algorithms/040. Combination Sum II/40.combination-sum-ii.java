import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * [40] Combination Sum II
 *
 * https://leetcode-cn.com/problems/combination-sum-ii/description/
 *
 * algorithms
 * Medium (48.37%)
 * Total Accepted:    1.1K
 * Total Submissions: 2.2K
 * Testcase Example:  '[10,1,2,7,6,1,5]\n8'
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 
 * 说明：
 * 
 * 
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 
 * 
 * 示例 1:
 * 
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * ⁠ [1, 7],
 * ⁠ [1, 2, 5],
 * ⁠ [2, 6],
 * ⁠ [1, 1, 6]
 * ]
 * 
 * 
 * 示例 2:
 * 
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 * 
 */
class CombinationSumII040 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        recurisveCombinationSum(candidates, target, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void recurisveCombinationSum(int[] candidates, int target, int candiadteIndex,
            List<Integer> currentAttemp, List<List<Integer>> result){
        if (target == 0) {
            addIfNotPresent(currentAttemp, result);
            return;
        }

        if (candiadteIndex >= candidates.length) {
            return;
        }

        int cur = candidates[candiadteIndex];

        // include current element
        if (cur <= target) {
            currentAttemp.add(cur);
            int newTarget = target - cur;
            recurisveCombinationSum(candidates, newTarget, candiadteIndex + 1, currentAttemp, result); 
            currentAttemp.remove(currentAttemp.size() -1);
        }
        // exclude current element
        recurisveCombinationSum(candidates, target, candiadteIndex + 1, currentAttemp, result);
    }

    private void addIfNotPresent(List<Integer> currentResult, List<List<Integer>> allResult) {
        boolean duplicate = false;
        for (int i = 0; i < allResult.size(); i ++) {
            List<Integer> list = allResult.get(i);
            if (list.size() == currentResult.size() && list.containsAll(currentResult)
                    && currentResult.containsAll(list)) {
                duplicate = true;
                break;
                    }
        }

        if (!duplicate) {
            allResult.add(new ArrayList<>(currentResult));
        }
    }

    public static void main(String[] args) {
        CombinationSumII040 solution = new CombinationSumII040();
        List<List<Integer>> result = solution.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);

        for (List<Integer> list : result) {
            for (Integer integer : list) {
                System.out.print(integer +",");
            }
            System.out.println();
        }
    }
}
