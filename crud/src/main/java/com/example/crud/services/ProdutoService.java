package com.example.crud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.crud.dtos.ProdutoDTO;
import com.example.crud.entities.Produto;
import com.example.crud.exceptions.ResourceNotFoundException;
import com.example.crud.mappers.ProdutoMapper;
import com.example.crud.messages.ProdutoSendMessage;
import com.example.crud.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;	
	
	@Autowired
	private ProdutoSendMessage produtoSendMessage;
	
	
	public ProdutoDTO create(ProdutoDTO produtoDTO) {
		Produto produto = produtoRepository.save(ProdutoMapper.toModel(produtoDTO));
		produtoSendMessage.sendMessage(ProdutoMapper.toDTO(produto));
		return ProdutoMapper.toDTO(produto);
	}
	
	public Page<ProdutoDTO> findAll(Pageable pageable){
		var page = produtoRepository.findAll(pageable);
		return page.map(this::convertToProdutoDTO);
	}
	
	public ProdutoDTO findById(Long id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return ProdutoMapper.toDTO(produto);
	}
	
	public ProdutoDTO update(ProdutoDTO produtoDTO) {
		final Optional<Produto> optionalProduto = produtoRepository.findById(produtoDTO.getId());
		
		if(optionalProduto.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		Produto produto = produtoRepository.save(ProdutoMapper.toModel(produtoDTO));
		return ProdutoMapper.toDTO(produto);
	}
	
	public void delete(Long id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		produtoRepository.delete(produto);
	}
	
	private ProdutoDTO convertToProdutoDTO(Produto produto) {
		return ProdutoMapper.toDTO(produto);
	}
	
}
