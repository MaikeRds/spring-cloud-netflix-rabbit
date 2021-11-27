package com.example.pagamento.mappers;

import org.modelmapper.ModelMapper;

import com.example.pagamento.dtos.ProdutoDTO;
import com.example.pagamento.entities.Produto;

public class ProdutoMapper {

	public static ProdutoDTO toDTO(Produto produto) {
		return new ModelMapper().map(produto, ProdutoDTO.class);
	}
	
	public static Produto toModel(ProdutoDTO produto) {
		return new ModelMapper().map(produto, Produto.class);
	}
}
