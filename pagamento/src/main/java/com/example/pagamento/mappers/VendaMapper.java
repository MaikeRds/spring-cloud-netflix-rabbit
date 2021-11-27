package com.example.pagamento.mappers;

import java.io.Serializable;

import org.modelmapper.ModelMapper;

import com.example.pagamento.dtos.VendaDTO;
import com.example.pagamento.entities.Venda;

public class VendaMapper implements Serializable {
	private static final long serialVersionUID = -5599847776372238825L;

	public static VendaDTO toDTO(Venda venda) {
		return new ModelMapper().map(venda, VendaDTO.class);
	}
	
	public static Venda toModel(VendaDTO venda) {
		return new ModelMapper().map(venda, Venda.class);
	}
}
