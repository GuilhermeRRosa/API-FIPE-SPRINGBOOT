package com.vecon.models.feignmodels;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OutsideModeloAux {

	@JsonIgnore
	private List<Ano> anos;
	private List<Modelo> modelos = new ArrayList<>();

	public OutsideModeloAux() {
	}

	public List<Ano> getAnos() {
		return anos;
	}

	public void setAnos(List<Ano> anos) {
		this.anos = anos;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

}
