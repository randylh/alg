package alg.dynamic;

public class DynamicTest {

    public static void main(String[] args) {
        testCanJump();
    }

    static void testCanJump() {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(canJump(nums));
        System.out.println(canJump1(nums));
    }
    
    /**
     * https://leetcode.cn/problems/house-robber/description/
     * 打家劫舍
     *
     */

     /**
      * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
        * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
        dp[i] 表示位置i是否可达,初始的时候都是0,只有dp[0]=1
        2, 3, 1, 1, 4
      */
      static public boolean canJump(int[] nums) {

            int mx = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i > mx) {
                    // 无法到达i
                    return false;
                }
                mx = Math.max(mx, i + nums[i]); // 从i最右可以跳到i+nums[i]
            }
            return true;
      }

      static boolean canJump1(int[] nums) {
        int cover = 0;
        if (nums.length == 1) {
            return true;
        }
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, i+ nums[i]);
            if (cover >= nums.length - 1) {
                return true;
            }
        }
        return false;
      }
      
    
}
