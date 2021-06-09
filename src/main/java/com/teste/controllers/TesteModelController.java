package com.teste.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/teste")
public class TesteModelController {

	@Autowired
	private TesteModelService _service;

	@PostMapping("/add")
	public ResponseEntity<TesteModel> add(@RequestBody TesteModel testeModel) {
		return ResponseEntity.ok(_service.add(testeModel));
	}

	@PostMapping("/aprova")
	public void aprovarSelecionados(@RequestBody List<TesteModel> testeModels) {
		_service.aprovar(testeModels);
	}

	@GetMapping("/search")
	public ResponseEntity<List<TesteModel>> findByBeneficiarioOrValorItem(
			@RequestParam(required = false) String beneficiario,
			@RequestParam(required = false) BigDecimal valorPedido) {
		return ResponseEntity.ok(_service.getByBeneficiarioOrValorItem(beneficiario, valorPedido));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<TesteModel>> getAll() {
		return ResponseEntity.ok(_service.getAll());
	}
}
