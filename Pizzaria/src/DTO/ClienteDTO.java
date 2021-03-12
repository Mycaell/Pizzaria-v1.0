package DTO;

import java.util.ArrayList;

public class ClienteDTO {

	private int idDoCliente;
	private String nome;
	private String sobrenome;
	private String CPF;
	private String telefone;
	private String bairro;
	private String rua;
	private int numeroDaCasa;
	private String endereço;
	private int funcionarioQueRealizouOCadastro;
	
	private ArrayList<String[]> dadosDosClientes;
	
	private ArrayList<String[]> dadosDosPedidosDeUmUnicoCliente;
	
	private boolean cpfValido;
	
	public ClienteDTO() {

	}
	
	public ClienteDTO(int idDoCliente, String nome, String sobrenome, String cPF, String telefone, String bairro, String rua,
			int numeroDaCasa, int funcionarioQueRealizouOCadastro) {
		this.idDoCliente = idDoCliente;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.CPF = cPF;
		this.telefone = telefone;
		this.bairro = bairro;
		this.rua = rua;
		this.numeroDaCasa = numeroDaCasa;
		this.endereço = bairro+", "+rua+", "+numeroDaCasa;
		this.funcionarioQueRealizouOCadastro = funcionarioQueRealizouOCadastro;
	}
	
	
	
	public int getIdDoCliente() {
		return idDoCliente;
	}

	public void setIdDoCliente(int idDoCliente) {
		this.idDoCliente = idDoCliente;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumeroDaCasa() {
		return numeroDaCasa;
	}
	public void setNumeroDaCasa(int numeroDaCasa) {
		this.numeroDaCasa = numeroDaCasa;
	}
	public String getEndereço() {
		return endereço;
	}
	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}
	public int getFuncionarioQueRealizouOCadastro() {
		return funcionarioQueRealizouOCadastro;
	}
	public void setFuncionarioQueRealizouOCadastro(int funcionarioQueRealizouOCadastro) {
		this.funcionarioQueRealizouOCadastro = funcionarioQueRealizouOCadastro;
	}

	public ArrayList<String[]> getDadosDosClientes() {
		return dadosDosClientes;
	}

	public void setDadosDosClientes(ArrayList<String[]> dadosDosClientes) {
		this.dadosDosClientes = dadosDosClientes;
	}

	public ArrayList<String[]> getDadosDosPedidosDeUmUnicoCliente() {
		return dadosDosPedidosDeUmUnicoCliente;
	}

	public void setDadosDosPedidosDeUmUnicoCliente(ArrayList<String[]> dadosDosPedidosDeUmUnicoCliente) {
		this.dadosDosPedidosDeUmUnicoCliente = dadosDosPedidosDeUmUnicoCliente;
	}

	public boolean isCpfValido() {
		return cpfValido;
	}

	public void setCpfValido(boolean cpfValido) {
		this.cpfValido = cpfValido;
	}
	
	
	
}
