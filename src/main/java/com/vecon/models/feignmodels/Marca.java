package com.vecon.models.feignmodels;

public class Marca {

	private String nome;
	private String codigo;

	public Marca() {
	}

	public Marca(String nome, String codigo) {
		super();
		this.nome = nome;
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Marca [nome=" + nome + ", codigo=" + codigo + "]";
	}

}
