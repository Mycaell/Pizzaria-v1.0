package Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import DAO.PedidoDAO_Postgre;
import DAO.PizzaDAO_Postgre;
import DTO.ClienteDTO;
import DTO.PedidoDTO;
import DTO.PizzaDTO;
import Interfaces.PedidoInterface;
import Interfaces.PizzaInterface;


public class Pedido {
	
//	DEVE HAVER UM ID AQUI e esse deve ser gerado automaticamente no construtor;
	private int numeroDoPedido;
	private String CPFcliente;
	private String enderecoDoCliente;
	private int qtdFatias;
	private ArrayList<String> saboresQueCompoemAPizza;
	private float preco;
	
	private boolean aberto;
	private boolean pronto;
	private boolean entregue;
	
	private Date dataDeCriacao;
	private Date dataDePreparo;
	private Date dataDeEntrega;
	
	private int codigoDoAtedente ;
	private int codigoDoPizzaiolo;
	private int codigoDoMotoboy;
	
	private PedidoInterface PDP = new PedidoDAO_Postgre();
	
	public Pedido() {
		
	}
	
	public Pedido(String CPFcliente, int qtdFatias, ArrayList<String>saboresQueCompoemAPizza, float preco, String enderecoDoCliente, int codigoDoAtendente) {
		this.CPFcliente = CPFcliente;
		this.qtdFatias = qtdFatias;
		this.saboresQueCompoemAPizza = saboresQueCompoemAPizza;
		this.preco = preco;
		this.enderecoDoCliente = enderecoDoCliente;
		this.aberto = true;
		this.dataDeCriacao = Calendar.getInstance().getTime();
		this.codigoDoAtedente = codigoDoAtendente;
	}

	public String getCPFcliente() {
		return CPFcliente;
	}
	public void setCPFcliente(String cPFcliente) {
		CPFcliente = cPFcliente;
	}
	public String getEnderecoDoCliente() {
		return enderecoDoCliente;
	}
	public void setEnderecoDoCliente(String enderecoDoCliente) {
		this.enderecoDoCliente = enderecoDoCliente;
	}

	public int getQtdFatias() {
		return qtdFatias;
	}
	public void setQtdFatias(int qtdFatias) {
		this.qtdFatias = qtdFatias;
	}

	public ArrayList<String> getSaboresQueCompoemAPizza() {
		return saboresQueCompoemAPizza;
	}

	public void setSaboresQueCompoemAPizza(ArrayList<String> saboresQueCompoemAPizza) {
		this.saboresQueCompoemAPizza = saboresQueCompoemAPizza;
	}

	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public int getNumeroDoPedido() {
		return numeroDoPedido;
	}

	public void setNumeroDoPedido(int numeroDoPedido) {
		this.numeroDoPedido = numeroDoPedido;
	}

	public boolean isPronto() {
		return pronto;
	}

	public void setPronto(boolean pronto) {
		this.pronto = pronto;
	}

	public boolean isEntregue() {
		return entregue;
	}

	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}

	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public Date getDataDePreparo() {
		return dataDePreparo;
	}

	public void setDataDePreparo(Date dataDePreparo) {
		this.dataDePreparo = dataDePreparo;
	}

	public Date getDataDeEntrega() {
		return dataDeEntrega;
	}

	public void setDataDeEntrega(Date dataDeEntrega) {
		this.dataDeEntrega = dataDeEntrega;
	}

	public int getCodigoDoAtedente() {
		return codigoDoAtedente;
	}

	public void setCodigoDoAtedente(int codigoDoAtedente) {
		this.codigoDoAtedente = codigoDoAtedente;
	}

	public int getCodigoDoPizzaiolo() {
		return codigoDoPizzaiolo;
	}

	public void setCodigoDoPizzaiolo(int codigoDoPizzaiolo) {
		this.codigoDoPizzaiolo = codigoDoPizzaiolo;
	}

	public int getCodigoDoMotoboy() {
		return codigoDoMotoboy;
	}

	public void setCodigoDoMotoboy(int codigoDoMotoboy) {
		this.codigoDoMotoboy = codigoDoMotoboy;
	}
	
		
	public ClienteDTO recuperarDadosDeTodosPedidosDeUmCliente(ClienteDTO clienteDTO){
		return PDP.recuperarDadosDeTodosPedidosDeUmCliente(clienteDTO);
	}
	
	public PedidoDTO calcularPrecoDoPedido(PedidoDTO pedidoDTO) {
		
		PizzaInterface pizzaDAO = new PizzaDAO_Postgre();
		
		PizzaDTO pizzaDTO = new PizzaDTO();
		
		int precoPedido = 0;
		
		if(pedidoDTO.getSabor1() != null) {
			String[] sabor1 = pedidoDTO.getSabor1().split("-");
			pizzaDTO.setIdDaPizza(Integer.parseInt(sabor1[0]));
			float precoSabor1 = pizzaDAO.getPrecoDaFatia(pizzaDTO).getPrecoProFatia();
			precoPedido += precoSabor1;
		}
		
		if(pedidoDTO.getSabor2() != null) {
			String[] sabor2 = pedidoDTO.getSabor2().split("-");
			pizzaDTO.setIdDaPizza(Integer.parseInt(sabor2[0]));
			float precoSabor2 = pizzaDAO.getPrecoDaFatia(pizzaDTO).getPrecoProFatia();
			precoPedido += precoSabor2;
		}
		
		if(pedidoDTO.getSabor3() != null) {
			String[] sabor3 = pedidoDTO.getSabor3().split("-");
			pizzaDTO.setIdDaPizza(Integer.parseInt(sabor3[0]));
			float precoSabor3 = pizzaDAO.getPrecoDaFatia(pizzaDTO).getPrecoProFatia();
			precoPedido += precoSabor3;
		}

		
		pedidoDTO.setPreco(precoPedido);
		
		return pedidoDTO;
		
	}
	
	
}
