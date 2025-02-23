package javaprogramming.commonmistakes.alg;

import java.util.ArrayList;
import java.util.List;

public class TwoNumSum {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 初始化
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int temp = 0;
        while (null != l1 || null != l2) {
            int x = null == l1 ? 0 : l1.val;
            int y = null == l2 ? 0 : l2.val;
            int sumVal = x + y + temp;
            temp = sumVal / 10;
            sumVal = sumVal % 10;
            cur.next = new ListNode(sumVal);

            cur = cur.next;
            if (null != l1) {
                l1 = l1.next;
            }
            if (null != l2) {
                l2 = l2.next;
            }
        }

        // 最后一位求和进位是1
        if (temp == 1) {
            cur.next = new ListNode(temp);
        }
        return pre.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 2;
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode();
        l2.val = 5;
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode res = addTwoNumbers(l1, l2);
        while (null != res) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
