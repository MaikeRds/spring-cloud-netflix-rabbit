package com.example.pagamento.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pagamento.dtos.VendaDTO;
import com.example.pagamento.services.VendaService;

@RestController
@RequestMapping("/venda")
@Transactional
public class VendaController {

	@Autowired
	private VendaService vendaService;

	@Autowired
	private PagedResourcesAssembler<VendaDTO> assembler;

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public VendaDTO findById(@PathVariable("id") Long id) {

		VendaDTO vendaDTO = vendaService.findById(id);

		vendaDTO.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());

		return vendaDTO;
	}

	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "diretion", defaultValue = "asc") String diretion) {

		var sortDirection = "desc".equalsIgnoreCase(diretion) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "data"));

		Page<VendaDTO> produtos = vendaService.findAll(pageable);

		produtos.stream()
				.forEach(p -> p.add(linkTo(methodOn(VendaController.class).findById(p.getId())).withSelfRel()));

		PagedModel<EntityModel<VendaDTO>> pagedModel = assembler.toModel(produtos);

		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public VendaDTO create(@RequestBody VendaDTO createVendaDTO) {
		VendaDTO vendaDTO = vendaService.create(createVendaDTO);
		vendaDTO.add(linkTo(methodOn(VendaController.class).findById(vendaDTO.getId())).withSelfRel());
		return vendaDTO;
	}

	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public VendaDTO update(@RequestBody VendaDTO updateVendaDTO) {
		VendaDTO produtoDTO = vendaService.update(updateVendaDTO);
		produtoDTO.add(linkTo(methodOn(VendaController.class).findById(produtoDTO.getId())).withSelfRel());
		return produtoDTO;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		vendaService.delete(id);
		return ResponseEntity.ok().build();
	}

}
