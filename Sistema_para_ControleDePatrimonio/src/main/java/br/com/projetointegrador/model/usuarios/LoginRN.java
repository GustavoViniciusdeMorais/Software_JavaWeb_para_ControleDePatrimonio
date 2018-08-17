package br.com.projetointegrador.model.usuarios;

import br.com.projetointegrador.model.usuarios.LoginDAO;

public class LoginRN {

	public String irPara(Usuario usuario) {
		String irPara = "error";
		Usuario usuarioConsultado = new LoginDAO()
				.selectByUserFromLoginAndSenha(usuario);
		if (usuarioConsultado != null) {
			if (usuarioConsultado.getTipo().equals("administrador")) {
				irPara = "lista_bens";
			} else {
				//irPara = "sucesso_login_convidado";
				irPara = "lista_bens";
			}
		}
		return irPara;
	}
}
