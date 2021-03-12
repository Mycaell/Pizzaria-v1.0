package View;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDeContabilidade extends TelaComMenu {

	private JTable tabela;

	private int codigoDoGerenteLogado;
	
	public TelaDeContabilidade(int codigoDoGerenteLogado) {
		super("Contabilidade");
		this.setSize(655,300);
		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		adicionarComponentes();
		
		this.setVisible(true);
	}
	

	
	private void adicionarComponentes() {
	
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Contabilidade", 250, 20, 350, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
		
		
		GerenteController gerenteController = new GerenteController();
	
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 5, 655, 20);
	
		
		
		DefaultTableModel modelo = new DefaultTableModel();		
		
		tabela = new JTable(modelo);

		
		ArrayList<String[]> pizzas = gerenteController.recuperarDadosDeTodasPizzas().getDadosDasPizzas();

		String[] pizzaMaisVendida = new String[2];
		
		boolean vendeuAlgumaPizza = false;
		
		
		if(pizzas.size() != 0) {
			
			String[] pizzaDaPosicaoZero = pizzas.get(0);
			pizzaMaisVendida[0] = pizzaDaPosicaoZero[0];
			pizzaMaisVendida[1] = pizzaDaPosicaoZero[2];

			
			for (String[] pizza : pizzas) {
				if((Integer.parseInt(pizza[2]) != 0)) {
					vendeuAlgumaPizza = true;
				}
				if(Integer.parseInt(pizza[2]) >= Integer.parseInt(pizzaMaisVendida[1])) {
					pizzaMaisVendida[0] = pizza[0];
					pizzaMaisVendida[1] = pizza[2];
				}
			}	
		}

		
		
		if(vendeuAlgumaPizza) {
			modelo.addColumn("pizzas vendidas");
			modelo.addColumn("Lucro");
			modelo.addColumn("sabor mais vendido");
			
			Object[] linha = {gerenteController.getQtdVendas().getVendas(), gerenteController.getLucro().getLucro(), pizzaMaisVendida[0]};
			modelo.addRow(linha);
		}else {
			modelo.addColumn("Nenhuma pizza foi vendida!");
		}
		
		
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(25, 70, 600, 38);
		add(scroll);
		
		
		JButton voltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 30, 200, 90, 20);
		voltar.setIcon(Icones.ICONE_VOLTAR);
		voltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoGerenteLogado));
	}

	
	
	
}
