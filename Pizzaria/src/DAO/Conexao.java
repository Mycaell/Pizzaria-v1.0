package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private String url;
	private String usuario;
	private String senha;
	private Connection conexao;
	
	
	
	public Connection getConexao() {
		return conexao;
	}


	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}


	public Conexao() {
		url = "jdbc:postgresql://localhost:5432/Pizzaria";
		usuario = "postgres";
		senha = "cramunhao";
	}
	
	
	public void conectar() {
			try {
				Class.forName("org.postgresql.Driver");
				conexao = DriverManager.getConnection(url, usuario, senha);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao se conectar!");
			}
	}
	
	public void desconectar() {
			try {
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao se desconectar!");
				e.printStackTrace();
			}
	}
	
}
