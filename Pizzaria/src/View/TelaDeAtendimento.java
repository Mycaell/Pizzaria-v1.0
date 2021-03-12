 package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.AtendenteController;
import Controller.GerenteController;
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import DTO.PedidoDTO;
import DTO.PizzaDTO;
import Exceções.CPFInexistenteException;
import Exceções.CampoVazioException;
import Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDeAtendimento extends TelaComMenu{
	
	private JFormattedTextField cpf;
	
	private JLabel LabelPreco;
	
	private JRadioButton saborUnico;
	private JRadioButton saboresMistos;
	
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private int codigoDoAtendenteLogado;

	private JComboBox<String> tamanho;
	
	private JComboBox<Object> sabor1;
	private JComboBox<Object> sabor2;
	private JComboBox<Object> sabor3;
	
	private Object[] sabores;
	
	
	public TelaDeAtendimento(int codigoDoAtendenteLogado) {
		super("Atendimento");

		this.setSize(990,600);
		JLabel label = new JLabel(Icones.IMAGEM_DE_FUNDO_ATENDIMENTO);
		label.setBounds(0, 0, 990, 600);
		setContentPane(label);
		
		this.codigoDoAtendenteLogado = codigoDoAtendenteLogado;
		adicionarMenu();
		adicionarLabels();
		adicionarFields();
		adicionarBotoes();
		adicionarTabelaDePedidos();
		adicionarComboBoxes();
		
		
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}

	
	public JFormattedTextField getCpf() {
		return cpf;
	}

	public void setCpf(JFormattedTextField cpf) {
		this.cpf = cpf;
	}



	private void adicionarFields() {
		try {
			cpf = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "###.###.###-##", 665, 70, 100, 20);
		}catch (ParseException e) {
		}
		
	}
	
	private void adicionarBotoes() {
		
		ButtonGroup grupo = new ButtonGroup();
		
		OuvinteRadioButtons ouvinteRadioButtons = new OuvinteRadioButtons(this);
		saborUnico = new JRadioButton("Sabor Único");
		saborUnico.addActionListener(ouvinteRadioButtons);
		saborUnico.setBounds(635, 125, 95, 20);
		saborUnico.setBackground(null);
		add(saborUnico);
		
		saboresMistos = new JRadioButton("Sabores Mistos");
		saboresMistos.addActionListener(ouvinteRadioButtons);
		saboresMistos.setBounds(755, 125, 120, 20);
		saboresMistos.setBackground(null);
		add(saboresMistos);
		
		grupo.add(saborUnico);
		grupo.add(saboresMistos);
		
		OuvinteBotoesTelaDeAtendimento ouvinteBotoes = new OuvinteBotoesTelaDeAtendimento(this);
		JButton botaoFinalizarPedido = AdicionadorDeComponentes.adicionarJButton(this, "Finalizar Pedido", 825, 500, 145, 20);
		botaoFinalizarPedido.setIcon(Icones.ICONE_CERTO);
		botaoFinalizarPedido.addActionListener(ouvinteBotoes);
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoAtendenteLogado);
		
		if(new GerenteController().recuperarCargo(funcionarioDTO).getCargo().equals("Gerente")) {
			JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 50, 500, 90, 20);
			botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
			botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoAtendenteLogado));
		}
		
	}
	
	private void adicionarComboBoxes() {
		String[] tam = {"Pequeno (4 fatias)","Médio (8 fatias)","Grande (12 fatias)"};
		tamanho = new JComboBox<>(tam);
		tamanho.setBounds(695, 95, 130, 20);
		add(tamanho);
		
		AtendenteController atendenteController = new AtendenteController();

		sabores = atendenteController.recuperarSaboresDePizzas().getSaboresDePizzas().toArray();
		
		if(sabores.length != 0) {
			
			OuvinteComboBoxes ouvinteComboBoxes = new OuvinteComboBoxes(this);
			
			sabor1 = new JComboBox<>(sabores);
			sabor1.setBounds(630, 175, 90, 20);
			add(sabor1);
			sabor1.setVisible(false);
			
			sabor2 = new JComboBox<>(sabores);
			sabor2.setBounds(755, 175, 90, 20);
			add(sabor2);
			sabor2.setVisible(false);
			
			sabor3 = new JComboBox<>(sabores);
			sabor3.setBounds(875, 175, 90, 20);
			add(sabor3);
			sabor3.setVisible(false);

		
			sabor1.addActionListener(ouvinteComboBoxes);
			sabor2.addActionListener(ouvinteComboBoxes);
			sabor3.addActionListener(ouvinteComboBoxes);
			
			
		}
	}
	
	private void adicionarTabelaDePedidos() {
		
		modelo = new DefaultTableModel();
		tabela = new JTable(modelo);
	

		AtendenteController atendenteController = new AtendenteController();
		
		if(atendenteController.recuperarDadosDeTodosPedidos().getPedidos().size() == 0) {
			modelo.addColumn("Não há pedidos");
		}else {
			modelo.addColumn("Nº do pedido");
			modelo.addColumn("CPF do cliente");
			modelo.addColumn("Tamanho");
			modelo.addColumn("Fatias");
			modelo.addColumn("Sabores");
			modelo.addColumn("Preço");
			modelo.addColumn("Pronto");
			modelo.addColumn("Entregue");
			modelo.addColumn("Data do Pedido");
			
			
//		\/ aumenta o tamanho de uma determinada célula do JTable
			tabela.getColumnModel().getColumn(0).setPreferredWidth(85);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(103);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(49);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(55);
			tabela.getColumnModel().getColumn(7).setPreferredWidth(58);
			tabela.getColumnModel().getColumn(8).setPreferredWidth(116);

			GerenteController gerenteController = new GerenteController();
			
//			PERCORRE O ARRAY DE PEDIDOS DE TRÁS PARA FRENTE
			for (int i = atendenteController.recuperarDadosDeTodosPedidos().getPedidos().size()-1; i >= 0; i--) {
				String[] pedido = atendenteController.recuperarDadosDeTodosPedidos().getPedidos().get(i);
				
				PizzaDTO pizzaDTO = new PizzaDTO();
				pizzaDTO.setIds(pedido[4]);
				String sabores = gerenteController.getSabores(pizzaDTO).getSabores();
				
//				adicionando a linha na tabela
				Object[] linha = {pedido[0], pedido[1], pedido[2], pedido[3], sabores, pedido[5], pedido[6], pedido[7], pedido[8]};
				modelo.addRow(linha);
			}
			
			
			tabela.addRowSelectionInterval(0, 0);
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(10, 70, 600, 405);
		add(scroll);
		
	}
	
	private void adicionarLabels() {
		
//		"<html><u> MEU TEXTO </u></html>"
		GerenteController gerenteController = new GerenteController();

		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoAtendenteLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoAtendenteLogado+")</u></html>", 5, 5, 990, 20);
		
		
		JLabel labelPedidosEmAberto = AdicionadorDeComponentes.adicionarJLabel(this, "Histórico de Pedidos", 205, 30, 195, 20);
		labelPedidosEmAberto.setFont(new Font("Comic Sans", Font.BOLD, 18));
		
		JLabel labelPedido = AdicionadorDeComponentes.adicionarJLabel(this, "Pedido", 775, 30, 80, 20);
		labelPedido.setFont(new Font("Comic Sans", Font.BOLD, 18));
		
		AdicionadorDeComponentes.adicionarJLabel(this, "CPF", 635, 70, 30, 20);
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Tamanho", 635, 95, 65, 20);
		
		JLabel cifrao = AdicionadorDeComponentes.adicionarJLabel(this, "Preço: R$", 690, 445, 95, 20);
		cifrao.setFont(new Font("Comic Sans", Font.BOLD, 18));
		
		LabelPreco = AdicionadorDeComponentes.adicionarJLabel(this, "00,00", 780, 445, 140, 20); 
		LabelPreco.setFont(new Font("Comic Sans", Font.BOLD, 18));
		
		
	}
	
	private void adicionarMenu() {
		JMenuBar barraDeMenu = this.getJMenuBar();

		JMenu menu = barraDeMenu.getMenu(0);
		
		JMenuItem itemNovoCliente = new JMenuItem("Novo Cliente");
		itemNovoCliente.setIcon(Icones.ICONE_USUARIO);
		itemNovoCliente.addActionListener(new OuvinteBotoesTelaDeAtendimento(this));
		
		menu.add(itemNovoCliente);
	}

	private class OuvinteRadioButtons implements ActionListener{
		
		private TelaDeAtendimento telaDeAtendimento;
		
		public OuvinteRadioButtons(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}

		AtendenteController atendenteController = new AtendenteController();
		Object[] sabores = atendenteController.recuperarSaboresDePizzas().getSaboresDePizzas().toArray();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			AdicionadorDeComponentes.adicionarJLabel(telaDeAtendimento, "Sabores que irão compor a pizza:", 700, 150, 220, 20);

			if(sabores.length != 0) {
				String sabor1Selecionado = null;
				String sabor2Selecionado = null;
				String sabor3Selecionado = null;
				
						
				if(e.getActionCommand().equals("Sabor Único")) {
					sabor1.setVisible(true);
					sabor2.setVisible(false);
					sabor3.setVisible(false);
					
					if(tamanho.getSelectedIndex() == 0) {
						sabor1Selecionado = (String) sabor1.getSelectedItem();
					}else if(tamanho.getSelectedIndex() == 1) {
						sabor1Selecionado = (String) sabor1.getSelectedItem();
						sabor3Selecionado = (String) sabor1.getSelectedItem();						
					}else if(tamanho.getSelectedIndex() == 2) {
						sabor1Selecionado = (String) sabor1.getSelectedItem();
						sabor2Selecionado = (String) sabor1.getSelectedItem();
						sabor3Selecionado = (String) sabor1.getSelectedItem();			
					}
					
				}else if(e.getActionCommand().equals("Sabores Mistos")) {
					
					if(tamanho.getSelectedIndex() == 0) {
						sabor1.setVisible(true);
						sabor2.setVisible(false);
						sabor3.setVisible(false);
						
						sabor1Selecionado = (String) sabor1.getSelectedItem();
					}else if(tamanho.getSelectedIndex() == 1) {
						sabor1.setVisible(true);
						sabor2.setVisible(true);
						sabor3.setVisible(false);
						
						sabor1Selecionado = (String) sabor1.getSelectedItem();
						sabor2Selecionado = (String) sabor2.getSelectedItem();
					}else if(tamanho.getSelectedIndex() == 2) {
						sabor1.setVisible(true);
						sabor2.setVisible(true);
						sabor3.setVisible(true);
						
						sabor1Selecionado = (String) sabor1.getSelectedItem();
						sabor2Selecionado = (String) sabor2.getSelectedItem();
						sabor3Selecionado = (String) sabor3.getSelectedItem();
					}
				}
				
				PedidoDTO pedidoDTO = new PedidoDTO(sabor1Selecionado, sabor2Selecionado, sabor3Selecionado);

//				esse 4 abaixo é a quantidade de fatias de cada sabor (valor definido por mim, o dono)
				LabelPreco.setText(Float.toString(4 * atendenteController.calcularPrecoDoPedido(pedidoDTO).getPreco()));
				
			}else {
				JLabel labelNenhumSabor = AdicionadorDeComponentes.adicionarJLabel(telaDeAtendimento, "Nenhum sabor foi cadastrado!",660, 180, 230, 20);
				labelNenhumSabor.setFont(new Font("Comic Sans", Font.BOLD, 16));
				labelNenhumSabor.setForeground(Color.RED);
				
			}
			
		}
	}

	
	private class OuvinteComboBoxes implements ActionListener{

		private TelaDeAtendimento telaDeAtendimento;
		
		public OuvinteComboBoxes(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}
		
		AtendenteController atendenteController = new AtendenteController();
		
		@Override
		public void actionPerformed(ActionEvent arg0) {

			String sabor1Selecionado = null;
			String sabor2Selecionado = null;
			String sabor3Selecionado = null;
			
			if(sabor1.isVisible() && sabor2.isVisible() && sabor3.isVisible()){
//				System.out.println("sabor 1 visivel AND sabor 2 visivel AND sabor 3 visivel");
				sabor1Selecionado = (String) sabor1.getSelectedItem();
				sabor2Selecionado = (String) sabor2.getSelectedItem();
				sabor3Selecionado = (String) sabor3.getSelectedItem();
			}else if(sabor1.isVisible() && sabor2.isVisible()) {
//				System.out.println("sabor 1 visivel AND sabor 2 visivel");
				sabor1Selecionado = (String) sabor1.getSelectedItem();
				sabor2Selecionado = (String) sabor2.getSelectedItem();
			}else if(sabor1.isVisible() && tamanho.getSelectedIndex() == 0) {
				sabor1Selecionado = (String) sabor1.getSelectedItem();
			}else if(sabor1.isVisible() && tamanho.getSelectedIndex() == 1) {
				sabor1Selecionado = (String) sabor1.getSelectedItem();
				sabor2Selecionado = (String) sabor1.getSelectedItem();
			}else if(sabor1.isVisible() && tamanho.getSelectedIndex() == 2) {
				sabor1Selecionado = (String) sabor1.getSelectedItem();
				sabor2Selecionado = (String) sabor1.getSelectedItem();
				sabor3Selecionado = (String) sabor1.getSelectedItem();
			}
			
			
			PedidoDTO pedidoDTO = new PedidoDTO(sabor1Selecionado, sabor2Selecionado, sabor3Selecionado);
//			esse 4 abaixo é a quantidade de fatias de cada sabor	(valor definido por mim, o dono) 
			LabelPreco.setText(Float.toString(4 * atendenteController.calcularPrecoDoPedido(pedidoDTO).getPreco()));
			
		}
		
	}
	
	
	private class OuvinteBotoesTelaDeAtendimento implements ActionListener{

		private TelaDeAtendimento telaDeAtendimento;
		
		public OuvinteBotoesTelaDeAtendimento(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			AtendenteController atendenteController = new AtendenteController();
			
			String botao = e.getActionCommand();
			
			if(botao.equals("Finalizar Pedido")) {
				
				try {
					ClienteDTO clienteDTO = new ClienteDTO();
					clienteDTO.setCPF(cpf.getText());
					atendenteController.validarCPF(clienteDTO);
					
					if(saborUnico.isSelected() || saboresMistos.isSelected()) {
						
						
						int qtdFatias = 0;
						float preco = 0;

						if(tamanho.getSelectedIndex() == 0) {
							qtdFatias = 4;
						}else if(tamanho.getSelectedIndex() == 1) {
							qtdFatias = 8;
						}else {
							qtdFatias = 12;
						}
						
						ArrayList<String> saboresQueCompoemAPizza = new ArrayList<>();
						
						PizzaDTO pizzaDTO1 = new PizzaDTO();
						PizzaDTO pizzaDTO2 = new PizzaDTO();
						
//						valor definido por mim (o dono)
						int qtdDeFatiasDeCadaSabor = 4;
						
						if(saborUnico.isSelected()) {

							String saborUnicoString = (String)sabor1.getSelectedItem();
							String [] saborUnico = saborUnicoString.split("-");
							saboresQueCompoemAPizza.add(saborUnico[0]);
							pizzaDTO1.setIdDaPizza(Integer.parseInt(saborUnico[0]));
							
							if(qtdFatias == 4) {
								preco += qtdFatias * atendenteController.recuperarPrecoDaFatia(pizzaDTO1).getPrecoProFatia();
							}else if(qtdFatias == 8) {
								preco += qtdFatias * atendenteController.recuperarPrecoDaFatia(pizzaDTO1).getPrecoProFatia();
							}else if(qtdFatias == 12) {
								preco += qtdFatias * atendenteController.recuperarPrecoDaFatia(pizzaDTO1).getPrecoProFatia();
							}
							
						}else if(saboresMistos.isSelected()) {
							if(qtdFatias == 4) {
							
								String saborUnicoString = (String)sabor1.getSelectedItem();
								
								String[] saborUnico = saborUnicoString.split("-");
									
								saboresQueCompoemAPizza.add(saborUnico[0]);
								
								
								pizzaDTO1.setIdDaPizza(Integer.parseInt(saborUnico[0]));
								preco += qtdDeFatiasDeCadaSabor * atendenteController.recuperarPrecoDaFatia(pizzaDTO1).getPrecoProFatia();

							}else if(qtdFatias == 8) {
								
								
								String sabor1String = (String) sabor1.getSelectedItem();
								String sabor2String = (String) sabor2.getSelectedItem();
										
								String[] sabor1 = sabor1String.split("-");
								String[] sabor2 = sabor2String.split("-");
										
								saboresQueCompoemAPizza.add(sabor1[0]);
								saboresQueCompoemAPizza.add(sabor2[0]);
								
								
								pizzaDTO1.setIdDaPizza(Integer.parseInt(sabor1[0]));
								float preco1 = atendenteController.recuperarPrecoDaFatia(pizzaDTO1).getPrecoProFatia();
								
								pizzaDTO2.setIdDaPizza(Integer.parseInt(sabor2[0]));
								float preco2 = atendenteController.recuperarPrecoDaFatia(pizzaDTO2).getPrecoProFatia();	
								
								preco += (qtdDeFatiasDeCadaSabor * preco1) + (qtdDeFatiasDeCadaSabor * preco2);
								
								
							}else if(qtdFatias == 12) {
								
								String sabor1String = (String) sabor1.getSelectedItem();
								String sabor2String = (String) sabor2.getSelectedItem();
								String sabor3String = (String) sabor3.getSelectedItem();
								
								String[] sabor1 = sabor1String.split("-");
								String[] sabor2 = sabor2String.split("-");
								String[] sabor3 = sabor3String.split("-");
								
								saboresQueCompoemAPizza.add(sabor1[0]);
								saboresQueCompoemAPizza.add(sabor2[0]);
								saboresQueCompoemAPizza.add(sabor3[0]);
								
								pizzaDTO1.setIdDaPizza(Integer.parseInt(sabor1[0]));	
								float preco1 = atendenteController.recuperarPrecoDaFatia(pizzaDTO1).getPrecoProFatia();
							
								pizzaDTO2.setIdDaPizza(Integer.parseInt(sabor2[0]));
								float preco2 = atendenteController.recuperarPrecoDaFatia(pizzaDTO2).getPrecoProFatia();	
								
								
								PizzaDTO pizzaDTO3 = new PizzaDTO();
								pizzaDTO3.setIdDaPizza(Integer.parseInt(sabor3[0]));
								float preco3 = atendenteController.recuperarPrecoDaFatia(pizzaDTO3).getPrecoProFatia();
								
								preco += (qtdDeFatiasDeCadaSabor * preco1) + (qtdDeFatiasDeCadaSabor * preco2) + (qtdDeFatiasDeCadaSabor * preco3);
							}
							
						}

						int idDoPedido = atendenteController.recuperarDadosDeTodosPedidos().getPedidos().size() + 1;
						
						atendenteController.adicionarPedido(new PedidoDTO(idDoPedido, cpf.getText(), qtdFatias, saboresQueCompoemAPizza, preco, atendenteController.recuperarEndereco(clienteDTO).getEndereço(), codigoDoAtendenteLogado));
						
		// gambiarra para atualizar a página
						JOptionPane.showMessageDialog(telaDeAtendimento, "Pedido Finalizado!");
						TelaDeAtendimento telaDeAtendimentoAtualizada = new TelaDeAtendimento(codigoDoAtendenteLogado);
						telaDeAtendimentoAtualizada.setLocationRelativeTo(telaDeAtendimento);
						telaDeAtendimento.dispose();
						
					}else {
						JOptionPane.showMessageDialog(telaDeAtendimento, "Escolha o(s) sabor(es) da pizza!", "Nenhum sabor foi selecionado", JOptionPane.ERROR_MESSAGE);
					}
				}catch(CPFInexistenteException er) {
					JOptionPane.showMessageDialog(telaDeAtendimento, er.getMessage(), "CPF inválido", JOptionPane.ERROR_MESSAGE);
				}catch(CampoVazioException er) {
					JOptionPane.showMessageDialog(telaDeAtendimento, "Preencha o campo CPF!", "CPF vazio", JOptionPane.ERROR_MESSAGE);
				}
				

				
			}else if(botao.equals("Novo Cliente")) {
				TelaDeCadastroDeCliente telaDeCadastroDeCliente = new TelaDeCadastroDeCliente(codigoDoAtendenteLogado);
				telaDeCadastroDeCliente.setLocationRelativeTo(telaDeAtendimento);
				telaDeAtendimento.dispose();
				
			}
			
		}
		
	}
	
}
