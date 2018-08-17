package br.com.projetointegrador.model.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.projetointegrador.util.ConnectionFactory;

public class LoginDAO extends ConnectionFactory{

	public Usuario selectByUserFromLoginAndSenha(Usuario usuario){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT id, nome, tipo FROM usuario WHERE login = ? AND senha = ?";
		Usuario user = null;
		try {
			con = openConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());
			rs = ps.executeQuery();
			if (rs.next()){
				user = new Usuario();
				user.setId(rs.getLong("id"));
				user.setNome(rs.getString("nome"));
				user.setTipo(rs.getString("tipo"));
				System.out.println(user.getNome()+"-----------------------");
			}
		}catch (Exception e){
			System.err.println("Erro: ");
			e.printStackTrace();
		}finally {
			closeConnection(con, ps, rs);
		}
		return user;
	}
	
}
