/*
 * [47] Permutations II
 *
 * https://leetcode-cn.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (42.75%)
 * Total Accepted:    1.1K
 * Total Submissions: 2.5K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,1,2]
 * 输出:
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 * 
 */
class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        return recursivePermuteUnique(nums);
    }

    private List<List<Integer>> recursivePermuteUnique(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            Integer curElement = nums[i];
            int[] subNums = removeOneElement(nums, i);

            List<List<Integer>> subResult = recursivePermuteUnique(subNums);

            if (subResult.size() == 0) {
                subResult.add(new ArrayList<Integer>());
            }
            for (List<Integer> onePermute : subResult) {
                onePermute.add(0, curElement);
                result.add(onePermute);
            }
        }
        return removeDuplicate(result);
    }
    private int[] removeOneElement(int[] nums, int index) {
        int[] result = new int[nums.length - 1];

        for (int i = 0; i < result.length ; i++) {
            if (i < index) {
                result[i] = nums[i];
            } else {
                result[i] = nums[i + 1];
            }
        }

        return result;
    }

    private List<List<Integer>> removeDuplicate(List<List<Integer>> result) {
        Comparator<List<Integer>> comp = new Comparator<List<Integer>>() {

            @Override
            public int compare(List<Integer> l1, List<Integer> l2) {
                if (l1.size() < l2.size() ) {
                    return -1;
                }
                if (l1.size() > l2.size() ) {
                    return 1;
                }
                for (int i = 0; i < l1.size(); i++) {
                    Integer i1 = l1.get(i);
                    Integer i2 = l2.get(i);
                    if (i1  < i2) {
                        return -1;
                    } else if (i1 > i2) {
                        return 1;
                    }
                }

                return 0;
            }
        };

        Collections.sort(result, comp);

        List<List<Integer>> deDuplication = new ArrayList<List<Integer>>();

        for (int i = 0; i < result.size(); i++) {
            if (i == 0) {
                deDuplication.add(result.get(i));
            } else {
                List<Integer> prev = result.get(i -1);
                List<Integer> cur = result.get(i);

                if (comp.compare(prev, cur) != 0) {
                    deDuplication.add(cur);
                }
            }
        }
        return deDuplication;
    }


}
