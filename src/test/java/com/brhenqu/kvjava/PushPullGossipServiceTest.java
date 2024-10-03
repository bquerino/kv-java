package com.brhenqu.kvjava;

import com.brhenqu.kvjava.service.PushPullGossipServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class PushPullGossipServiceTest {

    @InjectMocks
    private PushPullGossipServiceImpl pushPullGossipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void testInitialState() {
        // Verifica o estado inicial: apenas node1 tem os dados
        assertAll("Initial state of nodes",
                () -> assertTrue(pushPullGossipService.hasData(1)), // node1 has data
                () -> assertFalse(pushPullGossipService.hasData(2)), // node2 doesn't have data
                () -> assertFalse(pushPullGossipService.hasData(3)), // node3 doesn't have data
                () -> assertFalse(pushPullGossipService.hasData(4))  // node4 doesn't have data
        );
    }

    @Test
    void testOneRoundOfPushPullGossip() {
        // Executa uma rodada de gossip push-pull
        pushPullGossipService.runGossipRound();

        // Verifica que pelo menos um nó a mais tem os dados
        assertAll("After one round of push-pull gossip",
                () -> assertTrue(pushPullGossipService.hasData(1)), // node1 já tinha os dados
                () -> assertTrue(
                        pushPullGossipService.hasData(2) ||
                                pushPullGossipService.hasData(3) ||
                                pushPullGossipService.hasData(4) // Um dos outros nós deve ter os dados agora
                )
        );
    }

    @Test
    void testFullPushPullPropagation() {
        // Executa várias rodadas até que todos os nós tenham os dados
        int maxRounds = 10;
        for (int i = 0; i < maxRounds; i++) {
            pushPullGossipService.runGossipRound();
            if (pushPullGossipService.isGossipComplete()) {
                break;
            }
        }

        // Verifica se todos os nós têm os dados
        assertTrue(pushPullGossipService.isGossipComplete());
    }
}
