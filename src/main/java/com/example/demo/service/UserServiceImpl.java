package com.example.demo.service;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @explain 消费者监听
 */
@Slf4j
@Service
public class UserServiceImpl {

    /**
     * 点对点
     *
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "hello"))
    void receive01(String message) {
        log.info(message);
    }

    /**
     * 广播
     *
     * @param message
     */
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = "world", durable = "true"),
                    exchange = @Exchange(value = "hello", type = "topic"),
                    key = {"user.*"}
            )
    })
    void receive02(Message message, Channel channel) {
        log.info(new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
