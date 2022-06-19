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
@Table(name="pais")
@SequenceGenerator(name = "pais_seq", sequenceName = "pais_seq", initialValue = 1, allocationSize = 1)
public class Pais implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "C�digo", campoConsulta = "id_pais")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_seq")
	private Long id_pais;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "pai_nome", principal = 1)
	@Column(nullable = false, length = 80)
	private String pai_nome;
	
	@Column(nullable = true, length = 15)
	private String pai_sigla;	

	@Version
	@Column(name = "versionNum" )
	private int versionNum;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date pai_data = new Date();	

	public Long getId_pais() {
		return id_pais;
	}

	public void setId_pais(Long id_pais) {
		this.id_pais = id_pais;
	}

	public String getPai_nome() {
		return pai_nome;
	}

	public void setPai_nome(String pai_nome) {
		this.pai_nome = pai_nome;
	}

	public String getPai_sigla() {
		return pai_sigla;
	}

	public void setPai_sigla(String pai_sigla) {
		this.pai_sigla = pai_sigla;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Date getPai_data() {
		return pai_data;
	}

	public void setPai_data(Date pai_data) {
		this.pai_data = pai_data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_pais == null) ? 0 : id_pais.hashCode());
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
		Pais other = (Pais) obj;
		if (id_pais == null) {
			if (other.id_pais != null)
				return false;
		} else if (!id_pais.equals(other.id_pais))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pais [id_pais=" + id_pais + ", pai_nome=" + pai_nome + "]";
	}
	
	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id_pais", id_pais);
		map.put("pai_nome", pai_nome);
		return new JSONObject(map);
	}	
	
	
}
