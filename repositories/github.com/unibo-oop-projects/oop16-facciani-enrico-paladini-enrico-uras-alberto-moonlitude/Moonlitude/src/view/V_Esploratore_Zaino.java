package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;

public class V_Esploratore_Zaino implements V_Properties{
	
	private JPanel main, contenitore, contenitore2, contenitore3, contenitore4;
	
	private DefaultListModel<String> modello, modello2;
	private String[] lista_Zaino;
	private JList<String> visualizza_Lista_Zaino;
	private JScrollPane scorri_Lista_Zaino;
	
	private String[] lista_Trovati;
	private JList<String> visualizza_Lista_Trovati;
	private JScrollPane scorri_Lista_Trovati;
	
	private final JLabel text_Contesto = new JLabel("Oggetti nel tuo zaino:");
	private final JLabel text_Contesto2 = new JLabel("Oggetti trovati:");
	private JLabel text_Trovati, text_Zaino;
	
	protected ViewImpl view;
	
	private final JButton b_Avanti = new JButton("Avanti");
	private final JButton b_Aggiungi	= new JButton("Aggiungi");
	private final JButton b_Rimuovi	= new JButton("Rimuovi");
	
	private final GridBagConstraints cnst = new GridBagConstraints();
	
	private V_Parametri parametri;
	
	public V_Esploratore_Zaino(String[] l_Zaino, String[] l_Trovati, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		view = master;
		parametri = new V_Parametri(view);
		contenitore = new JPanel();
		contenitore.setLayout(new GridBagLayout());
		contenitore2 =  new JPanel();
		contenitore2.setLayout(new BoxLayout(contenitore2, BoxLayout.X_AXIS));
		contenitore3 =  new JPanel();
		contenitore3.setLayout(new BoxLayout(contenitore3, BoxLayout.Y_AXIS));
		contenitore4 =  new JPanel();
		contenitore4.setLayout(new BoxLayout(contenitore4, BoxLayout.Y_AXIS));
		contenitore.setOpaque(false);
		contenitore2.setOpaque(false);
		contenitore3.setOpaque(false);
		contenitore4.setOpaque(false);
		
		text_Zaino = new JLabel(" ");
		text_Trovati = new JLabel(" ");
		
		modello = new DefaultListModel<String>();
		lista_Zaino = l_Zaino;
		AddToModel(modello, lista_Zaino);
		visualizza_Lista_Zaino = new JList<String>(modello);
		scorri_Lista_Zaino = new JScrollPane(visualizza_Lista_Zaino);
		scorri_Lista_Zaino.setPreferredSize(new Dimension(100, 100));
		
		modello2 = new DefaultListModel<String>();
		lista_Trovati = l_Trovati;
		AddToModel(modello2, lista_Trovati);
		visualizza_Lista_Trovati = new JList<String>(modello2);
		scorri_Lista_Trovati = new JScrollPane(visualizza_Lista_Trovati);
		scorri_Lista_Trovati.setPreferredSize(new Dimension(200, 200));
		
		b_Aggiungi.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(!visualizza_Lista_Trovati.isSelectionEmpty()){
					Controller.getLog().setView(view);
					Controller.getLog().getControllerEsplorazione().bottoneAggiungi(visualizza_Lista_Trovati.getSelectedValue().toString());
				}
			}
			
		});
		
		b_Rimuovi.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(!visualizza_Lista_Zaino.isSelectionEmpty()){
					Controller.getLog().setView(view);
					Controller.getLog().getControllerEsplorazione().bottoneRimuovi(visualizza_Lista_Zaino.getSelectedValue().toString());
				}
			}
			
		});
		
		b_Avanti.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetPrincipale();
			}
			
		});
		
		visualizza_Lista_Zaino.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				if(!visualizza_Lista_Zaino.isSelectionEmpty()){
					Update_View(10);
				}
			}
			
		});
		
		visualizza_Lista_Trovati.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				if(!visualizza_Lista_Trovati.isSelectionEmpty()){
					Update_View(-10);
				}
			}
			
		});
		
		contenitore2.add(b_Avanti);
		contenitore3.add(text_Contesto);
		contenitore3.add(scorri_Lista_Zaino);
		contenitore4.add(text_Contesto2);
		contenitore4.add(scorri_Lista_Trovati);
		
		cnst.gridx = 0;
		cnst.insets = new Insets(50,50,50,50);
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(text_Zaino, cnst);
		cnst.gridx++;
		contenitore.add(contenitore3, cnst);
		cnst.gridx++;
		contenitore.add(b_Rimuovi);
		cnst.gridx++;
		contenitore.add(b_Aggiungi);
		cnst.gridx++;
		contenitore.add(contenitore4, cnst);
		cnst.gridx++;
		contenitore.add(text_Trovati, cnst);
		
		SetToolTip();
		
		main.add(contenitore,BorderLayout.CENTER);
		main.add(parametri.Get_View(), BorderLayout.NORTH);
		main.add(contenitore2, BorderLayout.EAST);
	}
	
	public JPanel Get_View() {
		return main;
	}
	
	public void Update_Parameters(int pFame, int pSete, int pOssigeno, int pGiorno, String sOra,String sMeteo) {
		parametri.Update_Parameters(pFame, pSete, pOssigeno, pGiorno, sOra, sMeteo);
	}
	
	private void AddToModel(DefaultListModel<String> model, String[] aggiunte){
		for(int i = 0 ; i < aggiunte.length ; i++){
			model.addElement(aggiunte[i]);
		}
	}

	public void Update_View(String[] l_Zaino, String[] l_Trovati){
		lista_Zaino = l_Zaino;
		modello.removeAllElements();
		AddToModel(modello, lista_Zaino);
		
		lista_Trovati = l_Trovati;
		modello2.removeAllElements();
		AddToModel(modello2, lista_Trovati);
		view.schermata.setTitle("In viaggio");
	}
	
	public void Update_View(int scelta){
		int quantita = -1;
		if(scelta > 0){
			if(visualizza_Lista_Zaino.getSelectedIndex() > -1){
				quantita = Controller.getLog().getControllerEsplorazione().getQuantitaOggettiZaino(visualizza_Lista_Zaino.getSelectedIndex());
				text_Zaino.setText("Quantità: " + quantita);
			}
			else
				text_Zaino.setText(" ");
		}
		else{
			if(visualizza_Lista_Trovati.getSelectedIndex() > -1){
				quantita =  Controller.getLog().getControllerEsplorazione().getQuantitaOggettiOttenuti(visualizza_Lista_Trovati.getSelectedIndex());
				text_Trovati.setText("Quantità: " + quantita);
			}
			else
				text_Trovati.setText(" ");
		}
	}
	
	private void SetToolTip(){
		visualizza_Lista_Trovati.setToolTipText("Oggetti che hai trovato in esplorazione.");
		visualizza_Lista_Zaino.setToolTipText("Oggetti presenti nel tuo zaino.");
		b_Aggiungi.setToolTipText("Aggiungi un oggetto selezionato al tuo zaino.");
		b_Rimuovi.setToolTipText("Rimuovi un oggetto selezionato dal tuo zaino.");
		b_Avanti.setToolTipText("Torna alla base.");
		
	}
}
