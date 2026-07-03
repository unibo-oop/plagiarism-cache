package hud;

import java.awt.Color;

import players.Player;
import players.PlayerImpl;

public class SetUpHud {
	public static HUD interfaccia= new HUD();
	
	
	public static void updateHUD(final Player getActualPlayer){ 
	
		//meteodo che aggiorna il nome giocatore e colore
		
	interfaccia.label_2.setText(getActualPlayer.getNome());
	
	if (getActualPlayer.getColore()==1) {
		
		interfaccia.label_3.setBackground(Color.YELLOW);
		
		} else if (getActualPlayer.getColore()==2) {
			
			interfaccia.label_3.setBackground(Color.RED);
			
		} else if (getActualPlayer.getColore()==3) {
			
			interfaccia.label_3.setBackground(Color.GREEN);
			
		} else if (getActualPlayer.getColore()==4) {
			
			interfaccia.label_3.setBackground(Color.CYAN);
			
		} else if (getActualPlayer.getColore()==5) {
			
			interfaccia.label_3.setBackground(Color.PINK);
			
		} else if (getActualPlayer.getColore()==6) {
			
			interfaccia.label_3.setBackground(Color.GRAY);
		} else {
			
			interfaccia.label_3.setBackground(Color.WHITE);
		}
	
	interfaccia.validate();
	}
	
	public static void updateDice (int [] NumsAtk, int [] NumsDef) {
		
		//metodo che aggiorna i dadi
		
		interfaccia.label_5.setText(Integer.toString(NumsAtk[2]));
		
		interfaccia.label_6.setText(Integer.toString(NumsAtk[1]));
		
		interfaccia.label_7.setText(Integer.toString(NumsAtk[0]));
		
		interfaccia.label_9.setText(Integer.toString(NumsDef[2]));
		
		interfaccia.label_10.setText(Integer.toString(NumsDef[1]));
		
		interfaccia.label_11.setText(Integer.toString(NumsDef[0]));
		
		interfaccia.validate();
			

}
	
	public HUD getHUD() {
		return interfaccia;
	}

}
