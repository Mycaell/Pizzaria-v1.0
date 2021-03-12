package Interfaces;

import DTO.IngredienteDTO;

public interface IngredienteIntefarce {

	
	public void adicionarIngrediente(IngredienteDTO ingredienteDTO);
	public void removerIngrediente(IngredienteDTO ingredienteDTO);
	
	
	public IngredienteDTO recuperarTodosIngredientes();
	public IngredienteDTO recuperarIngredientePorID(IngredienteDTO ingredienteDTO);
	
}
