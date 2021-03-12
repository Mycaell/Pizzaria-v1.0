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

public class TelaDeFuncionarios extends TelaComMenu{

	private JTable tabela;

	private int codigoDoGerenteLogado;
	
	public TelaDeFuncionarios(int codigoDoGerenteLogado) {
		super("Funcionários");
		this.setSize(655,300);
		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		adicionarTabela();
		adicionarBotoes();
	
		this.setVisible(true);
	}
	
	private DefaultTableModel modelo;
	
	private void adicionarTabela() {

		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Funcionários", 250, 20, 350, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
		
		GerenteController gerenteController = new GerenteController();
	
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 5, 655, 20);
		
		modelo = new DefaultTableModel();
		
		tabela = new JTable(modelo);
		
		modelo.addColumn("Nome");
		modelo.addColumn("Código de Identificação");
		modelo.addColumn("Cargo");
		
		for (String[] dadosDoFuncionario : gerenteController.recuperarDadosDeTodosFuncionarios().getDadosDosFuncionarios()) {
			Object[] linha = {dadosDoFuncionario[0], dadosDoFuncionario[1], dadosDoFuncionario[2]};
			modelo.addRow(linha);
		}
		
		tabela.addRowSelectionInterval(0, 0);		

		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(25, 55, 600, 135);
		add(scroll);
	}

	private void adicionarBotoes() {

		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 25, 210, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoGerenteLogado));
		
		OuvinteTelaDeFuncionarios ouvint = new OuvinteTelaDeFuncionarios(this);

		JButton botaoExcluir = AdicionadorDeComponentes.adicionarJButton(this, "Excluir", 240, 210, 95, 20);
		botaoExcluir.setIcon(Icones.ICONE_LIXO);
		botaoExcluir.addActionListener(ouvint);
		

//		x do botaoExcluir = 170
//		JButton botaoDetalhes = AdicionadorDeComponentes.adicionarJButton(this, "Detalhes", 310, 200, 100, 20);
//		botaoDetalhes.setIcon(arg0);
//		botaoDetalhes.addActionListener(arg0);

		
		JButton botaoAdicionarFuncionario = AdicionadorDeComponentes.adicionarJButton(this, "Cadastrar Funcionário", 442, 210, 180, 20);
		botaoAdicionarFuncionario.setIcon(Icones.ICONE_USUARIO);
		botaoAdicionarFuncionario.addActionListener(ouvint);
		
	}

	private class OuvinteTelaDeFuncionarios implements ActionListener{

		private TelaDeFuncionarios telaDeFuncionarios;
		
		public OuvinteTelaDeFuncionarios(TelaDeFuncionarios telaDeFuncionarios) {
			this.telaDeFuncionarios = telaDeFuncionarios;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			int linha = tabela.getSelectedRow();

			if (botao.equals("Excluir")){
				
				if(linha != -1) {
					
					String codigoString = (String) tabela.getValueAt(linha, 1);
					
					int codigo = Integer.parseInt(codigoString);
					
					
					GerenteController gerenteController = new GerenteController();
					
					FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
					funcionarioDTO.setCodigo(codigo);
					
					if(codigoDoGerenteLogado == codigo) {
						int opcao = JOptionPane.showConfirmDialog(telaDeFuncionarios, "Você concorda em excluir sua própria conta?","Excluir Conta",JOptionPane.YES_NO_OPTION);
					
						if(opcao == JOptionPane.YES_OPTION) {
							
							gerenteController.excluirFuncionario(funcionarioDTO);
							
							JOptionPane.showMessageDialog(telaDeFuncionarios, "Conta excluída!");
							
							TelaDeLogin telaDeLogin = new TelaDeLogin();
							telaDeLogin.setLocationRelativeTo(telaDeFuncionarios);
							telaDeFuncionarios.dispose();
						}
						
					}else {
						int opcao = JOptionPane.showConfirmDialog(telaDeFuncionarios, "Tem certeza que quer excluir esse funcionário?","Excluir Funionário",JOptionPane.YES_NO_OPTION);
						
						if(opcao == JOptionPane.YES_OPTION) {
							gerenteController.excluirFuncionario(funcionarioDTO);
							
							JOptionPane.showMessageDialog(telaDeFuncionarios, "Funcionário excluído!");
							
							modelo.removeRow(linha);
						}	
					}					
				}else {
					JOptionPane.showMessageDialog(telaDeFuncionarios, "Selecione um funcionário!", "Nenhum funcionário selecionado", JOptionPane.ERROR_MESSAGE);
				}
				
			}else {
				TelaDeCadastro telaDeCadastro = new TelaDeCadastro(codigoDoGerenteLogado);
				telaDeCadastro.setLocationRelativeTo(telaDeFuncionarios);
				telaDeFuncionarios.dispose();
			}
			
			
		}
		
	}
	

}
