package com.my.seckill.config;



import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRabbitmqConfig {

    //交换机
    @Bean("seckillexchange")
    public Exchange seckillexchange(){

        return ExchangeBuilder.directExchange("seckill_exchange").build();
    }

    //队列
    @Bean("seckillqueue")
    public Queue seckillqueue(){
        return QueueBuilder.durable("seckill_queue").build();
    }

    //队列和交换机绑定
    @Bean
    public Binding binding(@Qualifier("seckillqueue") Queue queue, @Qualifier("seckillexchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("seckill").noargs();
    }
}
