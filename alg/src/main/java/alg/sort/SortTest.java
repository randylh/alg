package alg.sort;

public class SortTest {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2, 5, 4};
//        selectSort(nums);
//        bubbleSort(nums);
        insertSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }

    }

    /**
     * 选择排序
     */
    static void selectSort(int[] nums) {
        int length = nums.length;

        for (int i = 0; i <length; i++) {
            int k = i;
            for (int j = i + 1; j < length; j++) {
                if (nums[j] < nums[k]) {
                    k = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }
    }

    /**
     * 冒泡排序
     */
    static void bubbleSort(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j+1]) {
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }


        }
    }

    /**
     * 插入排序
     */
    static void insertSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int base = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] > base) {
                    // 元素后移
                    for (int k = i; k > j; k--) {
                        nums[k] = nums[k-1];
                    }
                    nums[j] = base;
                }
            }
        }
    }
}
