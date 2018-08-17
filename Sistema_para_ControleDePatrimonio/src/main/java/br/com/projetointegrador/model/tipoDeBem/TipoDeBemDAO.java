package br.com.projetointegrador.model.tipoDeBem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.util.ConnectionFactory;

public class TipoDeBemDAO extends ConnectionFactory {

	/*
	 * CREATE TABLE tipoDeBem( codigo SERIAL, categoria VARCHAR, PRIMARY KEY
	 * (codigo) );
	 */

	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public void insert(TipoDeBem tipo) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO tipoDeBem (categoria) VALUES (?)";

		try {
			con = openConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, tipo.getCategoria());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closeConnection(con, ps);
			closeConnection(con, ps, rs);
		}
	}

	public List<TipoDeBem> selectAll() {
		List<TipoDeBem> tipo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT codigo, categoria FROM tipoDeBem";
		try {
			con = openConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			tipo = new ArrayList<TipoDeBem>();
			while (rs.next()) {
				TipoDeBem objT = new TipoDeBem();
				objT.setCodigo(rs.getLong("codigo"));
				objT.setCategoria(rs.getString("categoria"));
				tipo.add(objT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closeConnection(con, ps);
			closeConnection(con, ps, rs);
		}
		return tipo;
	}

	public void delete(TipoDeBem objT) {
		String sql = "DELETE FROM tipoDeBem WHERE codigo = ?";
		try {
			con = openConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, objT.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("---------------------");
			System.err.println("Erro: " + e.getMessage());
			e.printStackTrace();
			System.err.println("---------------------");
		} finally {
			// closeConnection(con, ps);
			closeConnection(con, ps, rs);
		}
	}

	public void update(TipoDeBem objT) {
		String sql = "UPDATE tipoDeBem SET categoria = ? " + "WHERE codigo = ?";
		try {
			con = openConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, objT.getCategoria());
			ps.setLong(2, objT.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("---------------------");
			System.err.println("Erro: " + e.getMessage());
			e.printStackTrace();
			System.err.println("---------------------");
		} finally {
			// closeConnection(con, ps);
			closeConnection(con, ps, rs);
		}
	}

}
