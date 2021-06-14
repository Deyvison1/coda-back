package com.teste.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.teste.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	Page<Pedido> findByBeneficiarioContainingIgnoreCase(String beneficiario, Pageable pageable);

	Long countByBeneficiarioContainingIgnoreCase(String beneficiario);

	@Query(value = "update from Pedido p set p.aprovacao = true where p.id in (:ids)")
	@Modifying
	void updateFromAprovacao(List<Long> ids);
}
