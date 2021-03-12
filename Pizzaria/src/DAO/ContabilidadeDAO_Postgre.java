package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.ContabilidadeDTO;
import Interfaces.ContablidadadeInterface;

public class ContabilidadeDAO_Postgre implements ContablidadadeInterface{

	private Conexao conexao = new Conexao();
	private ResultSet resultado;
	private PreparedStatement preparador;
	

//	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//	É NECESSÁRIO CRIAR A TUPLA DA CONTABILIDADE NA MÃO DIRETAMENTE PELO PGADMIN, SETANDO SEUS ATRIBUTOS COMO 0
//	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@Override
	public ContabilidadeDTO getLucro() {
		

		String sql = "Select lucro FROM contabilidade";
			
		conexao.conectar();
	
		ContabilidadeDTO contabilidadeDTO = new ContabilidadeDTO();

		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			resultado = preparador.executeQuery();

			while(resultado.next()) {
				contabilidadeDTO.setLucro(resultado.getDouble("lucro"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		return contabilidadeDTO;
	
	}

	@Override
	public ContabilidadeDTO getQtdVendas() {
	

		String sql = "Select vendas FROM contabilidade";
		
		conexao.conectar();
	
		ContabilidadeDTO contabilidadeDTO = new ContabilidadeDTO();

		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			resultado = preparador.executeQuery();

			while(resultado.next()) {
				contabilidadeDTO.setVendas(resultado.getInt("vendas"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		return contabilidadeDTO;
	
	}

	

}
