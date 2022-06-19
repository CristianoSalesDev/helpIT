package br.com.helpIT.project.geral.controller;

import java.util.HashMap;

import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;

@ApplicationScoped
public class SessionControllerImp implements SessionController {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, HttpSession> hashMap = new HashMap<String, HttpSession>();

	@Override
	public void addSession(String keyLoginUser, HttpSession httpSession) {
		
		hashMap.put(keyLoginUser, httpSession);
	}

	@Override
	public void invalidateSession(String keyLoginUser) {
		
		HttpSession session = hashMap.get(keyLoginUser);
		if (session != null){ // remove sess�o do usu�rio passado como parametro
			try {
				session.invalidate();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("n�o tem usu�rio");
		}
		
		hashMap.remove(keyLoginUser);		
	}

}
