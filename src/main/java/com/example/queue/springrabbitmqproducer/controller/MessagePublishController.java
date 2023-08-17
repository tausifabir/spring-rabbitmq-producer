package com.example.queue.springrabbitmqproducer.controller;

import com.example.queue.springrabbitmqproducer.CustomMessage;
import com.example.queue.springrabbitmqproducer.MQConfig;
import java.util.Date;
import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagePublishController {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @PostMapping("/publish")
  public String publishMessage(@RequestBody CustomMessage customMessage){
    customMessage.setMessageId(UUID.randomUUID().toString());
    customMessage.setMessageDate(new Date());
    rabbitTemplate
        .convertAndSend(MQConfig.MESSAGE_EXCHANGE, MQConfig.MESSAGE_ROUTING_KEY,customMessage);
    return "Message published!";
  }
}
