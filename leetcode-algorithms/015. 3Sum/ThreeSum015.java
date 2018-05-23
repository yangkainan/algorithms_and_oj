import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author yankaina on 23/5/2018.
 */
public class ThreeSum015 {

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();

        if (nums.length < 3) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        System.out.print('[');
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] +",");

        }
        System.out.println(']');

        if (nums[0] > 0 || nums[nums.length -1] < 0) {
            return Collections.emptyList();
        }

        for(int i = 0 ; i < nums.length; i++) {
            int a = nums[i];
            if (a > 0) {
                break;
            }
            for (int j = i + 1; j < nums.length; j++) {
                int b = nums[j];
                if (a + b > 0) {
                    break;
                }

                for(int k = nums.length -1; k > j; k --) {
                    int c = nums [k];

                    if (a + b + c < 0) {
                        break;
                    }

                    if ( a + b + c == 0)  {
                        List<Integer> list = Arrays.asList(a, b, c);
                        if (!isContain(result, list)) {
                            result.add(list);
                        }
                    }


                }
            }
        }

        return new ArrayList<>(result);
    }

    private boolean isContain(Set<List<Integer>> container, List<Integer> item) {
        if (container.contains(item)) {
            return true;
        }

        Iterator<List<Integer>> iterator = container.iterator();
        while(iterator.hasNext()) {
            List<Integer> element = iterator.next();

            if (element.containsAll(item) && item.containsAll(element)) {
                return true;
            }

        }

        return false;

    }

    public static void main(String[] args) {
        ThreeSum015 solution = new ThreeSum015();

        int[] input =new int[] {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> expectedOutput = new ArrayList<>();



        expectedOutput.add(Arrays.asList(-1, -1, 2));
        expectedOutput.add(Arrays.asList(-1, 0, 1));

        List<List<Integer>> list = solution.threeSum(input);

        System.out.println(list);


    }
}
