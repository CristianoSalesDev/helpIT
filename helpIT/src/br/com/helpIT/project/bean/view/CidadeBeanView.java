package br.com.helpIT.project.bean.view;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Cidade;
import br.com.helpIT.project.bean.geral.BeanManagedViewAbstract;
import br.com.helpIT.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.helpIT.project.geral.controller.CidadeController;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";		
	private String urlFind = "/cadastro/find_cidade.jsf?faces-redirect=true";
	
	@Resource
    private CidadeController cidadeController;	
	
	private Cidade objetoSelecionado = new Cidade();
	
	private CarregamentoLazyListForObject<Cidade> list = new CarregamentoLazyListForObject<Cidade>();
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
	    super.setNomeRelatorioJasper("rel_cidades");
	    super.setNomeRelatorioSaida("rel_cidades");
	    super.setListDataBeanColletionReport(cidadeController.findList(getClassImplement()));		
		return super.getArquivoReport();
	}
	
	public CarregamentoLazyListForObject<Cidade> getList() throws Exception {		
		return list;
	}
	
	@Override
	public String save() throws Exception {
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
	   list.clean();
	   objetoSelecionado = cidadeController.merge(objetoSelecionado);
	   list.add(objetoSelecionado);
	   objetoSelecionado = new Cidade();
	   sucesso();
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Cidade) cidadeController.getSession().get(getClassImplement(), objetoSelecionado.getId_cidade());
		cidadeController.delete(objetoSelecionado);
		list.remove(objetoSelecionado);
		objetoSelecionado = new Cidade();
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
    
	public Cidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Cidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	protected Class<Cidade> getClassImplement() {

		return Cidade.class;
	}
	
    @Override
    public void setarVariaveisNulas() throws Exception {
    	list.clean();
		objetoSelecionado = new Cidade();    	
    }	
    
    @Override
    public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
    }

	@Override
	protected InterfaceCrud<Cidade> getController() {
		return cidadeController;
	}
	
	@Override
    public void consultarEntidade() throws Exception {
		objetoSelecionado = new Cidade();
		list.clean();
	    list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
    }

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}	

}
