package br.com.helpIT.project.bean.view;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.helpIT.model.classes.Entidade;
import br.com.helpIT.project.geral.controller.EntidadeController;
import br.com.helpIT.project.geral.controller.SessionController;

@Scope(value = "session")
@Component(value = "contextoBeanView")
public class ContextoBeanView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String USER_LOGADO_SESSAO = "userLogadoSessao";
	
	@Autowired
	private EntidadeController entidadeController;
	
	@Autowired
	private SessionController sessionController;
	
	
	/**
	 * 
	 * @return String Usuário logado user principal, possui todas as informações do usuário logado
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}	
	
	public Entidade getEntidadeLogada() throws Exception {
	       
		Entidade entidade = (Entidade) getExternalContext().getSessionMap().get(USER_LOGADO_SESSAO);
		
		if (entidade == null || (entidade != null && !entidade.getEnt_login().equals(getUserPrincipal()))) {
			if (getAuthentication().isAuthenticated()) {
				entidadeController.updateUltimoAcessoUser(getAuthentication().getName());
				entidade = entidadeController.findUserLogado(getAuthentication().getName());
				getExternalContext().getSessionMap().put(USER_LOGADO_SESSAO, entidade);
				sessionController.addSession(entidade.getEnt_login(), (HttpSession) getExternalContext().getSession(false));
			}
		}
		return entidade;
	}
	
	/**
	 * 
	 * @return ExternalContext da application
	 */
	public ExternalContext getExternalContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		return external;
	}
	
	/**
	 * 
	 * @return String Usuário logado user principal
	 */
	public String getUserPrincipal() {
		return getExternalContext().getUserPrincipal().getName();
	}

	/**
	 * Retorna se o usuario logado possui os acesso passados como paramentro, este metodo destina-se ao uso onde não é possivel usar
	 * as tags do spring security
	 */
	public boolean possuiAcesso(String... acessos) {
		for (String acesso : acessos) {
			for (GrantedAuthority autorizacao : getAuthentication().getAuthorities()){ 
				if (autorizacao.getAuthority().trim().equals(acesso.trim())){
					return true;
				}
			}
		}
		return false;
	}		

}
