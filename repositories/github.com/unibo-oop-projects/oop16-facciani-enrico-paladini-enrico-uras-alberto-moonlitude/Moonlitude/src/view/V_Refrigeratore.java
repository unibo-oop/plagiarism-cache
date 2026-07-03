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

public class V_Refrigeratore implements V_Properties{
	
	private JPanel main, contenitore, contenitore2;
	private V_Parametri parametri;
	
	private DefaultListModel<String> modello;
	private String[] lista_Congelati;
	private JList<String> visualizza_Lista;
	private JScrollPane scorri_Lista;
	
	protected ViewImpl view;
	
	private int n_Slot;
	private final JButton b_Indietro = new JButton("Indietro");
	private final JButton b_Aumenta = new JButton("Aumenta spazio");
	private final JButton b_Libera = new JButton("Libera spazio");
	private JLabel text_Selezionato, text_Slot;
	
	private GridBagConstraints cnst;
	private GridBagConstraints cnst2;
	
	public V_Refrigeratore(String[] l_Congelati, int i_Slot, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		view = master;
		parametri = new V_Parametri(view);
		
		modello = new DefaultListModel<String>();
		lista_Congelati = l_Congelati;
		AddToModel(modello, lista_Congelati);
		visualizza_Lista = new JList<String>(modello);
		scorri_Lista = new JScrollPane(visualizza_Lista);
		scorri_Lista.setPreferredSize(new Dimension(200, 200));

		contenitore = new JPanel();
		main.setLayout(new BorderLayout());
		contenitore.setLayout(new GridBagLayout());
		contenitore2 = new JPanel();
		contenitore2.setLayout(new GridBagLayout());
		contenitore.setOpaque(false);
		contenitore2.setOpaque(false);
		
		n_Slot = i_Slot;
		text_Slot = new JLabel("Slot rimasti: " + n_Slot);
		text_Selezionato = new JLabel("");
		
		visualizza_Lista.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				Update_View();
			}
			
		});
		
		b_Indietro.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetPrincipale();
			}
			
		});
		
		b_Aumenta.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getLog().setView(view);
				Controller.getLog().getControllerRefrigeratore().aumentaSpazioRefrigeratore();
			}
			
		});
		
		b_Libera.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(!visualizza_Lista.isSelectionEmpty()){
					Controller.getLog().setView(view);
					Controller.getLog().getControllerRefrigeratore().liberaSlotRefrigeratore(visualizza_Lista.getSelectedIndex());
				}
			}
			
		});
		
		cnst = new GridBagConstraints();
		cnst.gridy = 0;
		cnst.gridx = 0;
		cnst.insets = new Insets(50,50,50,50);
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(scorri_Lista, cnst);
		cnst.gridy++;
		contenitore.add(b_Aumenta, cnst);
		cnst.gridy--;
		cnst.gridx++;
		contenitore.add(b_Indietro,cnst);
		cnst.gridx++;
		contenitore.add(text_Slot);
		
		cnst2 = new GridBagConstraints();
		cnst2.gridy = 0;
		cnst2.insets = new Insets(50,50,50,50);
		cnst2.fill = GridBagConstraints.VERTICAL;
		contenitore2.add(text_Selezionato, cnst2);
		cnst2.gridy++;
		contenitore2.add(b_Libera, cnst2);
		cnst2.gridy++;
		
		SetToolTip();
		
		main.add(parametri.Get_View(), BorderLayout.NORTH);
		main.add(contenitore, BorderLayout.CENTER);
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
	
	private void Update_View(){
		if(visualizza_Lista.getSelectedIndex() > -1){
			int quantita = Controller.getLog().getControllerRefrigeratore().conversioneNumeri(visualizza_Lista.getSelectedIndex());
			text_Selezionato.setText("Quantità : " + quantita);
		}
		else
			text_Selezionato.setText(" ");
	}
	
	public void Update_View(String[] l_Congelati, int i_Slot){
		lista_Congelati = l_Congelati;
		modello.removeAllElements();
		AddToModel(modello, lista_Congelati);
		n_Slot = i_Slot;
		text_Slot.setText("Slot rimasti: " + n_Slot);
		text_Selezionato.setText(" ");
		view.schermata.setTitle("Refrigeratore");
	}
	
	private void SetToolTip(){
		b_Indietro.setToolTipText("Torna alla schermata precedente.");
		visualizza_Lista.setToolTipText("Lista dei cibi congelati che possiedi.");
		b_Aumenta.setToolTipText("Aumenta la capacità del refrigeratore utilizzando i mattoni.");
	}
}
