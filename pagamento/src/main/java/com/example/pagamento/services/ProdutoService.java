package com.example.pagamento.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.pagamento.dtos.ProdutoDTO;
import com.example.pagamento.entities.Produto;
import com.example.pagamento.exceptions.ResourceNotFoundException;
import com.example.pagamento.mappers.ProdutoMapper;
import com.example.pagamento.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	

	@Autowired
	private ProdutoRepository produtoRepository;	
	
	
	public ProdutoDTO create(ProdutoDTO produtoDTO) {
		Produto produto = produtoRepository.save(ProdutoMapper.toModel(produtoDTO));
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
