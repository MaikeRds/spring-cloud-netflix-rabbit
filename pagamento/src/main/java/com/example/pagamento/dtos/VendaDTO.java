package com.example.pagamento.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({ "id", "data", "valorTotal", "produtos" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper =  false)
public class VendaDTO extends RepresentationModel<VendaDTO> implements Serializable  {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("data")
	private Date data;

	@JsonProperty("valorTotal")
	private Double valorTotal;

	@JsonProperty("produtos")
	private List<ProdutoVendaDTO> produtos;		

}
