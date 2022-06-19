package br.com.helpIT.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.helpIT.project.bean.geral.ObjetoCampoConsulta;

@FacesConverter(forClass = ObjetoCampoConsulta.class)
public class ObjetoCampoConsultaConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.isEmpty()) {
			String[] vals = value.split("\\*");
			ObjetoCampoConsulta objetoCampoConsulta = new ObjetoCampoConsulta();
			objetoCampoConsulta.setCampoBanco(vals[0]);
			objetoCampoConsulta.setTipoClass(vals[1]);
			return objetoCampoConsulta;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			ObjetoCampoConsulta c = (ObjetoCampoConsulta) value;
			return c.getCampoBanco() + "*" + c.getTipoClass();
		} else {
			return "Não foi possível estabeler o valor";
		}
	}

}
