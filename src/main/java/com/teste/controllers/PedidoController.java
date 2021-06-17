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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.models.Pedido;
import com.teste.services.PedidoService;

import javassist.NotFoundException;

@CrossOrigin(origins = "*", exposedHeaders = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping("/adicionar")
	public ResponseEntity<Pedido> adicionar(@RequestBody Pedido pedido) {
		return ResponseEntity.ok(pedidoService.adicionar(pedido));
	}

	@PostMapping("/aprova")
	public void aprovarSelecionados(@RequestBody List<Pedido> pedidos) {
		pedidoService.aprovar(pedidos);
	}
	
	@GetMapping("/listarPeloId/{id}")
	private ResponseEntity<Pedido> listarPeloId(@PathVariable Long id) throws NotFoundException, Exception { 
		try {
			return ResponseEntity.ok(pedidoService.listarPeloId(id));
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@GetMapping("/listarTodosComOuSemFiltro")
	public ResponseEntity<List<Pedido>> listarTodosComOuSemFiltro(@RequestParam(required = false) String beneficiario,
			@RequestParam(required = false) BigDecimal valorPedido, Pageable pageable) {
		Page<Pedido> pedidoPagination = pedidoService.listarTodosComOuSemFiltro(beneficiario, valorPedido,
				pageable);
		final Long total = pedidoService.contarTodos(beneficiario, valorPedido);

		HttpHeaders headers = new HttpHeaders();
		headers.add("X_TOTAL_COUNT", String.valueOf(total));
		return new ResponseEntity<List<Pedido>>(pedidoPagination.getContent(), headers, HttpStatus.OK);
	}
}
