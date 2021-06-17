package com.teste.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teste.models.Pedido;
import com.teste.repository.PedidoRepository;

import javassist.NotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido adicionar(Pedido pedido) {
		pedido.setDataSolicitacao(LocalDateTime.now());
		return pedidoRepository.save(pedido);
	}

	public Pedido listarPeloId(Long id) throws NotFoundException {
		return pedidoRepository.findById(id).orElseThrow(() ->  new NotFoundException("Nenhum registro encontrado"));
	}
	
	public Long contarTodos(String beneficiario, BigDecimal valorItem) {
		return pedidoRepository.countByBeneficiarioContainingIgnoreCase(beneficiario);
	}

	public Page<Pedido> listarTodosComOuSemFiltro(String beneficiario, BigDecimal valorPedido, Pageable pageable) {
		return pedidoRepository.findByBeneficiarioContainingIgnoreCase(beneficiario, pageable);
	}

	@Transactional
	public void aprovar(List<Pedido> pedidos) {
		List<Long> ids = pedidos
				.stream()
				.map(p -> p.getId())
				.collect(Collectors.toList());
		pedidoRepository.updateFromAprovacao(ids);
	}
}
