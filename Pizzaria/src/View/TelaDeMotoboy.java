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
import Controller.MotoboyController;
import DTO.FuncionarioDTO;
import DTO.PedidoDTO;
import DTO.PizzaDTO;
import Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDeMotoboy extends TelaComMenu {

	
	
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private int codigoDoMotoboyLogado;

	
	
	public TelaDeMotoboy(int codigoDoMotoboyLogado) {
		super("Motoboy");
		
		this.setSize(655,300);
	
		this.codigoDoMotoboyLogado = codigoDoMotoboyLogado;
		adicionarComponentes();
	
		this.setVisible(true);
	}
	
	private void adicionarComponentes() {
		JLabel labelPedidosEmAberto = AdicionadorDeComponentes.adicionarJLabel(this, "Pedidos prontos para entrega", 220, 20, 270, 20);
		labelPedidosEmAberto.setFont(new Font("Comic Sans", Font.BOLD, 18));
	
	
		GerenteController gerenteController = new GerenteController();
	
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoMotoboyLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoMotoboyLogado+")</u></html>", 5, 5, 655, 20);
	
		
		modelo = new DefaultTableModel();
		tabela = new JTable(modelo);
	
		MotoboyController motoboyController = new MotoboyController();

		if(motoboyController.recuperarDadosDetodosPedidosProntosParaEntrega().getDadosDosPedidosProntosParaEntrega().size() == 0) {
			modelo.addColumn("Não há pedidos");
		}else {
			
			modelo.addColumn("Nº do pedido");
			modelo.addColumn("CPF do cliente");
			modelo.addColumn("Fatias");
			modelo.addColumn("Sabores");
			modelo.addColumn("Preço");
			modelo.addColumn("Endereço (B,R,N)");
			
			for (String[] dadosDoPedido : motoboyController.recuperarDadosDetodosPedidosProntosParaEntrega().getDadosDosPedidosProntosParaEntrega()) {
				
				
				PizzaDTO pizzaDTO = new PizzaDTO();
				pizzaDTO.setIds(dadosDoPedido[3]);
				String sabores = gerenteController.getSabores(pizzaDTO).getSabores();
				
				
				Object[] linha = {dadosDoPedido[0], dadosDoPedido[1], dadosDoPedido[2], sabores, dadosDoPedido[4], dadosDoPedido[5]};
				
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);	
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(15, 55, 615, 135);
		add(scroll);

		JButton botaoRealizarEntrega = AdicionadorDeComponentes.adicionarJButton(this, "Realizar Entrega", 485, 210, 150, 20);
		botaoRealizarEntrega.setIcon(Icones.ICONE_CERTO);
		botaoRealizarEntrega.addActionListener(new OuvinteTelaDeMotoboy(this));

		if(new GerenteController().recuperarCargo(funcionarioDTO).getCargo().equals("Gerente")) {
			JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 15, 210, 90, 20);
			botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
			botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoMotoboyLogado));
		}
		
		
	}
	
	private class OuvinteTelaDeMotoboy implements ActionListener{
		
		private TelaDeMotoboy telaDeMotoboy;
		
		public OuvinteTelaDeMotoboy(TelaDeMotoboy telaDeMotoboy) {
			this.telaDeMotoboy = telaDeMotoboy;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			
			String botao = e.getActionCommand();

			if(botao.equals("Realizar Entrega")) {
				
				int linha = tabela.getSelectedRow();
				
				
				if(linha != -1) {
					
					int opcao = JOptionPane.showConfirmDialog(telaDeMotoboy, "Você quer mesmo realizar a entrega do pedido?","Realizar entrega",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						String numeroDoPedidoString = (String) tabela.getValueAt(linha, 0);
						
						int numeroDoPedido = Integer.parseInt(numeroDoPedidoString);
						
						MotoboyController motoboyController = new MotoboyController();
						
						PedidoDTO pedidoDTO = new PedidoDTO();
						pedidoDTO.setNumeroDoPedido(numeroDoPedido);
						pedidoDTO.setCodigoDoMotoboy(codigoDoMotoboyLogado);
						
//						alterações
						float precoPedido = Float.parseFloat((String) tabela.getValueAt(linha, 4));
						pedidoDTO.setPreco(precoPedido);
//						!!!!!!!!!!!!!!!!!!!!
						
						
						motoboyController.realizarEntregaDePedido(pedidoDTO);
						
						modelo.removeRow(linha);
						
						JOptionPane.showMessageDialog(telaDeMotoboy, "Pedido entregue!");
						
					}
					
					
				}else {
					JOptionPane.showMessageDialog(telaDeMotoboy, "Selecione um pedido!", "Nenhum pedido selecionado", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			
			
		}
		
	}
	
	
	
}
