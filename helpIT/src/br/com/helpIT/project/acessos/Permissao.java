package br.com.helpIT.project.acessos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Permissao {

	ADMIN("ADMIN", "Administrador"),
	USUARIO("USUARIO","Usuário Padrão"), 
	CADASTRO_ACESSAR("CADASTRO_ACESSAR", "Cadastro - Acessar"),
    FINANCIERO_ACESSAR("FINANCIERO_ACESSAR", "Financeiro - Acessar"),
	MENSAGEM_ACESSAR("MENSAGEM_ACESSAR", "Mensagem Recebida - Acessar"),
	INTEGRACAO_ACESSAR("INTEGRACAO_ACESSAR", "Integracao - Acessar"),
	PROCESSOS_ACESSAR("PROCESSOS_ACESSAR", "Almanaque de Processos - Acessar"),								
	
	BAIRRO_ACESSAR("BAIRRO_ACESSAR", "Bairro - Acessar"),
	BAIRRO_NOVO("BAIRRO_NOVO", "Bairro - Novo"),
	BAIRRO_EDITAR("BAIRRO_EDITAR", "Bairro - Editar"),
	BAIRRO_EXCLUIR("BAIRRO_EXCLUIR", "Bairro - Excluir"),
	
	MENSAGENS_ENVIAR_ACESSAR("MENSAGENS_ENVIAR_ACESSAR", "Enviar mensagem - Acessar"),
	MENSAGENS_ENVIAR_NOVO("MENSAGENS_ENVIAR_NOVO", "Enviar mensagem - Novo"),
	MENSAGENS_ENVIAR_EDITAR("MENSAGENS_ENVIAR_EDITAR", "Enviar mensagem - Editar"),
	MENSAGENS_ENVIAR_EXCLUIR("MENSAGENS_ENVIAR_EXCLUIR", "Enviar mensagem - Excluir"),	
	
	SETOR_ACESSAR("SETOR_ACESSAR", "Setor - Acessar"),
	SETOR_NOVO("SETOR_NOVO", "Setor - Novo"),
	SETOR_EDITAR("SETOR_EDITAR", "Setor - Editar"),
	SETOR_EXCLUIR("SETOR_EXCLUIR", "Setor - Excluir"),
	
	CARGO_ACESSAR("CARGO_ACESSAR", "Cargo - Acessar"),
	CARGO_NOVO("CARGO_NOVO", "Cargo - Novo"),
	CARGO_EDITAR("CARGO_EDITAR", "Cargo - Editar"),
	CARGO_EXCLUIR("CARGO_EXCLUIR", "Cargo - Excluir"),	
	
	FUNCIONARIO_ACESSAR("FUNCIONARIO_ACESSAR", "Funcionário - Acessar"),
	FUNCIONARIO_NOVO("FUNCIONARIO_NOVO", "Funcionário - Novo"),
	FUNCIONARIO_EDITAR("FUNCIONARIO_EDITAR", "Funcionário - Editar"),
	FUNCIONARIO_EXCLUIR("FUNCIONARIO_EXCLUIR", "Funcionário - Excluir");	
	
	private String valor = "";
	private String descricao = "";
	
	private Permissao(String name, String descricao) {
		this.valor = name;
		this.descricao = descricao;
	}	
	
	private Permissao() {

	}

	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return getValor();
	}
	
	public static List<Permissao> getListPermissao() {
		List<Permissao> permissoes = new ArrayList<Permissao>();
		for (Permissao permissao : Permissao.values()) {
			permissoes.add(permissao);
		}
		Collections.sort(permissoes, new Comparator<Permissao>() {

			@Override
			public int compare(Permissao o1, Permissao o2) {
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}
		});
		return permissoes;
	}
	
	
}
