class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <2) {
            return null;
        }
        int i, j = 0;
        for (i = 0; i < nums.length; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    int[] result = new int[2];
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
        
    }
}
