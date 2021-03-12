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
import Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDeIngredientes extends TelaComMenu{

	private int codigoDoGerenteLogado;
	
	private JTable tabela;
	private DefaultTableModel modelo;
	
	public TelaDeIngredientes(int codigoDoGerenteLogado) {
		super("Ingredientes");

		this.setSize(655, 300);
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		
		adicionarTabela();
		
		adicionarBotoes();
	
		this.setVisible(true);
	}

	private void adicionarBotoes() {

		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 30, 210, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoGerenteLogado));
		
		OuvinteBotoesTelaDeIngredientes ouvinte = new OuvinteBotoesTelaDeIngredientes(this);

		JButton botaoEditar = AdicionadorDeComponentes.adicionarJButton(this, "Editar", 220, 210, 90, 20);
		botaoEditar.setIcon(Icones.ICONE_EDICAO);
		botaoEditar.addActionListener(ouvinte);
		
		JButton adicionarSabor = AdicionadorDeComponentes.adicionarJButton(this, "Adicionar", 480, 197, 149, 20);
		adicionarSabor.setIcon(Icones.ICONE_MAIS);
		adicionarSabor.addActionListener(ouvinte);
	
		JButton removerSabor = AdicionadorDeComponentes.adicionarJButton(this, "Remover", 480, 223, 150, 20);
		removerSabor.setIcon(Icones.ICONE_MENOS);
		removerSabor.addActionListener(ouvinte);

		
	}

	private void adicionarTabela() {

		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Ingredientes", 250, 20, 350, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));

		GerenteController gerenteController = new GerenteController();
	
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 5, 655, 20);
	
		
		modelo = new DefaultTableModel();		
		
		tabela = new JTable(modelo);

		
		if(gerenteController.recuperarIngredientes().getIngredientes().size() != 0) {

			modelo.addColumn("ID");
			modelo.addColumn("Nome");
			modelo.addColumn("Preço");

//			escondendo a coluda ID
			tabela.getColumnModel().getColumn(0).setMinWidth(0);
			tabela.getColumnModel().getColumn(0).setMaxWidth(0);

			for (String[] ingredientes : gerenteController.recuperarIngredientes().getIngredientes()) {
				Object[] linha = {ingredientes[0], ingredientes[1], ingredientes[2]};
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);		
		}else {
			modelo.addColumn("Não há Ingredientes");
		}
		
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(30, 55, 600, 135);
		add(scroll);

		
		
	}

	
	private class OuvinteBotoesTelaDeIngredientes implements ActionListener{

		
		private TelaDeIngredientes telaDeIngredientes;
		
		public OuvinteBotoesTelaDeIngredientes(TelaDeIngredientes telaDeIngredientes) {
			this.telaDeIngredientes = telaDeIngredientes;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			
			int linhaSelecionada = tabela.getSelectedRow();

			
			if(botao.equals("Editar")) {

				if(linhaSelecionada != -1) {
					JOptionPane.showMessageDialog(null, "Lógica ainda não implementada");
//					LÓGICA DE EDIÇÃO
					
				}else {
					JOptionPane.showMessageDialog(telaDeIngredientes, "Selecione um ingrediente!", "Nenhum ingrediente selecionado", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(botao.equals("Adicionar")) {
				TelaNovoIngrediente telaNovoIngrediente = new TelaNovoIngrediente(telaDeIngredientes, codigoDoGerenteLogado);
				telaNovoIngrediente.setLocationRelativeTo(telaDeIngredientes);
				
			}else if(botao.equals("Remove")) {
				
				if(linhaSelecionada != -1) {
					int opcao = JOptionPane.showConfirmDialog(telaDeIngredientes, "Você tem certeza que quer excluir esse ingrediente?","Excluir ingrediente",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
//						int IdDoIngredienteSelecionado = Integer.parseInt((String) tabela.getValueAt(linhaSelecionada, 0));
						
						JOptionPane.showMessageDialog(null, "Lógiac ainda não implementada");
//						LÓGICA DE REMOÇÃO
						
//						JOptionPane.showMessageDialog(telaDeIngredientes, "Ingrediente removido!");
						
//						modelo.removeRow(linhaSelecionada);
					}
					
				}else {
					JOptionPane.showMessageDialog(telaDeIngredientes, "Selecione um ingrediente!", "Nenhum ingrediente selecionado", JOptionPane.ERROR_MESSAGE);
				}
			}			
		}
	}
	
	
}
