package Exceções;

public class CPFInexistenteException extends Exception{

	public CPFInexistenteException() {
		super("Não existe um cliente cadastrado com esse CPF!");
	}
	
}
