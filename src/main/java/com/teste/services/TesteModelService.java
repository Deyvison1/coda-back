package com.teste.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.models.TesteModel;
import com.teste.repository.TesteModelRepository;

@Service
public class TesteModelService {

	@Autowired
	private TesteModelRepository _repo;

	public TesteModel add(TesteModel testeModel) {
		return _repo.save(testeModel);
	}

	public List<TesteModel> getAll() {
		return _repo.findAll();
	}

	public List<TesteModel> getByBeneficiarioOrValorItem(String beneficiario, BigDecimal valorPedido) {
		return _repo.findByBeneficiarioContainingIgnoreCase(beneficiario);
	}

	public void aprovar(List<TesteModel> testeModels) {
		for (TesteModel testeModel : testeModels) {
			TesteModel findTesteModelById = _repo.findById(testeModel.getId()).get();

			findTesteModelById.setAprovacao(true);
			_repo.save(findTesteModelById);
		}
	}
}
