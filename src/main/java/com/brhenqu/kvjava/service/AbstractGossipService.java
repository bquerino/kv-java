package com.brhenqu.kvjava.service;

import com.brhenqu.kvjava.model.Node;
import java.util.Random;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractGossipService implements GossipService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractGossipService.class);

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
}
