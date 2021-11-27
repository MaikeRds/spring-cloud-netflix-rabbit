package com.example.pagamento.messages;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.pagamento.dtos.ProdutoDTO;
import com.example.pagamento.entities.Produto;
import com.example.pagamento.mappers.ProdutoMapper;
import com.example.pagamento.repositories.ProdutoRepository;

@Component
public class ProdutoReceivedMessage {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload ProdutoDTO produtoDTO) {
		produtoRepository.save(ProdutoMapper.toModel(produtoDTO));
	}
	
	
}
