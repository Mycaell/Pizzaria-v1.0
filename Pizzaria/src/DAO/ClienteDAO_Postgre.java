package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ClienteDTO;
import Exceções.CPFInexistenteException;
import Exceções.CampoVazioException;
import Exceções.ClienteExistenteException;
import Interfaces.ClienteInterface;

public class ClienteDAO_Postgre implements ClienteInterface{

	private PreparedStatement preparador;
	private Conexao conexao = new Conexao();
	private ResultSet resultado;
	
	
	@Override
	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException {
		
		conexao.conectar();
		
		String sql = "INSERT INTO cliente(nome,sobrenome,cpf,telefone,bairro,rua,casa,endereco,iddofuncionarioqueRealizouocadastro) VALUES(?,?,?,?,?,?,?,?,?)";
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			preparador.setString(1, clienteDTO.getNome());
			preparador.setString(2, clienteDTO.getSobrenome());
			preparador.setString(3, clienteDTO.getCPF());
			preparador.setString(4, clienteDTO.getTelefone());
			preparador.setString(5, clienteDTO.getBairro());
			preparador.setString(6, clienteDTO.getRua());
			preparador.setInt(7, clienteDTO.getNumeroDaCasa());
			preparador.setString(8, clienteDTO.getEndereço());
			preparador.setInt(9, clienteDTO.getFuncionarioQueRealizouOCadastro());
			
			preparador.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
	}

	@Override
	public void excluirCliente(ClienteDTO clienteDTO) {
		String sql = "DELETE FROM cliente WHERE cpf = ?";

		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setString(1, clienteDTO.getCPF());
			
			preparador.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conexao.desconectar();
		}
		
		
	}

	
	@Override
	public ClienteDTO recuperarDadosDeTodosOsClientes() {


		String sql = "SELECT nome, cpf, telefone, endereco FROM cliente";
		
		conexao.conectar();
		
		ArrayList<String[]> dadosDosClientes = new ArrayList<String[]>();

		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				String[] linha = new String[4];
				linha[0] = resultado.getString("nome");
				linha[1] = resultado.getString("cpf");
				linha[2] = resultado.getString("telefone");
				linha[3] = resultado.getString("endereco");
				
				dadosDosClientes.add(linha);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setDadosDosClientes(dadosDosClientes);
		return clienteDTO;
	
	}

	
	
	
	@Override
	public ClienteDTO validarCPF(ClienteDTO clienteDTO) throws CPFInexistenteException, CampoVazioException {

//		esse teste deve estar em alguma outra camada (eu acho)
		if(clienteDTO.getCPF().equals("   .   .   -  ")) {
			throw new CampoVazioException();
		}
	
		String sql = "SELECT * FROM cliente WHERE cpf=?";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);

			preparador.setString(1, clienteDTO.getCPF());
			
			resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				clienteDTO.setCpfValido(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		
		if(clienteDTO.isCpfValido()) {
			return clienteDTO;
		}
		
		throw new CPFInexistenteException();
		
	}

	
	@Override
	public ClienteDTO getEndereco(ClienteDTO clienteDTO) {

		String sql = "SELECT * FROM cliente WHERE cpf = ?";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setString(1, clienteDTO.getCPF());

			resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				clienteDTO.setEndereço(resultado.getString("endereco"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		return clienteDTO;
		
	}



}
