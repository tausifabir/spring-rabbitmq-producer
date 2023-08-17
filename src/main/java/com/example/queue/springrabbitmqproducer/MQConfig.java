package com.example.queue.springrabbitmqproducer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class MQConfig {

  public static final String MESSAGE_QUEUE = "message_queue";
  public static final String MESSAGE_EXCHANGE = "message_exchange";
  public static final String MESSAGE_ROUTING_KEY = "message_RoutingKey";

  @Bean
  public Queue queue(){
    return new Queue(MESSAGE_QUEUE);
  }

  @Bean
  public TopicExchange exchange(){
    return new TopicExchange(MESSAGE_EXCHANGE);
  }

  @Bean
  public Binding binding(Queue queue , TopicExchange topicExchange){
    return BindingBuilder
        .bind(queue)
        .to(topicExchange)
        .with(MESSAGE_ROUTING_KEY);
  }

  @Bean
  public Jackson2JsonMessageConverter messageConverter(){
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpTemplate template(ConnectionFactory connectionFactory){
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter());
    return rabbitTemplate;
  }
}
