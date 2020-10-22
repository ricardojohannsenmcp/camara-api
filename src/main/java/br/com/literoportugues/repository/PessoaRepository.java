package br.com.literoportugues.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.literoportugues.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
