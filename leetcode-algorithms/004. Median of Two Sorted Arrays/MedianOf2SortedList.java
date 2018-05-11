import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * @author yankaina on 9/5/2018.
 */

public class MedianOf2SortedList {
    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            System.out.println("Nums1: ");
            Arrays.stream(nums1).forEach(i -> System.out.print(i +","));
            System.out.println();
            System.out.println("Nums2: ");
            Arrays.stream(nums2).forEach(i -> System.out.print(i +","));
            System.out.println();

            if (nums1 == null || nums1.length == 0) {
                return findMedianSortedArray(nums2);
            }

            if (nums2 == null || nums2.length == 0) {
                return findMedianSortedArray(nums1);
            }

            int targetIndex_left = (nums1.length + nums2.length -  1) /2 ;
            int targetIndex_right = (nums1.length + nums2.length)/2;
            int nums1StartIndex = 0;
            int nums1EndIndex = nums1.length;
            int nums2StartIndex = 0;
            int nums2EndIndex = nums2.length;

            while(targetIndex_left > 0) {
                int halfTargetIndex = targetIndex_left/2;
                System.out.println(String.format("indicate index: %s", halfTargetIndex));

                System.out.println(String.format("Nums1 Start: %s, Nums1 End: %s, Nums2 Start: %s, Nums2 End: %s," +
                                                         " targetLeft: %s, targetRight: %s, Nums1[%s]:%s, " +
                                                         "Nums2[%s]:%s",
                                                 nums1StartIndex,
                                                 nums1EndIndex,
                                                 nums2StartIndex,
                                                 nums2EndIndex,
                                                 targetIndex_left,
                                                 targetIndex_right,
                                                 nums1StartIndex, (nums1StartIndex + halfTargetIndex < nums2EndIndex) ?
                                                                  nums1[nums1StartIndex + halfTargetIndex] : -888,
                                                 nums2StartIndex, (nums2StartIndex + halfTargetIndex < nums2EndIndex) ?
                                                                  nums2[nums2StartIndex + halfTargetIndex] : -999));

                int targetOfNums1 = Integer.MAX_VALUE;
                int targetOfNums2 = Integer.MAX_VALUE;
                if (nums1StartIndex + halfTargetIndex < nums1EndIndex) {
                    targetOfNums1 = nums1[nums1StartIndex + halfTargetIndex];
                }
                if (nums2StartIndex + halfTargetIndex < nums2EndIndex) {
                    targetOfNums2 = nums2[nums2StartIndex + halfTargetIndex];
                }

                int step = Math.max(halfTargetIndex, 1);
                if (targetOfNums1 <= targetOfNums2) {
                    nums1StartIndex += step;
                } else {
                    nums2StartIndex += step;
                }

                targetIndex_left -= step;
                targetIndex_right -= step;
            }

            System.out.println(String.format("Nums1 Start: %s, Nums1 End: %s, Nums2 Start: %s, Nums2 End: %s," +
                                                     " targetLeft: %s, targetRight: %s, Nums1[%s]:%s, " +
                                                     "Nums2[%s]:%s",
                                             nums1StartIndex,
                                             nums1EndIndex,
                                             nums2StartIndex,
                                             nums2EndIndex,
                                             targetIndex_left,
                                             targetIndex_right,
                                             nums1StartIndex, (nums1StartIndex < nums2EndIndex) ?
                                                              nums1[nums1StartIndex] : -888,
                                             nums2StartIndex, (nums2StartIndex < nums2EndIndex) ?
                                                     nums2[nums2StartIndex] : -999));
            if (nums1StartIndex < nums1EndIndex) {

                if (nums2StartIndex < nums2EndIndex) {
                    if (targetIndex_left == targetIndex_right) {
                        return Double.valueOf(Math.min(nums1[nums1StartIndex], nums2[nums2StartIndex]));
                    } else {
                        // find 2 smallest of two arrays.
                        int i = nums1StartIndex;
                        int j = nums2StartIndex;
                        int smallest;
                        if (nums1[i] <= nums2[j]) {
                            smallest = nums1[i];
                            i++;
                        } else {
                            smallest = nums2[j];
                            j ++;
                        }
                        int secondSmall;

                        if (i < nums1EndIndex) {
                            if (j < nums2EndIndex) {
                                secondSmall = Math.min(nums1[i], nums2[j]);
                            } else {
                                secondSmall = nums1[i];
                            }
                        } else {
                            secondSmall = nums2[j];
                        }

                        System.out.println(String.format("Smallest: %s, SecondSmall: %s", smallest, secondSmall));
                        return Double.valueOf(smallest + secondSmall) / 2;

                    }

                } else {
                    return Double.valueOf(nums1[nums1StartIndex] + nums1[nums1StartIndex + targetIndex_right])/2;
                }

            } else {
                return Double.valueOf((nums2[nums2StartIndex] + nums2[nums2StartIndex + targetIndex_right]))/2;
            }

        }

        private double findMedianSortedArray(int[] nums2) {
            if (nums2 == null || nums2.length == 0) {
                return 0.0;
            }

            int mid_left = (nums2.length -1) /2;
            int mid_right = nums2.length /2;

            return Double.valueOf(nums2[mid_left] + nums2[mid_right])/2;

        }
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String doubleToString(double input) {
        return new DecimalFormat("0.00000").format(input);
    }

    public static void main(String[] args) throws IOException {
        int[] nums1;
        int[] nums2;
        double ret;
        String out;

         nums1 = stringToIntegerArray("1");
         nums2 = stringToIntegerArray("1");

         ret = new Solution().findMedianSortedArrays(nums1, nums2);

         out = doubleToString(ret);

        System.out.print(out);

        nums1 = stringToIntegerArray("1,2,2");
        nums2 = stringToIntegerArray("1,2,3");

        ret = new Solution().findMedianSortedArrays(nums1, nums2);

        out = doubleToString(ret);

        System.out.print(out);

        nums1 = stringToIntegerArray("1,3");
        nums2 = stringToIntegerArray("2");

        ret = new Solution().findMedianSortedArrays(nums1, nums2);

        out = doubleToString(ret);

        System.out.print(out);
    }
}
