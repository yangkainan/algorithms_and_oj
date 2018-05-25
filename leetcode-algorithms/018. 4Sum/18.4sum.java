import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * [18] 4Sum
 *
 * https://leetcode-cn.com/problems/4sum/description/
 *
 * algorithms
 * Medium (28.62%)
 * Total Accepted:    1.1K
 * Total Submissions: 3.9K
 * Testcase Example:  '[1,0,-1,0,-2,2]\n0'
 *
 * Given an array nums of n integers and an integer target, are there elements
 * a, b, c, and d in nums such that a + b + c + d = target? Find all unique
 * quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * 
 * The solution set must not contain duplicate quadruplets.
 * 
 * Example:
 * 
 * 
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * 
 * A solution set is:
 * [
 * ⁠ [-1,  0, 0, 1],
 * ⁠ [-2, -1, 1, 2],
 * ⁠ [-2,  0, 0, 2]
 * ]
 * 
 * 
 */
class FourSum018 {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <4)
            return result;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            int sum = a;
            if (sum >= 0 && sum > target) {
                return result;
            }

            for (int j = i + 1; j < nums.length; j++) {
                int b = nums[j];
                sum = a + b;

                if ((sum >= 0) && (sum > target)) {
                    break;
                }

                for (int k = j + 1; k < nums.length; k++) {

                    int c = nums[k];
                    sum = a + b + c;

                    if ((sum >= 0) && (sum > target)) {
                        break;
                    }

                    for (int l = k + 1; l < nums.length; l++) {
                        int d = nums[l];

                        sum = a + b + c + d;

                        if ((sum >= 0) && (sum > target)) {
                            break;
                        }

                        if (sum == target) {
                            List<Integer> solution = Arrays.asList(a, b, c, d);
                            if (! containSolution(result, solution)) {
                                result.add(solution);
                            }
                        }

                    }

                }

            }
        }

        return result;

    }

    private boolean containSolution(List<List<Integer>> result, List<Integer> solution) {
        for (int i = 0; i < result.size(); i++) {
            List<Integer> list = result.get(i);
            if (list.containsAll(solution) && solution.containsAll(list)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {0,2,2,2,10,-3,-9,2,-10,-4,-9,-2,2,8,7};

        FourSum018 solution = new FourSum018();

        int target = 6;
        List<List<Integer>> listList = solution.fourSum(nums, target);
        for (int i = 0; i < listList.size(); i++) {
            List<Integer> integerList = listList.get(i);
            String out="[";
            for (int j = 0; j < integerList.size(); j++) {
                out += integerList.get(j) +", ";
            }
            out = out.substring(0, out.lastIndexOf(","));
            out += "]";
            System.out.println(out);
        }
    }
}
