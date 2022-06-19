package br.com.helpIT.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Cargo;
import br.com.helpIT.project.bean.geral.BeanManagedViewAbstract;
import br.com.helpIT.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.helpIT.project.geral.controller.CargoController;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cargoBeanView")
public class CargoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private String url = "/cadastro/cad_cargo.jsf?faces-redirect=true";	

	private String urlFind = "/cadastro/find_cargo.jsf?faces-redirect=true";	

	private Cargo objetoSelecionado = new Cargo();
	
	@Autowired
	private CargoController cargoController;
	
	private CarregamentoLazyListForObject<Cargo> list = new CarregamentoLazyListForObject<Cargo>();
	
	public CarregamentoLazyListForObject<Cargo> getList() throws Exception {
		return list;
	}
	
	@Override
	public String save() throws Exception {
		objetoSelecionado = cargoController.merge(objetoSelecionado);
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
		objetoSelecionado = cargoController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Cargo();
		sucesso();
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Cargo) cargoController.getSession().get(getClassImplement(), objetoSelecionado.getId_cargo());
        cargoController.delete(objetoSelecionado);
        list.remove(objetoSelecionado);
        objetoSelecionado = new Cargo();
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

	@Override
	protected Class<Cargo> getClassImplement() {		
		return Cargo.class;
	}

	@Override
	protected InterfaceCrud<Cargo> getController() {
		return cargoController;
	}
	
	@Override
	public void setarVariaveisNulas() throws Exception {
		list.clean();
		objetoSelecionado = new Cargo();
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
		objetoSelecionado = new Cargo();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	public Cargo getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Cargo objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}	

}
