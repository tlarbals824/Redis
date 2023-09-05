package com.sim.orderservice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final StringRedisTemplate stringRedisTemplate;

    @GetMapping("/order")
    public String order(@RequestParam String userId, @RequestParam String productId, @RequestParam String price){
        Map<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("userId", userId);
        fieldMap.put("productId", productId);
        fieldMap.put("price", price);

        stringRedisTemplate.opsForStream().add("order-events",fieldMap);

        return "ok";
    }
}
