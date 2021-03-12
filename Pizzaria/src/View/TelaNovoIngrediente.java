package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.GerenteController;
import DTO.IngredienteDTO;
import Utilidades.Icones;

public class TelaNovoIngrediente extends JDialog{
	
	
	private TelaDeIngredientes telaDeIngredientes;
	
	private int codigoDoGerenteLogado;

	private JTextField campoNome;
	private JTextField campoPreco;
	
	public TelaNovoIngrediente(TelaDeIngredientes telaDeIngredientes, int codigoDoGerenteLogado) {
		setTitle("Novo Ingrediente");
		setSize(270, 190);
		setResizable(false);
		setLayout(null);
		
		JLabel label = new JLabel(Icones.IMAGEM_DE_FUNDO);
		label.setBounds(0, 0, 270, 190);
		setContentPane(label);
		
		this.telaDeIngredientes = telaDeIngredientes;
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		adicionarJLabels();
		adicionarFields();
		adicionarBotoes();
		
		
		
		setVisible(true);
	}
	
	public void adicionarJLabels() {
		
		 JLabel labelNome = new JLabel("Nome");
		 labelNome.setBounds(10, 10, 40, 20);
	     add(labelNome);
	     
	     JLabel labelValor = new JLabel("Valor");
	     labelValor.setBounds(10, 50, 40, 20);
	     add(labelValor);	    	     
    
	}
	
	private void adicionarFields() {
		 campoNome = new JTextField();
	     campoNome.setBounds(50, 10, 170, 20);
	     add(campoNome);
	     
	     campoPreco = new JTextField();
	     campoPreco.setBounds(50, 50, 170, 20);
	     add(campoPreco);
	}
	
	private void adicionarBotoes() {
		
		Ouvinte ouvinte = new Ouvinte(this);

		JButton botaoAdicionar = new JButton("Adicionar");
		botaoAdicionar.setBounds(10, 105, 110, 20);
		botaoAdicionar.setIcon(Icones.ICONE_SALVAR);
		add(botaoAdicionar);
		botaoAdicionar.addActionListener(ouvinte);
     
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setBounds(135, 105, 105, 20);
		botaoCancelar.setIcon(Icones.ICONE_EXCLUIR);
		add(botaoCancelar);
		botaoCancelar.addActionListener(ouvinte);
		
	}
	
	public class Ouvinte implements ActionListener{

		private TelaNovoIngrediente telaNovoIngrediente;
		
		
		public Ouvinte(TelaNovoIngrediente telaNovoIngrediente) {
				this.telaNovoIngrediente = telaNovoIngrediente;
			}
			 
		public void actionPerformed(ActionEvent e) {
			
			String botao = e.getActionCommand();
			
			
		 	if(botao.equals("Adicionar")) {
//		 		fazer validações dos campos
				String nomeDigitado = telaNovoIngrediente.campoNome.getText();
				float valorDigitado = Float.parseFloat(telaNovoIngrediente.campoPreco.getText());							
				
				GerenteController gerenteController = new GerenteController();
				IngredienteDTO ingredienteDTO = new IngredienteDTO(nomeDigitado, valorDigitado);				
				gerenteController.adicionarIngrediente(ingredienteDTO);
				
				JOptionPane.showMessageDialog(telaNovoIngrediente, "Ingrediente adicionado!"); 

				
//				gambiarra pra atalizar a pizza
				TelaDeIngredientes telaDeIngredientesAtualizada = new TelaDeIngredientes(codigoDoGerenteLogado);
				telaDeIngredientesAtualizada.setLocationRelativeTo(telaNovoIngrediente);
				telaNovoIngrediente.dispose();
				TelaNovoIngrediente.this.telaDeIngredientes.dispose();
				
				


		 	
		 	}else {
				   telaNovoIngrediente.dispose();
			}
		}
	}
	

}
