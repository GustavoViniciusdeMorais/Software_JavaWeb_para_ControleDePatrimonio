package br.com.projetointegrador.model.bem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.model.tipoDeBem.TipoDeBem;
import br.com.projetointegrador.util.ConnectionFactory;

public class BemDao extends ConnectionFactory {

	/*
	 * CREATE TABLE bem( id SERIAL, nome VARCHAR, dtAquisicao DATE,
	 * valorAquisicao NUMERIC(15,2), vidaUtil INT, valorResidual INT,
	 * valorContabil NUMERIC(15,2), status VARCHAR, valorDeVenda NUMERIC(15,2),
	 * dtVenda DATE, turno VARCHAR, tipo VARCHAR, statusDeUso VARCHAR,
	 * tempoDeUso INT, valorDepreciado NUMERIC(15,2), PRIMARY KEY (id) );
	 */

	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public void insert(Bem obj) {
		String sql = "INSERT INTO bem (nome, dtAquisicao, tipo, valorAquisicao, vidaUtil, valorResidual "
				+ ", statusDeUso, turno, tempoDeUso, status)"
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			con = openConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, obj.getNome());
			ps.setDate(2, new Date(obj.getDtAquisicao().getTime()));
			ps.setLong(3, obj.getTipo().getCodigo());
			ps.setDouble(4, obj.getValorAquisicao());
			ps.setInt(5, obj.getVidaUtil());
			ps.setInt(6, obj.getValorResidual());
			ps.setString(7, obj.getUsado());
			ps.setString(8, obj.getTurno());
			ps.setInt(9, obj.getTempoDeUso());
			ps.setString(10, "Ativo");
			// ps.setInt(2, obj.getIdade());
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

	public List<Bem> selectAll() {
		List<Bem> listaBens = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT id, nome, dtAquisicao, valorAquisicao, vidaUtil, valorResidual, valorContabil, "
				+ "status, valorDeVenda, dtVenda, turno, tipo, statusDeUso, tempoDeUso, valorDepreciado, "
				+ "codigo, categoria "
				+ "FROM bem INNER JOIN tipoDeBem ON bem.tipo = tipoDeBem.codigo ";
		try {
			con = openConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			listaBens = new ArrayList<Bem>();
			while (rs.next()) {
				Bem objBem = new Bem();
				TipoDeBem objT = new TipoDeBem();
				objBem.setId(rs.getLong("id"));
				objBem.setNome(rs.getString("nome"));
				objBem.setDtAquisicao(rs.getDate("dtAquisicao"));
				objBem.setValorAquisicao(rs.getDouble("valorAquisicao"));
				objBem.setVidaUtil(rs.getInt("vidaUtil"));
				objBem.setValorResidual(rs.getInt("valorResidual"));
				objBem.setValorContabil(rs.getDouble("valorContabil"));
				objBem.setStatus(rs.getString("status"));
				objBem.setValorDeVenda(rs.getDouble("valorDeVenda"));
				objBem.setDtVenda(rs.getDate("dtVenda"));
				objBem.setTurno(rs.getString("turno"));
				objT.setCodigo(rs.getLong("codigo"));
				System.out.println("codigo da categoria: "+objT.getCodigo());
				objT.setCategoria(rs.getString("categoria"));
				objBem.setTipo(objT);
				objBem.setUsado(rs.getString("statusDeUso"));
				objBem.setTempoDeUso(rs.getInt("tempoDeUso"));
				objBem.setValorDepreciado(rs.getDouble("valorDepreciado"));
				// p.setDataNascimento(rs.getDate("dataNascimento"));
				listaBens.add(objBem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closeConnection(con, ps);
			closeConnection(con, ps, rs);
		}
		return listaBens;
	}

	public List<Bem> selectByTipo(String status, Integer tipo) {
		List<Bem> listaBens = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT id, nome, dtAquisicao, valorAquisicao, vidaUtil, valorResidual, valorContabil, "
				+ "status, valorDeVenda, dtVenda, turno, tipo, statusDeUso, tempoDeUso, valorDepreciado, "
				+ "codigo, categoria "
				+ "FROM bem INNER JOIN tipoDeBem ON bem.tipo = tipoDeBem.codigo "
				+ "WHERE tipo = ? AND status = ?";
		try {
			con = openConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, tipo);
			ps.setString(2, status);
			rs = ps.executeQuery();
			listaBens = new ArrayList<Bem>();
			while (rs.next()) {
				Bem objBem = new Bem();
				TipoDeBem objT = new TipoDeBem();
				objBem.setId(rs.getLong("id"));
				objBem.setNome(rs.getString("nome"));
				objBem.setDtAquisicao(rs.getDate("dtAquisicao"));
				objBem.setValorAquisicao(rs.getDouble("valorAquisicao"));
				objBem.setVidaUtil(rs.getInt("vidaUtil"));
				objBem.setValorResidual(rs.getInt("valorResidual"));
				objBem.setValorContabil(rs.getDouble("valorContabil"));
				objBem.setStatus(rs.getString("status"));
				objBem.setValorDeVenda(rs.getDouble("valorDeVenda"));
				objBem.setDtVenda(rs.getDate("dtVenda"));
				objBem.setTurno(rs.getString("turno"));
				objT.setCategoria(rs.getString("categoria"));
				objT.setCodigo(rs.getLong("codigo"));
				objBem.setTipo(objT);
				objBem.setUsado(rs.getString("statusDeUso"));
				objBem.setTempoDeUso(rs.getInt("tempoDeUso"));
				objBem.setValorDepreciado(rs.getDouble("valorDepreciado"));
				// p.setDataNascimento(rs.getDate("dataNascimento"));
				listaBens.add(objBem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closeConnection(con, ps);
			closeConnection(con, ps, rs);
		}
		return listaBens;
	}

	

	public void updateBaixarOBem(Bem obj) {
		// valorDepreciado = da
		String sql = "UPDATE bem SET dtVenda = ?, valorDeVenda = ?, valorContabil = ?, valorDepreciado = ?,"
				+ " status = ? " + "WHERE id = ?";
		try {
			con = openConnection();
			ps = con.prepareStatement(sql);
			// ps.setString(1, obj.getNome()); ps.setDate(2, new
			// Date(obj.getDtAquisicao().getTime()));
			ps.setDate(1, new Date(obj.getDtVenda().getTime()));
			ps.setDouble(2, obj.getValorDeVenda());
			ps.setDouble(3, obj.getValorContabil());
			ps.setDouble(4, obj.getValorDepreciado());
			ps.setString(5, "Inativo");
			ps.setLong(6, obj.getId());
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
	
	public void update(Bem obj) {
		// valorDepreciado = da
		String sql = "UPDATE bem SET nome = ?, dtAquisicao = ?, tipo = ?, valorAquisicao = ?, "
				+ "vidaUtil = ?, valorResidual = ?, "
				+ " statusDeUso = ?, turno = ?, tempoDeUso = ?, status = ? "
				+ "WHERE id = ?";
		try {
			con = openConnection();
			ps = con.prepareStatement(sql);
			// ps.setString(1, obj.getNome()); ps.setDate(2, new
			// Date(obj.getDtAquisicao().getTime()));
			ps.setString(1, obj.getNome());
			ps.setDate(2, new Date(obj.getDtAquisicao().getTime()));
			ps.setLong(3, obj.getTipo().getCodigo());
			ps.setDouble(4, obj.getValorAquisicao());
			ps.setInt(5, obj.getVidaUtil());
			ps.setInt(6, obj.getValorResidual());
			ps.setString(7, obj.getUsado());
			ps.setString(8, obj.getTurno());
			ps.setInt(9, obj.getTempoDeUso());
			ps.setString(10, "Ativo");
			ps.setLong(11, obj.getId());
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
