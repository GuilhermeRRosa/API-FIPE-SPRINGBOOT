package com.vecon.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vecon.models.Veiculo;
import com.vecon.models.feignmodels.Ano;
import com.vecon.models.feignmodels.Marca;
import com.vecon.models.feignmodels.OutsideModeloAux;

@Component
@FeignClient(name = "FIPE-API", url="parallelum.com.br/fipe/api/v1", path = "/carros")
public interface VeiculoFeignClient {

	//Retorna as marcas
	
	@GetMapping(value = "/marcas")
	ResponseEntity<List<Marca>> getVehiclesMarcas();
	
	//Retorna os modelos
	
	@GetMapping(value = "/marcas/{cod_marca}/modelos")
	ResponseEntity<OutsideModeloAux> getVehicleModelos(@PathVariable("cod_marca") String cod_marca);
	
	//Retorna os anos
	
	@GetMapping(value = "/marcas/{cod_marca}/modelos/{cod_modelo}/anos")
	ResponseEntity<List<Ano>> getVehicleAnos(@PathVariable("cod_marca") String cod_marca, @PathVariable("cod_modelo") String cod_modelo);
	
	//Retorna o veiculo
	
	@GetMapping(value = "/marcas/{cod_marca}/modelos/{cod_modelo}/anos/{cod_ano}")
	ResponseEntity<Veiculo> getVehicle(@PathVariable("cod_marca") String cod_marca, @PathVariable("cod_modelo") String cod_modelo, @PathVariable("cod_ano") String cod_ano);
}
