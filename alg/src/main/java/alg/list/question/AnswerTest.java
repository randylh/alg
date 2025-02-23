package alg.list.question;


import alg.list.ListNode;

public class AnswerTest {

    public static void main(String[] args) {
//        ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
//        System.out.println(hasCircle(head));
//
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node2;
//        System.out.println(hasCircle(node1));
//
//        ListNode node = findCircleNode(node1);
//        System.out.println(node.val);

//        ListNode list1 = createLinkedList(new int[]{1,4,3,2,5,2});
//        ListNode res = partition(list1, 3);
//        traverse(res);


//        ListNode list2 = createLinkedList(new int[]{1,2,3,4,5});
//        ListNode list3 = createLinkedList(new int[]{1,2,3});
//        ListNode list4 = createLinkedList(new int[]{1,6});
//        traverse(mergeKLists(new ListNode[]{list2, list3, list4}));

//        ListNode list = createLinkedList(new int[]{1,2,3,4,5});
//        ListNode res = getKthFromEnd(list, 2);
//        if (null != res) {
//            System.out.println(res.val);
//        } else {
//            System.out.println("null");
//        }
//
//        ListNode res1 = deleteKthFromEnd(list, 5);
//        traverse(res1);


//        ListNode head1 = createLinkedList(new int[]{1,2,9,4});
//        ListNode head2 = createLinkedList(new int[]{5,3,7,9,4});
//        ListNode res = getIntersectionNode(head1, head2);
//        if (null != res) {
//            System.out.println(res.val);
//        } else {
//            System.out.println("null");
//        }

//        ListNode head = createLinkedList(new int[]{1,2,3,4,5});
//        traverse(reverseList(head));
//        System.out.println("\n");
//        ListNode head2 = createLinkedList(new int[]{1,2,3,4,5});
//        traverse(reverseBetween(head2, 2, 4));

        ListNode head = createLinkedList(new int[]{1,2,3,4,5});
        traverse(reverseKGroup(head, 2));

        System.out.println("\n");
        traverse(middleNode(head));
    }

    static void traverse(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            System.out.print(p.val + "  ");
        }
    }

    static ListNode createLinkedList(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }

        ListNode head = new ListNode(arr[0]);
        // 初始化一个指示指针
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * 单链表的倒数第k个节点 https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     *
     * 常规思路：遍历两次，第一次获取链表长度，第二次获取倒数第k个节点
     * 如何值遍历一次列表获取倒数第k个节点？
     * 思路：使用两个指针，第一个指针先走k步，然后两个指针一起走，直到第一个指针到达链表尾部，此时第二个指针指向的节点即为倒数第k个节点
     *
     * 这个思路也可以用来解决 删除链表的倒数第k个节点 https://leetcode.cn/problems/shan-chu-lian-biao-de-dao-shu-di-kge-jie-dian-lcof/
     */
    static ListNode getKthFromEnd(ListNode head, int k) {

        ListNode p1 = head;
        ListNode p2 = head;
        int step = 0;
        while (null != p1) {
            if (step < k) {
                p1 = p1.next;
                step++;
            }else {
                p1 = p1.next;
                p2 = p2.next;
            }
        }

        return p2;
    }

    static ListNode deleteKthFromEnd(ListNode head, int k) {
        // 删除第k个节点，单链表没有前向指针，所以需要找出第k个节点前的，即倒数k+1个节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode target = getKthFromEnd(dummy, k + 1);
        // target为空，说明删除的是头节点
        target.next = target.next.next;
        return dummy.next;
    }

    /**
     * 21. 合并两个有序链表 https://leetcode.cn/problems/merge-two-sorted-lists/
     *
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        ListNode p1 = list1;
        ListNode p2 = list2;

        while (null != p1 && null != p2) {
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            }else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }

        if (null != p1) {
            p.next = p1;
        }

        if (null != p2) {
            p.next = p2;
        }

        return dummy.next;
    }

    /**
     * 拆分列表： https://leetcode.cn/problems/partition-list/description/
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     *
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     */
    static ListNode partition(ListNode head, int x) {

        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode p1 = dummy1;
        ListNode p2 = dummy2;

        ListNode p = head;
        while (null != p) {
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            }else {
                p2.next = p;
                p2 = p2.next;
            }
//            p = p.next;  TODO 针对链表有环的情况，要特殊处理，断开原始链表
            ListNode temp = p.next;
            p.next = null;
            p = temp;
        }

        p1.next = dummy2.next;

        return dummy1.next;
    }

    /**
     * 合并k个有序链表： https://leetcode.cn/problems/merge-k-sorted-lists/description/
     * 给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
     */
    static ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }

        ListNode p1 = lists[0];
        for (int i = 1; i < lists.length; i++) {
            p1 = mergeTwoLists(p1, lists[i]);
        }

        return p1;
    }

    /**
     * 141 环形链表  https://leetcode.cn/problems/linked-list-cycle/
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     *
     * 思路：采用快慢指针，快指针每次走两步，慢指针每次走一步，如果存在环，则快指针会追上慢指针
     */
    static boolean hasCircle(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    /**
     * 142. 环形链表 II https://leetcode.cn/problems/linked-list-cycle-ii/description/
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 不允许修改 链表。
     *
     * 分析：
     *
     */

    static ListNode findCircleNode(ListNode head) {
        if (null == head || null == head.next) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }

        if (null == fast || null == fast.next) {
            // 没有环
            return null;
        }

        // 找到了快慢指针相遇的位置
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    /**
     * 滑动窗口
     * 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     */
    static void minWindow(String s, String t) {
        if (null == s || null == t) {
            return;
        }
        
    }





    /**
     * 两个链表是否相交
     * @param head1
     * @param head2
     * @return
     */
    static ListNode getIntersectionNode(ListNode head1, ListNode head2) {

        ListNode p1 = head1;
        ListNode p2 = head2;

        while (p1 != p2) {
            if (p1 == null) {
                p1 = head2;
            }else {
                p1 = p1.next;
            }

            if (null == p2) {
                p2 = head1;
            }else {
                p2 = p2.next;
            }
        }
        return p1;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
        return addTwo(l1, l2, 0);
    }

    static ListNode addTwo(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
                // l1和l2都是空节点，判断是否有进位
            return carry != 0 ? new ListNode(carry) : null;
        }
        if (l1 == null) {
            l1 = l2;
            l2 = null;
        }

        int sum = carry + l1.val + (l2 != null ? l2.val : 0);
        l1.val = sum % 10; // 每个节点保存一个数位（直接修改原链表）
        l1.next = addTwo(l1.next, (l2 != null ? l2.next : null), sum / 10); // 进位
        return l1;
    }

    /**
     * 反转链表
     */
    static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode temp;
        ListNode curr = head;

        while (null != curr) {
            temp = curr.next;
            if (null == temp) {
                curr.next = prev;
                break;
            }
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return curr;

    }
    /**
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     */
    static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0); // 设置一个虚拟头结点
        dummy.next = head; // 将虚拟头结点与原链表连上

        ListNode p0 = dummy;
        for (int i = 0; i < left-1; i++) {
            p0 = p0.next;
        }
        ListNode prev = null;
        ListNode curr = p0.next;

        for (int j = left; j <= right; j++) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        p0.next.next = curr;
        p0.next = prev; // 将反转后的链表与原链表连上


        return dummy.next;
    }

    /**
     * k个一组反转链表
     * @param head
     * @param k
     * @return
     */
    private static ListNode reverseKGroup(ListNode head, int k) {
        if (null == head) {
            return null;
        }
        // dummy虚节点，保存原始头不变
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // p0指向是实际处理节点的前一个节点，这里需要更新
        ListNode p0 = dummy;
        ListNode prev = null;
        ListNode cur = p0.next;
        // 先遍历一次，获取链表长度，如果小于k，则不需要反转，直接返回
        int n = 0;
        while (head != null) {
            n++;
            head = head.next;
        }

        while (n >= k) {
            n -= k;
            int i = 0;
            while (i < k) {
                i++;
                ListNode temp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = temp;
            }
            ListNode nxt = p0.next;
            p0.next.next = cur;
            // p0指向反转后的头节点，即prev
            p0.next = prev;
            p0 = nxt;
        }

        // n <k  原样输出
        return dummy.next;
    }

    /**
     * 链表的中间节点
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            // 快指针每次走两步
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }


}
