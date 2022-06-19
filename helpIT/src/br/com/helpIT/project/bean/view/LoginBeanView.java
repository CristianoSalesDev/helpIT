package br.com.helpIT.project.bean.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.project.bean.geral.BeanManagedViewAbstract;
import br.com.helpIT.project.geral.controller.SessionControllerImp;
import br.com.helpIT.srv.interfaces.SrvLogin;

@Controller
@Scope(value="request")
@ManagedBean(name = "loginBeanView")
public class LoginBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SessionControllerImp sessionController;

	@Autowired
	private SrvLogin srvLogin;		
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@RequestMapping(value = "**/invalidar_session", method = RequestMethod.POST)
	public void invalidateSessionMetodo(HttpServletRequest httpServletRequest) throws Exception {
		
		String userLogadoSessao = null;
		
		if (httpServletRequest.getUserPrincipal() != null){
			 userLogadoSessao = httpServletRequest.getUserPrincipal().getName();
		}

		if (userLogadoSessao == null || (userLogadoSessao != null && userLogadoSessao.trim().isEmpty())) {
			userLogadoSessao = httpServletRequest.getRemoteUser();
		}
		
		if (userLogadoSessao != null && !userLogadoSessao.isEmpty())
			sessionController.invalidateSession(userLogadoSessao);
	}	

	public void invalidar(ActionEvent event) throws Exception {
		 RequestContext context = RequestContext.getCurrentInstance();
	        FacesMessage message = null;
	        boolean loggedIn = false;
	         
	        if(srvLogin.autentico(getUsername(), getPassword())) {
	        	sessionController.invalidateSession(getUsername());
	        	loggedIn = true;
	        } else {
	            loggedIn = false;
	            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acesso negado, login ou senha incorretos.", "");
	        }
	        
	        if (message != null)
	        	FacesContext.getCurrentInstance().addMessage("msg", message);
	        
	        context.addCallbackParam("loggedIn", loggedIn);
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
		

}
