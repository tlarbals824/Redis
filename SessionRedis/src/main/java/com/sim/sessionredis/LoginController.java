package com.sim.sessionredis;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {

//    private final Map<String, String> sessionMap = new HashMap<>();
//    private final StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public String login(HttpSession httpSession, @RequestParam String name){
//        sessionMap.put(httpSession.getId(), name);
//        redisTemplate.opsForValue().set(httpSession.getId(), name);
        httpSession.setAttribute("name", name); //  spring.session.store-type: redis
        return "saved";
    }

    @GetMapping("/myname")
    public String login(HttpSession httpSession){
//        final String name = sessionMap.get(httpSession.getId());
//        final String name = redisTemplate.opsForValue().get(httpSession.getId());
        final String name = (String)httpSession.getAttribute("name"); //  spring.session.store-type: redis
        return name;
    }
}
