import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * [49] Group Anagrams
 *
 * https://leetcode-cn.com/problems/group-anagrams/description/
 *
 * algorithms
 * Medium (43.59%)
 * Total Accepted:    1.9K
 * Total Submissions: 4.1K
 * Testcase Example:  '["eat","tea","tan","ate","nat","bat"]'
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 
 * 示例:
 * 
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ⁠ ["ate","eat","tea"],
 * ⁠ ["nat","tan"],
 * ⁠ ["bat"]
 * ]
 * 
 * 说明：
 * 
 * 
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * 
 * 
 */
class GroupAnagrams049 {
    private Map<String, List<String>> stanardToGroupMap;
    public List<List<String>> groupAnagrams(String[] strs) {
        stanardToGroupMap = new HashMap<String, List<String>>();

        List<List<String>> result = new ArrayList<List<String>>();
        if (strs == null || strs.length < 1) {
            return result;
        }

        for (int i = 0; i < strs.length; i++) {
            String standard = convert2StandardStr(strs[i]);
            List<String> group = stanardToGroupMap.get(standard);
            if (group == null) {
                group = new ArrayList<String>();
                stanardToGroupMap.put(standard, group);
                result.add(group);
            }
            group.add(strs[i]);
        }

        return result;
    }


    private String convert2StandardStr(String str) {
        Map<String, Integer> charCount = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            String cur = String.valueOf(str.charAt(i));

            Integer cnt = charCount.get(cur);
            if (cnt == null) {
                cnt = 0;
            }
            cnt ++;
            charCount.put(cur, cnt);
        }

        String result = "";
        for (char ch = 'a'; ch <= 'z'; ch++) {
            String cur = String.valueOf(ch);
            Integer cnt = charCount.get(cur);

            if (cnt != null) {
                result = result + cur + ":" + cnt;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        GroupAnagrams049 solution = new GroupAnagrams049();
        List<List<String>> lists = solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});

        for (List<String> list : lists) {
            for (String str : list) {
                System.out.print(str + ",");
            }
            System.out.println("==========");
        }
    }
}
