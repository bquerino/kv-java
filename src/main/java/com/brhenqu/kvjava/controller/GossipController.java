package com.brhenqu.kvjava.controller;

import com.brhenqu.kvjava.service.EpidemicGossipServiceImpl;
import com.brhenqu.kvjava.service.GossipService;
import com.brhenqu.kvjava.service.PushPullGossipServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gossip")
public class GossipController {

    private final GossipService epidemicGossipService;
    private final GossipService pushPullGossipService;

    public GossipController( @Qualifier("epidemic") GossipService epidemicGossipService,
                             @Qualifier("pushPull") GossipService pushPullGossipService) {
        this.epidemicGossipService = epidemicGossipService;
        this.pushPullGossipService = pushPullGossipService;
    }

    @GetMapping("/epidemic")
    public String startEpidemicGossip() {
        epidemicGossipService.runGossipRound();
        if (epidemicGossipService.isGossipComplete()) {
            return "Epidemic gossip propagation complete!";
        } else {
            return "Epidemic gossip still spreading...";
        }
    }

    @GetMapping("/push-pull")
    public String startPushPullGossip() {
        pushPullGossipService.runGossipRound();
        if (pushPullGossipService.isGossipComplete()) {
            return "Push-pull gossip propagation complete!";
        } else {
            return "Push-pull gossip still spreading...";
        }
    }
}
