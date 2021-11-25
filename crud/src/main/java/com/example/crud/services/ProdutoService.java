package com.example.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.dtos.ProdutoDTO;
import com.example.crud.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;	
	
	
	public ProdutoDTO create(ProdutoDTO produtoDTO) {
		return null;
	}
	
}
