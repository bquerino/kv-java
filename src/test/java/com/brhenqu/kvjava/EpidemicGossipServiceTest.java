package com.brhenqu.kvjava;

import com.brhenqu.kvjava.service.EpidemicGossipServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class EpidemicGossipServiceTest {

    @InjectMocks
    private EpidemicGossipServiceImpl epidemicGossipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void testInitialState() {
        // Verifica o estado inicial: apenas node1 tem os dados
        assertAll("Initial state of nodes",
                () -> assertTrue(epidemicGossipService.hasData(1)), // node1 has data
                () -> assertFalse(epidemicGossipService.hasData(2)), // node2 doesn't have data
                () -> assertFalse(epidemicGossipService.hasData(3)), // node3 doesn't have data
                () -> assertFalse(epidemicGossipService.hasData(4))  // node4 doesn't have data
        );
    }


    @Test
    void testOneRoundOfGossip() {
        // Executa uma rodada de gossip
        epidemicGossipService.runGossipRound();

        // Verifica que pelo menos um nó a mais tem os dados
        assertAll("After one round of gossip",
                () -> assertTrue(epidemicGossipService.hasData(1)), // node1 já tinha os dados
                () -> assertTrue(
                        epidemicGossipService.hasData(2) ||
                                epidemicGossipService.hasData(3) ||
                                epidemicGossipService.hasData(4) // Um dos outros nós deve ter os dados agora
                )
        );
    }

    @Test
    void testFullPropagation() {
        // Executa várias rodadas até que todos os nós tenham os dados
        int maxRounds = 10;
        for (int i = 0; i < maxRounds; i++) {
            epidemicGossipService.runGossipRound();
            if (epidemicGossipService.isGossipComplete()) {
                break;
            }
        }

        // Verifica se todos os nós têm os dados
        assertTrue(epidemicGossipService.isGossipComplete());
    }
}