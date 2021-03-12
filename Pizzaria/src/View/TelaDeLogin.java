	package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import Exceções.CampoVazioException;
import Exceções.FuncionarioNaoExistenteException;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;
import Utilidades.ValidacaoDeSenhaEmailECampos;

public class TelaDeLogin extends TelaPadrao{

	private JTextField email;
	private JPasswordField senha;

	public TelaDeLogin() {
		super("Login");
		
		adicionarLabels();
		adicionarBotoes();
		adicionarFields();
		
		this.setVisible(true);
	}	
	
	private void adicionarLabels() {
		AdicionadorDeComponentes.adicionarJLabel(this, "E-mail", 70, 40, 50, 30);		
		AdicionadorDeComponentes.adicionarJLabel(this, "Senha",70, 80, 50, 30);
	}
	
	private void adicionarBotoes() {
		JButton botaoEntrar = AdicionadorDeComponentes.adicionarJButton(this, "Entrar", 248, 115, 70, 20);
		botaoEntrar.addActionListener(new OuvinteTelaDeLogin(this));
		
		JButton botaoCadastrar = AdicionadorDeComponentes.adicionarJButton(this, "Cadastrar-se", 190, 200, 130, 20);
		botaoCadastrar.setIcon(Icones.ICONE_USUARIO);
		botaoCadastrar.addActionListener(new OuvinteTelaDeLogin(this));
	}
	
	private void adicionarFields() {
		email = AdicionadorDeComponentes.adicionarJTextField(this, 120, 45, 200, 20);
		senha = AdicionadorDeComponentes.adicionarJPasswordField(this, 120, 85, 200, 20);
	}
	
	private class OuvinteTelaDeLogin implements ActionListener {

		
		private TelaDeLogin telaDeLogin;
		
		public OuvinteTelaDeLogin(TelaDeLogin telaDeLogin) {
			this.telaDeLogin = telaDeLogin;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			if(botao.equals("Cadastrar-se")){
				String codigoDeAcesso = JOptionPane.showInputDialog(telaDeLogin,"Digite o código de acesso","Código de Acesso",JOptionPane.QUESTION_MESSAGE);
				
				if(codigoDeAcesso != null) {
					if(codigoDeAcesso.equals("chave mestre")) {
//						esse parâmetro é o código do dono do SISTEMA
						TelaDeCadastro  telaDeCadastro = new TelaDeCadastro(666);
						telaDeCadastro.setLocationRelativeTo(telaDeLogin);
						telaDeLogin.dispose();
					}else {
						JOptionPane.showMessageDialog(telaDeLogin, "Código inválido!", "Erro!", JOptionPane.ERROR_MESSAGE); 		    	
					}	
				}
				
			}else if(botao.equals("Entrar")) {

				try {
					
					String emailDigitado = email.getText();
					String senhaDigitada = new String(senha.getPassword());
					
					ValidacaoDeSenhaEmailECampos.validarCampo(emailDigitado);
					ValidacaoDeSenhaEmailECampos.validarCampo(senhaDigitada);
					
					GerenteController gerenteController = new GerenteController();
					FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
					funcionarioDTO.setEmail(emailDigitado);
					funcionarioDTO.setSenha(senhaDigitada);
					
				
					funcionarioDTO = gerenteController.recuperarFuncionario(funcionarioDTO);
					
					String cargo = funcionarioDTO.getCargo();
					
					if(cargo != null) {
						
						int codigoDoFuncionarioLogado = gerenteController.recuperarCodigo(funcionarioDTO).getCodigo();
						if(cargo.equals("Gerente")) {
							TelaDeGerente telaDeGerente = new TelaDeGerente(codigoDoFuncionarioLogado);
							telaDeGerente.setLocationRelativeTo(telaDeLogin);
							telaDeLogin.dispose();
							
						}else if(cargo.equals("Atendente")) {
							TelaDeAtendimento telaDeAtendente = new TelaDeAtendimento(codigoDoFuncionarioLogado);
							telaDeAtendente.setLocationRelativeTo(telaDeLogin);
							telaDeLogin.dispose();
							
						}else if(cargo.equals("Pizzaiolo")) {
							TelaDePizzaiolo telaDePizzaiolo = new TelaDePizzaiolo(codigoDoFuncionarioLogado);
							telaDePizzaiolo.setLocationRelativeTo(telaDeLogin);
							telaDeLogin.dispose();
						}else if(cargo.equals("Motoboy")) {
							TelaDeMotoboy telaDeMotoboy = new TelaDeMotoboy(codigoDoFuncionarioLogado);
							telaDeMotoboy.setLocationRelativeTo(telaDeLogin);
							telaDeLogin.dispose();
						}
					}
						
						
				}catch (CampoVazioException er) {
					JOptionPane.showMessageDialog(telaDeLogin,er.getMessage(),"Campo vázio",JOptionPane.ERROR_MESSAGE);
				}catch (FuncionarioNaoExistenteException e1) {
				JOptionPane .showMessageDialog(telaDeLogin, e1.getMessage());
				}
				
				
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		new TelaDeLogin();
	}
	
}
