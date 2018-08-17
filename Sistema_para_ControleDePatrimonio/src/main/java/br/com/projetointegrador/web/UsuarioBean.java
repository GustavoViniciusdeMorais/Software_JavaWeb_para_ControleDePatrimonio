package br.com.projetointegrador.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.projetointegrador.model.usuarios.LoginRN;
import br.com.projetointegrador.model.usuarios.Usuario;

@ManagedBean(name = "MBUsuario")
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String actionLogin() {
		return new LoginRN().irPara(usuario);
	}

}
