package Interfaces;


import DTO.FuncionarioDTO;
import Exce��es.FuncionarioExistenteException;
import Exce��es.FuncionarioNaoExistenteException;

public interface FuncionarioInterface {

	
	public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioExistenteException;
	public void excluirFuncionario(FuncionarioDTO funcionarioDTO);

	public FuncionarioDTO recuperarDadosDeTodosFuncionaros();
	public FuncionarioDTO recuperarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExistenteException;
	
	public FuncionarioDTO getCodigo(FuncionarioDTO funcionarioDTO);
	public FuncionarioDTO getCargo(FuncionarioDTO funcionarioDTO);
	public FuncionarioDTO getNome(FuncionarioDTO funcionarioDTO);
	
		
}
