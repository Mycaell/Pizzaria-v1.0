package Model;

import java.util.ArrayList;

import DAO.IngredienteDAO_Postgre;
import DTO.IngredienteDTO;
import Interfaces.IngredienteIntefarce;

public abstract class Ingrediente {

	
	private int id;
	private String nome;
	private float preco;
	
	private ArrayList<String> nomes = new ArrayList<>();
	
	
	private IngredienteIntefarce ingredienteDAO = new IngredienteDAO_Postgre();
	
	
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
	
	
	
	public final void adicionarIngrediente(IngredienteDTO ingredienteDTO) {
		ingredienteDAO.adicionarIngrediente(ingredienteDTO);
		
	}
	
	public final void removerIngrediente(IngredienteDTO ingredienteDTO) {
		ingredienteDAO.removerIngrediente(ingredienteDTO);
	}

	
	public final IngredienteDTO recuperarTodosIngredientes() {
		return ingredienteDAO.recuperarTodosIngredientes();
	}
	
	public final IngredienteDTO recuperarIngredientePorID(IngredienteDTO ingredienteDTO) {
		return ingredienteDAO.recuperarIngredientePorID(ingredienteDTO);
	}
	
	public ArrayList<String> getNomes() {
		return nomes;
	}

	public void setNomes(String nome) {
		nomes.add(nome);
	}
	
	
	
	
	
}
