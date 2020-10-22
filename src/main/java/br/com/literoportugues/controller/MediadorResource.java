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

import br.com.literoportugues.model.Mediador;
import br.com.literoportugues.repository.MediadorRepository;

@RestController
@RequestMapping("/mediadors")
public class MediadorResource {

	@Autowired
	private MediadorRepository mediadorRepository;

	@GetMapping
	public ResponseEntity<Collection<Mediador>> lista(){
		Collection<Mediador> mediadors = mediadorRepository.findAll();
		return ResponseEntity.ok(mediadors);
	}


	@GetMapping("/{mediadorId}")
	public ResponseEntity<Mediador> recuperar(@PathVariable("mediadorId") Integer mediadorId){
		Mediador mediador =  mediadorRepository.findById(mediadorId).orElse(null);
		if(mediador == null) {

			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(mediador);
	}

	@PostMapping
	public ResponseEntity<Mediador> salvar(@RequestBody Mediador mediador) {
		mediador =  mediadorRepository.save(mediador);
		return ResponseEntity.ok(mediador);
	}


	@PutMapping("/{mediadorId}")
	public ResponseEntity<Mediador> alterar(@PathVariable("mediadorId") Integer mediadorId, @RequestBody Mediador mediador) {

		Mediador mediadorToUpdate =  mediadorRepository.findById(mediadorId).orElse(null);
		if(mediador == null) {
			return ResponseEntity.badRequest().build();
		}
		BeanUtils.copyProperties(mediador, mediadorToUpdate, "mediadorId");
		mediadorToUpdate =  mediadorRepository.save(mediador);
		return ResponseEntity.ok(mediador);	
	}
	
	@ResponseStatus(code=HttpStatus.OK)
	@DeleteMapping("/{mediadorId}")
	public void remover(@PathVariable("mediadorId") Integer mediadorId) {

		if(!mediadorRepository.existsById(mediadorId)){
            throw new RuntimeException("");
      }
		mediadorRepository.deleteById(mediadorId);
	}





}
