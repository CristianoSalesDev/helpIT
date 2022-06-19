package br.com.helpIT.project.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Estado;
import br.com.helpIT.project.bean.geral.BeanManagedViewAbstract;
import br.com.helpIT.project.geral.controller.EstadoController;

@Controller
@Scope(value = "session")
@ManagedBean(name = "estadoBeanView")
public class EstadoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EstadoController estadoController;
	
	public List<SelectItem> getEstados() throws Exception {
		return estadoController.getListEstado();
	}

	@Override
	protected Class<Estado> getClassImplement() {
		return Estado.class;
	}

	@Override
	protected InterfaceCrud<Estado> getController() {
		return estadoController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
