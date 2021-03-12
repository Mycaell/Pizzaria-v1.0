package Interfaces;



import DTO.ClienteDTO;
import Exceções.CPFInexistenteException;
import Exceções.CampoVazioException;
import Exceções.ClienteExistenteException;

public interface ClienteInterface {
	

	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException;
	public void excluirCliente(ClienteDTO clinteDTO);
	public ClienteDTO recuperarDadosDeTodosOsClientes();
	public ClienteDTO getEndereco(ClienteDTO clienteDTO);
	public ClienteDTO validarCPF(ClienteDTO clienteDTOs) throws CPFInexistenteException, CampoVazioException ;
	
	
}
