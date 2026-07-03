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

public class V_Laboratorio implements V_Properties{
	
	private JPanel main, contenitore, contenitore2, contenitore3;
	
	private V_Parametri parametri;
	
	private final JButton b_Indietro = new JButton("Indietro");
	private final JButton b_Ricerca = new JButton("Ricerca");
	private final JButton b_Crafta = new JButton("Crafta");
	
	private DefaultListModel<String> modello, modello2;
	private String[] lista_Ricercabili;
	private JList<String> visualizza_Lista_Ricercabili;
	private JScrollPane scorri_Lista_Ricercabili;
	
	protected ViewImpl view;
	
	private String[] lista_Craftabili;
	private JList<String> visualizza_Lista_Craftabili;
	private JScrollPane scorri_Lista_Craftabili;
	
	private final JLabel text_Contesto = new JLabel("Oggetti ricercabili:");
	private final JLabel text_Contesto2 = new JLabel("Disponibili da Craftare:");
	private JLabel text_Quantita_Craftabili;
	
	public V_Laboratorio(String[] l_Ricercabili, String[] l_Craftabili, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		view = master;
		parametri = new V_Parametri(view);
		
		modello = new DefaultListModel<String>();
		lista_Craftabili = l_Craftabili;
		modello2 = new DefaultListModel<String>();
		lista_Ricercabili = l_Ricercabili;
		
		AddToModel(modello2, lista_Craftabili);
		visualizza_Lista_Craftabili = new JList<String>(modello2);
		scorri_Lista_Craftabili = new JScrollPane(visualizza_Lista_Craftabili);
		
		AddToModel(modello, lista_Ricercabili);
		visualizza_Lista_Ricercabili = new JList<String>(modello);
		scorri_Lista_Ricercabili = new JScrollPane(visualizza_Lista_Ricercabili);
		
		scorri_Lista_Craftabili.setPreferredSize(new Dimension(200, 200));
		scorri_Lista_Ricercabili.setPreferredSize(new Dimension(200, 200));
		
		text_Quantita_Craftabili = new JLabel("");
		
		b_Indietro.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetPrincipale();
			}
			
		});
		
		b_Ricerca.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(!visualizza_Lista_Ricercabili.isSelectionEmpty()){
					Controller.getLog().setView(view);
					Controller.getLog().getControllerLaboratorio().ricercaLaboratorio(visualizza_Lista_Ricercabili.getSelectedIndex());
				}
			}
			
		});
		
		b_Crafta.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(!visualizza_Lista_Craftabili.isSelectionEmpty()){
					Controller.getLog().setView(view);
					Controller.getLog().getControllerLaboratorio().craftaLaboratorio(visualizza_Lista_Craftabili.getSelectedIndex());
				}
			}
			
		});
		
		contenitore = new JPanel();
		contenitore.setOpaque(false);
		contenitore2 = new JPanel();
		contenitore2.setOpaque(false);
		contenitore3 = new JPanel();
		contenitore3.setOpaque(false);
		main.setLayout(new BorderLayout());
		contenitore.setLayout(new GridBagLayout());
		contenitore2.setLayout(new BoxLayout(contenitore2, BoxLayout.Y_AXIS));
		contenitore3.setLayout(new BoxLayout(contenitore3, BoxLayout.Y_AXIS));
		
		visualizza_Lista_Craftabili.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				Update_View_Craftabili();
			}
			
		});
		
		contenitore2.add(text_Contesto);
		contenitore2.add(scorri_Lista_Ricercabili);
		contenitore3.add(text_Contesto2);
		contenitore3.add(scorri_Lista_Craftabili);
		
		SetToolTip();
		
		final GridBagConstraints cnst = new GridBagConstraints();
		cnst.gridy = 0;
		cnst.gridx = 0;
		cnst.insets = new Insets(10,10,10,10);
		cnst.weighty = 0;
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(contenitore3, cnst);
		cnst.gridx++;
		contenitore.add(b_Crafta, cnst);
		cnst.gridx++;
		contenitore.add(text_Quantita_Craftabili, cnst);
		cnst.gridy++;
		cnst.gridx-=2;
		contenitore.add(contenitore2, cnst);
		cnst.gridx++;
		contenitore.add(b_Ricerca, cnst);
		cnst.gridy++;
		cnst.gridx--;
		contenitore.add(b_Indietro, cnst);
		
		main.add(parametri.Get_View(), BorderLayout.NORTH);
		main.add(contenitore, BorderLayout.CENTER);
	}
	
	public JPanel Get_View(){
		return main;
	}
	
	private void AddToModel(DefaultListModel<String> model, String[] aggiunte){
		for(int i = 0 ; i < aggiunte.length ; i++){
			model.addElement(aggiunte[i]);
		}
	}
	
	public void Update_Parameters(int pFame, int pSete, int pOssigeno, int pGiorno, String sOra,String sMeteo) {
		parametri.Update_Parameters(pFame, pSete, pOssigeno, pGiorno, sOra, sMeteo);
	}
	
	public void Update_View(String[] l_Ricercabili, String[] l_Craftabili){
		lista_Ricercabili = l_Ricercabili;
		modello.removeAllElements();
		AddToModel(modello, lista_Ricercabili);
		lista_Craftabili = l_Craftabili;
		modello2.removeAllElements();
		AddToModel(modello2, lista_Craftabili);
		view.schermata.setTitle("Laboratorio");
	}
	
	public void Update_View_Craftabili(){
		if(visualizza_Lista_Craftabili.getSelectedIndex() > -1)
			text_Quantita_Craftabili.setText("" + Controller.getLog().getControllerLaboratorio().getOggettiRichiesti(visualizza_Lista_Craftabili.getSelectedIndex()));
		else
			text_Quantita_Craftabili.setText(" ");
	}
	
	private void SetToolTip(){
		b_Indietro.setToolTipText("Torna alla schermata precedente.");
		visualizza_Lista_Ricercabili.setToolTipText("Lista degli oggetti o materiali ricercabili.");
		visualizza_Lista_Craftabili.setToolTipText("Lista degli oggetti o materiali craftabili.");
		b_Crafta.setToolTipText("Crafta l'oggetto o il materiale selezionato.");
		b_Ricerca.setToolTipText("Ricerca l'oggetto o il materiale selezionato.");
	}
}
