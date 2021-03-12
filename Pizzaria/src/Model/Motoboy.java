package Model;


import DAO.PedidoDAO_Postgre;
import DTO.PedidoDTO;
import Interfaces.PedidoInterface;


public class Motoboy extends Funcionario{


	private PedidoInterface PDP = new PedidoDAO_Postgre();
	
	
	public PedidoDTO recuperarDadosDetodosPedidosProntosParaEntrega(){
		return PDP.recuperarDadosDetodosPedidosProntosParaEntrega();
	}
	
	public void realizarEntregaDePedido(PedidoDTO pedidoDTO) {
		PDP.realizarEntregaDePedido(pedidoDTO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
