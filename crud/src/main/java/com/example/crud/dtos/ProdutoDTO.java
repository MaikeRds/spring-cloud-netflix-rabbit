package com.example.crud.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({ "id", "nome", "estoque", "preco" })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = -4507889513870863178L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("estoque")
	private Integer estoque;
	
	@JsonProperty("preco")
	private Double preco;



}
