package Model;


import DAO.PedidoDAO_Postgre;
import DAO.PizzaDAO_Postgre;
import DTO.PedidoDTO;
import DTO.PizzaDTO;
import Interfaces.PedidoInterface;
import Interfaces.PizzaInterface;

public class Atendente extends Funcionario{
	

	private PedidoInterface PDP = new PedidoDAO_Postgre();
	
	
	private PizzaInterface pizzaDAO = new PizzaDAO_Postgre();
	
	
	
	public void adicionarPedido(PedidoDTO pedidoDTO) {
		PDP.adicionarPedido(pedidoDTO);
	}

	public PedidoDTO recuperarDadosDeTodosPedidos(){
		return PDP.getPedidos();
	}
	
	public PizzaDTO getSaboresDePizzas(){
		return pizzaDAO.getSaboresDePizzas();
	}
	
	public PizzaDTO getPrecoDaFatia(PizzaDTO pizzaDTO) {
		return pizzaDAO.getPrecoDaFatia(pizzaDTO);
	}

//	public PizzaDTO getSaborDePizza(PizzaDTO pizzaDTO) {
//		return pizzaDAO.getSabor(pizzaDTO);
//	}
//	
	
	

	
	
	
	
	
	
	
	
}	