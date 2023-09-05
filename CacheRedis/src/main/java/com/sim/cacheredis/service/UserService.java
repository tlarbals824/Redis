package com.sim.cacheredis.service;

import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.sim.cacheredis.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiService externalApiService;

    private final StringRedisTemplate redisTemplate;

    public UserProfile getUserProfile(String userId){
        String userName;
        final ValueOperations<String, String> ops = redisTemplate.opsForValue();
        final String cachedName = ops.get("nameKey:" + userId);
        if(StringUtils.hasText(cachedName)){
            userName = cachedName;
        }else{
            userName = externalApiService.getUserName(userId);
            ops.set("nameKey:"+userId, userName);
        }

        final int userAge = externalApiService.getUserAge(userId);
        return new UserProfile(userName, userAge);
    }
}
