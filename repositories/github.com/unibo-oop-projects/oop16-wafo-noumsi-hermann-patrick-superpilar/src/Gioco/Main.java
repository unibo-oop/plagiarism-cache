package Gioco;

import javax.swing.JFrame;

public class Main {

	public  static Piattaforma scene ;
	public static void main(String[] args) {
		
		//finestra della mia applicazione
		JFrame finestra = new JFrame("super_pilar");
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finestra.setSize(700 ,360);
		finestra.setLocationRelativeTo(null);
		finestra.setResizable(true);
		finestra.setAlwaysOnTop(true);
		
		
		//aggiungiamo la piattaforma
		scene = new Piattaforma();
		finestra.setContentPane(scene);
		finestra.setVisible(true);
				
	}

}
