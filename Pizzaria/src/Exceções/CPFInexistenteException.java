package Exce��es;

public class CPFInexistenteException extends Exception{

	public CPFInexistenteException() {
		super("N�o existe um cliente cadastrado com esse CPF!");
	}
	
}
