package Controller;


import DTO.ClienteDTO;
import DTO.PedidoDTO;
import DTO.PizzaDTO;
import Exceções.CPFInexistenteException;
import Exceções.CampoVazioException;
import Exceções.ClienteExistenteException;
import Model.Atendente;
import Model.Cliente;
import Model.Pedido;

public class AtendenteController {
	
	private Atendente atendente = new Atendente();
	private Cliente cliente = new Cliente();
	private Pedido pedido = new Pedido();
	
	public PedidoDTO recuperarDadosDeTodosPedidos(){
		return atendente.recuperarDadosDeTodosPedidos();
	}
	
	public PizzaDTO recuperarSaboresDePizzas(){
		return atendente.getSaboresDePizzas();
	}
 	
	
	public PizzaDTO recuperarPrecoDaFatia(PizzaDTO pizzaDTO) {
		return atendente.getPrecoDaFatia(pizzaDTO);
	}
	
	public ClienteDTO recuperarEndereco(ClienteDTO clienteDTO) {
		return cliente.getEndereco(clienteDTO);
	}
	
	public void adicionarPedido(PedidoDTO pedidoDTO) {
		atendente.adicionarPedido(pedidoDTO);
	}
	
	
	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException {
		cliente.cadastrarCliente(clienteDTO);
	}
	
	public ClienteDTO validarCPF(ClienteDTO clienteDTO) throws CPFInexistenteException, CampoVazioException {
		return cliente.validarCPF(clienteDTO);
	}

	
	
	public PedidoDTO calcularPrecoDoPedido(PedidoDTO pedidoDTO) {
		return pedido.calcularPrecoDoPedido(pedidoDTO);
	}
	
	
	
	
	

}
