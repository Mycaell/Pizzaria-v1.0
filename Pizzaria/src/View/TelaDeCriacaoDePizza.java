package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import DTO.IngredienteDTO;
import DTO.PizzaDTO;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDeCriacaoDePizza extends TelaComMenu{

	
	private JTable tabelaIngredientesQueCompoeAPizza;
	private DefaultTableModel modelo1;
	
	
	private JTable tabelaIngredientes;
	
	
	private JTextField campoNome;
	private JTextField campoPreco;

	private JTextField campoValorFatia;
	
	private int codigoDoGerenteLogado;
	
	private GerenteController gerenteController = new GerenteController();
	

	private IngredienteDTO ingredientes = new IngredienteDTO();
	
	public TelaDeCriacaoDePizza(int codigoDoGerenteLogado) {
		super("Nova pizza");


		this.setSize(655,300);
		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		
		adicionarLabels();
		adicionarFields();
		adicionarTabelaDeIngredientesQueEstaoCompondoAPizza();
		adicionarTabelaDeIngredientes();
//		adicionarComboBox();
		adicionarBotoes();
	
		this.setVisible(true);
	}


	private void adicionarTabelaDeIngredientes() {
		
		DefaultTableModel modelo = new DefaultTableModel();		
		tabelaIngredientes = new JTable(modelo);

		GerenteController gerenteController = new GerenteController();
		
		if(gerenteController.recuperarIngredientes().getIngredientes().size() != 0) {

			modelo.addColumn("ID");
			modelo.addColumn("Nome");
			modelo.addColumn("Preço");

//			escondendo a coluda ID
			tabelaIngredientes.getColumnModel().getColumn(0).setMinWidth(0);
			tabelaIngredientes.getColumnModel().getColumn(0).setMaxWidth(0);

			for (String[] ingredientes : gerenteController.recuperarIngredientes().getIngredientes()) {
				Object[] linha = {ingredientes[0], ingredientes[1], ingredientes[2]};
				modelo.addRow(linha);
			}
			
			tabelaIngredientes.addRowSelectionInterval(0, 0);		
		}else {
			modelo.addColumn("Não há Ingredientes");
		}
		
		
		JScrollPane scroll = new JScrollPane(tabelaIngredientes);
		scroll.setBounds(450, 60, 190, 85);
		add(scroll);
		
	}


	private void adicionarFields() {
		campoNome = AdicionadorDeComponentes.adicionarJTextField(this, 60, 40, 130, 20);
		campoPreco = AdicionadorDeComponentes.adicionarJTextField(this, 565, 190, 60, 20);
		campoPreco.setEnabled(false);
		campoValorFatia = AdicionadorDeComponentes.adicionarJTextField(this, 320, 40, 70, 20);
	}


	private void adicionarLabels() {
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Nome", 20, 40, 40, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Valor por fatia", 235, 40, 95, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Ingredientes:", 500, 40, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Ingrediente(s) que compõe a pizza:", 20, 60, 210, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Custo de preparo", 455, 190, 130, 20);

	}


//	private void adicionarComboBox() {
//		
//		
//		Object[] ingredientes = new GerenteController().recuperarIngredientes().getIngredientesParaOComboBox().toArray();
//		
//		if(ingredientes.length!= 0) {
//			JComboBox<Object> ComboBoxIngredientes = new JComboBox<>(ingredientes);
//			ComboBoxIngredientes.setBounds(480, 65, 80, 20);
//			add(ComboBoxIngredientes);		
//		}else{
//			JLabel labelNenhumIngrediente1 = AdicionadorDeComponentes.adicionarJLabel(this, "Nenhum ingrediente",460, 90, 160, 20);
//			labelNenhumIngrediente1.setFont(new Font("Comic Sans", Font.BOLD, 16));
//			labelNenhumIngrediente1.setForeground(Color.RED);
//			
//			JLabel labelNenhumIngrediente2 = AdicionadorDeComponentes.adicionarJLabel(this, "foi cadastrado!",460, 110, 120, 20);
//			labelNenhumIngrediente2.setFont(new Font("Comic Sans", Font.BOLD, 16));
//			labelNenhumIngrediente2.setForeground(Color.RED);
//		}
//	
//	}


	private void adicionarBotoes() {

		OuvinteBotoesTelaDeCriacaoDePizza ouvinte = new OuvinteBotoesTelaDeCriacaoDePizza(this);

		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 30, 220, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(ouvinte);
		
		JButton adicionarSabor = AdicionadorDeComponentes.adicionarJButton(this, "Adicionar", 480, 155, 145, 20);
		adicionarSabor.setIcon(Icones.ICONE_MAIS);
		adicionarSabor.addActionListener(ouvinte);
	
		JButton removerSabor = AdicionadorDeComponentes.adicionarJButton(this, "Remover", 283, 220, 145, 20);
		removerSabor.setIcon(Icones.ICONE_MENOS);
		removerSabor.addActionListener(ouvinte);
		

		JButton botaoConcluir = AdicionadorDeComponentes.adicionarJButton(this, "Finalizar", 490, 220, 110, 20);
		botaoConcluir.setIcon(Icones.ICONE_CERTO);
		botaoConcluir.addActionListener(ouvinte);
		
		
		
	}


	private void adicionarTabelaDeIngredientesQueEstaoCompondoAPizza() {
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Nova pizza", 250, 10, 350, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
		
		GerenteController gerenteController = new GerenteController();
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 5, 655, 20);
	
		modelo1 = new DefaultTableModel();
		modelo1.addColumn("ID");
		modelo1.addColumn("Nome");
		modelo1.addColumn("Preco");

//		escondendo a coluna ID
		
		tabelaIngredientesQueCompoeAPizza = new JTable(modelo1);
		tabelaIngredientesQueCompoeAPizza.getColumnModel().getColumn(0).setMinWidth(0);
		tabelaIngredientesQueCompoeAPizza.getColumnModel().getColumn(0).setMaxWidth(0);
		
		JScrollPane scroll = new JScrollPane(tabelaIngredientesQueCompoeAPizza);
		scroll.setBounds(30, 80, 400, 135);
		add(scroll);
		
		
	}

	
	

	private class OuvinteBotoesTelaDeCriacaoDePizza implements ActionListener{
		
		private TelaDeCriacaoDePizza telaDeCriacaoDePizza;

		public OuvinteBotoesTelaDeCriacaoDePizza(TelaDeCriacaoDePizza telaDeSaboresDePizza) {
			this.telaDeCriacaoDePizza = telaDeSaboresDePizza;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			if(botao.equals("Remover")) {
				int linhaSelecionada = tabelaIngredientesQueCompoeAPizza.getSelectedRow();
				if(linhaSelecionada != -1) {
					
					float preco = Float.parseFloat((String)tabelaIngredientesQueCompoeAPizza.getValueAt(tabelaIngredientesQueCompoeAPizza.getSelectedRow(), 2));
					IngredienteDTO i = new IngredienteDTO();
					i.setPreco(preco);
					
					
					IngredienteDTO ingdto = gerenteController.removendoIngrediente(i);

					campoPreco.setText(Float.toString(ingdto.getPreco()));	
					
					modelo1.removeRow(linhaSelecionada);
					
				}else {
					JOptionPane.showMessageDialog(telaDeCriacaoDePizza, "Selecione um ingrediente da pizza!", "Nenhum ingrediente selecionado", JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao.equals("Adicionar")) {
				
 				int linhaSelecionada = tabelaIngredientes.getSelectedRow();
 				
 				
 				IngredienteDTO i = new IngredienteDTO();
 				
				i.setId(Integer.parseInt((String)tabelaIngredientes.getValueAt(linhaSelecionada, 0)));;

				
				IngredienteDTO ingdto = gerenteController.recuperarIngredientePorID(i);

				 
				modelo1.addRow(new String[] {Integer.toString(ingdto.getId()), ingdto.getNome(), Float.toString(ingdto.getPreco())});
				
				float valorPizza = gerenteController.adicionandoIngredientes(ingdto).getPreco();
				
				campoPreco.setText(Float.toString(valorPizza));	
				
				
			}else if(botao.equals("Finalizar")) {
				String nome = campoNome.getText();
				float preco = Float.parseFloat(campoPreco.getText());
				float valorPorFatia = Float.parseFloat(campoValorFatia.getText());
				
				PizzaDTO pizzaDTO = new PizzaDTO(nome, valorPorFatia, preco, codigoDoGerenteLogado);
						
				for(int i = 0; i < tabelaIngredientesQueCompoeAPizza.getModel().getRowCount(); i++) {
					String[] ingrediente = new String[2];
					
					ingrediente[0]=String.valueOf(tabelaIngredientesQueCompoeAPizza.getValueAt(i, 0)+"-"+tabelaIngredientesQueCompoeAPizza.getValueAt(i, 1));
					ingrediente[1]=String.valueOf(tabelaIngredientesQueCompoeAPizza.getValueAt(i, 2));
					
					ingredientes.getIngredientes().add(ingrediente);
				}
				
				IngredienteDTO ingredienteDTO_decorado = gerenteController.decorar(ingredientes);

				gerenteController.adicionarPizza(pizzaDTO, ingredienteDTO_decorado);
				
				JOptionPane.showMessageDialog(telaDeCriacaoDePizza, "Pizza adicionada");
				TelaDePizzas telaDePizzas = new TelaDePizzas(codigoDoGerenteLogado);
				telaDePizzas.setLocationRelativeTo(telaDeCriacaoDePizza);
				telaDeCriacaoDePizza.dispose();
				
			}else if(botao.equals("Voltar")) {
				TelaDePizzas telaDePizzas = new TelaDePizzas(codigoDoGerenteLogado);
				telaDePizzas.setLocationRelativeTo(telaDeCriacaoDePizza);
				telaDeCriacaoDePizza.dispose();
			}
		}
	}

}
