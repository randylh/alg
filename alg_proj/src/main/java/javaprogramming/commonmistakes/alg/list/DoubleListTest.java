package javaprogramming.commonmistakes.alg.list;

public class DoubleListTest {

    public static void main(String[] args) {
        // 双向链表的遍历
        DoubleListNode head = createDoubleLinkedList(new int[]{1,2,3,4,5});
//        traverse(head);

        //双向列表头部插入节点
        head = insertAtHead(head, 0);
        traverse(head);
    }

    static DoubleListNode createDoubleLinkedList(int[] arr) {

        if (null == arr || arr.length == 0) {
            return null;
        }
        DoubleListNode head = new DoubleListNode(arr[0]);
        DoubleListNode cur = head;
        for (int i = 1; i < arr.length; i++) {
           DoubleListNode node = new DoubleListNode(arr[i]);
           cur.next = node;
           node.pre = cur;
           cur = cur.next;
        }
        return head;
    }

    static void traverse(DoubleListNode head) {
        // 头节点或者尾节点
        DoubleListNode p = head;
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }

    static DoubleListNode insertAtHead(DoubleListNode head, int target) {
        if (null == head) {
            return null;
        }
        DoubleListNode newNode = new DoubleListNode(target);

        newNode.next = head;
        head.pre = newNode;
        head = newNode;

        return head;

    }

}
