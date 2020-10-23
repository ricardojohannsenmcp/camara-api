package br.com.literoportugues.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.literoportugues.model.Ramo;

public interface RamoRepository extends JpaRepository<Ramo, Long>{
	
	
	List<Ramo> findByArea_AreaId(Long areaId);

}
