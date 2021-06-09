package com.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.models.TesteModel;

public interface TesteModelRepository extends JpaRepository<TesteModel, Long> {

	List<TesteModel> findByBeneficiarioContainingIgnoreCase(String beneficiario);
}
