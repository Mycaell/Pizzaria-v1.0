package Utilidades;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


public class AdicionadorDeComponentes {
	
	
	public static JLabel adicionarJLabel(JFrame janela, String textoDoLabel, int x, int y, int c, int a) {
		JLabel label = new JLabel(textoDoLabel);
//		label.setFont(new Font("Comic Sans", Font.BOLD, 12));
		label.setForeground(Color.BLACK);
		
		
		
		label.setBounds(x, y, c, a);
		janela.add(label);
		return label;
	}
	
	public static JButton adicionarJButton(JFrame janela, String textoDoBotao, int x, int y, int c, int a) {
		JButton botao = new JButton(textoDoBotao);
		botao.setBounds(x, y, c, a);
		janela.add(botao);

//		\/ faz um contorno suave no em cima do botão para indicar que ele está selecionado
		botao.setFocusPainted(false);
		return botao;
	}
	
	public static JTextField adicionarJTextField(JFrame janela, int x, int y, int c, int a) {
		JTextField textField = new JTextField();
		textField.setBounds(x, y, c, a);
		janela.add(textField);
		return textField;
	}
	
	public static JPasswordField adicionarJPasswordField(JFrame janela, int x, int y, int c, int a) {
		JPasswordField senha = new JPasswordField(); 
		senha.setBounds(x, y, c, a);
		janela.add(senha);
		return senha;
	}
	
	public static JFormattedTextField adicionarJFormattedTextFiel(JFrame janela, String formato, int x, int y, int c, int a ) throws ParseException {
		MaskFormatter mascara = new MaskFormatter(formato);
		
//		###.###.###-##
		JFormattedTextField campo = new JFormattedTextField(mascara);
		campo.setBounds(x, y, c, a);
		janela.add(campo);
		return campo;
	}
	
	public static JTextArea adicionarJTextArea(JFrame janela, int x, int y, int c, int a) {
		JTextArea textArea = new JTextArea();
		textArea.setBounds(x, y, c, a);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		janela.add(textArea);
		return textArea;
	}

	public static JMenuBar adicionarJMenubar(JFrame janela,int x,int y, int c, int a) {
		JMenuBar barraDeMenu = new JMenuBar();
		barraDeMenu.setBounds(x, y, c, a);
		janela.add(barraDeMenu);
		return barraDeMenu;
	}

	public static JMenu adicionarJMenuComIconeSemTexo(JMenuBar barraDeMenu,ImageIcon icone) {
		JMenu menu = new JMenu();
		menu.setIcon(icone);
		barraDeMenu.add(menu);
		return menu;
	}
	
	public static JMenuItem adicionarJMenuItem(JMenu menu, String nome) {
		JMenuItem item = new JMenuItem(nome);
		menu.add(item);
		return item;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
