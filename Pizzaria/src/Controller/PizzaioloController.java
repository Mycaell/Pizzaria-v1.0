package Controller;


import DTO.PedidoDTO;
import Model.Pizzaiolo;

public class PizzaioloController {
	
	private Pizzaiolo pizzaiolo = new Pizzaiolo();

	public PedidoDTO recuperarDadosDeTodosPedidosProntos(){
		return pizzaiolo.recuperarDadosDeTodosPedidosProntos();
	}
	
	public void concluirPedido(PedidoDTO pedidoDTO) {
		pizzaiolo.concluirPedido(pedidoDTO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
