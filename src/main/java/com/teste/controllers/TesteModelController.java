package com.teste.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.models.TesteModel;
import com.teste.services.TesteModelService;

@CrossOrigin(origins = "*", exposedHeaders = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/teste")
public class TesteModelController {

	@Autowired
	private TesteModelService testeModelService;

	@PostMapping("/adicionar")
	public ResponseEntity<TesteModel> adicionar(@RequestBody TesteModel testeModel) {
		return ResponseEntity.ok(testeModelService.adicionar(testeModel));
	}

	@PostMapping("/aprova")
	public void aprovarSelecionados(@RequestBody List<TesteModel> testeModels) {
		testeModelService.aprovar(testeModels);
	}

	@GetMapping("/listarTodosComOuSemFiltro")
	public ResponseEntity<List<TesteModel>> listarTodosComOuSemFiltro(
			@RequestParam(required = false) String beneficiario, @RequestParam(required = false) BigDecimal valorPedido,
			Pageable pageable) {
		Page<TesteModel> testeModelsPagination = testeModelService.listarTodosComOuSemFiltro(beneficiario, valorPedido,
				pageable);
		final Long total = testeModelService.contarTodos(beneficiario, valorPedido);

		HttpHeaders headers = new HttpHeaders();
		headers.add("X_TOTAL_COUNT", String.valueOf(total));
		return new ResponseEntity<List<TesteModel>>(testeModelsPagination.getContent(), headers, HttpStatus.OK);
	}
}
