package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.FuncionarioDTO;
import Exceções.FuncionarioExistenteException;
import Exceções.FuncionarioNaoExistenteException;
import Interfaces.FuncionarioInterface;

public class FuncionarioDAO_Postgre implements FuncionarioInterface{

	private PreparedStatement preparador;
	private Conexao conexao = new Conexao();
	private ResultSet resultado;
	

	@Override
	public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioExistenteException {
		
		String sql = "INSERT INTO funcionario(nome,sobrenome,sexo,nascimento,endereço,telefone,cpf,email,senha,cargo,IDDoFuncionarioQueRealizouOCadastro) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setString(1, funcionarioDTO.getNome());
			preparador.setString(2, funcionarioDTO.getSobrenome());
			preparador.setString(3, Character.toString(funcionarioDTO.getSexo()));
			
			Date dataSQL = new Date(funcionarioDTO.getDataDeNascimento().getTime());
			preparador.setDate(4, dataSQL);
			
			preparador.setString(5, funcionarioDTO.getEndereço());
			preparador.setString(6, funcionarioDTO.getTelefone());
			preparador.setString(7, funcionarioDTO.getCPF());
			preparador.setString(8, funcionarioDTO.getEmail());
			preparador.setString(9, funcionarioDTO.getSenha());
			preparador.setString(10, funcionarioDTO.getCargo());
			preparador.setInt(11, funcionarioDTO.getCodigoDoFuncionarioQueRealizouOCadastro());

			preparador.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
	}

	@Override
	public void excluirFuncionario(FuncionarioDTO funcionarioDTO) {

		String sql = "DELETE FROM funcionario WHERE id=?";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setInt(1, funcionarioDTO.getCodigo());
			
			preparador.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
	}

	
	@Override
	public FuncionarioDTO recuperarDadosDeTodosFuncionaros() {
		

		String sql = "SELECT * FROM funcionario";
				
		conexao.conectar();
		
		ArrayList<String[]> dadosDosFuncionarios = new ArrayList<String[]>();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				String[] linha = new String[3];
				linha[0] = resultado.getString("nome");
				linha[1] = Integer.toString(resultado.getInt("id"));
				linha[2] = resultado.getString("cargo");

				dadosDosFuncionarios.add(linha);
			}
			
		}  catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}

		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setDadosDosFuncionarios(dadosDosFuncionarios);
		return funcionarioDTO;
	
	}
	
	
	@Override
	public FuncionarioDTO recuperarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExistenteException {

		String sql = "SELECT * FROM funcionario WHERE email=? AND senha=?";
	
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			

			preparador.setString(1, funcionarioDTO.getEmail());
			preparador.setString(2, funcionarioDTO.getSenha());
			
			resultado = preparador.executeQuery();
		
			while(resultado.next()) {
				funcionarioDTO.setCargo(resultado.getString("cargo"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
	
		return funcionarioDTO;
	
	}

	@Override
	public FuncionarioDTO getCodigo(FuncionarioDTO funcionarioDTO) {


		String sql = "SELECT * FROM funcionario WHERE email=?";
	
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setString(1, funcionarioDTO.getEmail());
			
			resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				funcionarioDTO.setCodigo(resultado.getInt("id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		return funcionarioDTO;
	}

	@Override
	public FuncionarioDTO getCargo(FuncionarioDTO funcionarioDTO) {
		

		String sql = "SELECT * FROM funcionario WHERE id=?";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setInt(1, funcionarioDTO.getCodigo());
			
			resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				funcionarioDTO.setCargo(resultado.getString("cargo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		return funcionarioDTO;
	}

	@Override
	public FuncionarioDTO getNome(FuncionarioDTO funcionarioDTO) {
		

		String sql = "SELECT * FROM funcionario WHERE id=?";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setInt(1, funcionarioDTO.getCodigo());
			
			resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				funcionarioDTO.setNome(resultado.getString("nome"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		return funcionarioDTO;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
