package com.example.pagamento.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.pagamento.dtos.VendaDTO;
import com.example.pagamento.entities.ProdutoVenda;
import com.example.pagamento.entities.Venda;
import com.example.pagamento.exceptions.ResourceNotFoundException;
import com.example.pagamento.mappers.ProdutoVendaMapper;
import com.example.pagamento.mappers.VendaMapper;
import com.example.pagamento.repositories.ProdutoVendaRepository;
import com.example.pagamento.repositories.VendaRepository;

@Service
public class VendaService {
	

	@Autowired
	private VendaRepository vendaRepository;	
	
	@Autowired
	private ProdutoVendaRepository produtoVendaRepository;	
	
	
	public VendaDTO create(VendaDTO vendaDTO) {
		Venda venda = vendaRepository.save(VendaMapper.toModel(vendaDTO));
		
		List<ProdutoVenda> produtosSalvos = new ArrayList<>();
		
		vendaDTO.getProdutos().forEach(p -> {
			ProdutoVenda pv = ProdutoVendaMapper.toModel(p);
			pv.setVenda(venda);				
			produtosSalvos.add(produtoVendaRepository.save(pv));
		});
		
		venda.setProdutos(produtosSalvos);
		
		return VendaMapper.toDTO(venda);
	}
	
	public Page<VendaDTO> findAll(Pageable pageable){
		var page = vendaRepository.findAll(pageable);
		return page.map(this::convertToVendaDTO);
	}
	
	public VendaDTO findById(Long id) {
		Venda venda = vendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return VendaMapper.toDTO(venda);
	}
	
	public VendaDTO update(VendaDTO vendaDTO) {
		final Optional<Venda> optionalVenda = vendaRepository.findById(vendaDTO.getId());
		
		if(optionalVenda.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		Venda venda = vendaRepository.save(VendaMapper.toModel(vendaDTO));
		return VendaMapper.toDTO(venda);
	}
	
	public void delete(Long id) {
		Venda venda = vendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		vendaRepository.delete(venda);
	}
	
	private VendaDTO convertToVendaDTO(Venda venda) {
		return VendaMapper.toDTO(venda);
	}

}
