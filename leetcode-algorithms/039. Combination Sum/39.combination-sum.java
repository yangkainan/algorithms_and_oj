import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * [39] Combination Sum
 *
 * https://leetcode-cn.com/problems/combination-sum/description/
 *
 * algorithms
 * Medium (57.25%)
 * Total Accepted:    1.4K
 * Total Submissions: 2.4K
 * Testcase Example:  '[2,3,6,7]\n7'
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的数字可以无限制重复被选取。
 * 
 * 说明：
 * 
 * 
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 
 * 
 * 示例 1:
 * 
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * ⁠ [7],
 * ⁠ [2,2,3]
 * ]
 * 
 * 
 * 示例 2:
 * 
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * 
 */
class CombinationSum039 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if (candidates == null || candidates.length <= 0 || target <= 0) {
            return result;
        }

        Arrays.sort(candidates);

        if (candidates[0] > target) {
            return result;
        }

        recursiveCombine(candidates, target, new ArrayList<>(), result);

        return result;
    }

    private void recursiveCombine(int[] candidates, int target, List<Integer> currentAnswer, List<List<Integer>>
            result) {
        if (target == 0) {
            Integer[] answer = new Integer[currentAnswer.size()];
            currentAnswer.toArray(answer);

            Arrays.sort(answer);

            List<Integer> newAnswer = Arrays.asList(answer);

            if (!containsAnswer(result, newAnswer)) {
                result.add(newAnswer);
            }
            return;
        }

        for (int i = 0; i < candidates.length; i++) {
            int cur = candidates[i];

            if (cur <= target) {
                currentAnswer.add(cur);
                recursiveCombine(candidates, target - cur, currentAnswer, result);
                currentAnswer.remove(currentAnswer.size() - 1);
            }
        }
    }

    private boolean containsAnswer(List<List<Integer>> result, List<Integer> currentAnswer) {
        for (List<Integer> integers : result) {
            if (integers.size() == currentAnswer.size() &&
                    integers.containsAll(currentAnswer) &&
                    currentAnswer.containsAll(integers)) {

                boolean allSame= true;
                for (int i = 0; i < integers.size(); i++) {
                    if (integers.get(i) != currentAnswer.get(i)) {
                        allSame = false;
                    }
                }
                if (allSame) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CombinationSum039 solution = new CombinationSum039();
        List<List<Integer>> lists = solution.combinationSum(new int[]{5,10,8,4,3,12,9}, 27);

        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + ",");
            }
            System.out.println();
        }

    }

}
