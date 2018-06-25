/*
 * [42] Trapping Rain Water
 *
 * https://leetcode-cn.com/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (32.37%)
 * Total Accepted:    1.1K
 * Total Submissions: 3.3K
 * Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]'
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 
 * 
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢
 * Marcos 贡献此图。
 * 
 * 示例:
 * 
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * 
 */
class Solution {
    boolean debug = false;
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int totalWater = 0;
        int rangeStartIndex = 0;

        ContainableRange range = findContainableRange(height, rangeStartIndex);

        while (range != null) {
            totalWater += range.countContainedWater();
            if (debug) {
                System.out.println(range.toString());
            }
            rangeStartIndex = range.getRightIndex();
            range = findContainableRange(height, rangeStartIndex);
        }

        return totalWater;
    }

    private ContainableRange findContainableRange(int[] height, int rangeStartIndex) {
        int containableRangeLeftIndex = rangeStartIndex;
        for (int i = rangeStartIndex + 1; i < height.length; i++) {
            if (height[i] >= height[containableRangeLeftIndex]) {
                containableRangeLeftIndex = i;
            } else {
                break;
            }
        }
        if (containableRangeLeftIndex == height.length - 1) {
            return null;
        }
        int containableRangeRightIndex = findRangeRightIndex(height, containableRangeLeftIndex,
                                                             containableRangeLeftIndex + 1, height.length -1);

        if (containableRangeRightIndex == -1) {
            return null;
        }

        return new ContainableRange(height, containableRangeLeftIndex,  containableRangeRightIndex);
    }

    private int findRangeRightIndex(int[] height, int rangeLeftIndex, int start, int end) {
        int rangeRightIndex = -1;
        for (int i = start; i <= end; i++) {
            if (rangeRightIndex == -1 || height[i] >= height[rangeRightIndex]) {
                rangeRightIndex = i;
            }
            if (height[rangeRightIndex] >= height[rangeLeftIndex]) {
                break;
            }
        }
        return rangeRightIndex;
    }

    class ContainableRange {
        int leftIndex;
        int rightIndex;
        int[] height;

        public ContainableRange(int[] height, int leftIndex,  int rightIndex) {
            this.height = height;
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
        }

        public int countContainedWater() {
            int upLimit = Math.min(height[leftIndex], height[rightIndex]);
            int total = 0;
            for (int i = leftIndex + 1; i < rightIndex; i++) {
                if (height[i] <= upLimit) {
                    total += (upLimit - height[i]);
                }
            }
            return total;
        }
        public int getRightIndex() {
            return this.rightIndex;
        }
        public String toString() {
            return String.format("leftIndex: %s,  rightIndex: %s, heightLeft: %s,  heightRight: %s, total: %s",
                    leftIndex, rightIndex, height[leftIndex],  height[rightIndex], countContainedWater());
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int trap = solution.trap(new int[]{5, 2, 1, 2, 1, 5});
        System.out.println(trap);
    }

}
