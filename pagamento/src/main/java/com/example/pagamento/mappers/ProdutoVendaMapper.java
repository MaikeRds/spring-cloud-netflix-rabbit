package com.example.pagamento.mappers;

import org.modelmapper.ModelMapper;

import com.example.pagamento.dtos.ProdutoVendaDTO;
import com.example.pagamento.entities.ProdutoVenda;

public class ProdutoVendaMapper {

	public static ProdutoVendaDTO toDTO(ProdutoVenda produtoVenda) {
		return new ModelMapper().map(produtoVenda, ProdutoVendaDTO.class);
	}
	
	public static ProdutoVenda toModel(ProdutoVendaDTO produtoVenda) {
		return new ModelMapper().map(produtoVenda, ProdutoVenda.class);
	}
}
