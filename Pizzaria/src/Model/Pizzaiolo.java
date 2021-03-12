package Model;


import DAO.PedidoDAO_Postgre;
import DTO.PedidoDTO;
import Interfaces.PedidoInterface;

public class Pizzaiolo extends Funcionario{


	private PedidoInterface PDP = new PedidoDAO_Postgre();
	
	
	
	public PedidoDTO recuperarDadosDeTodosPedidosProntos(){
		return PDP.recuperarDadosDeTodosPedidosProntosParaPreparo();
	}
	
	public void concluirPedido(PedidoDTO pedidoDTO) {
		PDP.concluirPedido(pedidoDTO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
