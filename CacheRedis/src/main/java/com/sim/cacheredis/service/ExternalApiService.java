package com.sim.cacheredis.service;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {

    @SneakyThrows
    public String getUserName(String userId){
        Thread.sleep(500);

        if(userId.equals("A")){
            return "Adam";
        }
        if(userId.equals("B")){
            return "Bob";
        }

        return "fail";
    }


    @Cacheable(cacheNames = "userAgeCache", key = "#userId")
    @SneakyThrows
    public int getUserAge(String userId){
        Thread.sleep(500);

        if(userId.equals("A")){
            return 10;
        }
        if(userId.equals("B")){
            return 20;
        }

        return 0;
    }
}
