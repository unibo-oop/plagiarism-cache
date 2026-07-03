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

import controller.Controller;

public class V_Equipaggiamento implements V_Properties{
	
	private JPanel main, contenitore, contenitore2, contenitore3;
	
	private DefaultListModel<String> modello;
	private String[] lista_Equipaggiamento;
	private JList<String> visualizza_Lista;
	private JScrollPane scorri_Lista;
	private String equipaggiato, tuta, lista_Bonus;
	
	private final GridBagConstraints cnst = new GridBagConstraints();
	
	private ViewImpl view;
	
	private final JLabel text_Contesto = new JLabel("Bonus:");
	private JLabel text_Bonus, text_Equipaggiato, text_Tuta;
	
	private V_Parametri parametri;
	
	private final JButton b_Seleziona = new JButton("Seleziona");
	private final JButton b_Indietro = new JButton("Indietro");
	
	public V_Equipaggiamento(String[] l_Equipaggiamento, String s_Tuta, String l_Bonus, String s_Equipaggiato, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		view = master;
		contenitore = new JPanel();
		contenitore2 = new JPanel();
		contenitore3 = new JPanel();
		contenitore.setLayout(new GridBagLayout());
		contenitore2.setLayout(new BoxLayout(contenitore2, BoxLayout.Y_AXIS));
		contenitore3.setLayout(new BoxLayout(contenitore3, BoxLayout.Y_AXIS));
		parametri = new V_Parametri(view);
		equipaggiato = s_Equipaggiato;
		tuta = s_Tuta;
		contenitore.setOpaque(false);
		contenitore2.setOpaque(false);
		contenitore3.setOpaque(false);
		
		
		modello = new DefaultListModel<String>();
		lista_Bonus = l_Bonus;
		lista_Equipaggiamento = l_Equipaggiamento;
		AddToModel(modello, lista_Equipaggiamento);
		visualizza_Lista = new JList<String>(modello);
		scorri_Lista = new JScrollPane(visualizza_Lista);
		scorri_Lista.setPreferredSize(new Dimension(200, 200));
		
		text_Bonus = new JLabel(lista_Bonus);
		text_Equipaggiato = new JLabel("Oggetto equipaggato: " + equipaggiato);
		text_Tuta = new JLabel("Tuta equipaggiata: " + tuta);
		
		SetToolTip();
		
		b_Indietro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				view.SetPrincipale();
			}
			
		});
		
		b_Seleziona.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!visualizza_Lista.isSelectionEmpty()){
					Controller.getLog().setView(view);
					Controller.getLog().getControllerAstronauta().selezionaEquipaggiamento(visualizza_Lista.getSelectedIndex());
				}
			}
			
		});
		
		contenitore2.add(text_Tuta);
		contenitore2.add(text_Equipaggiato);		
		contenitore2.add(text_Contesto);
		contenitore2.add(text_Bonus);
		contenitore3.add(b_Seleziona);
		contenitore3.add(b_Indietro);
		
		cnst.gridx = 0;
		cnst.gridy = 0;
		cnst.insets = new Insets(50,50,50,50);
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(scorri_Lista, cnst);
		cnst.gridx++;
		contenitore.add(contenitore2, cnst);
		cnst.gridx++;
		contenitore.add(contenitore3, cnst);
		
		main.add(contenitore, BorderLayout.CENTER);
		main.add(parametri.Get_View(), BorderLayout.NORTH);
		
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
	
	public void Update_View(String[] l_Equipaggiamento, String s_Tuta, String l_Bonus, String s_Equipaggiato){
		lista_Equipaggiamento = l_Equipaggiamento;
		modello.removeAllElements();
		AddToModel(modello, lista_Equipaggiamento);
		equipaggiato = s_Equipaggiato;
		lista_Bonus = l_Bonus;
		tuta = s_Tuta;
		text_Tuta.setText("Tuta equipaggiata: " + tuta);
		text_Equipaggiato.setText("Oggetto equipaggato: " + equipaggiato);
		text_Bonus.setText(lista_Bonus);
		view.schermata.setTitle("Equipaggiamento");
	}
	
	private void SetToolTip(){
		b_Indietro.setToolTipText("Torna alla schermata precedente.");
		visualizza_Lista.setToolTipText("Lista degli equipaggiamenti.");
		b_Seleziona.setToolTipText("Equipaggia l'oggetto selezionato.");
		text_Contesto.setToolTipText("Lista dei bonus garantiti dall'oggetto selezionato.");
	}
	
}
