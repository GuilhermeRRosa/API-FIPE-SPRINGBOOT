package com.vecon.services;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vecon.feignclients.VeiculoFeignClient;
import com.vecon.models.Veiculo;
import com.vecon.models.feignmodels.Ano;
import com.vecon.models.feignmodels.Marca;
import com.vecon.models.feignmodels.Modelo;
import com.vecon.repositories.VeiculoRepository;
import com.vecon.services.exceptions.ObjectNotFoundException;

/**Classe para de Serviço para veículo.
* @author Guilherme R Rosa
*/

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private VeiculoFeignClient veiculoFeignClient;

	 /**Método set de dados do veículo
	 * @param veiculo Veiculo - Veiculo recebido do POST.
	 * @return vSet - veículo com todos os dados setados
	 */

	public Veiculo setDados(Veiculo veiculo) {
		String marca = setMarca(veiculo.getMarca());
		String modelo = setModelo(marca, veiculo.getModelo());
		String ano = setAno(marca, modelo, veiculo.getAno());
		Veiculo vSet = veiculoFeignClient.getVehicle(marca, modelo, ano).getBody();
		if (usuarioService.verifyUserExist(veiculo.getUsuario().getId())) {
			vSet.setUsuario(veiculo.getUsuario());
		}

		// Set de dados do rodizio, com o veiculo ja setado

		vSet.setDiaDoRodizio();
		vSet.setRodizioAtivo();
		return vSet;
	}

	// 

	/**Método específico para busca da marca na API Fipe, utilizando a marca passada
	 * pelo usuário
	 * @param marca String - marca do veículo recebido no POST.
	 * @return cod_marca - codigo da marca
	 */
	
	public String setMarca(String marca) {
		List<Marca> marcas = veiculoFeignClient.getVehiclesMarcas().getBody();
		for (String itemPesquisa : marca.split(" ")) {
			marcas = marcas.stream().filter((Marca) -> StringUtils.containsIgnoreCase(Marca.getNome(), itemPesquisa))
					.collect(Collectors.toList());
		}
		try {
			return marcas.get(0).getCodigo();
		} catch (IndexOutOfBoundsException e) {
			throw new ObjectNotFoundException("Marca não encontrada: " + marca);
		}
	}
	
	/**Método específico para busca do modelo na API Fipe, utilizando o modelo passado
	 * pelo usuário e a marca recebida do metodo @see setMarca()
	 * @param modelo String - modelo do veículo recebido no POST.
	 * @param cod_marca - codigo gerado pelo @see setMarca()
	 * @return cod_modelo - codigo do modelo
	 */

	public String setModelo(String cod_marca, String modelo) {
		List<Modelo> modelos = veiculoFeignClient.getVehicleModelos(cod_marca).getBody().getModelos();
		for (String itemPesquisa : modelo.split(" ")) {
			if (modelos.size() > 1) {
				modelos = modelos.stream()
						.filter((Modelo) -> StringUtils.containsIgnoreCase(Modelo.getNome(), itemPesquisa))
						.collect(Collectors.toList());
			}
		}
		try {
			return modelos.get(0).getCodigo();
		} catch (Exception e) {
			throw new ObjectNotFoundException("Modelo não encontrado: " + modelo +", Código da marca: "+cod_marca);
		}
		
	}
	
	/**Método específico para busca do ano na API Fipe, utilizando o ano passado
	 * pelo usuário, o modelo e a marca recebida do metodo @see setMarca() e @see setModelo()
	 * @param ano String - ano do veículo recebido no POST.
	 * @param cod_marca - codigo gerado pelo @see setMarca()
	 * @param cod_modelo - codigo gerado pelo @see setModelo()
	 * @return cod_ano - codigo do ano
	 */

	public String setAno(String cod_marca, String cod_modelo, String ano) {
		List<Ano> anos = veiculoFeignClient.getVehicleAnos(cod_marca, cod_modelo).getBody();
		anos = anos.stream().filter((Ano) -> StringUtils.containsIgnoreCase(Ano.getNome(), ano))
				.collect(Collectors.toList());
		try {
			return anos.get(0).getCodigo();
		} catch (Exception e) {
			throw new ObjectNotFoundException("Ano não encontrado: " + ano +", Código da marca: "+cod_marca+", Código do modelo: "+cod_modelo);
		}
		
	}

	public Veiculo saveVeiculo(Veiculo veiculo) {
		return veiculoRepository.save(setDados(veiculo));
	}

	public List<Veiculo> findVeiculosByUser(Long usuario_id) {
		return usuarioService.findById(usuario_id).getVeiculos();
	}

}
