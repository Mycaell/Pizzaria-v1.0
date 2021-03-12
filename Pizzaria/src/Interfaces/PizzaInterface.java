package Interfaces;


import DTO.IngredienteDTO;
import DTO.PizzaDTO;

public interface PizzaInterface {
	
	
	
	
	public void editarPizza(PizzaDTO pizzaDTO);
	public void adicionarPizza(PizzaDTO pizzaDTO,  IngredienteDTO idto);
	public void removerPizza(PizzaDTO pizzaDTO);
	
	public PizzaDTO getSaboresDePizzas();
	public PizzaDTO getSabor(PizzaDTO pizzaDTO);
	public PizzaDTO getPrecoDaFatia(PizzaDTO pizzaDTO);
	
	
	public PizzaDTO recuperarDadosDeTodasPizzas();

}
