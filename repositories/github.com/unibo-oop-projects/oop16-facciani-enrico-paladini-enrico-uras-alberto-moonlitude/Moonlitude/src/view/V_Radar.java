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

import controller.Controller;

public class V_Radar implements V_Properties{
	
	private JPanel main, contenitore, contenitore2;

	private final JButton b_Indietro = new JButton("Indietro");
	private final JButton b_Lancia= new JButton("Lancia Drone");
	
	private DefaultListModel<String> modello;
	private String[] lista_Luoghi;
	private JList<String> visualizza_Lista;
	private JScrollPane scorri_Lista;
	
	protected ViewImpl view;
	
	private final JLabel text_Contesto = new JLabel("Luoghi scoperti");
	
	private final GridBagConstraints cnst = new GridBagConstraints();
	
	private V_Parametri parametri;
	
	public V_Radar(String[] l_luoghi, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		view = master;
		parametri = new V_Parametri(view);
		
		lista_Luoghi = l_luoghi;
		modello = new DefaultListModel<String>();
		AddToModel(modello, lista_Luoghi);
		visualizza_Lista = new JList<String>(modello);
		scorri_Lista = new JScrollPane(visualizza_Lista);
		scorri_Lista.setPreferredSize(new Dimension(200, 200));
		
		b_Indietro.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetPrincipale();
			}
			
		});
		
		b_Lancia.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getLog().setView(view);
				Controller.getLog().getControllerRadar().lanciaDroneRadar();
			}
			
		});
		
		main.setLayout(new BorderLayout());
		contenitore = new JPanel();
		contenitore.setLayout(new GridBagLayout());
		contenitore2 = new JPanel();
		contenitore2.setLayout(new GridBagLayout());
		contenitore.setOpaque(false);
		contenitore2.setOpaque(false);
		
		cnst.gridy = 0;
		cnst.gridx = 0;
		cnst.insets = new Insets(50,50,50,50);
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(text_Contesto, cnst);
		cnst.gridy++;
		contenitore.add(scorri_Lista, cnst);
		cnst.gridy--;
		cnst.gridx++;
		contenitore.add(b_Lancia, cnst);
		cnst.gridy++;
		contenitore.add(b_Indietro, cnst);
		
		SetToolTip();
		
		main.add(parametri.Get_View(), BorderLayout.NORTH);
		main.add(contenitore, BorderLayout.CENTER);
		
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
	
	public void Update_View(String[] l_luoghi){
		lista_Luoghi = l_luoghi;
		modello.removeAllElements();
		AddToModel(modello, lista_Luoghi);
		view.schermata.setTitle("Radar");
	}
	
	private void SetToolTip(){
		b_Indietro.setToolTipText("Torna alla schermata precedente.");
		visualizza_Lista.setToolTipText("Lista dei luoghi scoperti.");
		b_Lancia.setToolTipText("Lancia un drone alla ricerca di nuovi luoghi da poter esplorare.");
	}
}
