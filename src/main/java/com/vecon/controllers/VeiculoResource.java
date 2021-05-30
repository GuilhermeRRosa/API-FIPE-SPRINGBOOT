package com.vecon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vecon.models.Veiculo;
import com.vecon.services.VeiculoService;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoResource {

	@Autowired
	private VeiculoService veiculoService;
	
	@PostMapping
	public ResponseEntity<Veiculo> saveVeiculo(@RequestBody Veiculo veiculo){
		Veiculo veiculoSalvo = veiculoService.saveVeiculo(veiculo);
		return new ResponseEntity<Veiculo>(veiculoSalvo, HttpStatus.CREATED);
	}	
}
