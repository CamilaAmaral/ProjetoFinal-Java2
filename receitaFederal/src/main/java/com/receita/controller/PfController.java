package com.receita.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.receita.dominio.PF;
import com.receita.dto.PFResponseDTO;
import com.receita.repositorio.PfRepository;

@RestController
@RequestMapping("/cpf")
public class PfController {

	@Autowired
	private PfRepository repository;

	@GetMapping("")
	public ResponseEntity<List<PFResponseDTO>> getPF(@RequestParam Map<String, String> parametros) {

		List<PF> todos = (List<PF>) repository.findAll();
		List<PFResponseDTO> todosDto = new ArrayList<>();
		
		for (PF pf : todos) {
			todosDto.add(PFResponseDTO.fromPF(pf));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(todosDto);
	}

	@PostMapping
	public ResponseEntity<PF> postPF(@RequestBody PF novo) {

		PF existente = repository.findByCpf(novo.getCpf());

		if (existente != null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		repository.save(novo);
		return new ResponseEntity<>(novo, HttpStatus.CREATED);
	}

	@PutMapping("/{cpf}")
	public ResponseEntity<?> pustPF(@PathVariable String cpf, @RequestBody PF modificado, @RequestHeader("password") String password) {
		
		 if (!"francisco_me_da_10".equals(password)) {
	        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida. Não foi possível remover o socio");
	        }
		 
		PF existente = repository.findByCpf(cpf);

		if (existente != null) {
			existente.setCpf(modificado.getCpf());
			existente.setDtNascimento(modificado.getDtNascimento());
			existente.setNome(modificado.getNome());
			existente.setProfissao(modificado.getProfissao());
			existente.setRua(modificado.getRua());
			existente.setBairro(modificado.getBairro());
			existente.setCidade(modificado.getCidade());
			existente.setUf(modificado.getUf());
			
			repository.save(existente);

			repository.save(existente);
			return ResponseEntity.ok().body(existente);

		}
		return new ResponseEntity<>(existente, HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/{cpf}")

	public ResponseEntity<?> deletePF(@PathVariable String cpf, @RequestHeader("segredo") String password) {
		
		if(!"francisco_me_da_10".equals(password)) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Senha inválida");
		}
		
		PF existente = repository.findByCpf(cpf);

		if (existente != null) {
	          PFResponseDTO deletado = PFResponseDTO.fromPF(existente);
	          repository.delete(existente);
	          return ResponseEntity.ok().body(deletado);
	        }

		return ResponseEntity.notFound().build();
	}
}