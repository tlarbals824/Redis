package com.sim.notificationservice;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventStreamListener implements StreamListener<String, MapRecord<String, String, String>> {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        Map<String, String> entry = message.getValue();
        log.info("payment entry: {}",entry);

        String userId = entry.get("userId");
        String paymentProcessId = entry.get("paymentProcessId");

        log.info("sns 발송, userId: {}, paymentProcessId: {}",userId, paymentProcessId);
    }
}
