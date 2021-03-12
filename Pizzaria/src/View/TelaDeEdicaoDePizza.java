package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import DTO.PizzaDTO;
import Utilidades.AdicionadorDeComponentes;
import Utilidades.Icones;

public class TelaDeEdicaoDePizza extends TelaComMenu{

	private JTextField campoSabor;
	private JTextField campoPreco;

	private int idDaPizza;
	private String saborAtual;
	private float precoAtual;
	
	private int codigoDoGerenteLogado;
	
	private TelaDePizzas tela;
	
	public TelaDeEdicaoDePizza(TelaDePizzas tela, int codigoDoGerenteLogado, int idDaPizza, String saborAtual, float precoAtual) {
		super("Edição de Pizza");
		this.setSize(270, 230);
	
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		this.idDaPizza = idDaPizza;
		this.saborAtual = saborAtual;
		this.precoAtual = precoAtual;
		
		this.tela = tela;
		adicionarComponentes();
	
		this.setVisible(true);
	}
	
	private void adicionarComponentes() {
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Dados da Pizza", 70, 20, 350, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
		
		GerenteController gerenteController = new GerenteController();
	
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 5, 270, 20);
	
		
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Sabor", 30, 60, 40, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Preço por fatia", 30, 100, 100, 20);

		campoSabor = AdicionadorDeComponentes.adicionarJTextField(this, 75, 60, 150, 20);
		campoSabor.setText(saborAtual);
		
		campoPreco = AdicionadorDeComponentes.adicionarJTextField(this, 120, 100, 105, 20);
		campoPreco.setText(Float.toString(precoAtual));
		
		Ouvinte ouvinte = new Ouvinte(this);
		
		JButton botaoSalvar = AdicionadorDeComponentes.adicionarJButton(this, "Salvar", 40, 150, 90, 20);
		botaoSalvar.setIcon(Icones.ICONE_SALVAR);
		botaoSalvar.addActionListener(ouvinte);
		
		JButton botaoCancelar = AdicionadorDeComponentes.adicionarJButton(this, "Cancelar", 140, 150, 105, 20);
		botaoCancelar.setIcon(Icones.ICONE_EXCLUIR);
		botaoCancelar.addActionListener(ouvinte);
	}

	private class Ouvinte implements ActionListener{

		private TelaDeEdicaoDePizza telaDeEdicaoDePizza;
		
		public Ouvinte(TelaDeEdicaoDePizza telaDeEdicaoDePizza) {
			this.telaDeEdicaoDePizza = telaDeEdicaoDePizza;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
//			esse método terá q ser totalmente modificado
			
			if(botao.equals("Salvar")) {
				GerenteController gerenteController = new GerenteController();
				
				
				PizzaDTO pizzaDTO = new PizzaDTO();
				
				pizzaDTO.setIdDaPizza(idDaPizza);
				pizzaDTO.setNovoSabor(campoSabor.getText());
				pizzaDTO.setNovoPrecoPorFatia(Float.parseFloat(campoPreco.getText()));
				
				gerenteController.editarPizza(pizzaDTO);
				
				TelaDePizzas telaDeSaboresDePizza = new TelaDePizzas(codigoDoGerenteLogado);
				telaDeSaboresDePizza.setLocationRelativeTo(telaDeEdicaoDePizza);
				telaDeEdicaoDePizza.dispose();
				tela.dispose();
				
			}else if(botao.equals("Cancelar")) {
				telaDeEdicaoDePizza.dispose();
			}
			
		}
		
	}
	
}
