package javaprogramming.commonmistakes.alg.graph;

import com.google.common.collect.Lists;

import java.util.List;

public class Node {
    int id;
    List<Node> children;

    public Node(int id) {
        this.id = id;
        this.children = Lists.newArrayList();
    }

}
