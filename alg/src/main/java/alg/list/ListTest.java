package alg.list;

public class ListTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
        // 遍历单列表
        traverse(head);

        // 单链表头部插入元素
        head = insertAtHead(head, 0);
        traverse(head);

        // 单链表尾部插入元素
        head = insertAtTail(head, 6);
        traverse(head);

        // 单链表中间插入元素
        head = insertAtMiddle(head, 7, 2);
        traverse(head);

        // 单列表中间删除一个节点
        head = deleteNode(head, 7);
        traverse(head);

        // 单列表删除尾部元素
        head = deleteTailNode(head);
    }

    static void traverse(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            System.out.println(p.val);
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

    static ListNode insertAtHead(ListNode head, int target) {

        if (null == head) {
            return null;
        }

        ListNode newHead = new ListNode(target);
        newHead.next = head;
        head = newHead;

        return head;
    }

    static ListNode insertAtTail(ListNode head, int target) {
        if (null == head) {
            return null;
        }

        ListNode tail = new ListNode(target);
        ListNode curr = head;
        // 或者用while
//        for (ListNode p = head; p != null; p = p.next) {
//            curr = p;
//        }

        while(curr.next != null) {
            curr = curr.next;
        }

        curr.next = tail;
        return head;
    }

    /**
     * 第index节点后插入target新节点
     * @param head
     * @param target
     * @param index
     * @return
     */
    static ListNode insertAtMiddle(ListNode head, int target, int index) {
        if (null == head) {
            return null;
        }

        int tempIndex = 0;
        ListNode curr = head;
        while (tempIndex < index) {
            curr = curr.next;
            tempIndex++;
        }
        ListNode newNode = new ListNode(target);
        newNode.next = curr.next;
        curr.next = newNode;

        return head;

    }

    static ListNode deleteNode(ListNode head, int target) {
        if (null == head) {
            return null;
        }
        // 需要两个指针，一个是前驱节点，一个是当前节点
        ListNode pre = null;
        ListNode curr = head;
        // TODO 可以简化  curr.next = curr.next.next
        while (curr != null && curr.val != target) {
            pre = curr;
            curr = curr.next;
        }

        // 命中
        if (null != curr) {
            if (null == pre) {
                // 只有一个节点且命中
                return null;
            }
            pre.next = curr.next;
        }

        return head;
    }

    static ListNode deleteTailNode(ListNode head) {
        if (null == head) {
            return null;
        }

        ListNode curr = head;
        // 指针慢一步循环
        while (curr.next.next != null) {
            curr = curr.next;
        }

        curr.next = null;
        return head;

    }






}
