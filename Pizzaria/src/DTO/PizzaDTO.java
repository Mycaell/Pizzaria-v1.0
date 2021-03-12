package DTO;

import java.util.ArrayList;

public class PizzaDTO {
	
	private int idDaPizza;
	private String sabor;
	private float precoProFatia;
	private int qtdVendida;
	private int codigoDoGerenteQueCadastrou;
	
	private float custoPreparo;
	
	private ArrayList<String[]> dadosDasPizzas;
	
	private ArrayList<String> saboresDePizzas;
	
	
	private String novoSabor;
	private float novoPrecoPorFatia;
	
	private String ids;
	private String sabores;
	
	public PizzaDTO() {

	}
	
	
	
	public PizzaDTO(String sabor, float precoProFatia, float custoPreparo, int codigoDoGerenteQueCadastrou) {
		this.sabor = sabor;
		this.precoProFatia = precoProFatia;
		this.custoPreparo = custoPreparo;
		this.codigoDoGerenteQueCadastrou = codigoDoGerenteQueCadastrou;
	}

	
	
	public int getIdDaPizza() {
		return idDaPizza;
	}

	public void setIdDaPizza(int idDaPizza) {
		this.idDaPizza = idDaPizza;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public float getPrecoProFatia() {
		return precoProFatia;
	}

	public void setPrecoProFatia(float precoProFatia) {
		this.precoProFatia = precoProFatia;
	}

	public int getQtdVendida() {
		return qtdVendida;
	}

	public void setQtdVendida(int qtdVendida) {
		this.qtdVendida = qtdVendida;
	}

	public int getCodigoDoGerenteQueCadastrou() {
		return codigoDoGerenteQueCadastrou;
	}

	public void setCodigoDoGerenteQueCadastrou(int codigoDoGerenteQueCadastrou) {
		this.codigoDoGerenteQueCadastrou = codigoDoGerenteQueCadastrou;
	}

	public ArrayList<String[]> getDadosDasPizzas() {
		return dadosDasPizzas;
	}

	public void setDadosDasPizzas(ArrayList<String[]> dadosDasPizzas) {
		this.dadosDasPizzas = dadosDasPizzas;
	}

	public String getNovoSabor() {
		return novoSabor;
	}

	public void setNovoSabor(String novoSabor) {
		this.novoSabor = novoSabor;
	}

	public float getNovoPrecoPorFatia() {
		return novoPrecoPorFatia;
	}

	public void setNovoPrecoPorFatia(float novoPrecoPorFatia) {
		this.novoPrecoPorFatia = novoPrecoPorFatia;
	}

	public ArrayList<String> getSaboresDePizzas() {
		return saboresDePizzas;
	}

	public void setSaboresDePizzas(ArrayList<String> saboresDePizzas) {
		this.saboresDePizzas = saboresDePizzas;
	}

	
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSabores() {
		return sabores;
	}

	public void setSabores(String sabores) {
		this.sabores = sabores;
	}

	public float getCustoPreparo() {
		return custoPreparo;
	}

	public void setCustoPreparo(float custoPreparo) {
		this.custoPreparo = custoPreparo;
	}

	
	
	

}
