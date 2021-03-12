package DTO;

import java.util.ArrayList;

public class IngredienteDTO {

	private int id;
	private String nome;
	private float preco;
	
	private ArrayList<String[]> ingredientes = new ArrayList<>();
//	private ArrayList<String> ingredientesParaOComboBox;
	
	
	private ArrayList<String> nomes = new ArrayList<>();
	
	public IngredienteDTO() {
		
	}
	
	public IngredienteDTO(String nome, float preco) {
		super();
		this.nome = nome;
		this.preco = preco;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public ArrayList<String[]> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(ArrayList<String[]> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public ArrayList<String> getNomes() {
		return nomes;
	}

	public void setNomes(ArrayList<String> nomes) {
		this.nomes = nomes;
	}

	
//	public ArrayList<String> getIngredientesParaOComboBox() {
//		return ingredientesParaOComboBox;
//	}
//
//	public void setIngredientesParaOComboBox(ArrayList<String> ingredientesParaOComboBox) {
//		this.ingredientesParaOComboBox = ingredientesParaOComboBox;
//	}

	
	
	
	
}
