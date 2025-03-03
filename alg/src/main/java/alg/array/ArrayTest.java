package alg.array;

import java.util.*;

public class ArrayTest {

    public static void main(String[] args) {
//        int res = findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3});
//        System.out.println(res);
//
//        int res2 = findRepeatNumber2(new int[]{2, 3, 1, 0, 2, 5, 3});
//        System.out.println(res2);

//        List<Integer> res = spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
//        for (Integer i : res) {
//            System.out.print(i + " ");
//        }

//        int count = maxVowels("abciiidef", 3);
//        System.out.println("\n" + count);

//        int[] result = twoSum(new int[]{2, 7, 11, 15}, 9);
//        for (int i : result) {
//            System.out.print(i+ " ");
//        }
        // int[] nums = new int[]{1, 3, 2, 2, 4, 5, 1, 2};
        // System.out.println(removeEle(nums, 2));

        int[] nums1 = new int[]{-4,-1,0,3,10};
        int[] res = numSquare(nums1);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

    }

    /**
     * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，
     * 但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 下面的解法空间复杂度是O(n)，不是O(1)
     * 因此不能使用排序的方法，也不能使用额外的标记数组
     */
    static int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int hit = -1;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                hit = nums[i];
                break;
            } else {
                map.put(nums[i], 1);
            }
        }
        return hit;
    }
    /**
     * 最优解法：
     * 对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解。在调整过程中，如果第 i 位置上已经有一个值为 i 的元素，就可以知道 i 值重复。
     */
    static int findRepeatNumber2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[nums[i]] == nums[i]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
            swap(nums, i, nums[i]); // 防止死循环
        }
        return -1;
    }
    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    /**
     * 螺旋矩阵
     * 给定一个包含m x n个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     */
    static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (null == matrix || 0 == matrix.length || matrix[0].length == 0) {
            return res;
        }

        int left = 0;
        int right = matrix[0].length;
        int top = 0;
        int bottom = matrix.length;

        while(left < right && top < bottom) {
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]); // 从左到右
            }
            ++top;
            if (top >= bottom) {
                break;
            }

            for (int i = top; i < bottom; i++) {
                res.add(matrix[i][right-1]); // 从上到下
            }
            --right;
            if (left >= right) {
                break;
            }

            for (int i = right-1; i >= left; i--) {
                res.add(matrix[bottom-1][i]); // 从右到左
            }
            --bottom;
            if (top >= bottom) {
                break;
            }

            for (int i = bottom-1; i >= top; i--) {
                res.add(matrix[i][left]); // 从下到上
            }

            ++left;
        }
        return res;
    }
    /**
     * 在给定的一个字符串 S 中找到长度为 k 的子串，使得这个子串中的元音字母数量最多，并返回这个最大数量。
     * @param S
     * @param k
     * @return
     */
    static int maxVowels(String S, int k) {
        char[] s = S.toCharArray();
        // 返回的结果
        int ans = 0;
        int vowel = 0;

        for (int i = 0; i < s.length; i++) {
            // 1、进入窗口
            if (s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u') {
                vowel++;
            }
            if (i < k - 1) {
                // 窗口大小不足k,继续循环进入窗口，直到窗口大小为k
                continue;
            }
            // 2、更新答案
            ans = Math.max(ans, vowel);
            // 3、离开窗口
            char out = s[i-k+1];
            if (out == 'a' || out == 'e' || out == 'i' || out == 'o' || out == 'u') {
                vowel--;
            }
        }
        return ans;
    }
    /**
     * 解决“接雨水”问题。这个问题通常出现在算法竞赛或者面试题中，它描述了一组高度不同的柱子排列成一行，
     * 每根柱子宽度相同但高度不同。下雨后，这些柱子之间可以积聚一定量的水。目标是计算总共能积聚多少单位体积的水。
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            // 如果栈不为空，并且当前指向的高度大于栈顶所指向的高度，就一直循环
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.isEmpty()) { // 栈空就出去
                    break;
                }

                int distance = current - stack.peek() - 1; //两堵墙的距离
                // 高度取 当前墙和栈顶之间最小的那个
                int heightMin = Math.min(height[stack.peek()], height[current]);
                int square = distance * (heightMin - h);
                sum += square;
            }
            stack.push(current);
            current++;
        }
        return sum;
    }

    /**
     * TODO  两数之和  -  输入有序数组
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     * 时间复杂度  O(N)
     * 空间复杂度  O(1)
     */
    static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;


        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                return new int[]{left+1, right+1};
            }
            if (numbers[left] + numbers[right] < target) {
                left = left + 1;
            }else if (numbers[left] + numbers[right] > target) {
                right = right - 1;
            }
        }
        return new int[]{left+1, right+1};
    }

    /**
     * 三数之和
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * @param
     * @return
     */


    public int[][] merge(int[][] intervals) {
        // 按照数组的第一个元素升序排序  [[1,3],[2,6],[8,10],[15,18]]
        Arrays.sort(intervals, Comparator.comparingInt(p -> p[0]));

        int[][] res = new int[][]{};
        List<int[]> list = new ArrayList<>();
        int left = 0;
        int right = 0;

        for (int[] p: intervals) {
            int m = list.size();
            if (m > 0 && p[0] <= list.get(m-1)[1]){
                // 第二个元素的起点小于第一个的终点，可以合并，更新第一个元素的终点
                list.get(m-1)[1] = Math.max(p[1], list.get(m-1)[1]);
            }else {
                // 不相交，无法合并
                list.add(p);
            }
        }
        return list.toArray(new int[list.size()][]);
    }


    /**
     * 基本的二分查找
     * 查找目标值 [left, right]
     */
    static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 防止溢出
            if (target == nums[mid]) {
                return mid;
            }else if (target < nums[mid]) {
                right = mid - 1;
            }else if (target > nums[mid]) {
                left = mid + 1;
            }
        }
        return -1;
    }
    /**
     * 你提供的代码是用来解决一个经典的二分查找问题，即在一个有序数组中查找目标值的第一个和最后一个出现的位置。
     * 这个问题通常被称为“查找元素的范围”或“查找目标值的起始和结束索引”。
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        // nums = [5,7,7,8,8,10], target = 8     3,4
        // nums = [5,7,7,8,8,10], target = 6
        int start = lowerBound(nums, target);

        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1};
        }

        int end = lowerBound(nums, target + 1) - 1;
        return new int[]{start, end};

    }

    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;  // 闭区间 [left,right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新⻓度。 
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新⻓度后面的元素。
        示例 1:
        给定 nums = [3,2,2,3], val = 3,
        函数应该返回新的⻓度 2, 并且 nums 中的前两个元素均为 2。 你不需要考虑数组中超出新⻓度后面的元素。
        示例 2:
        给定 nums = [0,1,2,2,3,0,4,2], val = 2,
        函数应该返回新的⻓度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
        你不需要考虑数组中超出新⻓度后面的元素。
        思路比较简单：就是快慢指针
     */
        static int removeEle(int[] nums, int val) {
            int slow = 0;
            int fast = 0;
            for (; fast < nums.length; fast++) {
                if (nums[fast] != val) {
                    nums[slow++] = nums[fast];
                }
            }
            return slow;
        }

        /**
         * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排 序。
            示例 1:
            输入:nums = [-4,-1,0,3,10]
            输出:[0,1,9,16,100]
            解释:平方后，数组变为 [16,1,0,9,100]，排序后，数组变为 [0,1,9,16,100]
         */


        static int[] numSquare(int[] nums) {
            int[] resNums = new int[nums.length];
            int left = 0;
            int right = nums.length - 1;
            int i = 0;
            while (left <= right) {
                if (left == right) {
                    resNums[i++] = nums[left]; 
                    break;   
                }else {
                    if (nums[left] * nums[left] > nums[right] * nums[right]) {
                        resNums[i++] = nums[left] * nums[left];
                        left++;
                    }else {
                        resNums[i++] = nums[right] * nums[right];
                        right--;
                    }
                }
            }
            return resNums;
        }

}
