package com.teste.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.teste.models.TesteModel;

public interface TesteModelRepository extends JpaRepository<TesteModel, Long> {

	Page<TesteModel> findByBeneficiarioContainingIgnoreCase(String beneficiario, Pageable pageable);

	Long countByBeneficiarioContainingIgnoreCase(String beneficiario);

	@Query(value = "update from TesteModel t set t.aprovacao = true where t.id in (:ids)")
	@Modifying
	void updateFromAprovacao(List<Long> ids);
}
