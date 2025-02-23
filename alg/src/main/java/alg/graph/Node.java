package alg.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int id;
    List<Node> children;

    public Node(int id) {
        this.id = id;
        this.children = new ArrayList<>();
    }

}
