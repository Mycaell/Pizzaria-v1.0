package Model;

import DAO.PizzaDAO_Postgre;
import DTO.IngredienteDTO;
import DTO.PizzaDTO;
import Interfaces.PizzaInterface;

public class Pizza {

//	DEVE HAVER UM ID AQUI e esse deve ser gerado automaticamente no construtor;
	private String sabor;
	private float precoProFatia;
	private int qtdVendida;
	private int codigoDoGerenteQueCadastrou;
	private float custoPreparo;
	
	
	private PizzaInterface pizzaDAO = new PizzaDAO_Postgre();
	
	public Pizza() {
		
	}
	
	public Pizza(String sabor) {
		this.sabor = sabor;
	}

	public Pizza(String sabor, float precoPorFatia, int codigoDoGerenteQueCadastrou) {
		this.sabor = sabor;
		this.precoProFatia = precoPorFatia;
		this.codigoDoGerenteQueCadastrou = codigoDoGerenteQueCadastrou;
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
	
	public float getCustoPreparo() {
		return custoPreparo;
	}

	public void setCustoPreparo(float custoPreparo) {
		this.custoPreparo = custoPreparo;
	}

	public PizzaDTO recuperarDadosDeTodasPizzas(){
		return pizzaDAO.recuperarDadosDeTodasPizzas();
	}
	
	public void adicionarPizza(PizzaDTO pizzaDTO,  IngredienteDTO idto) {
		pizzaDAO.adicionarPizza(pizzaDTO, idto);
	}
	
	public void removerPizza(PizzaDTO pizzaDTO) {
		pizzaDAO.removerPizza(pizzaDTO);
	}

	public void editarPizza(PizzaDTO pizzaDTO) {
		pizzaDAO.editarPizza(pizzaDTO);
	}

	public PizzaDTO trocarIdsPorSabores(PizzaDTO pizzaDTO) {
//		trocando os ids pelo nome do sabor de suas respectivas pizzas
		String[] idDosSabores = pizzaDTO.getIds().split(", ");
		String sabores = "";
		
		for(int j = 0; j < idDosSabores.length; j++) {
			pizzaDTO.setIdDaPizza(Integer.parseInt(idDosSabores[j]));
			sabores += pizzaDAO.getSabor(pizzaDTO).getSabor()+", ";
		}
		
//		retirando os dois últimos caracteres (, ) da string sabores
		sabores = sabores.substring(0, sabores.length() - 2);
		
		pizzaDTO.setSabores(sabores);
		return pizzaDTO;
		
	}
	
	
}
