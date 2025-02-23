package alg.offer;

import java.util.*;

public class AlgTest {

    public static void main(String[] args) {

        int res1 = getMaxNumber(23214, new int[]{2, 4, 9});
        int res2 = getMaxNumber(2533, new int[]{1, 2, 9, 4});
        int res3 = getMaxNumber(2543, new int[]{1, 2, 5, 4});
        int res4 = getMaxNumber(2111, new int[]{1, 2, 9, 4});
        int res5 = getMaxNumber(5555, new int[]{5, 9});
        System.out.println(res1 + " " + res2 + " " + res3 + " " + res4 + " " + res5);
    }


    public static int calculateOneCount(int n) {
        return 0;
    }

    /**
     * lintcode编号669的Coin Change问题
     * @param A
     * @param M
     * @return
     */
    public static int coinChange(int[] A, int M) {
        // A = [2,5,7]
        // M = 27
        int[] f = new int[M + 1];
        int n = A.length;
        // 硬币的种类
        // 初始化, 0个硬币
        f[0] = 0;
        // f[1], f[2], ... , f[27] = Integer.MAX_VALUE
        for (int i = 1; i <= M; i++) {
            f[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= M; i++) {
            // 使用第j个硬币 A[j]
            // f[i] = min{f[i-A[0]]+1, ... , f[i-A[n-1]]+1}
            for (int j = 0; j < n; ++j) {
                // 如果通过放这个硬币能够达到重量i
                if (i >= A[j] && f[i - A[j]] != Integer.MAX_VALUE) {
                    // 获得i的重量的硬币数就可能是获得i-A[j]重量硬币数的方案+1
                    // 拿这个方案数量与原本的方案数打擂台，取最小值就行
                    f[i] = Math.min(f[i - A[j]] + 1, f[i]);
                }
            }
        }
        if (f[M] == Integer.MAX_VALUE) {
            return -1;
        }
        return f[M];
    }

    /**
     * 给定一个n=23214，以及一个数组A={2,4,9} ，用A中的元素拼接一个小于n的最大数
     * 字节跳动经典算法题
     * TODO 理解有点问题
     */

    public static int getMaxNumber(int n, int[] A) {
        String nStr = String.valueOf(n);
        int length = nStr.length();

        List<Integer> list = new ArrayList<>();
        // 对A进行排序
        Arrays.sort(A);

        if (A.length > length) {
            // A的长度大于n的长度，直接返回-1, 不存在小于n的值
            return -1;
        }
        if (A.length == length) {
            // A的长度等于n的长度
            for (int i = 0; i < length; i++) {
                int target = 0;
                for (int j = 0; j < A.length; j++) {
                    if (A[j] == nStr.charAt(i) - '0') {
                        target = A[j];
                        break;
                    }else if (A[j] < nStr.charAt(i) - '0') {
                        // 小
                        if (A[j] >= target) {
                            target = A[j];
                        }
                    }else {
                        // 大, 跳过
                        continue;
                    }
                }
                list.add(target);
            }

            StringBuilder sb = new StringBuilder();
            for (Integer integer : list) {
                sb.append(integer);
            }
            int result = Integer.parseInt(sb.toString());
            if (result == n) {
                // 有问题，需要回溯
                A = Arrays.stream(A)
                        .map(i -> -i)
                        .sorted()
                        .map(i -> -i)
                        .toArray();
                boolean hit = false;
                int index = -1;
                int val = -1;
                for (int i = list.size() - 1; i >= 0; i--) {
                    if (!hit) {
                        for (int j = 0; j < A.length; j++) {
                            if (list.get(i) > A[j]) {
                                index = i;
                                val = A[j];
                                hit = true;
                                break;
                            }
                        }
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    if (i == index) {
                        list.set(i, val);
                    }else if (i > index) {
                        list.set(i, A[0]);
                    }
                }

            }
        }

        if (A.length < length) {
            // A的长度小于n的长度 逆序排列
            int[] sortedArr = Arrays.stream(A)
                    .map(i -> -i)
                    .sorted()
                    .map(i -> -i)
                    .toArray();
            for (int i = 0 ; i <sortedArr.length; i++){
                list.add(sortedArr[0]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Integer integer : list) {
            sb.append(integer);
        }
        return Integer.parseInt(sb.toString());
    }
}
