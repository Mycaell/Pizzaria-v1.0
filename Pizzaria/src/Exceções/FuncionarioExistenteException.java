package Exce��es;

public class FuncionarioExistenteException extends Exception{

	public FuncionarioExistenteException() {
		super("J� existe um funcion�rio cadastrado com esse e-mail");
	}
	
}
