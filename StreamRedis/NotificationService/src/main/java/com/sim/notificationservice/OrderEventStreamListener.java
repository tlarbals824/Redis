package com.sim.paymentservice;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventStreamListener implements StreamListener<String, MapRecord<String, String, String>> {

    private final StringRedisTemplate stringRedisTemplate;

    int paymentProcessId=0;
    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        Map<String, String> entry = message.getValue();
        log.info("entry: {}",entry);

        String userId = entry.get("userId");
        String productId = entry.get("productId");
        String price = entry.get("price");

        String paymentIdStr = Integer.toString(paymentProcessId++);
        Map<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("userId", userId);
        fieldMap.put("productId", productId);
        fieldMap.put("price", price);
        fieldMap.put("paymentProcessId", paymentIdStr);

        stringRedisTemplate.opsForStream().add("payment-events", fieldMap);
        log.info("event: {}",fieldMap);
    }
}
