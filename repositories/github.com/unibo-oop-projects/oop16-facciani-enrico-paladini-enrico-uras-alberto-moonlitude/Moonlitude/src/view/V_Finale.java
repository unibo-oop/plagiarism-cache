package view;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class V_Finale {
	
	private JPanel main, contenitore;
	
	public V_Finale(int vittoria, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		main.setLayout(new GridBagLayout());
		if(vittoria <= 0)
			contenitore = V_Background.Create_Sfondo("/perso.png");
		if(vittoria > 0)
			contenitore = V_Background.Create_Sfondo("/vinto.png");
		
		main.add(contenitore);
	}
	
	public JPanel Get_View(){
		return main;
	}
}
