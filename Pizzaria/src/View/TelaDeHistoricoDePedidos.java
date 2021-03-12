package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import DTO.PizzaDTO;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDeHistoricoDePedidos extends TelaComMenu {

	
	private int codigoDoGerenteLogado;
	private String nomeDoCliente;
	private String cpfDoCliente;
	
	public TelaDeHistoricoDePedidos(int codigoDoGerenteLogado, String nomeDoCliente, String cpfDoCliente) {
		super("Histórico de Pedidos");
		this.setSize(1000, 300);
		
		JLabel label = new JLabel(Icones.IMAGEM_DE_FUNDO_HISTORICO_DE_PEDIDOS);
		label.setBounds(0, 0, 1000, 300);
		setContentPane(label);

		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		this.nomeDoCliente = nomeDoCliente;
		this.cpfDoCliente = cpfDoCliente;
		
		adicionarComponentes();
		
		this.setVisible(true);
	}

	
	public void adicionarComponentes() {
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Pedidos de "+nomeDoCliente, 410, 20, 350, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
	
		GerenteController gerenteController = new GerenteController();
	
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 5, 1000, 20);
	
		
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		JTable tabela = new JTable(modelo);
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setCPF(cpfDoCliente);
		
		if(gerenteController.recuperarDadosDeTodosPedidosDeUmCliente(clienteDTO).getDadosDosPedidosDeUmUnicoCliente().size() == 0) {
			modelo.addColumn("Não há pedidos");
		}else {
			modelo.addColumn("Nº do pedido");
			modelo.addColumn("CPF do cliente");
			modelo.addColumn("Tamanho");
			modelo.addColumn("Fatias");
			modelo.addColumn("Sabores");
			modelo.addColumn("Preço");
			modelo.addColumn("Endereço (B,R,N)");
			modelo.addColumn("Data do Pedido");
			modelo.addColumn("Data de Preparo");
			modelo.addColumn("Data de Entrega");

			

			tabela.getColumnModel().getColumn(0).setPreferredWidth(55);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(40);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
			
			
			
			
			for (int i = gerenteController.recuperarDadosDeTodosPedidosDeUmCliente(clienteDTO).getDadosDosPedidosDeUmUnicoCliente().size()-1; i >= 0; i--) {
				String[] pedido = gerenteController.recuperarDadosDeTodosPedidosDeUmCliente(clienteDTO).getDadosDosPedidosDeUmUnicoCliente().get(i);
				

				PizzaDTO pizzaDTO = new PizzaDTO();
				pizzaDTO.setIds(pedido[4]);
				String sabores = gerenteController.getSabores(pizzaDTO).getSabores();
				
				
				
				Object[] linha = {pedido[0], pedido[1], pedido[2], pedido[3], sabores, pedido[5], pedido[6], pedido[7], pedido[8], pedido[9]};
				modelo.addRow(linha);
			}
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(20, 55, 952, 135);
		add(scroll);
		
		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "voltar", 40, 210, 91, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(new Ouvinte(this));
	}
	
	
	
	private class Ouvinte implements ActionListener{

		private TelaDeHistoricoDePedidos telaDeHistoricoDePedidos;
		
		public Ouvinte(TelaDeHistoricoDePedidos telaDeHistoricoDePedidos) {
			this.telaDeHistoricoDePedidos = telaDeHistoricoDePedidos;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {

			TelaDeClientes telaDeClientes = new TelaDeClientes(codigoDoGerenteLogado);
			telaDeClientes.setLocationRelativeTo(telaDeHistoricoDePedidos);
			telaDeHistoricoDePedidos.dispose();
			
		}
		
	}
	
	
	
	
	
}
