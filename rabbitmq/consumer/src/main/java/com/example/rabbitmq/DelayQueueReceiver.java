package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DelayQueueReceiver {
    @RabbitListener(queues = RabbitMqDelayConfig.DELAYED_QUEUE_NAME)
    public void process(Message message, Channel channel) throws IOException {

        System.out.println("DelayQueueReceiver消费者收到消息  : " + message.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
