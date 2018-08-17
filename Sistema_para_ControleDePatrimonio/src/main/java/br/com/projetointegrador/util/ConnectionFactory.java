package br.com.projetointegrador.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactory {

	private final String DRIVER = "org.postgresql.Driver";
	private final String URL = "jdbc:postgresql://localhost:5432/bd_scgtools";
	private final String USER = "postgres";
	private final String PASSWORD = "gustavo";
	
	public void closeConnection(Connection con, PreparedStatement ps, ResultSet rs){
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (con != null) con.close();
		} catch (Exception e){
			System.err.println("---------------------");
			System.err.println("Erro: " + e.getMessage());
			e.printStackTrace();
			System.err.println("---------------------");
		}
	}
	
	public Connection openConnection(){
	    Connection con = null;
	    try {
	    	Class.forName(DRIVER);
	    	con = DriverManager.getConnection(URL, USER, PASSWORD);
	    } catch (Exception e){
	    	System.err.println("---------------------");
			System.err.println("Erro: " + e.getMessage());
			e.printStackTrace();
			System.err.println("---------------------");
	    }
	    return con;
	}
	
	
}
