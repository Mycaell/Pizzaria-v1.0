package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.IngredienteDTO;
import Interfaces.IngredienteIntefarce;

public class IngredienteDAO_Postgre implements IngredienteIntefarce{

	private Conexao conexao = new Conexao();
	private PreparedStatement preparador;
	private ResultSet resultado;
	
	
	@Override
	public void adicionarIngrediente(IngredienteDTO ingredienteDTO) {
		
		String sql = "INSERT INTO ingrediente (nome, preco) VALUES (?,?)";
		
		conexao.conectar();
		

		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setString(1, ingredienteDTO.getNome());
			preparador.setFloat(2, ingredienteDTO.getPreco());
			
			preparador.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		
		
		
	}

	@Override
	public void removerIngrediente(IngredienteDTO ingredienteDTO) {
//		a fazer 
	}

	@Override
	public IngredienteDTO recuperarTodosIngredientes() {
		ArrayList<String[]> ingredientes = new ArrayList<String[]>();
		
//		ArrayList<String> ingredientesParaComboBox = new ArrayList<String>();
		
		
		String sql = "SELECT * FROM ingrediente";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);

			resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				String[] linha = new String[3];
				linha[0] = resultado.getString("id");
				linha[1] = resultado.getString("nome");
				linha[2] = resultado.getString("preco");
				
				ingredientes.add(linha);
				
//				ingredientesParaComboBox.add(resultado.getString("id")+"-"+resultado.getString("nome"));
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		
		IngredienteDTO ingredienteDTO = new IngredienteDTO();
		ingredienteDTO.setIngredientes(ingredientes);
//		ingredienteDTO.setIngredientesParaOComboBox(ingredientesParaComboBox);
		
		return ingredienteDTO;
		
	}

	@Override
	public IngredienteDTO recuperarIngredientePorID(IngredienteDTO ingredienteDTO) {


		String sql = "SELECT * FROM ingrediente WHERE id=?";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);

			preparador.setInt(1, ingredienteDTO.getId());
			
			resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				ingredienteDTO.setNome(resultado.getString("nome"));
				ingredienteDTO.setPreco(resultado.getFloat("preco"));
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		return ingredienteDTO;
		
	}
		
}
