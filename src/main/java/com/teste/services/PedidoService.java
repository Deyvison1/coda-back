package com.teste.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teste.models.Pedido;
import com.teste.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository testeModelRepository;

	public Pedido adicionar(Pedido testeModel) {
		return testeModelRepository.save(testeModel);
	}

	public Long contarTodos(String beneficiario, BigDecimal valorItem) {
		return testeModelRepository.countByBeneficiarioContainingIgnoreCase(beneficiario);
	}

	public Page<Pedido> listarTodosComOuSemFiltro(String beneficiario, BigDecimal valorPedido, Pageable pageable) {
		return testeModelRepository.findByBeneficiarioContainingIgnoreCase(beneficiario, pageable);
	}

	@Transactional
	public void aprovar(List<Pedido> testeModels) {
		List<Long> ids = testeModels
				.stream()
				.map(p -> p.getId())
				.collect(Collectors.toList());
		testeModelRepository.updateFromAprovacao(ids);
	}
}
