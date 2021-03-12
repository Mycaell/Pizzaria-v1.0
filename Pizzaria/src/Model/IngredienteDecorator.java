package Model;

import java.util.ArrayList;

public abstract class IngredienteDecorator extends Ingrediente{

	private Ingrediente ingrediente;
	
	
	public IngredienteDecorator(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	
	
	public ArrayList<String> getNomes() {
		
		ArrayList<String> vetor = new ArrayList<>();
		
		for (String i : super.getNomes()) {
			vetor.add(i);
		}
		
		for (String i : ingrediente.getNomes()) {
			vetor.add(i);
		}
		
		return vetor;
	}

	
	public float getPreco() {
		return super.getPreco() + ingrediente.getPreco();
	}
	
	
	
}
