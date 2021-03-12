package Exceções;

public class FuncionarioExistenteException extends Exception{

	public FuncionarioExistenteException() {
		super("Já existe um funcionário cadastrado com esse e-mail");
	}
	
}
