package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;

public class V_Zaino implements V_Properties{
	
	private JPanel main, contenitore;
	private V_Parametri parametri;
	
	private String[] lista_Oggetti;
	private JList<String> visualizza_Lista;
	private JScrollPane scorri_Lista;
	
	protected ViewImpl view;
	
	private final JButton b_Indietro = new JButton("Indietro");
	private final JButton b_Sposta = new JButton("Sposta");
	
	private final GridBagConstraints cnst = new GridBagConstraints();
	
	private JLabel text_Quantita;
	private DefaultListModel<String> modello;
	
	public V_Zaino(String[] l_Oggetti, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		view = master;
		parametri = new V_Parametri(view);
		
		modello = new DefaultListModel<String>();
		lista_Oggetti = l_Oggetti;
		AddToModel(modello, lista_Oggetti);
		visualizza_Lista = new JList<String>(modello);
		scorri_Lista = new JScrollPane(visualizza_Lista);
		scorri_Lista.setPreferredSize(new Dimension(200, 200));
		
		text_Quantita = new JLabel("");
		
		b_Indietro.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetPrincipale();
			}
			
		});
		
		visualizza_Lista.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				Update_View();
			}
			
		});
		
		b_Sposta.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(!visualizza_Lista.isSelectionEmpty()){
					Controller.getLog().setView(view);
					Controller.getLog().getControllerZaino().spostaOggettoZaino(visualizza_Lista.getSelectedValue().toString());
				}
			}
			
		});
		
		contenitore = new JPanel();
		main.setLayout(new BorderLayout());
		contenitore.setLayout(new GridBagLayout());
		contenitore.setOpaque(false);
		
		SetToolTip();
		
		cnst.gridy = 0;
		cnst.gridx = 0;
		cnst.insets = new Insets(50,50,50,50);
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(scorri_Lista, cnst);
		cnst.gridx++;
		contenitore.add(text_Quantita, cnst);
		cnst.gridy++;
		cnst.gridx--;
		contenitore.add(b_Indietro, cnst);
		cnst.gridx++;
		contenitore.add(b_Sposta, cnst);
		
		main.add(contenitore, BorderLayout.CENTER);
		main.add(parametri.Get_View(), BorderLayout.NORTH);
	}
	
	public JPanel Get_View(){
		return main;
	}
	
	private void Update_View(){
		if(visualizza_Lista.getSelectedIndex() >= 0){
			int quantita = Controller.getLog().getControllerZaino().conversioneNumero(visualizza_Lista.getSelectedIndex());
			text_Quantita.setText("Quantità: " + quantita);
		}
		else
			text_Quantita.setText(" ");
	}
	
	public void Update_Parameters(int pFame, int pSete, int pOssigeno, int pGiorno, String sOra,String sMeteo) {
		parametri.Update_Parameters(pFame, pSete, pOssigeno, pGiorno, sOra, sMeteo);
	}
	
	public void Update_View(String[] l_Oggetti){
		modello.removeAllElements();
		lista_Oggetti = l_Oggetti;
		AddToModel(modello, lista_Oggetti);
		view.schermata.setTitle("Zaino");
	}
	
	private void AddToModel(DefaultListModel<String> model, String[] aggiunte){
		for(int i = 0 ; i < aggiunte.length ; i++){
			model.addElement(aggiunte[i]);
		}
	}
	
	private void SetToolTip(){
		b_Indietro.setToolTipText("Torna alla schermata precedente.");
		b_Sposta.setToolTipText("Sposta l'oggetto selezionato nel magazzino.");
		visualizza_Lista.setToolTipText("Lista degli oggetti nel tuo zaino.");
	}
}
