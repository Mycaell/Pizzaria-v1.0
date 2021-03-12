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
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDeClientes extends TelaComMenu {

	private JTable tabela;
	
	
	private int codigoDoGerenteLogado;
	public TelaDeClientes(int codigoDoGerenteLogado) {
		super("Clientes");
		this.setSize(655,300);
		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		adicionarTabela();
		adicionarBotoes();
		
		this.setVisible(true);
	}

	
	
	private DefaultTableModel modelo;
	
	private void adicionarTabela() {
	
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Clientes", 265, 20, 350, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
		
		GerenteController gerenteController = new GerenteController();
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);;
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 5, 655, 20);
	
		
		modelo = new DefaultTableModel();
		
		tabela = new JTable(modelo);
		
		
		
		if(gerenteController.recuperarDadosDeTodosOsClientes().getDadosDosClientes().size() != 0) {
			modelo.addColumn("Nome");
			modelo.addColumn("CPF");
			modelo.addColumn("Telefone");
			modelo.addColumn("Endereço (B,R,N)");
			
			for (String[] dadosDoCliente : gerenteController.recuperarDadosDeTodosOsClientes().getDadosDosClientes()) {
				Object[] linha = {dadosDoCliente[0], dadosDoCliente[1], dadosDoCliente[2], dadosDoCliente[3]};
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);		
		}else {
			modelo.addColumn("Não há clientes");
		}

		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(30, 55, 600, 135);
		add(scroll);
		
		
	}
	
	private void adicionarBotoes() {
		
		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 25, 210, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoGerenteLogado));
		
		Ouvint ouvint = new Ouvint(this);

		JButton botaoExcluir = AdicionadorDeComponentes.adicionarJButton(this, "Excluir", 230, 210, 95, 20);
		botaoExcluir.setIcon(Icones.ICONE_LIXO);
		botaoExcluir.addActionListener(ouvint);
	
		JButton botaoHistoricoDePedidos = AdicionadorDeComponentes.adicionarJButton(this, "Histórico de Pedidos", 435, 210, 175, 20);
		botaoHistoricoDePedidos.setIcon(Icones.ICONE_HISTORICO);
		botaoHistoricoDePedidos.addActionListener(ouvint);
				
	}
	
	private class Ouvint implements ActionListener{

		private TelaDeClientes telaDeClientes;
		
		public Ouvint(TelaDeClientes telaDeClientes) {
			this.telaDeClientes = telaDeClientes;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();

			int linha = tabela.getSelectedRow();
			
			if(botao.equals("Excluir")){
				
				if(linha != -1) {
					String cpf = (String)tabela.getValueAt(linha, 1);
					
					GerenteController gerenteController = new GerenteController();

					int opcao = JOptionPane.showConfirmDialog(telaDeClientes, "Você tem certeza que quer excluir esse cliente?","Excluir Cliente",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						
						ClienteDTO clienteDTO = new ClienteDTO();
						clienteDTO.setCPF(cpf);
						gerenteController.excluirCliente(clienteDTO);
						
						JOptionPane.showMessageDialog(telaDeClientes, "Cliente excluído!");
						
						modelo.removeRow(linha);
					}
					
				}else {
					JOptionPane.showMessageDialog(telaDeClientes, "Selecione um cliente!", "Nenhum cliente selecionado", JOptionPane.ERROR_MESSAGE);
				}
					
			}else if(botao.equals("Histórico de Pedidos")) {

				if(linha != -1) {
					String nomeDoCliente = (String) tabela.getValueAt(linha, 0);
					String cpfDoCliente = (String) tabela.getValueAt(linha, 1);
					
					TelaDeHistoricoDePedidos telaDeHistoricoDePedidos = new TelaDeHistoricoDePedidos(codigoDoGerenteLogado, nomeDoCliente, cpfDoCliente);
					telaDeHistoricoDePedidos.setLocationRelativeTo(telaDeClientes);
					telaDeClientes.dispose();
				}else {
					JOptionPane.showMessageDialog(telaDeClientes, "Selecione um cliente!", "Nenhum cliente selecionado", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		}
		
	}
	
}


