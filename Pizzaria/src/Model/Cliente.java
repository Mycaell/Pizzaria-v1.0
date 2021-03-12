package Model;

import DAO.ClienteDAO_Postgre;
import DTO.ClienteDTO;
import Exceções.CPFInexistenteException;
import Exceções.CampoVazioException;
import Exceções.ClienteExistenteException;
import Interfaces.ClienteInterface;



public class Cliente{
	
	private String nome;
	private String sobrenome;
	private String CPF;
	private String telefone;
	private String bairro;
	private String rua;
	private int numeroDaCasa;
	private String endereço;
	private int funcionarioQueRealizouOCadastro;

	private ClienteInterface CDP = new ClienteDAO_Postgre();
	
	
	public Cliente() {
		
	}
	
	public Cliente(String nome, String sobrenome, String CPF, String telefone, String bairro, String rua, int numeroDaCasa, int funcionarioQueRealizouOCadastro) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.CPF = CPF;
		this.telefone = telefone;
		this.bairro = bairro;
		this.rua = rua;
		this.numeroDaCasa = numeroDaCasa;
		this.endereço = bairro+", "+rua+", "+numeroDaCasa;
		this.funcionarioQueRealizouOCadastro = funcionarioQueRealizouOCadastro;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEndereço() {
		return endereço;
	}
	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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
	public int getFuncionarioQueRealizouOCadastro() {
		return funcionarioQueRealizouOCadastro;
	}
	public void setFuncionarioQueRealizouOCadastro(int funcionarioQueRealizouOCadastro) {
		this.funcionarioQueRealizouOCadastro = funcionarioQueRealizouOCadastro;
	}
	
	
	
	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException {
		CDP.cadastrarCliente(clienteDTO);
	}
	
	public void excluirCliente(ClienteDTO clienteDTO) {
		CDP.excluirCliente(clienteDTO);
	}
	
	public ClienteDTO recuperarDadosDeTodosOsClientes(){
		return CDP.recuperarDadosDeTodosOsClientes();
	}

	
	public ClienteDTO validarCPF(ClienteDTO clienteDTO) throws CPFInexistenteException, CampoVazioException {
		return CDP.validarCPF(clienteDTO);
	}

	public ClienteDTO getEndereco(ClienteDTO clienteDTO) {
		return CDP.getEndereco(clienteDTO);
	}
	
	
	
	
}
