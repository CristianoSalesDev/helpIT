package br.com.helpIT.project.bean.view;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Entidade;
import br.com.helpIT.project.bean.geral.BeanManagedViewAbstract;
import br.com.helpIT.project.bean.geral.EntidadeAtualizaSenhaBean;
import br.com.helpIT.project.geral.controller.EntidadeController;

@Controller
@Scope(value = "session")
@ManagedBean(name = "entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	
	@Autowired
	private ContextoBeanView contextoBeanView;
	
	@Autowired
	private EntidadeController entidadeController; 

	public String getUsuarioLogadoSecurity() {
		return contextoBeanView.getAuthentication().getName();
	}
	
	public Date getUltimoAcesso() throws Exception {
		return contextoBeanView.getEntidadeLogada().getEnt_ultimoacesso();
	}

	@Override
	protected Class<Entidade> getClassImplement() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public EntidadeAtualizaSenhaBean getEntidadeAtualizaSenhaBean() {
		return entidadeAtualizaSenhaBean;
	}

	public void setEntidadeAtualizaSenhaBean(EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean) {
		this.entidadeAtualizaSenhaBean = entidadeAtualizaSenhaBean;
	}	
	
	public void updateSenha() throws Exception  {
		Entidade entidadeLogada = contextoBeanView.getEntidadeLogada();
		if (!entidadeAtualizaSenhaBean.getSenhaAtual().equals(
				entidadeLogada.getEnt_senha())) {
			addMsg("A senha atual não é válida");
			return;
		} else if (entidadeAtualizaSenhaBean.getSenhaAtual().equals(
				entidadeAtualizaSenhaBean.getNovaSenha())) {
			addMsg("A senha atual não pode ser igual a nova senha.");
			return;
		} else if (!entidadeAtualizaSenhaBean.getNovaSenha().equals(
				entidadeAtualizaSenhaBean.getConfirmaSenha())) {
			addMsg("A nova senha e a confimação não conferem.");
			return;
		} else {
			entidadeLogada.setEnt_senha(entidadeAtualizaSenhaBean
					.getNovaSenha());
			entidadeController.saveOrUpdate(entidadeLogada);
			entidadeLogada = entidadeController.findByPorId(Entidade.class,
					entidadeLogada.getEnt_codigo());
			if (entidadeLogada.getEnt_senha().equals(
					entidadeAtualizaSenhaBean.getNovaSenha())) {
				sucesso();
			} else {
				addMsg("A nova senha não pode ser atualizada.");
				error();
			}		
	    }
	
    }
	
}	
