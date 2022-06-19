package br.com.helpIT.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
@Table(name = "setor")
@SequenceGenerator(name = "seq_setor", sequenceName = "seq_setor", initialValue = 50, allocationSize = 1)
public class Setor implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "id_setor")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_setor")	
	private Long id_setor;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Setor", campoConsulta = "set_descricao", principal = 1)
	@Column(nullable = false, length = 60)
    private String set_descricao;
	
	@Version
	@Column(name = "versionNum" )
	private int versionNum;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date set_data = new Date();		

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Date getSet_data() {
		return set_data;
	}

	public void setSet_data(Date set_data) {
		this.set_data = set_data;
	}

	public Long getId_setor() {
		return id_setor;
	}

	public void setId_setor(Long id_setor) {
		this.id_setor = id_setor;
	}

	public String getSet_descricao() {
		return set_descricao;
	}

	public void setSet_descricao(String set_descricao) {
		this.set_descricao = set_descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_setor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setor other = (Setor) obj;
		return Objects.equals(id_setor, other.id_setor);
	}

	@Override
	public String toString() {
		return "Setor [id_setor=" + id_setor + ", set_descricao=" + set_descricao + "]";
	}	
	
	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id_setor", id_setor);
		map.put("set_descricao", set_descricao);
		return new JSONObject(map);
	}	
	
	
}
