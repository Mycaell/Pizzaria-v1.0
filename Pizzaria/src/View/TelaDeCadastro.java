package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import Exceções.FuncionarioExistenteException;
import Ouvintes.OuvinteDoOlho;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;
import Utilidades.ValidacaoDeSenhaEmailECampos;


public class TelaDeCadastro  extends TelaPadrao {

	private JTextField nome;
	private JTextField sobrenome;
	private char sexo = 'F';
	private JFormattedTextField dataDeNascimento;
	private JTextField endereço;
	private JFormattedTextField telefone;
	private JFormattedTextField CPF;
	private JTextField email;
	private JPasswordField senha;
	private JPasswordField confirmacaoDeSenha;
	
	private JComboBox<Object> funcionarios;

	private JButton botaoSalvar;
	private JButton botaoCancelar;
	
	private JLabel olho;
	private JTextField senhaRevelada;
	
	private JRadioButton radioButtonF;
	private JRadioButton radioButtonM;
	

	private int codigoDoGerenteLogado;
	
	public TelaDeCadastro(int codigoDoGerenteLogado) {
		super("Cadastro");
		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;

		adicionarLabels();
		adicionarBotoes();
		adicionarFields();
		adicionarMascaras();
		this.setVisible(true);
	}
	
	
	private void adicionarLabels() {
		AdicionadorDeComponentes.adicionarJLabel(this, "Nome", 20, 10, 40, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Sobrenome", 190, 10, 70, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Sexo", 20, 40, 40, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Data de Nasc.", 190, 40, 130, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Endereço", 20, 70, 60, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Telefone", 20, 100, 50, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "CPF", 250, 100, 60, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "E-mail", 20, 130, 40, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Senha", 20, 160, 40, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Confirme a Senha", 20, 190, 110, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Cargo", 225, 160, 40, 20);
		
		GerenteController gerenteController = new GerenteController();
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		
		if(gerenteController.recuperarCargo(funcionarioDTO).getCargo() == null) {
			AdicionadorDeComponentes.adicionarJLabel(this, "<html><u> Direção (Direção : "+codigoDoGerenteLogado+")</u></html>", 5, 252, 400, 20);
		}else {
			AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 252, 400, 20);
		}
	
	}



	private void adicionarBotoes() {
		botaoSalvar = AdicionadorDeComponentes.adicionarJButton(this, "Salvar", 108, 230, 90, 20);
		botaoSalvar.setIcon(Icones.ICONE_SALVAR);
		botaoSalvar.addActionListener(new OuvinteBotaoSalvar(this));
		
		botaoCancelar = AdicionadorDeComponentes.adicionarJButton(this, "Cancelar", 212, 230, 105, 20);
		botaoCancelar.setIcon(Icones.ICONE_EXCLUIR);
		botaoCancelar.addActionListener(new OuvinteBotaoCancelar(this));
		
		radioButtonF = new JRadioButton("F");
		radioButtonF.setBounds(60, 40, 40, 20);
		radioButtonM = new JRadioButton("M");
		radioButtonM.setBounds(100, 40, 45, 20);

	    ButtonGroup grupoRadioSexo = new ButtonGroup();
	    grupoRadioSexo.add(radioButtonF);
	    grupoRadioSexo.add(radioButtonM);
	    
	    radioButtonF.setSelected(true);
	    
	    radioButtonF.setBackground(null);
	    radioButtonM.setBackground(null);
	    
	    add(radioButtonF);
	    add(radioButtonM);
	    
	    ImageIcon imgOlho = Icones.ICONE_OLHO_FECHADO;
	    olho = new JLabel(imgOlho);
	    olho.setToolTipText("revelar senha");
	    olho.setBounds(175, 160, 30, 20);
	    add(olho);
	    
	    senha = AdicionadorDeComponentes.adicionarJPasswordField(this,60, 160, 110, 20);
	    senhaRevelada = AdicionadorDeComponentes.adicionarJTextField(this, 60, 160,110, 20);
	    senhaRevelada.setVisible(false);
	    olho.addMouseListener(new OuvinteDoOlho(this,senha,senhaRevelada,olho));
	    
	    ArrayList<String> cargos = new ArrayList<String>();
	    
	    cargos.add("Gerente");
// 666 É O CÓDIGO DO DONO DO SISTEMA ( CADASTRANDO O PRIMEIRO GERENTE DO SISTEMA)
	    if(codigoDoGerenteLogado != 666) {
	    	cargos.add("Atendente");
	    	cargos.add("Pizzaiolo");
	    	cargos.add("Motoboy");
	    }
	    
	    funcionarios = new JComboBox<>(cargos.toArray());
	    funcionarios.setBounds(265, 160, 115, 20);
	    add(funcionarios);
	}
	
	private void adicionarFields() {
		nome = AdicionadorDeComponentes.adicionarJTextField(this,60, 10, 110, 20);
		sobrenome = AdicionadorDeComponentes.adicionarJTextField(this, 265, 10, 115, 20);
		endereço = AdicionadorDeComponentes.adicionarJTextField(this, 80, 70, 300, 20);
		email = AdicionadorDeComponentes.adicionarJTextField(this, 60, 130, 320, 20);
		email.setText("gmail");
		email.addFocusListener(new OuvinteDeFocoDoEmail(this));
		confirmacaoDeSenha = AdicionadorDeComponentes.adicionarJPasswordField(this, 130, 190, 110, 20);
	}

	private void adicionarMascaras() {
		try {
			dataDeNascimento = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "##/##/####", 280, 40, 100, 20);
			CPF = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "###.###.###-##", 280, 100, 100, 20);
			telefone = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "(##) ####-####", 75, 100, 110, 20);
		}catch (ParseException e) {

		}
	}
	
	private class OuvinteBotaoCancelar implements ActionListener {

		private TelaDeCadastro telaDeCadastro;
		
		public OuvinteBotaoCancelar(TelaDeCadastro telaDeCadastro) {
			this.telaDeCadastro = telaDeCadastro;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if(codigoDoGerenteLogado != 666) {
				TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoGerenteLogado);
				telaDeFuncionarios.setLocationRelativeTo(telaDeCadastro);
			}else {
				TelaDeLogin telaDeLogin = new TelaDeLogin();
				telaDeLogin.setLocationRelativeTo(telaDeCadastro);
			}
			
			telaDeCadastro.dispose();
			
		}
		
	}
	
	private class OuvinteDeFocoDoEmail implements FocusListener{

		private TelaDeCadastro telaDeCadastro;
		
		public OuvinteDeFocoDoEmail(TelaDeCadastro telaDeCadastro) {
			this.telaDeCadastro = telaDeCadastro;
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			email.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {
			if(email.getText().equals("")) {
				email.setText("gmail");
			}
			
		}
		
	}	
	
	private class OuvinteBotaoSalvar implements ActionListener{
		
		private TelaDeCadastro telaDeCadastro;
		
		public OuvinteBotaoSalvar(TelaDeCadastro telaDeCadastro) {
			this.telaDeCadastro = telaDeCadastro;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			
			if(!radioButtonF.isSelected()) {
				sexo = 'M';
			}
			
			
			if(ValidacaoDeSenhaEmailECampos.validarDadosDoUsuario(telaDeCadastro, nome, sobrenome, dataDeNascimento, endereço, telefone, CPF, email) && ValidacaoDeSenhaEmailECampos.validarSenhaEConfirmacaoDeSenha(telaDeCadastro, senha, confirmacaoDeSenha)) {

				
				String cargoSelecionado = (String) funcionarios.getSelectedItem();

				
				GerenteController gerenteController = new GerenteController();
				
				Date dataDeNasc = null;
				try {
					SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
					dataDeNasc = formataData.parse(dataDeNascimento.getText());
				}catch (ParseException er) {
				}

				
//				int codigo = gerenteController.recuperarDadosDeTodosFuncionarios().getDadosDosFuncionarios().size() + 1;
				
				try {
					gerenteController.cadastrarFuncionario(new FuncionarioDTO(nome.getText(),sobrenome.getText(),sexo,dataDeNasc,endereço.getText(),telefone.getText(),CPF.getText(),email.getText(),new String(senha.getPassword()),cargoSelecionado, codigoDoGerenteLogado));
					JOptionPane.showMessageDialog(telaDeCadastro, "Cadastrado realizado com sucesso!");
					
					if(codigoDoGerenteLogado != 666) {
						TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoGerenteLogado);
						telaDeFuncionarios.setLocationRelativeTo(telaDeCadastro);
					}else {
						TelaDeLogin telaDeLogin = new TelaDeLogin();
						telaDeLogin.setLocationRelativeTo(telaDeCadastro);
					}
					
					telaDeCadastro.dispose();
					
				}catch (FuncionarioExistenteException er) {
					JOptionPane.showMessageDialog(telaDeCadastro, er.getMessage(),  "E-mail inválido", JOptionPane.ERROR_MESSAGE);
				}

				
			}
		}
	}
	

}
