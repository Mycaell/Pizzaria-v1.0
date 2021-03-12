package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import View.TelaDeGerente;

public class OuvinteQueLevaParaTelaDoGerente implements ActionListener{

	private JFrame janela;
	private int codigoDoGerenteLogado;
	
	public OuvinteQueLevaParaTelaDoGerente(JFrame janela, int codigoDoGerenteLogado) {
		this.janela = janela;
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		TelaDeGerente telaDeGerente = new TelaDeGerente(codigoDoGerenteLogado);
		telaDeGerente.setLocationRelativeTo(janela);
		janela.dispose();
	}

}
