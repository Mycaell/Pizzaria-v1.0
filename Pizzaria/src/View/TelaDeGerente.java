package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDeGerente extends TelaComMenu{

	private JTable tabela;
	
	private int codigoDoGerenteLogado;
	
	public TelaDeGerente(int codigoDoGerenteLogado) {
		super("Gerenciamento");
		this.setSize(655, 300);
	
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		adicionarTabela();
		adicionarBotoes();
		
	
		this.setVisible(true);
	}

	private void adicionarBotoes() {		
		OuvinteTelaDeGerente ouvinteTelaDeGerente = new OuvinteTelaDeGerente(this);
		
		JButton botaoAvancar = AdicionadorDeComponentes.adicionarJButton(this, "Avançar", 525, 210, 105, 20);
		botaoAvancar.setIcon(Icones.ICONE_AVANCAR);
		botaoAvancar.addActionListener(ouvinteTelaDeGerente);
		
	}

	private void adicionarTabela() {

		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Gerenciamento", 250, 20, 350, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));

		GerenteController gerenteController = new GerenteController();
	
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 5, 655, 20);
	
		
		DefaultTableModel modelo = new DefaultTableModel();		
		
		tabela = new JTable(modelo);
		
		modelo.addColumn("Gerenciar");

		
		Object[] linha1 = {"Clientes"};
		Object[] linha2 = {"Funcionários"};		
		Object[] linha3 = {"Contabilidade"};
		Object[] linha4 = {"Pizzas"};
		Object[] linha5 = {"Ingredientes"};
		Object[] linha6 = {"Atendimento"};
		Object[] linha7 = {"Cozinha"};
		Object[] linha8 = {"Entrega"};
		
		
		modelo.addRow(linha1);
		modelo.addRow(linha2);
		modelo.addRow(linha3);
		modelo.addRow(linha4);
		modelo.addRow(linha5);
		modelo.addRow(linha6);
		modelo.addRow(linha7);
		modelo.addRow(linha8);
		
		
		
		tabela.addRowSelectionInterval(0, 0);
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(30, 55, 600, 135);
		add(scroll);
		
	}

		
	private class OuvinteTelaDeGerente implements ActionListener{

		private JFrame janela;
		
		public OuvinteTelaDeGerente (JFrame janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String botao = e.getActionCommand();
			
			
			if(botao.equals("Avançar")) {
				
				int linhaSelecionada = tabela.getSelectedRow();
				if(tabela.getValueAt(linhaSelecionada, 0).equals("Clientes")) {
					TelaDeClientes telaDeClientes = new TelaDeClientes(codigoDoGerenteLogado);
					telaDeClientes.setLocationRelativeTo(janela);
					janela.dispose();
				}else if(tabela.getValueAt(linhaSelecionada, 0).equals("Funcionários")) {
					TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoGerenteLogado);
					telaDeFuncionarios.setLocationRelativeTo(janela);
					janela.dispose();
				}else if(tabela.getValueAt(linhaSelecionada, 0).equals("Contabilidade")){
					TelaDeContabilidade telaDeContabilidade = new TelaDeContabilidade(codigoDoGerenteLogado);
					telaDeContabilidade.setLocationRelativeTo(janela);
					janela.dispose();
				}else if(tabela.getValueAt(linhaSelecionada, 0).equals("Pizzas")){
					TelaDePizzas telaDeSaboresDePizzas = new TelaDePizzas(codigoDoGerenteLogado);
					telaDeSaboresDePizzas.setLocationRelativeTo(janela);
					janela.dispose();
				}else if(tabela.getValueAt(linhaSelecionada, 0).equals("Atendimento")){
					TelaDeAtendimento telaDeAtendimento = new TelaDeAtendimento(codigoDoGerenteLogado);
					telaDeAtendimento.setLocationRelativeTo(janela);
					janela.dispose();
				}else if(tabela.getValueAt(linhaSelecionada, 0).equals("Cozinha")){
					TelaDePizzaiolo telaDePizzaiolo = new TelaDePizzaiolo(codigoDoGerenteLogado);
					telaDePizzaiolo.setLocationRelativeTo(janela);
					janela.dispose();
				}else if(tabela.getValueAt(linhaSelecionada, 0).equals("Entrega")){
					TelaDeMotoboy telaDeMotoboy = new TelaDeMotoboy(codigoDoGerenteLogado);
					telaDeMotoboy.setLocationRelativeTo(janela);
					janela.dispose();
				}else if(tabela.getValueAt(linhaSelecionada, 0).equals("Ingredientes")) {
					TelaDeIngredientes telaDeIngredientes = new TelaDeIngredientes(codigoDoGerenteLogado);
					telaDeIngredientes.setLocationRelativeTo(janela);
					janela.dispose();
					
				}
				
				
				
			}
			
			
		}
		
	}
		

	
}
