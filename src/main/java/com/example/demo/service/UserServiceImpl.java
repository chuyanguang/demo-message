package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @explain 消费者监听
 */
@Slf4j
@Service
public class UserServiceImpl {

    /**
     * 点对点
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "hello"))
    void receive01(String message) {
        log.info(message);
    }

    /**
     * 广播
     * @param message
     */
    @RabbitListener(bindings ={
            @QueueBinding(value = @Queue,
                    exchange =@Exchange(value = "hello", type = "fanout")
            )
    })
    void receive02(String message){
        log.info(message);
    }

}
