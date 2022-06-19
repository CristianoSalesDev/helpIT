package br.com.helpIT.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.primefaces.json.JSONObject;

import com.sun.istack.NotNull;

import br.com.helpIT.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name="estado")
@SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado", initialValue = 1, allocationSize = 1)
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "id_estado")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estado")
	private Long id_estado;	

	@Column(nullable = true, length = 2)
	private String est_sigla;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "est_nome", principal = 1)
	@Column(nullable = false, length = 100)
	private String est_nome;
	
	@NotAudited
	@OneToMany(mappedBy = "estado_id", orphanRemoval = false)
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Cidade> cidades = new ArrayList<Cidade>();
	
	@Basic
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pais_id")
	@NotNull
	@ForeignKey(name = "pais_fk")
	private Pais pais_id = new Pais();
	
	@Version
	@Column(name = "versionNum" )
	private int versionNum;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date est_data = new Date(); 

	public Long getId_estado() {
		return id_estado;
	}

	public void setId_estado(Long id_estado) {
		this.id_estado = id_estado;
	}

	public String getEst_sigla() {
		return est_sigla;
	}

	public void setEst_sigla(String est_sigla) {
		this.est_sigla = est_sigla;
	}

	public String getEst_nome() {
		return est_nome;
	}

	public void setEst_nome(String est_nome) {
		this.est_nome = est_nome;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Pais getPais_id() {
		return pais_id;
	}

	public void setPais_id(Pais pais_id) {
		this.pais_id = pais_id;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}	

	public Date getEst_data() {
		return est_data;
	}

	public void setEst_data(Date est_data) {
		this.est_data = est_data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_estado == null) ? 0 : id_estado.hashCode());
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
		Estado other = (Estado) obj;
		if (id_estado == null) {
			if (other.id_estado != null)
				return false;
		} else if (!id_estado.equals(other.id_estado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estado [id_estado=" + id_estado + ", est_nome=" + est_nome + "]";
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id_estado", id_estado);
		map.put("est_nome", est_nome);
		return new JSONObject(map);
	}	
	

}
