package DTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PedidoDTO {

	private int idDoPedido;
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
	
	private ArrayList<String[]> dadosDosPedidosProntosParaPreparo;
	private ArrayList<String[]> dadosDosPedidosProntosParaEntrega ;
	
	private ArrayList<String[]> pedidos;

	
	private String sabor1;
	private String sabor2;
	private String sabor3;
	
	public PedidoDTO() {
	}
	
	
	
	
	public PedidoDTO(String sabor1, String sabor2, String sabor3) {
		this.sabor1 = sabor1;
		this.sabor2 = sabor2;
		this.sabor3 = sabor3;
	}




	public PedidoDTO(int idDoPedido, String CPFcliente, int qtdFatias, ArrayList<String>saboresQueCompoemAPizza, float preco, String enderecoDoCliente, int codigoDoAtendente) {
		this.idDoPedido = idDoPedido;
		this.CPFcliente = CPFcliente;
		this.qtdFatias = qtdFatias;
		this.saboresQueCompoemAPizza = saboresQueCompoemAPizza;
		this.preco = preco;
		this.enderecoDoCliente = enderecoDoCliente;
		this.aberto = true;
		this.dataDeCriacao = Calendar.getInstance().getTime();
		this.codigoDoAtedente = codigoDoAtendente;
	}
	
	
	
	public int getIdDoPedido() {
		return idDoPedido;
	}

	public void setIdDoPedido(int idDoPedido) {
		this.idDoPedido = idDoPedido;
	}

	public int getNumeroDoPedido() {
		return numeroDoPedido;
	}
	public void setNumeroDoPedido(int numeroDoPedido) {
		this.numeroDoPedido = numeroDoPedido;
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

	public ArrayList<String[]> getDadosDosPedidosProntosParaPreparo() {
		return dadosDosPedidosProntosParaPreparo;
	}

	public void setDadosDosPedidosProntosParaPreparo(ArrayList<String[]> dadosDosPedidosProntosParaPreparo) {
		this.dadosDosPedidosProntosParaPreparo = dadosDosPedidosProntosParaPreparo;
	}

	public ArrayList<String[]> getDadosDosPedidosProntosParaEntrega() {
		return dadosDosPedidosProntosParaEntrega;
	}

	public void setDadosDosPedidosProntosParaEntrega(ArrayList<String[]> dadosDosPedidosProntosParaEntrega) {
		this.dadosDosPedidosProntosParaEntrega = dadosDosPedidosProntosParaEntrega;
	}

	public ArrayList<String[]> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<String[]> pedidos) {
		this.pedidos = pedidos;
	}

	public String getSabor1() {
		return sabor1;
	}

	public void setSabor1(String sabor1) {
		this.sabor1 = sabor1;
	}

	public String getSabor2() {
		return sabor2;
	}

	public void setSabor2(String sabor2) {
		this.sabor2 = sabor2;
	}

	public String getSabor3() {
		return sabor3;
	}

	public void setSabor3(String sabor3) {
		this.sabor3 = sabor3;
	}
	
	
	
}
