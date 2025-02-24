package alg.list;

import java.util.ArrayList;
import java.util.List;

public class CustomListTest {

    public static void main(String[] args) {
        ListNode l1 = createLinkedList(new int[]{2, 4, 3, 6});
        ListNode l2 = createLinkedList(new int[]{5, 6, 4});
        ListNode res = addTwoNumbers(l1, l2);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
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
     * 两个非空链表表示的数字，从链表的头节点到尾节点依次代表数字的每一位。
     * 我们需要将这两个数字相加，并返回一个新的链表表示它们的和。
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = 0;
        ListNode dummyNode = new ListNode(0);
        ListNode currentNode = dummyNode;
        ListNode p = l1;
        ListNode q = l2;
        while (p != null || null != q) {
            int sum = 0;
            if (p == null) {
                sum = q.val + temp;
            }else if (q == null) {
                sum = p.val + temp;
            }else {
              sum = p.val + q.val + temp;
            }
            
            int a = sum / 10;
            int b = sum % 10;
            temp = a;  
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
            currentNode.next = new ListNode(b);
            currentNode = currentNode.next;
        }
        return dummyNode.next;
    }


}
