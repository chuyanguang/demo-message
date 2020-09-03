package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 生产者测试
 */
@SpringBootTest
class DemoMessageApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 点对点模式
     */
    @Test
    void test01() {
        rabbitTemplate.convertAndSend("hello", "hello rabbitmq");
    }

}
