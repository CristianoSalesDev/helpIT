package br.com.helpIT.project.bean.geral;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.project.annotation.IdentificaCampoPesquisa;
import br.com.helpIT.project.enums.CondicaoPesquisa;
import br.com.helpIT.project.report.util.BeanReportView;
import br.com.helpIT.project.util.all.UtilitarioRegex;

@Component
public abstract class BeanManagedViewAbstract extends BeanReportView {

	private static final long serialVersionUID = 1L;
	
	protected abstract Class<?> getClassImplement();
	
	protected abstract InterfaceCrud<?> getController();
	
	public ObjetoCampoConsulta objetoCampoPesquisaSelecionado;
	
	public List<SelectItem> listaCampoPesquisa;
	
	public List<SelectItem> listaCondicaoPesquisa;
	
	public CondicaoPesquisa condicaoPesquisaSelecionado;
	
	public String valorPesquisa;
	
	public abstract String condicaoAndParaPesquisa() throws Exception;	
	
	public String getValorPesquisa() {
		return valorPesquisa != null ? new UtilitarioRegex().retiraAcentos(valorPesquisa) : "";
	}

	public void setValorPesquisa(String valorPesquisa) {
		this.valorPesquisa = valorPesquisa;
	}

	public CondicaoPesquisa getCondicaoPesquisaSelecionado() {
		return condicaoPesquisaSelecionado;
	}

	public void setCondicaoPesquisaSelecionado(CondicaoPesquisa condicaoPesquisaSelecionado) {
		this.condicaoPesquisaSelecionado = condicaoPesquisaSelecionado;
	}

	public List<SelectItem> getListaCondicaoPesquisa() {
		listaCondicaoPesquisa = new ArrayList<SelectItem>();
		for (CondicaoPesquisa condicaoPesquisa : CondicaoPesquisa.values()) {
			listaCondicaoPesquisa
					.add(new SelectItem(condicaoPesquisa, condicaoPesquisa.toString()));
		}	
		
		return listaCondicaoPesquisa;
	}
	
	public ObjetoCampoConsulta getObjetoCampoPesquisaSelecionado() {
		return objetoCampoPesquisaSelecionado;
	}

	public void setObjetoCampoPesquisaSelecionado(ObjetoCampoConsulta objetoCampoPesquisaSelecionado) {
		
		if (objetoCampoPesquisaSelecionado != null) {
			for (Field field : getClassImplement().getDeclaredFields()) {
				if (field.isAnnotationPresent(IdentificaCampoPesquisa.class)) {
					if (objetoCampoPesquisaSelecionado.getCampoBanco()
							.equalsIgnoreCase(field.getName())) {
						String descricaoCampo = field.getAnnotation(
								IdentificaCampoPesquisa.class).descricaoCampo();
						objetoCampoPesquisaSelecionado.setDescricao(descricaoCampo);
						objetoCampoPesquisaSelecionado.setTipoClass(field.getType()
								.getCanonicalName());
						objetoCampoPesquisaSelecionado.setPrincipal(field.getAnnotation(IdentificaCampoPesquisa.class).principal());
						break;
					}

				}
			}
		}
		this.objetoCampoPesquisaSelecionado = objetoCampoPesquisaSelecionado;	
		
	}
	
	public List<SelectItem> getListaCampoPesquisa() {
		
		listaCampoPesquisa = new ArrayList<SelectItem>();
		List<ObjetoCampoConsulta> listTemp = new ArrayList<ObjetoCampoConsulta>();
		for (Field field : getClassImplement().getDeclaredFields()) {
			if (field.isAnnotationPresent(IdentificaCampoPesquisa.class)) {
				String descricao = field.getAnnotation(
						IdentificaCampoPesquisa.class).descricaoCampo();
				String descricaoCampoConsulta = field.getAnnotation(
						IdentificaCampoPesquisa.class).campoConsulta();
				int isPrincipal = field.getAnnotation(
						IdentificaCampoPesquisa.class).principal();

				ObjetoCampoConsulta objetoCampoConsulta = new ObjetoCampoConsulta();
				objetoCampoConsulta.setDescricao(descricao);
				objetoCampoConsulta.setCampoBanco(descricaoCampoConsulta);
				objetoCampoConsulta.setTipoClass(field.getType()
						.getCanonicalName());
				objetoCampoConsulta.setPrincipal(isPrincipal);

				listTemp.add(objetoCampoConsulta);

			}
		}

		ordernarReverse(listTemp);

		for (ObjetoCampoConsulta objetoCampoConsulta : listTemp) {
			listaCampoPesquisa.add(new SelectItem(objetoCampoConsulta));
		}
		
		return listaCampoPesquisa;
	}
	
	private void ordernarReverse(List<ObjetoCampoConsulta> listTemp) {
		Collections.sort(listTemp, new Comparator<ObjetoCampoConsulta>() {
			@Override
			public int compare(ObjetoCampoConsulta objeto1,
					ObjetoCampoConsulta objeto2) {
				return objeto1.getPrincipal().compareTo(objeto2.getPrincipal());
			}
		});

		 //Collections.reverse(listTemp);
	}

	protected int totalRegistroConsulta() throws Exception {
		Query query = getController().obterQuery(
				" select count(entity) from " + getQueryConsulta());
		Number result = (Number) query.uniqueResult();
		return result.intValue();
	}
	
	protected String getSqlLazyQuery() throws Exception {
			StringBuilder sql = new StringBuilder();
			sql.append(" select entity from ");
			sql.append(getQueryConsulta());
			sql.append(" order by entity.");
			sql.append(objetoCampoPesquisaSelecionado.getCampoBanco());
			return sql.toString();
		}
		
		/*
		 * Retorna Query para consulta
		 */
		private StringBuilder getQueryConsulta() throws Exception {
			valorPesquisa = new  UtilitarioRegex().retiraAcentos(valorPesquisa);
			StringBuilder sql = new StringBuilder();
			sql.append(getClassImplement().getSimpleName());
			sql.append(" entity where ");

			sql.append(" retira_acentos(upper(cast(entity.");
			sql.append(objetoCampoPesquisaSelecionado.getCampoBanco());
			sql.append(" as text))) ");

			if (condicaoPesquisaSelecionado.name().equals(
					CondicaoPesquisa.IGUAL_A.name())) {
				sql.append(" = retira_acentos(upper('");
				sql.append(valorPesquisa);
				sql.append("'))");
			} else if (condicaoPesquisaSelecionado.name().equals(
					CondicaoPesquisa.CONTEM.name())) {
				sql.append(" like retira_acentos(upper('%");
				sql.append(valorPesquisa);
				sql.append("%'))");
			} else if (condicaoPesquisaSelecionado.name().equals(
					CondicaoPesquisa.INICIA_COM.name())) {
				sql.append(" like retira_acentos(upper('");
				sql.append(valorPesquisa);
				sql.append("%'))");
			} else if (condicaoPesquisaSelecionado.name().equals(
					CondicaoPesquisa.TERMINA_COM.name())) {
				sql.append(" like retira_acentos(upper('%");
				sql.append(valorPesquisa);
				sql.append("'))");
			}
			sql.append(" ");
			sql.append(condicaoAndParaPesquisa());
			return sql;
		}
	
}
	
