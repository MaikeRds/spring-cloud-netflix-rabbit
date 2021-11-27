package com.example.pagamento.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.pagamento.dtos.ProdutoVendaDTO;
import com.example.pagamento.entities.ProdutoVenda;
import com.example.pagamento.exceptions.ResourceNotFoundException;
import com.example.pagamento.mappers.ProdutoVendaMapper;
import com.example.pagamento.repositories.ProdutoVendaRepository;

@Service
public class ProdutoVendaService {
	

	@Autowired
	private ProdutoVendaRepository produtoVendaRepository;	
	
	
	public ProdutoVendaDTO create(ProdutoVendaDTO produtoVendaDTO) {
		ProdutoVenda produto = produtoVendaRepository.save(ProdutoVendaMapper.toModel(produtoVendaDTO));
		return ProdutoVendaMapper.toDTO(produto);
	}
	
	public Page<ProdutoVendaDTO> findAll(Pageable pageable){
		var page = produtoVendaRepository.findAll(pageable);
		return page.map(this::convertToProdutoVendaVendaDTO);
	}
	
	public ProdutoVendaDTO findById(Long id) {
		ProdutoVenda produto = produtoVendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return ProdutoVendaMapper.toDTO(produto);
	}
	
	public ProdutoVendaDTO update(ProdutoVendaDTO produtoVendaDTO) {
		final Optional<ProdutoVenda> optionalProdutoVenda = produtoVendaRepository.findById(produtoVendaDTO.getId());
		
		if(optionalProdutoVenda.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		ProdutoVenda produto = produtoVendaRepository.save(ProdutoVendaMapper.toModel(produtoVendaDTO));
		return ProdutoVendaMapper.toDTO(produto);
	}
	
	public void delete(Long id) {
		ProdutoVenda produto = produtoVendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		produtoVendaRepository.delete(produto);
	}
	
	private ProdutoVendaDTO convertToProdutoVendaVendaDTO(ProdutoVenda produtoVenda) {
		return ProdutoVendaMapper.toDTO(produtoVenda);
	}

}
