package Exce��es;

public class FuncionarioNaoExistenteException extends Exception{
	
	public FuncionarioNaoExistenteException() {
		super("Voc� n�o est� cadastrado");
	}

}
