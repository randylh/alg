package alg.str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StrTest {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(" "));
        System.out.println(lengthOfLongestSubstring("au"));
        System.out.println(lengthOfLongestSubstring("dvdf"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    private static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        while (right < s.length()) {
            if (map.containsKey(s.charAt(right)) && map.get(s.charAt(right)) == 1) {
                max = Math.max(max, map.keySet().size());
                // 当前字符已经存在，left右移，同时移除left的字符
                map.remove(s.charAt(left));
                left++;
            }else {
                // 当前字符不存在，添加到map
                map.put(s.charAt(right), 1);
                // right右移
                right++;
            }
        }
        max = Math.max(max, map.keySet().size());

        return max;
    }
}
