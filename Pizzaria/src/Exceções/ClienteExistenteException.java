package Exceções;

public class ClienteExistenteException extends Exception{

	public ClienteExistenteException() {
		super("Já existe uma cliente cadastrado com esse CPF!");
	}
	
}
