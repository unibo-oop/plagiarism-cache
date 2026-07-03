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

public class V_Giardino implements V_Properties{
	
	private JPanel main, contenitore, contenitore2, contenitore3, contenitore4, contenitore5;
	
	private final JButton b_Add_Slot = new JButton("Aggiungi slot");
	private final JButton b_Indietro = new JButton("Indietro");
	private final JButton b_Ossigena = new JButton("Aspira ossigeno");
	private final JButton b_Add_Pianta = new JButton("Aggiungi pianta");
	private final JButton b_Semina = new JButton("Semina");
	
	private final GridBagConstraints cnst = new GridBagConstraints();
	private final GridBagConstraints cnst2 = new GridBagConstraints();
	private final GridBagConstraints cnst3 = new GridBagConstraints();
	private final GridBagConstraints cnst4 = new GridBagConstraints();
	
	private JLabel text_Contesto = new JLabel("Scegli il seme da piantare:");
	
	protected ViewImpl view;
	
	private DefaultListModel<String> modello, modello2;
	private String[] lista_Slot;
	private JList<String> visualizza_Lista;
	private JScrollPane scorri_Lista;
	private int indice;
	
	private String[] lista_Semi;
	private JList<String> visualizza_Lista_Semi;
	private JScrollPane scorri_Lista_Semi;
	
	private V_Parametri parametri;
	
	private V_Pianta pianta;
	
	public V_Giardino(String[] l_Slot, String[] l_Semi, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		contenitore = new JPanel();
		contenitore.setLayout(new GridBagLayout());
		contenitore2 = new JPanel();
		contenitore2.setLayout(new GridBagLayout());
		contenitore3 = new JPanel();
		contenitore3.setLayout(new GridBagLayout());
		view = master;
		parametri = new V_Parametri(view);
		contenitore.setOpaque(false);
		contenitore2.setOpaque(false);
		contenitore3.setOpaque(false);
		
		lista_Slot = l_Slot;
		lista_Semi = l_Semi;
		modello = new DefaultListModel<String>();
		AddToModel(modello, lista_Slot);
		visualizza_Lista = new JList<String>(modello);
		scorri_Lista = new JScrollPane(visualizza_Lista);
		scorri_Lista.setPreferredSize(new Dimension(200, 200));
		
		visualizza_Lista.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				if(!visualizza_Lista.isSelectionEmpty()){
					indice = visualizza_Lista.getSelectedIndex();
					Controller.getLog().setView(view);
					Controller.getLog().getControllerGiardinoPianta().interfacciaPianta(visualizza_Lista.getSelectedIndex());
					Update_View();
				}
			}
			
		});
		
		b_Indietro.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetPrincipale();
			}
			
		});
		
		b_Add_Pianta.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(contenitore4 != null){
					main.remove(contenitore4);
					modello2.removeAllElements();
					AddToModel(modello2, lista_Semi);
				}
				
				contenitore4 = new JPanel();
				contenitore4.setLayout(new GridBagLayout());
				contenitore4.setOpaque(false);
				contenitore5 = new JPanel();
				contenitore5.setLayout(new BoxLayout(contenitore5, BoxLayout.Y_AXIS));
				contenitore5.setOpaque(false);
				modello2 = new DefaultListModel<String>();
				AddToModel(modello2, lista_Semi);
				visualizza_Lista_Semi = new JList<String>(modello2);
				visualizza_Lista_Semi.setToolTipText("Lista delle piante che puoi piantare.");
				scorri_Lista_Semi = new JScrollPane(visualizza_Lista_Semi);
				scorri_Lista_Semi.setPreferredSize(new Dimension(100, 100));
				contenitore5.add(text_Contesto);
				contenitore5.add(scorri_Lista_Semi);
				cnst4.gridx = 0;
				cnst4.insets = new Insets(25,25,25,25);
				cnst4.fill = GridBagConstraints.VERTICAL;
				contenitore4.add(contenitore5, cnst4);
				cnst4.gridx++;
				contenitore4.add(b_Semina, cnst);
				
				b_Semina.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if(!visualizza_Lista_Semi.isSelectionEmpty()){
							Controller.getLog().setView(view);
							Controller.getLog().getControllerGiardinoPianta().aggiungiPiantaGiardino(visualizza_Lista_Semi.getSelectedValue().toString());
						}
					}
					
				});
				
				main.add(contenitore4, BorderLayout.SOUTH);
				
				main.revalidate();
				main.repaint();
			}
			
		});
		
		b_Ossigena.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getLog().setView(view);
				Controller.getLog().getControllerGiardinoPianta().ristoroGiardino();
			}
			
		});
		
		b_Add_Slot.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getLog().setView(view);
				Controller.getLog().getControllerGiardinoPianta().aggiungiSlotGiardino();
			}
			
		});

		cnst2.gridy = 0;
		cnst2.insets = new Insets(25,25,25,25);
		cnst2.fill = GridBagConstraints.VERTICAL;
		contenitore2.add(b_Add_Slot, cnst2);
		cnst2.gridy++;
		contenitore2.add(b_Add_Pianta, cnst2);
		
		cnst3.gridy = 0;
		cnst3.insets = new Insets(25,25,25,25);
		cnst3.fill = GridBagConstraints.VERTICAL;
		contenitore3.add(b_Ossigena, cnst3);
		cnst3.gridy++;
		contenitore3.add(b_Indietro, cnst3);
		
		cnst.gridx = 0;
		cnst.insets = new Insets(50,50,50,50);
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(scorri_Lista, cnst);
		cnst.gridx++;
		contenitore.add(contenitore2, cnst);
		cnst.gridx++;
		contenitore.add(contenitore3,cnst);
		
		SetToolTip();
		
		main.add(contenitore, BorderLayout.CENTER);
		main.add(parametri.Get_View(), BorderLayout.NORTH);
	}
	
	public int Get_Indice(){
		return indice;
	}
	
	public void SetPianta(String tipo, int i_Annaffia, int i_Piantata, String stato){
		pianta = new V_Pianta(tipo, i_Annaffia, i_Piantata, stato);
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
		main.removeAll();
		main.add(contenitore, BorderLayout.CENTER);
		main.add(parametri.Get_View(), BorderLayout.NORTH);
		main.add(pianta.Get_View(), BorderLayout.EAST);
		view.schermata.setTitle("Giardino");
		main.revalidate();
		main.repaint();
	}
	
	public void Update_View(String[] l_Slot, String[] l_Semi){
		lista_Slot = l_Slot;
		modello.removeAllElements();
		AddToModel(modello, lista_Slot);
		
		lista_Semi = l_Semi;
	}
	
	
	
	public class V_Pianta{
		private JPanel contenitore, contenitore2, contenitore3;
		
		private final JButton b_Prendi = new JButton("Prendi");
		private final JButton b_Annaffia = new JButton("Annaffia");
		private JLabel text_Tipo, text_Piantata, text_ProssimaInnaffiata, text_Stato;
		
		private String s_Tipo, stato_Pianta;
		private int n_Annaffia, n_Piantata;
		
		public V_Pianta(String tipo, int i_Annaffia, int i_Piantata, String stato){
			
			contenitore = new JPanel();
			contenitore2 = new JPanel();
			contenitore3 = new JPanel();
			contenitore.setOpaque(false);
			contenitore2.setOpaque(false);
			contenitore3.setOpaque(false);
			
			contenitore.setLayout(new BoxLayout(contenitore, BoxLayout.Y_AXIS));
			contenitore2.setLayout(new BoxLayout(contenitore2, BoxLayout.X_AXIS));
			contenitore3.setLayout(new BoxLayout(contenitore3, BoxLayout.Y_AXIS));
			
			b_Annaffia.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e) {
					Controller.getLog().setView(view);
					Controller.getLog().getControllerGiardinoPianta().annaffiaPianta(view.Get_Indice_Pianta());
				}
				
			});
			
			b_Prendi.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e) {
					Controller.getLog().setView(view);
					Controller.getLog().getControllerGiardinoPianta().prendiPianta(view.Get_Indice_Pianta());
				}
				
			});
			
			s_Tipo = tipo;
			stato_Pianta = stato;
			n_Annaffia = i_Annaffia;
			n_Piantata = i_Piantata;
			
			text_Tipo = new JLabel("Tipo: " + s_Tipo);
			text_Piantata = new JLabel("Piantata da: " + n_Piantata + " ore.");
			text_ProssimaInnaffiata = new JLabel("Innaffiabile fra " + n_Annaffia + " ore.");
			text_Stato = new JLabel("Stato: " + stato_Pianta);
			
			contenitore3.add(text_Tipo);
			contenitore3.add(text_Piantata);
			contenitore3.add(text_ProssimaInnaffiata);
			contenitore3.add(text_Stato);
			contenitore2.add(b_Prendi);
			contenitore2.add(b_Annaffia);
			
			contenitore.add(contenitore3);
			contenitore.add(contenitore2);
			
			SetToolTip();
			
		}
		
		public JPanel Get_View(){
			return contenitore;
		}
		
		private void SetToolTip(){
			text_Tipo.setToolTipText("Informazioni sulla pianta selezionata.");
			text_Piantata.setToolTipText("Informazioni sulla pianta selezionata.");
			text_ProssimaInnaffiata.setToolTipText("Informazioni sulla pianta selezionata.");
			text_Stato.setToolTipText("Informazioni sulla pianta selezionata.");
			b_Prendi.setToolTipText("Toglie la pianta e ne raccoglie i frutti.");
			b_Annaffia.setToolTipText("Innaffia la pianta.");
		}
		
	}
	
	private void SetToolTip(){
		b_Indietro.setToolTipText("Torna alla schermata precedente.");
		b_Ossigena.setToolTipText("Cambia aria alle piante.");
		visualizza_Lista.setToolTipText("Lista delle piante presenti nel giardino.");
		b_Add_Slot.setToolTipText("Aggiunge spazio per altre piante nel giardino utilizzando i mattoni.");
		b_Add_Pianta.setToolTipText("Aggiungi una pianta in giardino.");
	}
}
