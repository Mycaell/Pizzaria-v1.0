package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.AtendenteController;
import Controller.GerenteController;
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import Exceções.CampoVazioException;
import Exceções.ClienteExistenteException;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDeCadastroDeCliente extends TelaComMenu{

	
	private JTextField nome;
	private JTextField sobrenome;
	private JFormattedTextField telefone;
	private JFormattedTextField CPF;
	private JTextField bairro;
	private JTextField rua;
	private JTextField numDaCasa;
	
	private int codigoDoAtendenteLogado;
	
	
	
	public TelaDeCadastroDeCliente(int codigoDoAtendenteLogado) {
		super("Cadastramento de Cliente");
		this.setSize(655,300);
		
		this.codigoDoAtendenteLogado = codigoDoAtendenteLogado;
		adicionarLabels();
		adicionarFields();
		adicionarBotoes();
		
		
		this.setVisible(true);
		
	}
	

	
	private void adicionarLabels() {
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Insira os dados do Cliente", 223, 20, 350, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Nome", 20, 60, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Sobrenome", 330, 60, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Telefone", 20, 90, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "CPF", 373, 90, 50, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Bairro", 20, 120, 70, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Rua", 20, 150, 40, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Nº da casa", 334, 120, 90, 20);
		
		GerenteController gerenteController = new GerenteController();
	
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoAtendenteLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoAtendenteLogado+")</u></html>", 5, 5, 655, 20);
	
		
		
	}

	
	
	private void adicionarFields() {
		nome = AdicionadorDeComponentes.adicionarJTextField(this, 60, 60, 210, 20);
		sobrenome = AdicionadorDeComponentes.adicionarJTextField(this, 400, 60, 200, 20);
		
		try {
			telefone = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "(##) ####-####", 75, 90, 90, 20);
			CPF = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "###.###.###-##", 400, 90, 100, 20);
		}catch (ParseException e) {
		}
		
		
		bairro = AdicionadorDeComponentes.adicionarJTextField(this, 60, 120, 210, 20);
		rua = AdicionadorDeComponentes.adicionarJTextField(this, 48, 150, 222, 20);
		numDaCasa = AdicionadorDeComponentes.adicionarJTextField(this, 400, 120, 50, 20);
	}
	
	
	private void adicionarBotoes() {
		
		OuvinteTelaDeCadastroDeCliente ouvinte = new OuvinteTelaDeCadastroDeCliente(this);
		JButton BTvoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 40, 210, 90, 20);
		BTvoltar.setIcon(Icones.ICONE_VOLTAR);
		BTvoltar.addActionListener(ouvinte);
		
		JButton BTlimparCampos = AdicionadorDeComponentes.adicionarJButton(this, "Limpar Campos", 230, 210, 145, 20);
		BTlimparCampos.setIcon(Icones.ICONE_VASSOURA);
		BTlimparCampos.addActionListener(ouvinte);
		
		JButton BTsalvar = AdicionadorDeComponentes.adicionarJButton(this, "Salvar", 480, 210, 90, 20);
		BTsalvar.setIcon(Icones.ICONE_SALVAR);
		BTsalvar.addActionListener(ouvinte);
	}

	private class OuvinteTelaDeCadastroDeCliente implements ActionListener{

		private TelaDeCadastroDeCliente janela;
		
		public OuvinteTelaDeCadastroDeCliente(TelaDeCadastroDeCliente janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			if(botao.equals("Salvar")) {
				
				AtendenteController atendenteController = new AtendenteController();
					
				try {
					
					if(numDaCasa.getText().equals("")) {
						throw new CampoVazioException();
					}
					
					
					int idDoCliente = new GerenteController().recuperarDadosDeTodosOsClientes().getDadosDosClientes().size() + 1;
					
					ClienteDTO clienteDTO = new ClienteDTO(idDoCliente, nome.getText(), sobrenome.getText(), CPF.getText(), telefone.getText(), bairro.getText(), rua.getText(), Integer.parseInt(numDaCasa.getText()), codigoDoAtendenteLogado);
					
					atendenteController.cadastrarCliente(clienteDTO);

					JOptionPane.showMessageDialog(janela, "Cliente Cadastrado!");
					
					TelaDeAtendimento telaDeAtendimento = new TelaDeAtendimento(codigoDoAtendenteLogado);
					telaDeAtendimento.setLocationRelativeTo(janela);
					
					telaDeAtendimento.getCpf().setText(CPF.getText());
					
					janela.dispose();
					
				}catch (CampoVazioException er) {
					JOptionPane.showMessageDialog(janela, er.getMessage(), "Campo vazio", JOptionPane.ERROR_MESSAGE);
				}catch (ClienteExistenteException er) {
					JOptionPane.showMessageDialog(janela, er.getMessage(), "CPF inválido", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException er) {
					JOptionPane.showMessageDialog(janela, "O Nº da casa deve ser preenchido apenas com números!", "Nº da casa inválido", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(botao.equals("Voltar")) {
				TelaDeAtendimento telaDeAtendimento = new TelaDeAtendimento(codigoDoAtendenteLogado);
				telaDeAtendimento.setLocationRelativeTo(janela);
				janela.dispose();
			}else {
				nome.setText("");
				sobrenome.setText("");
				telefone.setText("");
				CPF.setText("");
				bairro.setText("");
				rua.setText("");
				numDaCasa.setText("");
			}
			
		}
		
	}


	
	
}
