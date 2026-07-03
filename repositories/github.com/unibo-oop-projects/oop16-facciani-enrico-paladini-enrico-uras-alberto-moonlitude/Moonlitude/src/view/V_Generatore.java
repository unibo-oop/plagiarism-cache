package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class V_Generatore implements V_Properties{

	private JPanel main, contenitore, contenitore2;
	
	private JLabel text_Carica;
	private JLabel text_Luminosita;
	
	String contatore_Luminosita;
	private int contatore_Carica;
	final JButton b_Indietro = new JButton("Indietro");
	final JButton b_Ricarica = new JButton("Ricarica");
	final JButton b_Plus = new JButton("+");
	final JButton b_Less = new JButton("-");
	
	protected ViewImpl view;
	
	private V_Parametri parametri;
	
	public V_Generatore(int i_Carica, String s_Luminosita, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		
		view = master;
		
		main.setLayout(new BorderLayout());
		contenitore = new JPanel();
		contenitore.setLayout(new GridBagLayout());
		contenitore2 = new JPanel();
		contenitore2.setLayout(new BoxLayout(contenitore2, BoxLayout.X_AXIS));
		parametri = new V_Parametri(view);
		contenitore.setOpaque(false);
		contenitore2.setOpaque(false);
		
		b_Indietro.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetPrincipale();
			}
			
		});
		
		b_Ricarica.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getLog().setView(view);
				Controller.getLog().getControllerGeneratore().ricaricaGeneratore();
			}
			
		});
		
		b_Less.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getLog().setView(view);
				Controller.getLog().getControllerGeneratore().modificaLuminosita(-1);
			}
			
		});
		
		b_Plus.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getLog().setView(view);
				Controller.getLog().getControllerGeneratore().modificaLuminosita(+1);
			}
			
		});
		
		contenitore2.add(b_Less);
		contenitore2.add(b_Plus);
		
		contatore_Luminosita = s_Luminosita;
		contatore_Carica = i_Carica;
		text_Luminosita = new JLabel("Luminosita: " + contatore_Luminosita);
		text_Carica = new JLabel("Carica: " + contatore_Carica);
		
		final GridBagConstraints cnst = new GridBagConstraints();
		cnst.gridy = 0;
		cnst.gridx = 0;
		cnst.insets = new Insets(50,50,50,50);
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(text_Carica, cnst);
		cnst.gridy++;
		contenitore.add(text_Luminosita, cnst);
		cnst.gridx++;
		contenitore.add(contenitore2, cnst);
		cnst.gridy++;
		cnst.gridx--;
		contenitore.add(b_Ricarica, cnst);
		cnst.gridx++;
		contenitore.add(b_Indietro, cnst);
		
		SetToolTip();
		
		main.add(parametri.Get_View(), BorderLayout.NORTH);
		main.add(contenitore, BorderLayout.CENTER);
	}
	
	public JPanel Get_View(){
		return main;
	}
	
	public void Update_Parameters(int pFame, int pSete, int pOssigeno, int pGiorno, String sOra,String sMeteo) {
		parametri.Update_Parameters(pFame, pSete, pOssigeno, pGiorno, sOra, sMeteo);
	}
	
	public void Update_View(int carica, String s_Luminosita){
		contatore_Carica = carica;
		text_Carica.setText("Carica: " + contatore_Carica);
		contatore_Luminosita = s_Luminosita;
		text_Luminosita.setText("Luminosita: " + contatore_Luminosita);
		view.schermata.setTitle("Generatore");
	}
	
	private void SetToolTip(){
		b_Indietro.setToolTipText("Torna alla schermata precedente.");
		text_Luminosita.setToolTipText("Luminosità impostata per la crescita delle piante.");
		text_Carica.setToolTipText("Energia utilizzabile.");
		b_Plus.setToolTipText("Aumenta la luminosità.");
		b_Less.setToolTipText("Diminuisci la luminosità.");
		b_Ricarica.setToolTipText("Aumenta l'energia disponibile utilizzando le batterie.");
	}
}
