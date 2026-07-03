package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;

public class V_Menu implements V_Properties{
	
	private JPanel main, contenitore;
	
	private final JButton b_Nuovo = new JButton("Nuovo");
	private final JButton b_Carica = new JButton("Carica");
	
	ViewImpl view;
	
	public V_Menu(ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		main.setLayout(new BorderLayout());
		view = master;
		contenitore = new JPanel();
		contenitore.setLayout(new GridBagLayout());
		contenitore.setOpaque(false);
		contenitore.add(b_Nuovo);
		contenitore.add(b_Carica);
		
		b_Nuovo.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getLog().setView(view);
				Controller.getLog().setGioco();
				Controller.getLog().getControllerUtility().startViewBaseSpaziale();
			}
			
		});
		
		b_Carica.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getLog().setView(view);
				try {
					Controller.getLog().getControllerUtility().caricaGioco();
				} catch (FileNotFoundException e1) {
					view.Show_Message("Non esiste nessun gioco salvato.");
				} catch (ClassNotFoundException e1) {
					view.Show_Message("Non esiste nessun gioco salvato.");
				} catch (IOException e1) {
					view.Show_Message("Non esiste nessun gioco salvato.");
				}
			}
			
		});
		
		SetToolTip();
		
		main.add(contenitore,BorderLayout.CENTER);
	}
	
	public JPanel Get_View(){
		return main;
	}
	
	public void Update_Parameters(int pFame, int pSete, int pOssigeno, int pGiorno, String sOra,String sMeteo) {
		// TODO Auto-generated method stub
		
	}
	
	private void SetToolTip(){
		b_Nuovo.setToolTipText("Inizia una nuova partita!");
		b_Carica.setToolTipText("Carica un salvataggio.");
	}

}
