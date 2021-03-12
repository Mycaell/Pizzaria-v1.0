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
import Controller.PizzaioloController;
import DTO.FuncionarioDTO;
import DTO.PedidoDTO;
import DTO.PizzaDTO;
import Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDePizzaiolo extends TelaComMenu{

	private JTable tabela;
	private DefaultTableModel modelo;
	

	private int codigoDoPizzaioloLogado;
	
	public TelaDePizzaiolo(int codigoDoPizzaioloLogado) {
		super("Pizzaiolo");

		this.setSize(655,300);
		
		this.codigoDoPizzaioloLogado = codigoDoPizzaioloLogado;
		
		adicionarComponentes();
		
		this.setVisible(true);
	}
	
	private void adicionarComponentes() {
		
		JLabel labelPedidosEmAberto = AdicionadorDeComponentes.adicionarJLabel(this, "Pedidos", 275, 20, 180, 20);
		labelPedidosEmAberto.setFont(new Font("Comic Sans", Font.BOLD, 18));
		
		
		GerenteController gerenteController = new GerenteController();
	
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoPizzaioloLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoPizzaioloLogado+")</u></html>", 5, 5, 655, 20);
	
		
		
		modelo = new DefaultTableModel();
		tabela = new JTable(modelo);
	

		PizzaioloController pizzaioloController = new PizzaioloController();

		
		if(pizzaioloController.recuperarDadosDeTodosPedidosProntos().getDadosDosPedidosProntosParaPreparo().size() == 0) {
			modelo.addColumn("Não há pedidos");
		}else {
			modelo.addColumn("Nº do pedido");
			modelo.addColumn("Tamanho");
			modelo.addColumn("Fatias");
			modelo.addColumn("Sabores");
			

			for (String[] dadosDoPedido : pizzaioloController.recuperarDadosDeTodosPedidosProntos().getDadosDosPedidosProntosParaPreparo()) {
				
				
				PizzaDTO pizzaDTO = new PizzaDTO();
				pizzaDTO.setIds(dadosDoPedido[3]);
				String sabores = gerenteController.getSabores(pizzaDTO).getSabores();
				
				
				Object[] linha = {dadosDoPedido[0], dadosDoPedido[1], dadosDoPedido[2], sabores};
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);		
			
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(15, 55, 615, 135);
		add(scroll);

		JButton botaoConcluirPedido = AdicionadorDeComponentes.adicionarJButton(this, "Concluir Pedido", 490, 210, 145, 20);
		botaoConcluirPedido.setIcon(Icones.ICONE_CERTO);
		botaoConcluirPedido.addActionListener(new OuvinteTelaDePizzaiolo(this));

		
		if(new GerenteController().recuperarCargo(funcionarioDTO).getCargo().equals("Gerente")) {
			JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 15, 210, 90, 20);
			botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
			botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoPizzaioloLogado));
		}
		
	}
	
	private class OuvinteTelaDePizzaiolo implements ActionListener{

		private TelaDePizzaiolo telaDePizzaiolo;
		
		public OuvinteTelaDePizzaiolo(TelaDePizzaiolo telaDePizzaiolo) {
			this.telaDePizzaiolo = telaDePizzaiolo;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			PizzaioloController pizzaioloController = new PizzaioloController();

			if(botao.equals("Concluir Pedido")) {
				int linha = tabela.getSelectedRow();
				
				if(linha != -1) {
					
					int opcao = JOptionPane.showConfirmDialog(telaDePizzaiolo, "Você quer mesmo concluir o pedido?","Concluir pedido",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						String numeroDoPedidoString = (String) tabela.getValueAt(linha, 0);
						
						PedidoDTO pedidoDTO = new PedidoDTO();
						pedidoDTO.setNumeroDoPedido(Integer.parseInt(numeroDoPedidoString));
						pedidoDTO.setCodigoDoPizzaiolo(codigoDoPizzaioloLogado);
						
						pizzaioloController.concluirPedido(pedidoDTO);
						modelo.removeRow(linha);
						
						JOptionPane.showMessageDialog(telaDePizzaiolo, "Pedido concluído!");
						
					}
				}else {
					JOptionPane.showMessageDialog(telaDePizzaiolo, "Selecione um pedido!", "Nenhum pedido selecionado", JOptionPane.ERROR_MESSAGE);	
				}

				
			}
			
		}
		
	}
	
	
}
