package br.com.helpIT.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Setor;
import br.com.helpIT.project.bean.geral.BeanManagedViewAbstract;
import br.com.helpIT.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.helpIT.project.geral.controller.SetorController;

@Controller
@Scope(value = "session")
@ManagedBean(name = "setorBeanView")
public class SetorBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private String url = "/cadastro/cad_setor.jsf?faces-redirect=true";	

	private String urlFind = "/cadastro/find_setor.jsf?faces-redirect=true";	
	
	private Setor objetoSelecionado = new Setor();
	
	@Autowired
	private SetorController setorController;
	
	private CarregamentoLazyListForObject<Setor> list = new CarregamentoLazyListForObject<Setor>();
	
	public CarregamentoLazyListForObject<Setor> getList() throws Exception {		
		return list;
	}
	
    @Override
    public String save() throws Exception {
		objetoSelecionado = setorController.merge(objetoSelecionado);
		return "";
    }	
    
    @Override
    public void saveNotReturn() throws Exception {
 	   list.clean();
 	   objetoSelecionado = setorController.merge(objetoSelecionado);
 	   list.add(objetoSelecionado);
 	   objetoSelecionado = new Setor();
 	   sucesso();
    }
    
    @Override
    public void saveEdit() throws Exception {
		saveNotReturn();
    }
    
    @Override
    public void excluir() throws Exception {
		objetoSelecionado = (Setor) setorController.getSession().get(getClassImplement(), objetoSelecionado.getId_setor());
		setorController.delete(objetoSelecionado);
		list.remove(objetoSelecionado);
		objetoSelecionado = new Setor();
		sucesso();
    }
	
    @Override
    public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
    }    
    
    @Override
    public String editar() throws Exception {
		list.clean();
		return url;
    }   

	public Setor getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Setor objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	protected Class<Setor> getClassImplement() {
		return Setor.class;
	}

	@Override
	protected InterfaceCrud<Setor> getController() {
		return setorController;
	}
	
	@Override
	public void setarVariaveisNulas() throws Exception {
    	list.clean();
		objetoSelecionado = new Setor();
	}
	
    @Override
    public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
    }	

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}
	
    @Override
    public void consultarEntidade() throws Exception {
		objetoSelecionado = new Setor();
		list.clean();
	    list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
    }	

}
