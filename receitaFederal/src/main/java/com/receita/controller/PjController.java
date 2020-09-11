package com.receita.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.receita.dominio.PF;
import com.receita.dominio.PJ;
import com.receita.dto.PJRequestDTO;
import com.receita.dto.PJResponseDTO;
import com.receita.repositorio.PfRepository;
import com.receita.repositorio.PjRepository;

@RestController
@RequestMapping("/cnpj")
public class PjController {

	@Autowired
	private PjRepository pjRepository;

	@Autowired
	private PfRepository pfRepository;

	@GetMapping("")
	public ResponseEntity<List<PJResponseDTO>> getPJ() {

		List<PJ> todos = pjRepository.findAll();
		List<PJResponseDTO> todosDto = new ArrayList<>();
		
		for (PJ pj : todos) {
			todosDto.add(PJResponseDTO.fromPJ(pj));
		}
		return ResponseEntity.status(HttpStatus.OK).body(todosDto);
	}

	@PostMapping
	public ResponseEntity<?> post(@Valid @RequestBody PJRequestDTO dto) {

		PJ pj = dto.toPJ(pfRepository);

		pjRepository.save(pj);

		return new ResponseEntity<>(dto, HttpStatus.CREATED);

	}

	@PutMapping("/{cnpj}")
	public ResponseEntity<?> pustPJ(@PathVariable String cnpj, @RequestBody PJRequestDTO dto, @RequestHeader("password") String password) {
		
		 if (!"francisco_me_da_10".equals(password)) {
	        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida. Não foi possível remover o socio");
	        }
		 
		PJ modificado = dto.toPJ(pfRepository);
		PJ existente = pjRepository.findByCnpj(cnpj);

		if (existente != null) {
			existente.setCnpj(modificado.getCnpj());
			existente.setNome(modificado.getNome());
			existente.setBairro(modificado.getBairro());
			existente.setCidade(modificado.getCidade());
			existente.setSocios(modificado.getSocios());
			existente.setRua(modificado.getRua());
			existente.setUf(modificado.getUf());
			existente.setDtInicial(modificado.getDtInicial());
			existente.setAtividade(modificado.getAtividade());
			existente.setCdAtividade(modificado.getCdAtividade());

			pjRepository.save(existente);

			pjRepository.save(existente);
			return ResponseEntity.ok().body(existente);
		}
		return ResponseEntity.notFound().build();
	}
	
    @DeleteMapping("/{cnpj}/{cpf}")
    public ResponseEntity<?> deleteDependente(@PathVariable String cnpj, @PathVariable String cpf, @RequestHeader("password") String password){
        if (!"francisco_me_da_10".equals(password)) {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida. Não foi possível remover o socio");
        }
        
        PJ pj = pjRepository.findByCnpj(cnpj);
        PF socio = pfRepository.findByCpf(cpf);

        pj.getSocios().remove(socio);
        pjRepository.save(pj);

        return ResponseEntity.status(HttpStatus.OK).body(pj);
    }

	@DeleteMapping("/{cnpj}")
	public ResponseEntity<?> deletePJ(@PathVariable String cnpj, @RequestHeader ("password") String password ) {

		if(!"francisco_me_da_10".equals(password)) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Senha inválida");
		}
		
		PJ existente = pjRepository.findByCnpj(cnpj);

		 if (existente != null) {
	          PJResponseDTO deletado = PJResponseDTO.fromPJ(existente);
	          pjRepository.delete(existente);
	          return ResponseEntity.ok().body(deletado);
	        }

		return ResponseEntity.notFound().build();
	}
}