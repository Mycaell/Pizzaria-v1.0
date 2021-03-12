package Model;

public class Adicional extends IngredienteDecorator{

	public Adicional(Ingrediente ingrediente,String nome, float preco) {
		super(ingrediente);
		setNomes(nome);
		setPreco(preco);
	}

}
