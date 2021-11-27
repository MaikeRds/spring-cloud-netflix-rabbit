package com.example.crud.messages;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.crud.dtos.ProdutoDTO;

@Component
public class ProdutoSendMessage {
	
	@Value("${crud.rabbitmq.exchange}")
	String exchange;
	
	@Value("${crud.rabbitmq.routingkey}")
	String routingkey;
	
	@Autowired
	public RabbitTemplate rabbitTemplate;
	
	public void sendMessage(ProdutoDTO produto) {
		rabbitTemplate.convertAndSend(exchange, routingkey, produto);
	}
	
	
}
