package br.com.helpIT.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.helpIT.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name="cargo")
@SequenceGenerator(name = "seq_cargo", sequenceName = "seq_cargo", initialValue = 160, allocationSize = 1)
public class Cargo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "id_cargo", principal = 2)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cargo")
    private Long id_cargo; 
    
	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "car_descricao", principal = 1)
	@Column(nullable = false, length = 30)
    private String car_descricao;
	
	@Version
	@Column(name = "versionNum" )
	private int versionNum;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date car_data = new Date();	

	public Long getId_cargo() {
		return id_cargo;
	}

	public void setId_cargo(Long id_cargo) {
		this.id_cargo = id_cargo;
	}

	public String getCar_descricao() {
		return car_descricao;
	}

	public void setCar_descricao(String car_descricao) {
		this.car_descricao = car_descricao;
	}	

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}
	
	public Date getCar_data() {
		return car_data;
	}

	public void setCar_data(Date car_data) {
		this.car_data = car_data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_cargo == null) ? 0 : id_cargo.hashCode());
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
		Cargo other = (Cargo) obj;
		if (id_cargo == null) {
			if (other.id_cargo != null)
				return false;
		} else if (!id_cargo.equals(other.id_cargo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cargo [id_cargo=" + id_cargo + ", car_descricao=" + car_descricao + "]";
	}
	
	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id_cargo", id_cargo);
		map.put("car_descricao", car_descricao);
		return new JSONObject(map);
	}	

}
