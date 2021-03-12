package View;

import java.awt.Color;

import javax.swing.JFrame;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;

import Utilidades.Icones;


public class TelaPadrao extends JFrame{

	public TelaPadrao(String titulo) {
		super(titulo);
		this.setSize(400,300);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.setIconImage(Icones.ICONE_JANELA.getImage());
		this.getContentPane().setBackground(Color.lightGray);
		
		
		
//		this.getContentPane().setBackground(Color.DARK_GRAY);

//		this.getContentPane().setBackground(Color.red);

		
		JLabel label = new JLabel(Icones.IMAGEM_DE_FUNDO);
		label.setBounds(0, 0, 400, 300);
		setContentPane(label);
		
		
		
		
		
		
		
		
		
//		
////        // SETA LOOK AND FEEL
//        try {
//            // AQUI VOCÊ SETA O NOME DA CLASSE REFERENTE A CADA TEMA !
//		String tema = "com.jtattoo.plaf.Acryl.AcrylLookAndFeel";
//		
//            // AQUI VC SETA O LOOK AND FEEL
//            UIManager.setLookAndFeel(tema);
//        } catch (InstantiationException | IllegalAccessException  |
//                     UnsupportedLookAndFeelException | ClassNotFoundException e) {
//            System.out.println("Erro LAF : " + e.getMessage());
//        }

		
	}
	
}
