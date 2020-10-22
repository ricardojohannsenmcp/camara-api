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

import br.com.literoportugues.model.Ramo;
import br.com.literoportugues.repository.RamoRepository;

@RestController
@RequestMapping("/ramos")
public class RamoResource {

	@Autowired
	private RamoRepository ramoRepository;

	@GetMapping
	public ResponseEntity<Collection<Ramo>> lista(){
		Collection<Ramo> ramos = ramoRepository.findAll();
		return ResponseEntity.ok(ramos);
	}


	@GetMapping("/{ramoId}")
	public ResponseEntity<Ramo> recuperar(@PathVariable("ramoId") Integer ramoId){
		Ramo ramo =  ramoRepository.findById(ramoId).orElse(null);
		if(ramo == null) {

			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(ramo);
	}

	@PostMapping
	public ResponseEntity<Ramo> salvar(@RequestBody Ramo ramo) {
		ramo =  ramoRepository.save(ramo);
		return ResponseEntity.ok(ramo);
	}


	@PutMapping("/{ramoId}")
	public ResponseEntity<Ramo> alterar(@PathVariable("ramoId") Integer ramoId, @RequestBody Ramo ramo) {

		Ramo ramoToUpdate =  ramoRepository.findById(ramoId).orElse(null);
		if(ramo == null) {
			return ResponseEntity.badRequest().build();
		}
		BeanUtils.copyProperties(ramo, ramoToUpdate, "ramoId");
		ramoToUpdate =  ramoRepository.save(ramo);
		return ResponseEntity.ok(ramo);	
	}
	
	@ResponseStatus(code=HttpStatus.OK)
	@DeleteMapping("/{ramoId}")
	public void remover(@PathVariable("ramoId") Integer ramoId) {

		if(!ramoRepository.existsById(ramoId)){
            throw new RuntimeException("");
      }
		ramoRepository.deleteById(ramoId);
	}





}
