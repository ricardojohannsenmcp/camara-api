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

import br.com.literoportugues.model.Especialidade;
import br.com.literoportugues.repository.EspecialidadeRepository;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeResource {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	@GetMapping
	public ResponseEntity<Collection<Especialidade>> lista(){
		Collection<Especialidade> especialidades = especialidadeRepository.findAll();
		return ResponseEntity.ok(especialidades);
	}


	@GetMapping("/{especialidadeId}")
	public ResponseEntity<Especialidade> recuperar(@PathVariable("especialidadeId") Integer especialidadeId){
		Especialidade especialidade =  especialidadeRepository.findById(especialidadeId).orElse(null);
		if(especialidade == null) {

			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(especialidade);
	}

	@PostMapping
	public ResponseEntity<Especialidade> salvar(@RequestBody Especialidade especialidade) {
		especialidade =  especialidadeRepository.save(especialidade);
		return ResponseEntity.ok(especialidade);
	}


	@PutMapping("/{especialidadeId}")
	public ResponseEntity<Especialidade> alterar(@PathVariable("especialidadeId") Integer especialidadeId, @RequestBody Especialidade especialidade) {

		Especialidade especialidadeToUpdate =  especialidadeRepository.findById(especialidadeId).orElse(null);
		if(especialidade == null) {
			return ResponseEntity.badRequest().build();
		}
		BeanUtils.copyProperties(especialidade, especialidadeToUpdate, "especialidadeId");
		especialidadeToUpdate =  especialidadeRepository.save(especialidade);
		return ResponseEntity.ok(especialidade);	
	}
	
	@ResponseStatus(code=HttpStatus.OK)
	@DeleteMapping("/{especialidadeId}")
	public void remover(@PathVariable("especialidadeId") Integer especialidadeId) {

		if(!especialidadeRepository.existsById(especialidadeId)){
            throw new RuntimeException("");
      }
		especialidadeRepository.deleteById(especialidadeId);
	}





}
