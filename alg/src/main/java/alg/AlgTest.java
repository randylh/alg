package alg;
import java.util.*;

public class AlgTest {

    public static void main(String[] args) {

//        testTwoSum();

//        testThreeSum();

        testTriangle();

    }

    static void testTwoSum() {
        int[] nums = new int[]{3, 3};
        int target = 6;
        int[] res = twoSum_2(nums, target);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    static void testThreeSum() {
        //  -4 -1 -1 0 1  2
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        int[] nums1 = new int[]{0,1,1};
        int[] nums2 = new int[]{0,0,0};
        int[] nums3 = new int[]{0,0,0,0};
        int[] nums4 = new int[]{-2,0,0,2,2};
        List<List<Integer>> res = threeSum(nums4);
        for (List<Integer> list: res) {
            System.out.println(list);
        }
    }

    static void testTriangle() {
        int[] nums1 = new int[]{2,2,3,4};
        int res = triangleNumber(nums1);
        System.out.println(res);
    }

    /**
     * 这种解法针对的是数组有序
     * 这种情况下不需要额外的map，空间复杂度为O(0)
     * @param numbers
     * @param target
     * @return
     */
    static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left <= right) {
            if (numbers[left] + numbers[right] > target) {
                right--;
            }else if (numbers[left] + numbers[right] < target) {
                left++;
            }else {
                return new int[]{left, right};
            }
        }

        return new int[]{};
    }

    /**
     * 数组无序  （元素可能重复，有序或无序）
     * 思路：就需要一个map来存index和value
     * 存在问题：[3, 3]
     * 题目要求「不能使用两次相同的元素」，也就是两个数的下标必须不同。
     * @param nums
     * @param target
     * @return
     */
    static int[] twoSum_1(int[] nums, int target) {
        Map<Integer, Integer> resMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            resMap.put(nums[i], i);
        }
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] + nums[right] > target) {
                right--;
            }else if (nums[left] + nums[right] < target) {
                left++;
            }else {
                return new int[]{resMap.get(nums[left]), resMap.get(nums[right])};
            }
        }

        return new int[]{};
    }

    /**
     * 这个解法是 通过map来判断  先判断再添加，key是value，value是index
     * @param nums
     * @param target
     * @return
     */
    static int[] twoSum_2(int[] nums, int target) {
        Map<Integer, Integer> resMap = new HashMap<>(nums.length);
        for (int j = 0; ; j++) {
            int x = nums[j];
            // 判断target减x之后是否在map中
            if (resMap.containsKey(target - x)) {
                return new int[]{resMap.get(target - x), j};
            }
            resMap.put(x, j);
        }
    }

    /**
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
     * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
     * 这个是返回具体的值，不需要记录index，可以直接排序
     * nums[i] + nums[j] + nums[k] == 0 可以转换成求  nums[i] + nums[j] ==  -nums[k]  也即有序二元组找目标值
     * 注意：答案中不可以包含重复的三元组。
     */
    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int target = -nums[i];
            // target遍历的过程跳过与上次相同的元素
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            // 优化  i + 2 < nums.length 防止数组溢出，这里的优化也是利用了有序的特性
            if (i + 2 < nums.length && nums[i+1] + nums[i+2] > target) {
                break;
            }
            if ( nums[nums.length-1] + nums[nums.length-2] < target) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left <= right) {
                if (left == right) {
                    // 跳过指针相遇导致的重复
                    break;
                }
                if (nums[left] + nums[right] < target) {
                    left++;
                }else if (nums[left] + nums[right] > target) {
                    right--;
                }else {
                    List<Integer> threeArr = new ArrayList<>();
                    threeArr.add(-target);
                    threeArr.add(nums[left]);
                    threeArr.add(nums[right]);
                    res.add(threeArr);
                    // TODO  -2,0,0,2,2
                    // left加一之后跟之前的元素比较，相等，则继续加一
                    for (left++; left < right && nums[left] == nums[left - 1]; left++); // 跳过重复数字
                    // right减一之后判断是不是大于left，同时判断跟之前的元素是否相等，若相等，则跳过，再减一
                    for (right--; right > left && nums[right] == nums[right + 1]; right--); // 跳过重复数字
                }
            }
        }
        return res;
    }


    /**
     * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
     * a + b > c(两个小边相加大于第三边)  无序
     * 这里的思路核心是  固定最大边
     */
    static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 2; i < nums.length; i++) {
            int target = nums[i];
            int left = 0;
            int right = i - 1;

            while (left < right) {
                if (nums[left] + nums[right] > target) {
                    ans += right - left;
                    right--;
                }else {
                    left++;
                }
            }
        }
        return ans;
    }


}
















