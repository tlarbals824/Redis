package com.sim.simpleredis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final StringRedisTemplate stringRedisTemplate;

    @PostMapping("/fruit")
    public String setFruit(@RequestParam String name){
        long start = System.currentTimeMillis();
        stringRedisTemplate.opsForValue().set("fruit", name);
        long end = System.currentTimeMillis();
        System.out.println("실행 시간 : " + (end - start) / 1000.0);
        return name;
    }

    @GetMapping("/fruit")
    public String getFruit(){
        long start = System.currentTimeMillis();
        final String fruit = stringRedisTemplate.opsForValue().get("fruit");
        long end = System.currentTimeMillis();
        System.out.println("실행 시간 : " + (end - start) / 1000.0);
        return fruit;
    }
}
