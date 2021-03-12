package Exceções;

public class FuncionarioNaoExistenteException extends Exception{
	
	public FuncionarioNaoExistenteException() {
		super("Você não está cadastrado");
	}

}
