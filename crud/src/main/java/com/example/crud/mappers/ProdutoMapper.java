package com.example.crud.mappers;

import org.modelmapper.ModelMapper;

import com.example.crud.dtos.ProdutoDTO;
import com.example.crud.entities.Produto;

public class ProdutoMapper {
	
	public static ProdutoDTO toDTO(Produto produto) {
		return new ModelMapper().map(produto, ProdutoDTO.class);
	}
	
	public static Produto toModel(ProdutoDTO produto) {
		return new ModelMapper().map(produto, Produto.class);
	}
}
