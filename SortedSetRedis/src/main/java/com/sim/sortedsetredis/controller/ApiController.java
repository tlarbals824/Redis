package com.sim.sortedsetredis.controller;

import com.sim.sortedsetredis.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final RankingService rankingService;

    @PostMapping("/score")
    public boolean setScore(@RequestParam String userId, @RequestParam int score){
        return rankingService.setUserScore(userId, score);
    }

    @GetMapping("/rank")
    public Long getScore(@RequestParam String userId){
        return rankingService.getUserRanking(userId);
    }

    @GetMapping("/toprank")
    public List<String> getTopRanks(){
        return rankingService.getTopRank(10);
    }
}
