
package Model;

import DAO.FuncionarioDAO_Postgre;
import DTO.FuncionarioDTO;
import Exceções.FuncionarioExistenteException;
import Exceções.FuncionarioNaoExistenteException;
import Interfaces.FuncionarioInterface;

public class Gerente extends Funcionario{

	private FuncionarioInterface FDP = new FuncionarioDAO_Postgre();
	
	public FuncionarioDTO recuperarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExistenteException{
		return FDP.recuperarFuncionario(funcionarioDTO);
	}

	public FuncionarioDTO recuperarCodigo(FuncionarioDTO funcionarioDTO) {
		return FDP.getCodigo(funcionarioDTO);
	}
	
	public FuncionarioDTO recuperarCargo(FuncionarioDTO funcionarioDTO) {
		return FDP.getCargo(funcionarioDTO);
	}
	
	public FuncionarioDTO recuperarNome(FuncionarioDTO funcionarioDTO) {
		return FDP.getNome(funcionarioDTO);
	}
	
	public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioExistenteException {
		FDP.cadastrarFuncionario(funcionarioDTO);
	}


	public void excluirFuncionario(FuncionarioDTO funcionarioDTO) {
		FDP.excluirFuncionario(funcionarioDTO);
	}
	

	
	public FuncionarioDTO recuperarDadosDeTodosFuncionaros(){
		return FDP.recuperarDadosDeTodosFuncionaros();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
}
