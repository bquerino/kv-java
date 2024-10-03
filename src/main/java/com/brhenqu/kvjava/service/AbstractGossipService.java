package com.brhenqu.kvjava.service;

import com.brhenqu.kvjava.model.Node;
import java.util.Random;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractGossipService implements GossipService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractGossipService.class);

    protected Node node1 = new Node(1);
    protected Node node2 = new Node(2);
    protected Node node3 = new Node(3);
    protected Node node4 = new Node(4);

    protected Node getRandomNeighbor(Node currentNode) {
        Set<Node> neighbors = currentNode.neighbors;
        int size = neighbors.size();
        if (size == 0) return null;

        int item = new Random().nextInt(size);
        int i = 0;
        for (Node node : neighbors) {
            if (i == item) {
                logger.info("Node {} selected neighbor Node {} for gossip propagation", currentNode.id, node.id);
                return node;
            }
            i++;
        }
        return null;
    }

    public boolean hasData(int nodeId) {
        return switch (nodeId) {
            case 1 -> getNode1().hasData;
            case 2 -> getNode2().hasData;
            case 3 -> getNode3().hasData;
            case 4 -> getNode4().hasData;
            default -> throw new IllegalArgumentException("Invalid node id");
        };
    }

    // Verifica se todos os nós têm dados
    public boolean isGossipComplete() {
        boolean complete = getNode1().hasData && getNode2().hasData && getNode3().hasData && getNode4().hasData;
        if (complete) {
            logger.info("Gossip propagation complete among all nodes.");
        }
        return complete;
    }

    protected abstract Node getNode1();
    protected abstract Node getNode2();
    protected abstract Node getNode3();
    protected abstract Node getNode4();
}
