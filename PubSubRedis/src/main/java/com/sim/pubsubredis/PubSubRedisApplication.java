package com.sim.pubsubredis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class PubSubRedisApplication implements CommandLineRunner {

    private final ChatService chatService;

    public static void main(String[] args) {
        SpringApplication.run(PubSubRedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application stated ...");

        chatService.enterChatRoom("chat1");
    }
}
