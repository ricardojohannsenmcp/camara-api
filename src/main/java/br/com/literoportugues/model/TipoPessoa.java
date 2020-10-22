package br.com.literoportugues.model;

public enum TipoPessoa {
	
	
	FISICA("Física"),JUIDICA("Juridica");
	
	
	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}
	
	
	

}
