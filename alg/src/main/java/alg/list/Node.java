package alg.list;

public class Node<E> {
    E data;
    Node<E> next;
    Node<E> pre;

    public Node(E data) {
        this.data = data;
    }
}
