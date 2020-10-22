package br.com.literoportugues.controller;

import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.literoportugues.model.Pessoa;
import br.com.literoportugues.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public ResponseEntity<Collection<Pessoa>> lista(){
		Collection<Pessoa> pessoas = pessoaRepository.findAll();
		return ResponseEntity.ok(pessoas);
	}


	@GetMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> recuperar(@PathVariable("pessoaId") Integer pessoaId){
		Pessoa pessoa =  pessoaRepository.findById(pessoaId).orElse(null);
		if(pessoa == null) {

			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(pessoa);
	}

	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa) {
		pessoa =  pessoaRepository.save(pessoa);
		return ResponseEntity.ok(pessoa);
	}


	@PutMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> alterar(@PathVariable("pessoaId") Integer pessoaId, @RequestBody Pessoa pessoa) {

		Pessoa pessoaToUpdate =  pessoaRepository.findById(pessoaId).orElse(null);
		if(pessoa == null) {
			return ResponseEntity.badRequest().build();
		}
		BeanUtils.copyProperties(pessoa, pessoaToUpdate, "pessoaId");
		pessoaToUpdate =  pessoaRepository.save(pessoa);
		return ResponseEntity.ok(pessoa);	
	}
	
	@ResponseStatus(code=HttpStatus.OK)
	@DeleteMapping("/{pessoaId}")
	public void remover(@PathVariable("pessoaId") Integer pessoaId) {

		if(!pessoaRepository.existsById(pessoaId)){
            throw new RuntimeException("");
      }
		pessoaRepository.deleteById(pessoaId);
	}





}
