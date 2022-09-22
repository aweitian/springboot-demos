package com.example.rabbitmq;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqGrowthConfig {
    @Value("${spring.rabbitmq.queue-name.twowheel-paas.queue}")
    private String paasQueue;
    @Value("${spring.rabbitmq.queue-name.twowheel-paas.key}")
    private String paasKey;
    @Value("${spring.rabbitmq.queue-name.twowheel-paas.dead-queue}")
    private String paasDeadQueue;
    @Value("${spring.rabbitmq.queue-name.dead-letter.exchange.twowheel_paas}")
    private String deadLetterExchangePaas;

    @Bean("paasDirectExchange")
    DirectExchange PaasDirectExchange() {
        return new DirectExchange("twowheel.recharge.direct",true,false);
    }

    @Bean("paasDirectQueue")
    public Queue PaasDirectQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", deadLetterExchangePaas);
        return new Queue(paasQueue, true, false, false, args);
    }

    @Bean("deadLetterExchangePaas")
    public Exchange deadLetterExchangePaas() {
        return ExchangeBuilder.directExchange(deadLetterExchangePaas).durable(true).build();
    }

    @Bean("delayDirectExchange")
    public DirectExchange delayDirectExchange() {
        DirectExchange directExchange = new DirectExchange("twowheel.recharge.direct.delay");
        directExchange.setDelayed(true);
        return directExchange;
    }

    @Bean
    public Binding bindingPaasQueue(DirectExchange paasDirectExchange, @Qualifier("paasDirectQueue") Queue paasDirectQueue) {
        return BindingBuilder.bind(paasDirectQueue).to(paasDirectExchange).with(this.paasKey);
    }

    @Bean
    public Binding paasDeadLetterBinding() {
        return new Binding(paasDeadQueue, Binding.DestinationType.QUEUE, deadLetterExchangePaas, this.paasKey, null);
    }


    @Bean("paasDeadQueue")
    public Queue paasDeadQueue() {
        return new Queue(paasDeadQueue);
    }

}
