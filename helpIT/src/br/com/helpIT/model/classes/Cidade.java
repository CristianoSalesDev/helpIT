package br.com.helpIT.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.helpIT.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name="cidade")
@SequenceGenerator(name = "cidade_seq", sequenceName = "cidade_seq", initialValue = 1, allocationSize = 1)
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "id_cidade", principal = 2)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade_seq")
	private Long id_cidade;	

	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "cid_descricao", principal = 1)
	@Column(nullable = false, length = 100)
	private String cid_descricao;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Estado", campoConsulta = "estado.est_nome")
	@Basic
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	@ForeignKey(name = "estado_fk")
	private Estado estado_id = new Estado();
	
	// Solução paleativa abaixo
	
//	private String est_sigla;
//	
//	public void setEst_sigla(String est_sigla) {
//		this.est_sigla = est_sigla;
//	}
//	
//	public String getEst_sigla() {
//		est_sigla = estado_id.getEst_sigla();
//		return est_sigla;
//	}
	
	@Version
	@Column(name = "versionNum" )
	private int versionNum;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date cid_data = new Date();	
	
	public Long getId_cidade() {
		return id_cidade;
	}

	public void setId_cidade(Long id_cidade) {
		this.id_cidade = id_cidade;
	}

	public String getCid_descricao() {
		return cid_descricao;
	}

	public void setCid_descricao(String cid_descricao) {
		this.cid_descricao = cid_descricao;
	}

	public Estado getEstado_id() {
		return estado_id;
	}

	public void setEstado_id(Estado estado_id) {
		this.estado_id = estado_id;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}		

	public Date getCid_data() {
		return cid_data;
	}

	public void setCid_data(Date cid_data) {
		this.cid_data = cid_data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_cidade == null) ? 0 : id_cidade.hashCode());
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
		Cidade other = (Cidade) obj;
		if (id_cidade == null) {
			if (other.id_cidade != null)
				return false;
		} else if (!id_cidade.equals(other.id_cidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cidade [id_cidade=" + id_cidade + ", cid_descricao=" + cid_descricao + "]";
	}		
	
	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id_cidade", id_cidade);
		map.put("cid_descricao", cid_descricao);
		return new JSONObject(map);
	}	

	
}
