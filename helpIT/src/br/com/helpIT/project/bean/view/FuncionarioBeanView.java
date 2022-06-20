package br.com.helpIT.project.bean.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Entidade;
import br.com.helpIT.project.bean.geral.BeanManagedViewAbstract;
import br.com.helpIT.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.helpIT.project.geral.controller.EntidadeController;

@Controller
@Scope("session")
@ManagedBean(name = "funcionarioBeanView")
public class FuncionarioBeanView extends BeanManagedViewAbstract implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String urlFind = "/cadastro/find_funcionario.jsf?faces-redirect=true";
	
	private String url = "/cadastro/cad_funcionario.jsf?faces-redirect=true";
	
	private Entidade objetoSelecionado = new Entidade();
	
	@Autowired
	private ContextoBeanView contextoBeanView;
	
	@Autowired
	private EntidadeController entidadeController;
	
	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();	
	

	@Override
	protected Class<?> getClassImplement() {		
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		
		return " and entity.tipoEntidade = 'FUNCIONARIO' ";		
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}
	
	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new Entidade();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}	

	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public CarregamentoLazyListForObject<Entidade> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Entidade> list) {
		this.list = list;
	}
	
	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}
	
	@Override
	public void excluir() throws Exception {
			if (objetoSelecionado.getEnt_codigo() != null
					&& objetoSelecionado.getEnt_codigo() > 0) {
				entidadeController.delete(objetoSelecionado);
				list.remove(objetoSelecionado);
				objetoSelecionado = new Entidade();
				sucesso();
			}
	}	

}
