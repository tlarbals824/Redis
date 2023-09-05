package com.sim.pubsubredis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ChatService implements MessageListener {

    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisTemplate<String, String> redisTemplate;

    public void enterChatRoom(String chatRoomName){
        redisMessageListenerContainer.addMessageListener(this, new ChannelTopic(chatRoomName));
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String line = in.nextLine();
            if(line.equals("q")){
                System.out.println("Quit ...");
                break;
            }

            redisTemplate.convertAndSend(chatRoomName, line);
        }
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Message: "+message.toString());
    }

}
