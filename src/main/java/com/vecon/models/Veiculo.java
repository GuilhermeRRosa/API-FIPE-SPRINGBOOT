package com.vecon.models;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**Classe para objetos do tipo veículo.
* @author Guilherme R. Rosa
*/

@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("Marca")
	@NotEmpty
	private String marca;

	@JsonProperty("Modelo")
	@NotEmpty
	private String modelo;

	@JsonProperty("AnoModelo")
	@NotEmpty
	private String ano;

	@JsonProperty("Valor")
	private String valor;

	@JsonProperty("Combustivel")
	private String combustivel;

	@JsonProperty("CodigoFipe")
	private String codigoFipe;

	@JsonProperty("MesReferencia")
	private String mesReferencia;

	@JsonProperty("TipoVeiculo")
	private Integer tipoVeiculo;

	@JsonProperty("SiglaCombustivel")
	private String siglaCombustivel;

	@JsonProperty(access = Access.READ_ONLY)
	private String diaDoRodizio;

	@JsonProperty(access = Access.READ_ONLY)
	private Boolean rodizioAtivo;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Veiculo() {
	}

	public Veiculo(String marca, String modelo, String ano, Usuario usuario) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.usuario = usuario;
	}

	public Boolean getRodizioAtivo() {
		return rodizioAtivo;
	}

	/** Método que retorna o dia de rodízio do veículo
	 * @return boolean - rodizioAtivo*/
	
	public void setRodizioAtivo() {
		if(this.diaDoRodizio!=null) {
			String diaHj = LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
			if(diaHj.equals("Monday") && getDiaDoRodizio().equals("Segunda-feira")) {
				this.rodizioAtivo = true;
			}else if(diaHj.equals("Tuesday") && getDiaDoRodizio().equals("Terça-feira")) {
				this.rodizioAtivo = true;
			}else if(diaHj.equals("Wednesday") && getDiaDoRodizio().equals("Quarta-feira")) {
				this.rodizioAtivo = true;
			}else if(diaHj.equals("Thursday") && getDiaDoRodizio().equals("Quinta-feira")) {
				this.rodizioAtivo = true;
			}else if(diaHj.equals("Friday") && getDiaDoRodizio().equals("Sexta-feira")) {
				this.rodizioAtivo = true;
			}else {
				this.rodizioAtivo = false;
			}
		}else {
			this.rodizioAtivo = null;
		}	
	}

	public String getDiaDoRodizio() {
		return diaDoRodizio;
	}

	/** Método que retorna se o Veículo esta em rodízio ou não
	 * @return String - diaDoRodizio */
	
	public void setDiaDoRodizio() {
		if(this.ano!=null) {
			Integer ano = Integer.parseInt(this.ano.substring(3, 4));
			if(ano ==0 || ano ==1) {
				this.diaDoRodizio = "Segunda-feira";
			}else if(ano == 2 || ano == 3) {
				this.diaDoRodizio = "Terça-feira";
			}else if(ano == 4 || ano == 5) {
				this.diaDoRodizio = "Quarta-feira";
			}else if(ano == 6 || ano == 7) {
				this.diaDoRodizio = "Quinta-feira";
			}else if(ano == 8 || ano == 9) {
				this.diaDoRodizio = "Sexta-feira";
			}
		}else {
			this.diaDoRodizio = null;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getCodigoFipe() {
		return codigoFipe;
	}

	public void setCodigoFipe(String codigoFipe) {
		this.codigoFipe = codigoFipe;
	}

	public String getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public Integer getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(Integer tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public String getSiglaCombustivel() {
		return siglaCombustivel;
	}

	public void setSiglaCombustivel(String siglaCombustivel) {
		this.siglaCombustivel = siglaCombustivel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", ano=" + ano + ", valor=" + valor
				+ ", combustivel=" + combustivel + ", codigoFipe=" + codigoFipe + ", mesReferencia=" + mesReferencia
				+ ", tipoVeiculo=" + tipoVeiculo + ", siglaCombustivel=" + siglaCombustivel + ", usuario=" + usuario
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
