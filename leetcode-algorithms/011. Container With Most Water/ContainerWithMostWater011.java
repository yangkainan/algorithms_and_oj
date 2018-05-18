import com.sun.tools.javac.util.Assert;

import java.util.HashMap;

/**
 * @author yankaina on 18/5/2018.
 */
public class ContainerWithMostWater011 {
    private HashMap<String, Integer> cacheMaxHeightIndexWithinRange = new HashMap<>();
    private HashMap<String, Integer> cacheCubicOfRange = new HashMap<>();
    private boolean debug = false;

    private String intArrayToString(int[] arr) {
        String str = "[";

        boolean removeTailColon = false;
        for (int i = 0; i < arr.length ; i++) {
            str += arr[i] +",";
            removeTailColon = true;
        }

        if (removeTailColon) {
            str = str.substring(0, str.length() -1);
        }
        str +="]";

        return str;
    }
    public int maxArea(int[] height) {

        cacheCubicOfRange.clear();
        cacheMaxHeightIndexWithinRange.clear();

        int maxArea = -1;
        for (int start = 0; start < height.length; start ++) {
            for (int end = start; end < height.length; end ++) {
                int area = calculateCubicOfRange(height, start, end);
                if (area >= maxArea) {
                    maxArea = area;
                    if (debug) {
                        System.out.println(String.format("input height: %s, current MaxArea: %s, height[%s]: %s, " +
                                                                 "height[%s]: %s, steps= %s - %s = %s",
                                                         "",maxArea, start,height[start], end, height[end], end,
                                                         start, end - start));
                    }
                }

            }

        }

        return maxArea;
    }

    private int calculateCubicOfRange(int[] height, int start, int end) {
        if (start < 0 || end < 0 || end < start) {
            throw  new IllegalArgumentException(String.format("invalid input, start: %s, end: %s",
                                                              start, end));
        }

        int heightStart = height[start];
        int heightEnd = height[end];

        return Math.min(heightEnd, heightStart) * (end - start);

    }



    private int calculateCubicOfRangeComplex(int[] height, int start, int end) {
        if (start < 0 || end < 0 || end < start) {
            throw  new IllegalArgumentException(String.format("invalid input, start: %s, end: %s",
                                                              start, end));
        }

        int heightStart = height[start];
        int heightEnd = height[end];

        int cubic;

        if (end <= (start + 1)) {
            cubic = Math.min(heightEnd,heightStart) * (end - start);
            if (debug) {
                System.out.println(String.format("Input height: %s, start: %s, mid: %s, end: %s, area: %s",
                                                 intArrayToString(height), start, "", end, cubic));
            }
            return cubic;
        }

        String key = String.format("%s_%s", start, end);
        if (cacheCubicOfRange.keySet().contains(key)) {
            return cacheCubicOfRange.get(key);
        }

        int midMaxHeightIndex = maxHeightIndexWithinRange(height, start, end);

        int heightMid = height[midMaxHeightIndex];


        if (heightMid <= heightEnd) {
            if (heightMid <= heightStart) {
                cubic = Math.min(heightEnd, heightStart) * (end - start);
            } else {
                cubic = calculateCubicOfRange(height, start, midMaxHeightIndex)
                        + Math.min(heightEnd, heightMid)* (end - midMaxHeightIndex);
            }
        } else {
            cubic = calculateCubicOfRange(height, start, midMaxHeightIndex)
                    + calculateCubicOfRange(height, midMaxHeightIndex, end);
        }

        cacheCubicOfRange.put(key, cubic);

        if (debug) {
            System.out.println(String.format("Input height: %s, start: %s, mid: %s, end: %s, area: %s",
                                             intArrayToString(height), start, midMaxHeightIndex, end, cubic));
        }
        return cubic;

    }

    // don't include start and end
    private int maxHeightIndexWithinRange(int[] height, int start, int end) {
        if (start < 0 || end < start + 1) {
            throw  new IllegalArgumentException(String.format("invalid input, start: %s, end: %s",
                                                              start, end));
        }

        String key = String.format("%s_%s", start, end);
        if (cacheMaxHeightIndexWithinRange.keySet().contains(key)) {
            return cacheMaxHeightIndexWithinRange.get(key);
        }

        int maxHeightIndex = -1;
        for (int i = start +1; i < end; i++) {
            if (maxHeightIndex == -1 || height[maxHeightIndex] < height[i]) {
                maxHeightIndex = i;
            }
        }
        cacheMaxHeightIndexWithinRange.put(key, maxHeightIndex);

        return maxHeightIndex;
    }



    public static void main(String[] args) {
        ContainerWithMostWater011 solution = new ContainerWithMostWater011();

        int[] height = new int[2];
        height[0] = 1;
        height[1] = 2;

//        Assert.check(solution.maxArea(height) == 1);

        height = new int[3];
        height[0] = 1;
        height[1] = 2;
        height[2] = 3;

//        Assert.check(solution.maxArea(height) == 3);


        height = new int[3];
        height[0] = 2;
        height[1] = 1;
        height[2] = 3;

//        Assert.check(solution.maxArea(height) == 4);

        height = new int[15000];

        for(int i = 0; i < height.length; i++) {
            height[i] = height.length - i;
        }

        System.out.println(solution.maxArea(height));
    }
}
