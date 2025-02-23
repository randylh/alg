package alg.tree;

import java.util.*;

public class TreeTest {

    private static int ans;
    public static void main(String[] args) {
//        TreeNode root = buildTree();
//        List<Integer> list = levelOrder(root);
//        for (Integer integer : list) {
//            System.out.print(integer + "");
//        }
//
//        System.out.println("\ndepth=" + maxDepth(root));

        permute(new int[]{1, 2});

    }

    private static int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 平衡二叉树的高度
     * @param root
     * @return
     */
    private static int getHeight(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftH = getHeight(root.left);
        if (-1 == leftH) {
            return -1;
        }
        int rightH = getHeight(root.right);
        if (-1 == rightH || Math.abs(leftH - rightH) > 1) {
            return -1;
        }
        return Math.max(leftH, rightH) + 1;
    }

    private static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }

    private static int dfs(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftH = dfs(root.left) + 1;
        int rightH = dfs(root.right) + 1;

        ans = Math.max(ans, leftH + rightH);
        return Math.max(leftH, rightH);
    }

    private static int dfsNode(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftH = dfs(root.left) + 1;
        int rightH = dfs(root.right) + 1;
        if (null != root.left && root.val != root.left.val) {
            leftH = 0;
        }

        if (null != root.right && root.val != root.right.val) {
            rightH = 0;
        }
        ans = Math.max(ans, leftH + rightH);
        return Math.max(leftH, rightH);
    }

    /**
     * 求路径和
     * @param root
     * @return
     */
    private static int dfsNode2(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftSum = dfs(root.left);
        int rightSum = dfs(root.right);

        ans = Math.max(ans, leftSum + rightSum + root.val);

        return Math.max(Math.max(leftSum, rightSum) + root.val, 0);
    }





    public static TreeNode buildTree() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(2);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;


        // 插入节点
//        TreeNode p = new TreeNode(10);
//        treeNode1.left = p;
//        p.left = treeNode2;

        // 删除p节点
//        treeNode1.left = treeNode2;

        return treeNode1;
    }

        // TODO 完美二叉树 满二叉树

        // TODO 完全二叉树

        // TODO 完满二叉树


        // TODO 平衡二叉树

        public static List<Integer> levelOrder(TreeNode root) {

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            List<Integer> list = new LinkedList<>();
            while (!queue.isEmpty()) {
                // 队列出队
                TreeNode node = queue.poll();
                // 保存节点的值
                list.add(node.val);
                // 左子节点入队
                if (node.left != null) {
                    queue.add(node.left);
                }
                // 右子节点入队
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            return list;
        }

        // 深度优先搜索就是绕着二叉树外围走一圈  前序遍历
        public static void preOrder(TreeNode root, List<Integer> list) {
           if (null == root) {
               return;
           }
           list.add(root.val);
            preOrder(root.left, list);
            preOrder(root.right, list);
        }

    /**
     * 给定一棵二叉树，搜索并记录所有值为 7 的节点，请返回节点列表。
     */
    public static List<Integer> findByPreOrder(TreeNode root, int val, List<Integer> res) {
        if (null == root) {
            return null;
        }

        if (root.val == val) {
            res.add(root.val);
        }

        findByPreOrder(root.left, val, res);
        findByPreOrder(root.right, val, res);
        return res;
    }

    /**
     * 判断当前状态是否为解
     * @param state
     * @return
     */
    static boolean isSolution(List<TreeNode> state) {
        return !state.isEmpty() && state.get(state.size() - 1).val == 7;
    }

    /**
     * 记录解
     * @param state
     * @param res
     */
    static void recordSolution(List<TreeNode> state, List<List<TreeNode>> res) {
        res.add(new ArrayList<>(state));
    }

    /**
     * 判断在当前状态下，该选择是否合法
     * @param state
     * @param choice
     * @return
     */
    static boolean isValid(List<TreeNode> state, TreeNode choice) {
        return null != choice && choice.val != 3;
    }

    /**
     * 更新状态
     * @param state
     * @param choice
     */
    static void makeChoice(List<TreeNode> state, TreeNode choice) {
        state.add(choice);
    }

    static void unMakeChoice(List<TreeNode> state, TreeNode choice) {
        state.remove(state.size() - 1);
    }

    static void backtrack(List<TreeNode> state, List<TreeNode> choices, List<List<TreeNode>> res) {
        // 检查是否为解
        if (isSolution(state)) {
            // 记录解
            recordSolution(state, res);
            return;
        }

        for (TreeNode choice : choices) {
            // 剪枝：检查选择是否合法
            if (isValid(state, choice)) {
                // 尝试：做出选择，更新状态
                makeChoice(state, choice);
                // 进行下一轮选择
                backtrack(state, Arrays.asList(choice.left, choice.right), res);
                // // 回退：撤销选择，恢复到之前的状态
                unMakeChoice(state, choice);
            }
        }
    }

    /**
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackAll(new ArrayList<>(), nums, new boolean[nums.length], res);
        return res;
    }

    static void backtrackAll(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        // choices是不重复的数组，比如 [1, 2, 3], state是当前选择的状态和路径，包括选择的元素，比如 [1, 2], selected记录当前节点是否被选择，res是最终的全排列所有的结果
        if (state.size() == choices.length) {
            // 当前选择的状态和路径长度等于数组长度，说明已经选择了所有元素，将当前状态和路径加入结果集
            res.add(new ArrayList<>(state));
            return;
        }

        // 否则就一直循环遍历
        for (int i = 0; i < choices.length; i++) {
            if (!selected[i]) { // 如果当前节点未被选择，设置为选择态
                selected[i] = true;
                // 将当前节点加入到state中，记录下来并更新
                state.add(choices[i]);
                // 接下来继续选择下一个节点
                backtrackAll(state, choices, selected, res);
                selected[i] = false;
                state.remove(state.size() - 1); // 回溯，撤销选择，恢复到之前的状态
            }
        }
            // 可以省略掉selected
//        for (int i = 0; i < choices.length; i++) {
//            if (!state.contains(choices[i])) { // 如果当前节点未被选择，设置为选择态
//                // 将当前节点加入到state中，记录下来并更新
//                state.add(choices[i]);
//                // 接下来继续选择下一个节点
//                backtrackAll(state, choices, selected, res);
//                state.remove(state.size() - 1); // 回溯，撤销选择，恢复到之前的状态
//            }
//        }

    }

    /**
     * 输入的数组choices有可能由于重复元素
     * @param state
     * @param choices
     * @param selected
     * @param res
     */
    static void backtrackAll2(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        // choices是不重复的数组，比如 [1, 2, 3], state是当前选择的状态和路径，包括选择的元素，比如 [1, 2], selected记录当前节点是否被选择，res是最终的全排列所有的结果
        if (state.size() == choices.length) {
            // 当前选择的状态和路径长度等于数组长度，说明已经选择了所有元素，将当前状态和路径加入结果集
            res.add(new ArrayList<>(state));
            return;
        }

        // 否则就一直循环遍历

        // 增加一个重复集合
        Set<Integer> duplicateSet = new HashSet<>();
        for (int i = 0; i < choices.length; i++) {
            if (!selected[i] && !duplicateSet.contains(choices[i])) { // 如果当前节点未被选择，设置为选择态
                selected[i] = true;
                // 添加到重复集合中
                duplicateSet.add(choices[i]);
                // 将当前节点加入到state中，记录下来并更新
                state.add(choices[i]);
                // 接下来继续选择下一个节点
                backtrackAll2(state, choices, selected, res);
                selected[i] = false;
                state.remove(state.size() - 1); // 回溯，撤销选择，恢复到之前的状态
            }
        }

    }

 




}
