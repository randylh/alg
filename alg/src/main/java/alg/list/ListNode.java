package alg.list;

import java.util.Objects;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x;}

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
