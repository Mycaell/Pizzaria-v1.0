package Model;

import DAO.ContabilidadeDAO_Postgre;
import DTO.ContabilidadeDTO;
import Interfaces.ContablidadadeInterface;

public class Contabilidade {

//	DEVE HAVER UM ID AQUI;
	private int vendas;
	private double lucro;
	
	
	private ContablidadadeInterface contabilidadeDAO = new ContabilidadeDAO_Postgre();
	
	public int getVendas() {
		return vendas;
	}
	public void setVendas(int vendas) {
		this.vendas = vendas;
	}
	public double getLucro() {
		return lucro;
	}
	public void setLucro(double lucro) {
		this.lucro = lucro;
	}


	public ContabilidadeDTO recuperarLucro() {
		return contabilidadeDAO.getLucro();
	}
	
	public ContabilidadeDTO getQtdVendas() {
		return contabilidadeDAO.getQtdVendas();
	}
	
	
	
}
