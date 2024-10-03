package com.brhenqu.kvjava.model;

import java.util.HashSet;
import java.util.Set;

public class Node {
    public int id;
    public Set<Node> neighbors;
    public boolean hasData;

    public Node(int id) {
        this.id = id;
        this.neighbors = new HashSet<>();
        this.hasData = false;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }
}
