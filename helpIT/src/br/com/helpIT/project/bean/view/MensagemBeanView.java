package br.com.helpIT.project.bean.view;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Entidade;
import br.com.helpIT.model.classes.Mensagem;
import br.com.helpIT.project.bean.geral.BeanManagedViewAbstract;
import br.com.helpIT.project.geral.controller.EntidadeController;
import br.com.helpIT.project.geral.controller.MensagemController;

@Controller
@Scope(value = "session")
@ManagedBean(name = "mensagemBeanView")
public class MensagemBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private Mensagem objetoSelecionado = new Mensagem();
	
	@Autowired
	private ContextoBeanView contextoBeanView;
	
	@Autowired
	private EntidadeController entidadeController;
	
	@Autowired
	private MensagemController mensagemController;
	
	@Override
	public String novo() throws Exception {
		objetoSelecionado = new Mensagem();
		objetoSelecionado.setUsr_origem(contextoBeanView.getEntidadeLogada());
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		
		if (objetoSelecionado.getUsr_destino().getEnt_codigo()
				.equals(objetoSelecionado.getUsr_origem().getEnt_codigo())) {
				addMsg("Usuário Origem não pode ser igual ao usuário destino.");
			return;
		}		
		
		mensagemController.merge(objetoSelecionado);
		novo();
		addMsg("Mensagem enviada com sucesso!!!");
	}	

	@Override
	protected Class<?> getClassImplement() {
		return null;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return null;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Mensagem getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Mensagem objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	@RequestMapping("**/findUserDestino")
    public void findUserDestino(HttpServletResponse httpServletResponse,
    		@RequestParam(value = "codEntidade") Long codEntidade) throws Exception {
		
		Entidade entidade = entidadeController.findByPorId(Entidade.class, codEntidade);
		
		if (entidade != null) {
		   objetoSelecionado.setUsr_destino(entidade);
		   httpServletResponse.getWriter().write(entidade.getJson().toString());
		}
		
    	
    }

}
