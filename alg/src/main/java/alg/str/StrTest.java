package alg.str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StrTest {

    public static void main(String[] args) {
        // System.out.println(lengthOfLongestSubstring(" "));
        // System.out.println(lengthOfLongestSubstring("au"));
        // System.out.println(lengthOfLongestSubstring("dvdf"));
        // System.out.println(lengthOfLongestSubstring("abcabcbb"));

        reverseStr("asfasf");
    }
    /**
     * 最长子串
     */
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
    /**
     * 反转字符串
     * @param str
     * @return
     */
    static String reverseStr(String str) {
        if (null == str) {
            return null;
        }
        char[] strArr = str.toCharArray();
        int left = 0;
        int right = strArr.length - 1;
        while (left < right) {
            char temp = strArr[left];
            strArr[left] = strArr[right];
            strArr[right] = temp;
            left++;
            right--;
        }

        for (int i = 0; i < strArr.length; i++) {
            System.out.print(strArr[i] + " ");
        }
        
        return strArr.toString();
    }

    /**
     * 在给定的字符串中每隔 2k 个字符的前 k 个字符进行反转，其余字符保持原样。541
     * 剩余字符小于k个，将剩余字符全部翻转
     * 剩余字符小于2k，但是不小于k，则将前k个翻转，剩余不变
     */
    static String reverseStr2(String s, int k) {
        if (null == s || s.length() == 0) {
            return null;
        }
        char[] strArr = s.toCharArray();
        if (strArr.length < k) {
            swap(strArr, 0, strArr.length-1);
        }else if (strArr.length < 2 * k) {
            
        }
    }

    static void swap(char[] strArr, int start, int end) {

    }
}
