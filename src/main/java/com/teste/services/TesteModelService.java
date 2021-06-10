package com.teste.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teste.models.TesteModel;
import com.teste.repository.TesteModelRepository;

@Service
public class TesteModelService {

	@Autowired
	private TesteModelRepository testeModelRepository;

	public TesteModel adicionar(TesteModel testeModel) {
		return testeModelRepository.save(testeModel);
	}

	public Long contarTodos(String beneficiario, BigDecimal valorItem) {
		return testeModelRepository.countByBeneficiarioContainingIgnoreCase(beneficiario);
	}

	public Page<TesteModel> listarTodosComOuSemFiltro(String beneficiario, BigDecimal valorPedido, Pageable pageable) {
		return testeModelRepository.findByBeneficiarioContainingIgnoreCase(beneficiario, pageable);
	}

	@Transactional
	public void aprovar(List<TesteModel> testeModels) {
		List<Long> ids = testeModels
				.stream()
				.map(p -> p.getId())
				.collect(Collectors.toList());
		testeModelRepository.updateFromAprovacao(ids);
	}
}
