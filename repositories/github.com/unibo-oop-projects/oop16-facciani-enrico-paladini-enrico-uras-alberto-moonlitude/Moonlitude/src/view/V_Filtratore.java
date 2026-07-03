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

public class V_Filtratore implements V_Properties {

	private JPanel main, contenitore, contenitore2, contenitore3, contenitore4;
	
	private final JButton b_Add_Slot = new JButton("Aggiungi slot");
	private final JButton b_Indietro = new JButton("Indietro");
	private final JButton b_Bevi = new JButton("Bevi");
	private final JButton b_Raccogli = new JButton("Raccogli");
	private final JButton b_Plus = new JButton("+");
	private final JButton b_Less = new JButton("-");
	
	private final GridBagConstraints cnst = new GridBagConstraints();
	private final GridBagConstraints cnst2 = new GridBagConstraints();
	private final GridBagConstraints cnst3 = new GridBagConstraints();
	
	private int s_AcquaTotale, s_Quantita;
	private JLabel text_Acqua, text_Totale, text_Bere;
	
	protected ViewImpl view;
	
	private DefaultListModel<String> modello;
	private String[] lista_Slot;
	private JList<String> visualizza_Lista;
	private JScrollPane scorri_Lista;
	
	private V_Parametri parametri;
	
	
	public V_Filtratore(String[] l_Slot, int acqua_Totale, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		view = master;
		contenitore = new JPanel();
		contenitore.setLayout(new GridBagLayout());
		contenitore2 = new JPanel();
		contenitore2.setLayout(new GridBagLayout());
		contenitore3 = new JPanel();
		contenitore3.setLayout(new GridBagLayout());
		contenitore4 = new JPanel();
		contenitore4.setLayout(new BoxLayout(contenitore4, BoxLayout.X_AXIS));
		parametri = new V_Parametri(view);
		contenitore.setOpaque(false);
		contenitore2.setOpaque(false);
		contenitore3.setOpaque(false);
		contenitore4.setOpaque(false);
		
		lista_Slot = l_Slot;
		modello = new DefaultListModel<String>();
		lista_Slot = l_Slot;
		AddToModel(modello, lista_Slot);
		visualizza_Lista = new JList<String>(modello);
		scorri_Lista = new JScrollPane(visualizza_Lista);
		scorri_Lista.setPreferredSize(new Dimension(200, 200));
		
		s_AcquaTotale = acqua_Totale;
		s_Quantita = 10;
		
		text_Acqua = new JLabel("  ");
		text_Totale = new JLabel("Acqua totale raccolta: " + s_AcquaTotale + " litri.");
		text_Bere = new JLabel("Bevi: " + s_Quantita);
		b_Indietro.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetPrincipale();
			}
			
		});
		
		visualizza_Lista.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				if(!visualizza_Lista.isSelectionEmpty()){
					Update_View();
				}
			}
			
		});
		
		b_Add_Slot.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getLog().setView(view);
				Controller.getLog().getControllerFiltratore().aggiungiContenitore();
			}
			
		});
		
		
		b_Bevi.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
					Controller.getLog().setView(view);
					Controller.getLog().getControllerFiltratore().beviAcquaFiltratore(s_Quantita);
			}
			
		});
		
		b_Raccogli.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(!visualizza_Lista.isSelectionEmpty()){
					Controller.getLog().setView(view);
					Controller.getLog().getControllerFiltratore().spostaAcquaContenitore(visualizza_Lista.getSelectedIndex());
				}
			}
			
		});
		
		b_Less.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Update_View(-10);
			}
			
		});
		
		b_Plus.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Update_View(+10);
			}
			
		});
		
		s_Quantita = 10;
		text_Bere = new JLabel(" " + s_Quantita + " ");
		
		
		contenitore4.add(b_Less);
		contenitore4.add(text_Bere);
		contenitore4.add(b_Plus);
		contenitore4.add(b_Bevi);
		
		cnst3.gridy = 0;
		cnst3.insets = new Insets(25,25,25,25);
		cnst3.fill = GridBagConstraints.VERTICAL;
		contenitore3.add(contenitore4, cnst3);
		cnst3.gridy++;
		contenitore3.add(b_Indietro, cnst3);
		cnst3.gridy++;
		contenitore3.add(b_Raccogli, cnst3);

		cnst2.gridy = 0;
		cnst2.insets = new Insets(25,25,25,25);
		cnst2.fill = GridBagConstraints.VERTICAL;
		contenitore2.add(text_Acqua, cnst2);
		cnst2.gridy++;
		contenitore2.add(text_Totale, cnst2);
		cnst2.gridy++;
		contenitore2.add(b_Add_Slot,cnst2);
		
		cnst.gridx = 0;
		cnst.insets = new Insets(50,50,50,50);
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(scorri_Lista, cnst);
		cnst.gridx++;
		contenitore.add(contenitore2, cnst);
		cnst.gridx++;
		contenitore.add(contenitore3, cnst);
		
		SetToolTip();
		
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
	
	private void Update_View(){
		if(visualizza_Lista.getSelectedIndex() > -1){
			int quantita = Controller.getLog().getControllerFiltratore().conversioneNumeri(visualizza_Lista.getSelectedIndex());
			text_Acqua.setText("Acqua collezionata dal contenitore: " + quantita + " ");
		}
		else
			text_Acqua.setText(" ");
	}
	
	private void Update_View(int somma){
		s_Quantita += somma;
		if(s_Quantita < 10)
			s_Quantita = 10;
		text_Bere.setText(" " + s_Quantita + " ");
	}
	
	public void Update_View(String[] l_Slot, int acqua_Totale){
		s_AcquaTotale = acqua_Totale;
		lista_Slot = l_Slot;
		modello.removeAllElements();
		AddToModel(modello, lista_Slot);
		text_Totale.setText("Acqua totale raccolta: " + s_AcquaTotale + " litri.");
		view.schermata.setTitle("Filtratore");
	}
	
	private void SetToolTip(){
		b_Indietro.setToolTipText("Torna alla schermata precedente.");
		visualizza_Lista.setToolTipText("Lista dei contenitori per la raccolta d'acqua.");
		text_Bere.setToolTipText("Seleziona la quantità di acqua da bere.");
		b_Plus.setToolTipText("Seleziona la quantità di acqua da bere.");
		b_Less.setToolTipText("Seleziona la quantità di acqua da bere.");
		b_Bevi.setToolTipText("Bevi la quantità di acqua selezionata.");
		b_Add_Slot.setToolTipText("Aggiungi un contenitore per raccogliere acqua.");
	}
}
