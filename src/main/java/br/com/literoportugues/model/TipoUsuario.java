package br.com.literoportugues.model;

public enum TipoUsuario {
	
	MEDIADOR("mediador"),
	PARTE("parte"),
	ADMINISTRADOR("Administrador");
	
	
	
	private String descricao;
	
	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
