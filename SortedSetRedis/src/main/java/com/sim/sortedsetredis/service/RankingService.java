package com.sim.sortedsetredis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final StringRedisTemplate stringRedisTemplate;

    private static final String LEADER_BOARD_KEY = "leaderBoard";

    public boolean setUserScore(String userId, int score){
        final ZSetOperations<String, String> zSetOps = getzSetOperations();
        zSetOps.add(LEADER_BOARD_KEY, userId, score);

        return true;
    }

    private ZSetOperations<String, String> getzSetOperations() {
        final ZSetOperations<String, String> zSetOps = stringRedisTemplate.opsForZSet();
        return zSetOps;
    }

    public Long getUserRanking(String userId){
        ZSetOperations<String, String> zSetOps = getzSetOperations();
        Long rank = zSetOps.reverseRank(LEADER_BOARD_KEY, userId);

        return rank;
    }

    public List<String> getTopRank(int limit){
        ZSetOperations<String, String> zSetOps = getzSetOperations();
        Set<String> rangeSet = zSetOps.reverseRange(LEADER_BOARD_KEY, 0, limit - 1);
        return new ArrayList<>(rangeSet);
    }
}
