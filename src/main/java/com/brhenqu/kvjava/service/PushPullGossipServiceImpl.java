package com.brhenqu.kvjava.service;

import com.brhenqu.kvjava.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("pushPull")
public class PushPullGossipServiceImpl extends AbstractGossipService{

    private static final Logger logger = LoggerFactory.getLogger(PushPullGossipServiceImpl.class);

    // Inicializa os nós
    private final Node node1 = new Node(1);
    private final Node node2 = new Node(2);
    private final Node node3 = new Node(3);
    private final Node node4 = new Node(4);

    public PushPullGossipServiceImpl() {
        node1.addNeighbor(node2);
        node2.addNeighbor(node3);
        node3.addNeighbor(node4);
        node4.addNeighbor(node1);
        node1.hasData = true;  // Inicia com dados no node1
        logger.info("Initial data set on Node 1");
    }

    @Override
    protected Node getNode1() {
        return node1;
    }

    @Override
    protected Node getNode2() {
        return node2;
    }

    @Override
    protected Node getNode3() {
        return node3;
    }

    @Override
    protected Node getNode4() {
        return node4;
    }

    @Override
    public void runGossipRound() {
        pushPullGossip(node1);
        pushPullGossip(node2);
        pushPullGossip(node3);
        pushPullGossip(node4);
    }

    private void pushPullGossip(Node node) {
        Node randomNeighbor = getRandomNeighbor(node);
        if (randomNeighbor != null) {
            if (node.hasData && !randomNeighbor.hasData) {
                logger.info("Node {} pushing gossip to Node {}", node.id, randomNeighbor.id);
                randomNeighbor.hasData = true;
            } else if (!node.hasData && randomNeighbor.hasData) {
                logger.info("Node {} pulling gossip from Node {}", node.id, randomNeighbor.id);
                node.hasData = true;
            }
        }
    }
}
