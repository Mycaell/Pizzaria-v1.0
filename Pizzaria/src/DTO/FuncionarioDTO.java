package DTO;

import java.util.ArrayList;
import java.util.Date;

public class FuncionarioDTO {

	private String nome;
	private String sobrenome;
	private char sexo;
	private Date dataDeNascimento;
	private String endereço;
	private String telefone;
	private String CPF;
	private String email;
	private String senha;
	private String cargo;
	private int codigo;
	private int codigoDoFuncionarioQueRealizouOCadastro;
	
	
	private ArrayList<String[]> dadosDosFuncionarios;
	
	public FuncionarioDTO() {
	
	}

	public FuncionarioDTO(String nome, String sobrenome, char sexo, Date dataDeNascimento, String endereço,
			String telefone, String cPF, String email, String senha, String cargo,
			int codigoDoFuncionarioQueRealizouOCadastro) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.dataDeNascimento = dataDeNascimento;
		this.endereço = endereço;
		this.telefone = telefone;
		CPF = cPF;
		this.email = email;
		this.senha = senha;
		this.cargo = cargo;
//		this.codigo = codigo;
		this.codigoDoFuncionarioQueRealizouOCadastro = codigoDoFuncionarioQueRealizouOCadastro;
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
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
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
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodigoDoFuncionarioQueRealizouOCadastro() {
		return codigoDoFuncionarioQueRealizouOCadastro;
	}
	public void setCodigoDoFuncionarioQueRealizouOCadastro(int codigoDoFuncionarioQueRealizouOCadastro) {
		this.codigoDoFuncionarioQueRealizouOCadastro = codigoDoFuncionarioQueRealizouOCadastro;
	}

	public ArrayList<String[]> getDadosDosFuncionarios() {
		return dadosDosFuncionarios;
	}

	public void setDadosDosFuncionarios(ArrayList<String[]> dadosDosFuncionarios) {
		this.dadosDosFuncionarios = dadosDosFuncionarios;
	}
	
	
	
}
