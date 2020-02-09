package com.BfdDfs;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String data;
    List<Node> children = new ArrayList<>();

    public Node(String data) {
        this.data = data;
    }
}
