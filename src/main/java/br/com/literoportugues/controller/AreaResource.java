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

import br.com.literoportugues.model.Area;
import br.com.literoportugues.repository.AreaRepository;

@RestController
@RequestMapping("/areas")
public class AreaResource {

	@Autowired
	private AreaRepository areaRepository;

	@GetMapping
	public ResponseEntity<Collection<Area>> lista(){
		Collection<Area> areas = areaRepository.findAll();
		return ResponseEntity.ok(areas);
	}


	@GetMapping("/{areaId}")
	public ResponseEntity<Area> recuperar(@PathVariable("areaId") Long areaId){
		Area area =  areaRepository.findById(areaId).orElse(null);
		if(area == null) {

			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(area);
	}

	@PostMapping
	public ResponseEntity<Area> salvar(@RequestBody Area area) {
		area =  areaRepository.save(area);
		return ResponseEntity.ok(area);
	}


	@PutMapping("/{areaId}")
	public ResponseEntity<Area> alterar(@PathVariable("areaId") Long areaId, @RequestBody Area area) {

		Area areaToUpdate =  areaRepository.findById(areaId).orElse(null);
		if(area == null) {
			return ResponseEntity.badRequest().build();
		}
		BeanUtils.copyProperties(area, areaToUpdate, "areaId");
		areaToUpdate =  areaRepository.save(area);
		return ResponseEntity.ok(area);	
	}
	
	@ResponseStatus(code=HttpStatus.OK)
	@DeleteMapping("/{areaId}")
	public void remover(@PathVariable("areaId") Long areaId) {

		if(!areaRepository.existsById(areaId)){
            throw new RuntimeException("");
      }
		areaRepository.deleteById(areaId);
	}





}
