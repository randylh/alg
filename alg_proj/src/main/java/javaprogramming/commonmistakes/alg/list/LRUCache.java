package javaprogramming.commonmistakes.alg.list;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    /**
     * 定义双向链表节点
     */
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 链表容量
     */
    private final int capacity;
    /**
     * 哨兵节点
     */
    private Node dummy = new Node(0, 0);

    private final Map<Integer, Node> keyToNodeMap = new HashMap<>();


    public LRUCache(int capacity) {
        this.capacity = capacity;
        // 哨兵节点的prev和next都指向自己
        dummy.prev = dummy.next = dummy;
    }

    public int get(int key) {
        Node node = getNode(key);
        if (null == node) {
            return -1;
        }
        return node.value;
    }

    public void put(int key, int value) {
        // 双向链表添加元素
        Node node = getNode(key);
        if (node != null) {
            // 已存在，更新值
            node.value = value;
            return;
        }
        // 不存在，直接添加到顶部
        Node newNode = new Node(key, value);
        keyToNodeMap.put(key, newNode);
        pushToFront(newNode);
        // TODO 还要考虑是否超过容量限制
        if (keyToNodeMap.size() > capacity) {
            // 删除最后一个节点
            Node last = dummy.prev;
            keyToNodeMap.remove(last.key);
            remove(last);
        }
    }

    private Node getNode(int key) {
        if (!keyToNodeMap.containsKey(key)) {
            return null;
        }
        Node node = keyToNodeMap.get(key);
        remove(node);
        pushToFront(node);
        return node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void pushToFront(Node node) {
        node.next = dummy.next;
        dummy.next.prev = node;
        dummy.next = node;
        node.prev = dummy;
    }
}
