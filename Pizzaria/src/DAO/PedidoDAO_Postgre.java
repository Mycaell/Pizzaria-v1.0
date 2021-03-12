package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import DTO.ClienteDTO;
import DTO.PedidoDTO;
import Interfaces.PedidoInterface;

public class PedidoDAO_Postgre implements PedidoInterface{

	private Conexao conexao = new Conexao();
	private PreparedStatement preparador;
	private ResultSet resultado;
	
	
	
	@Override
	public void adicionarPedido(PedidoDTO pedidoDTO) {

		String sql = "INSERT INTO pedido (cliente,fatias,sabores,preco,endereco,aberto,pronto,entregue,criacao,idatendente) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);

			preparador.setString(1, pedidoDTO.getCPFcliente());
			preparador.setInt(2, pedidoDTO.getQtdFatias());
			
			
			String sabores = "";
			for (String sabor : pedidoDTO.getSaboresQueCompoemAPizza()) {
				sabores += sabor + ", ";
			}
			
//			retirando os dois últimos caracteres (, ) da string sabores
			sabores = sabores.substring(0, sabores.length() - 2);
			
			preparador.setString(3, sabores);

			
			preparador.setFloat(4, pedidoDTO.getPreco());
			preparador.setString(5, pedidoDTO.getEnderecoDoCliente());
			preparador.setBoolean(6, pedidoDTO.isAberto());
			preparador.setBoolean(7, pedidoDTO.isPronto());
			preparador.setBoolean(8, pedidoDTO.isEntregue());
			
			Timestamp criacao = new Timestamp(pedidoDTO.getDataDeCriacao().getTime());
			
			preparador.setTimestamp(9, criacao);
			preparador.setInt(10, pedidoDTO.getCodigoDoAtedente());
			
			preparador.execute();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		
	}

	@Override
	public void concluirPedido(PedidoDTO pedidoDTO) {

		String sql = "UPDATE pedido SET pronto=?, idpizzaiolo=?, preparo=? WHERE numero=?";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setBoolean(1, true);
			preparador.setInt(2, pedidoDTO.getCodigoDoPizzaiolo());

			Timestamp preparo = new Timestamp(Calendar.getInstance().getTime().getTime());
			preparador.setTimestamp(3, preparo);
			
			preparador.setInt(4, pedidoDTO.getNumeroDoPedido());
			
			preparador.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
	}

	
	
	@Override
	public void realizarEntregaDePedido(PedidoDTO pedidoDTO) {
			
		conexao.conectar();
		
		try {

			String sql = "UPDATE pedido SET entregue=?, idmotoboy=?, entrega=? WHERE numero=? ";
		
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setBoolean(1, true);
			
			preparador.setInt(2, pedidoDTO.getCodigoDoMotoboy());
			
			Timestamp entrega = new Timestamp(Calendar.getInstance().getTime().getTime());
			
			preparador.setTimestamp(3, entrega);

			preparador.setInt(4, pedidoDTO.getNumeroDoPedido());
			
			preparador.executeUpdate();


//						********						
//				!!!!	NOVO SQL !!!!!!!!!!
//						********			
			
			sql = "SELECT * FROM pedido WHERE numero=?";
			
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setInt(1, pedidoDTO.getNumeroDoPedido());
			
			resultado = preparador.executeQuery();
			
			
			while(resultado.next()) {
				
				String[] idDeSabores = resultado.getString("sabores").split(", ");
				
//						********						
//				!!!!	NOVO SQL !!!!!!!!!!
//						********			
		
				sql = "UPDATE pizza SET quantidade_vendida=quantidade_vendida+4 WHERE id=?";
//				4 é um valor especificado por mim (o dono)
				
				preparador = conexao.getConexao().prepareStatement(sql);
				
				for (String idPizza : idDeSabores) {
					
					preparador.setInt(1, Integer.parseInt(idPizza));
					
					preparador.executeUpdate();
					
				}
				
			}
			
//						********			
//				!!!!	NOVO SQL !!!!!!!!!!
			
//						********			
			sql = "UPDATE contabilidade SET vendas=vendas+1, lucro=lucro+"+pedidoDTO.getPreco();
			
			preparador = conexao.getConexao().prepareStatement(sql);


			preparador.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}

	}

	@Override
	public PedidoDTO recuperarDadosDeTodosPedidosProntosParaPreparo() {

		ArrayList<String[]> dadosDoPedido = new ArrayList<String[]>();
		
		String sql = "SELECT numero, fatias, sabores FROM pedido WHERE aberto=? AND pronto=?";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setBoolean(1, true);
			preparador.setBoolean(2, false);
			
			resultado = preparador.executeQuery();
			
			while(resultado.next()){
				String[] linha = new String[4];
				linha[0] = resultado.getString("numero");
				
				
				int fatias = Integer.parseInt(resultado.getString("fatias"));
				
				if(fatias == 4) {
					linha[1] = "Pequeno";
				}else if(fatias == 8) {
					linha[1] = "Médio";
				}else {
					linha[1] = "Grande";
				}
				
				linha[2] = Integer.toString(fatias);
				linha[3] = resultado.getString("sabores");
				
				dadosDoPedido.add(linha);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setDadosDosPedidosProntosParaPreparo(dadosDoPedido);

		return pedidoDTO;
	}

	
	
	
	@Override
	public PedidoDTO recuperarDadosDetodosPedidosProntosParaEntrega() {

		ArrayList<String[]> dadosDosPedidos = new ArrayList<String[]>();
		
		String sql = "SELECT numero, cliente, fatias, sabores, preco, endereco FROM pedido WHERE pronto=? AND entregue=?";
		
		conexao.conectar();
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			preparador.setBoolean(1, true);
			preparador.setBoolean(2, false);
			
			resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				String[] linha = new String[6];
				linha[0] = resultado.getString("numero");
				linha[1] = resultado.getString("cliente");
				linha[2] = resultado.getString("fatias");
				linha[3] = resultado.getString("sabores");
				linha[4] = resultado.getString("preco");
				linha[5] = resultado.getString("endereco");
				
				dadosDosPedidos.add(linha);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setDadosDosPedidosProntosParaEntrega(dadosDosPedidos);

		return pedidoDTO;
		
	}

	
	
	@Override
	public ClienteDTO recuperarDadosDeTodosPedidosDeUmCliente(ClienteDTO clienteDTO) {

		ArrayList<String[]> dadosDosPedidos = new ArrayList<String[]>();
		
		String sql = "SELECT * FROM pedido WHERE cliente=?";
		
		conexao.conectar();
		
		
		try {
			preparador = conexao.getConexao().prepareStatement(sql);

			preparador.setString(1, clienteDTO.getCPF());
			
			resultado = preparador.executeQuery();

			
			SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			while(resultado.next()) {
				String[] linha = new String[10];

				
				linha[0] = resultado.getString("numero");
				linha[1] = resultado.getString("cliente");
				
				int fatias = Integer.parseInt(resultado.getString("fatias"));
				
				if(fatias == 4) {
					linha[2] = "Pequeno";
				}else if(fatias == 8) {
					linha[2] = "Médio";
				}else {
					linha[2] = "Grande";
				}
				
				linha[3] = Integer.toString(fatias);
				linha[4] = resultado.getString("sabores");
				linha[5] = resultado.getString("preco");
				linha[6] = resultado.getString("endereco");
				
				Timestamp criacao = resultado.getTimestamp("criacao");
				linha[7] = formataData.format(criacao);


				Timestamp preparo = resultado.getTimestamp("preparo");
				if(preparo == null) {
					linha[8] = "Não foi preparado";
				}else {
					linha[8] = formataData.format(preparo);
				}


				Timestamp entrega = resultado.getTimestamp("entrega");
				if(entrega == null) {
					linha[9] = "Não foi Entregue";
				}else {
					linha[9] = formataData.format(entrega);
				}
				dadosDosPedidos.add(linha);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conexao.desconectar();
		}
	
		clienteDTO.setDadosDosPedidosDeUmUnicoCliente(dadosDosPedidos);
		return clienteDTO;
		
	}

	@Override
	public PedidoDTO getPedidos() {
		
		String sql = "SELECT numero, cliente, fatias, sabores, preco, pronto, entregue, criacao FROM pedido";
		
		conexao.conectar();

		ArrayList<String[]> dadosDosPedidos = new ArrayList<String[]>();

		try {
			preparador = conexao.getConexao().prepareStatement(sql);
			
			resultado = preparador.executeQuery();
			
			while(resultado.next()) {
					String[] linha = new String[9];
				
					linha[0] = resultado.getString("numero");
					
					linha[1] = resultado.getString("cliente");
					
					int fatias = Integer.parseInt(resultado.getString("fatias"));
					
					if(fatias == 4) {
						linha[2] = "Pequeno";
					}else if(fatias == 8) {
						linha[2] = "Médio";
					}else {
						linha[2] = "Grande";
					}
					
					linha[3] = Integer.toString(fatias);
					
				
					linha[4] = resultado.getString("sabores");
					linha[5] = resultado.getString("preco");
					
					if (resultado.getBoolean("pronto")) {
						linha[6] = "Sim";
					} else {
						linha[6] = "Não";
					}

					if (resultado.getBoolean("entregue")){
						linha[7] = "Sim";
					} else {
						linha[7] = "Não";
					}
					
					SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Timestamp criacao = resultado.getTimestamp("criacao");
					linha[8] = formataData.format(criacao);
					
					dadosDosPedidos.add(linha);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setPedidos(dadosDosPedidos);
		return pedidoDTO;
	}

	
	
	
	
	

}
