package br.com.literoportugues.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;


@Entity
@Table(name="ramo")
public class Ramo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ramo_id")
	private Long ramoId;
	
	//@NotEmpty
	private String nome;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="area_id",referencedColumnName="area_id")
	private Area area;
	

	public Long getRamoId() {
		return ramoId;
	}


	public void setRamoId(Long ramoId) {
		this.ramoId = ramoId;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ramoId == null) ? 0 : ramoId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ramo other = (Ramo) obj;
		if (ramoId == null) {
			if (other.ramoId != null)
				return false;
		} else if (!ramoId.equals(other.ramoId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Ramo [ramoId=" + ramoId + "]";
	}
	
	
	
	

}
