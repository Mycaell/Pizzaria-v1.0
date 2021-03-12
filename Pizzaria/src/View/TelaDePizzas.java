package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import DTO.PizzaDTO;
import Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDePizzas extends TelaComMenu {


	private JTable tabela;
	private DefaultTableModel modelo;
	
	private int codigoDoGerenteLogado;
	
	public TelaDePizzas(int codigoDoGerenteLogado) {
		super("Pizzas da Casa");

		this.setSize(655,300);
		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		adicionarTabelaDePizzas();
		
		adicionarBotoes();
		
		this.setVisible(true);
	}

	private void adicionarBotoes() {

		
		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 30, 200, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoGerenteLogado));
		
		OuvinteBotoesTelaDeSaboresDePizza ouvinte = new OuvinteBotoesTelaDeSaboresDePizza(this);

		JButton botaoEditar = AdicionadorDeComponentes.adicionarJButton(this, "Editar", 170, 200, 90, 20);
		botaoEditar.setIcon(Icones.ICONE_EDICAO);
		botaoEditar.addActionListener(ouvinte);
		
		JButton adicionarSabor = AdicionadorDeComponentes.adicionarJButton(this, "Adicionar Pizza", 480, 180, 145, 20);
		adicionarSabor.setIcon(Icones.ICONE_MAIS);
		adicionarSabor.addActionListener(ouvinte);
	
		JButton removerSabor = AdicionadorDeComponentes.adicionarJButton(this, "Remover Pizza", 480, 220, 145, 20);
		removerSabor.setIcon(Icones.ICONE_MENOS);
		removerSabor.addActionListener(ouvinte);


		JButton verIngredientes = AdicionadorDeComponentes.adicionarJButton(this, "Ver Ingredientes", 300, 200, 170, 20);
		verIngredientes.setIcon(Icones.ICONE_OLHO_ABERTO);
		verIngredientes.addActionListener(ouvinte);
		
		
	}
	
	private void adicionarTabelaDePizzas() {
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Pizzas da Casa", 250, 20, 350, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));

		
		GerenteController gerenteController = new GerenteController();
	
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 5, 655, 20);
	
		
		modelo = new DefaultTableModel();
		
		tabela = new JTable(modelo);
		
		
		
		if(gerenteController.recuperarDadosDeTodasPizzas().getDadosDasPizzas().size() != 0) {

			
			modelo.addColumn("ID");
			modelo.addColumn("Sabor");
			modelo.addColumn("Preço por fatia");
			modelo.addColumn("Fatias vendidas");
			
//			escondendo a coluda ID
			tabela.getColumnModel().getColumn(0).setMinWidth(0);
			tabela.getColumnModel().getColumn(0).setMaxWidth(0);

			for (String[] dadosDaPizza : gerenteController.recuperarDadosDeTodasPizzas().getDadosDasPizzas()) {
				Object[] linha = {dadosDaPizza[3], dadosDaPizza[0], dadosDaPizza[1], dadosDaPizza[2]};
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);		
		}else {
			modelo.addColumn("Não há pizzas");
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(30, 55, 600, 102);
		add(scroll);

	}
	
	private class OuvinteBotoesTelaDeSaboresDePizza implements ActionListener{
		
		private TelaDePizzas telaDeSaboresDePizza;

		public OuvinteBotoesTelaDeSaboresDePizza(TelaDePizzas telaDeSaboresDePizza) {
			this.telaDeSaboresDePizza = telaDeSaboresDePizza;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();

			
			int linhaSelecionada = tabela.getSelectedRow();

			GerenteController gerenteController = new GerenteController();
			
			if(botao.equals("Editar")) {

				if(linhaSelecionada != -1) {
					
//					FAZER A NOVA LÓGICA DE EDIÇÃO
					JOptionPane.showMessageDialog(null, "Lógica ainda não implementada");
					
//					
					
//					 ESSA LÓGICA NÃO É MAIS APLICÁVEL AO PROJETO
//					int idDaPizzaSelecionada = Integer.parseInt((String) tabela.getValueAt(linhaSelecionada, 0));
//					String saborDaPizzaSelecionada = (String) tabela.getValueAt(linhaSelecionada, 1);
//					float precoPorFatiaDaPizzaSelecionadaString = Float.parseFloat((String) tabela.getValueAt(linhaSelecionada, 2));
//					
//					TelaDeEdicaoDePizza telaDeEdicaoDePizza = new TelaDeEdicaoDePizza(telaDeSaboresDePizza, codigoDoGerenteLogado, idDaPizzaSelecionada, saborDaPizzaSelecionada, precoPorFatiaDaPizzaSelecionadaString);
//					telaDeEdicaoDePizza.setLocationRelativeTo(telaDeSaboresDePizza);
				}else {
					JOptionPane.showMessageDialog(telaDeSaboresDePizza, "Selecione uma pizza!", "Nenhuma pizza selecionada", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(botao.equals("Adicionar Pizza")) {
				TelaDeCriacaoDePizza telaDeCriacaoDePizza = new TelaDeCriacaoDePizza(codigoDoGerenteLogado);
				telaDeCriacaoDePizza.setLocationRelativeTo(telaDeSaboresDePizza);
				telaDeSaboresDePizza.dispose();
				
			}else if(botao.equals("Remover Pizza")) {
				
				if(linhaSelecionada != -1) {
					int opcao = JOptionPane.showConfirmDialog(telaDeSaboresDePizza, "Você tem certeza que quer excluir essa pizza?","Excluir Pizza",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						int IdDaPizzaSelecionada = Integer.parseInt((String) tabela.getValueAt(linhaSelecionada, 0));
						
						PizzaDTO pizzaDTO = new PizzaDTO();
						
						pizzaDTO.setIdDaPizza(IdDaPizzaSelecionada);
						
						gerenteController.removerPizza(pizzaDTO);

						JOptionPane.showMessageDialog(telaDeSaboresDePizza, "Pizza removida!");
						
						modelo.removeRow(linhaSelecionada);
					}
					
				}else {
					JOptionPane.showMessageDialog(telaDeSaboresDePizza, "Selecione uma pizza!", "Nenhuma pizza selecionada", JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao.equals("Ver ingredientes")) {
//				!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				JOptionPane.showMessageDialog(null, "Lógica ainda não implementada");
//				fazer essa lógica (exibir os ingredientes de uma determinada pizza em uma tabela)
// 				!!!!!!!!!!!!!!!!!
			}
		}
	}
	
	
	public static void main(String[] args) {
		new TelaDeCriacaoDePizza(2);
	}
	
}