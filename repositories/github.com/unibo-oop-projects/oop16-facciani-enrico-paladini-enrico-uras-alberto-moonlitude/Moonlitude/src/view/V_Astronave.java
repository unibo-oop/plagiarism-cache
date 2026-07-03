package view;

import java.awt.BorderLayout;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;

public class V_Astronave implements V_Properties{
	
	private JPanel main, contenitore, contenitore1, contenitore2;
	private  String[] lista_Pezzi;
	
	private JLabel text_Scudi, text_CelleEnergia, text_Quantita;
	
	private JList<String> visualizza_Lista;
	private DefaultListModel<String> modello;
	
	private final JButton b_Indietro = new JButton("Indietro");
	private final JButton b_Ricarica = new JButton("Ricarica");
	private final JButton b_Sostituisci = new JButton("Sostituisci");
	
	private ViewImpl view;
	
	private final GridBagConstraints cnst = new GridBagConstraints();
	private final GridBagConstraints cnst2 = new GridBagConstraints();
	
	private int scudi, celleEnergia;
	
	private V_Parametri parametri;
	
	public V_Astronave(String[] l_pezzi, int pScudi, int pCelleEnergia, ViewImpl master){

		main = V_Background.Create_Sfondo("/Untitled_1.png");
		view = master;
		parametri = new V_Parametri(view);
		
		scudi = pScudi;
		celleEnergia = pCelleEnergia;
		
		modello = new DefaultListModel<String>();
		lista_Pezzi = l_pezzi;
		AddToModel(modello, lista_Pezzi);
		visualizza_Lista = new JList<String>(modello);
		
		b_Ricarica.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
					Controller.getLog().setView(view);
					Controller.getLog().getControllerAstronave().ricaricaScudiAstronave();
			}
			
		});
		
		b_Sostituisci.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(!visualizza_Lista.isSelectionEmpty()){
					Controller.getLog().setView(view);
					Controller.getLog().getControllerAstronave().sostituisciPezziRottiAstronave(visualizza_Lista.getSelectedIndex());
				}
			}
			
		});
		
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
		
		contenitore1 = new JPanel();
		contenitore1.setOpaque(false);
		contenitore2 = new JPanel();
		contenitore2.setOpaque(false);
		contenitore = new JPanel();
		contenitore.setOpaque(false);
		
		main.setLayout(new BorderLayout());
		contenitore.setLayout(new GridBagLayout());
		contenitore1.setLayout(new BoxLayout(contenitore1, BoxLayout.Y_AXIS));
		contenitore2.setLayout(new GridBagLayout());
		
		text_Scudi = new JLabel("Scudi: " + scudi + "/3600");
		text_CelleEnergia = new JLabel("Celle di energia: " + celleEnergia + "");
		text_Quantita = new JLabel("Pezzi rotti:");
		
		contenitore1.add(text_Scudi);
		contenitore1.add(text_CelleEnergia);
		
		cnst2.gridy = 0;
		cnst2.insets = new Insets(0,0,0,0);
		cnst2.fill = GridBagConstraints.VERTICAL;
		contenitore2.add(text_Quantita, cnst2);
		cnst2.gridy++;
		contenitore2.add(visualizza_Lista,cnst2);
		
		cnst.gridy = 0;
		cnst.gridx = 0;
		cnst.insets = new Insets(30,30,30,30);
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(contenitore1, cnst);
		cnst.gridx++;
		contenitore.add(b_Ricarica, cnst);
		cnst.gridy++;
		cnst.gridx--;
		contenitore.add(contenitore2, cnst);
		cnst.gridx++;
		contenitore.add(b_Sostituisci, cnst);
		cnst.gridx++;
		contenitore.add(b_Indietro, cnst);
		
		SetToolTip();
		
		main.revalidate();
		main.repaint();
		
		main.add(contenitore,BorderLayout.CENTER);
		main.add(parametri.Get_View(), BorderLayout.NORTH);
	}

	
	public JPanel Get_View(){
		return main;
	}
	
	
	public void Update_View(String[] l_pezzi, int pScudi, int pCelleEnergia){
		lista_Pezzi = l_pezzi;
		modello.removeAllElements();
		AddToModel(modello, lista_Pezzi);
		scudi = pScudi;
		celleEnergia = pCelleEnergia;
		text_Scudi.setText("Scudi: " + scudi + "/3600");
		text_CelleEnergia.setText("Celle di energia: " + celleEnergia + "");
		view.schermata.setTitle("Astronave");
	}
	
	public void Update_Parameters(int pFame, int pSete, int pOssigeno, int pGiorno, String sOra,String sMeteo) {
		parametri.Update_Parameters(pFame, pSete, pOssigeno, pGiorno, sOra, sMeteo);
	}
	
	public void Update_View(){
		if(visualizza_Lista.getSelectedIndex() > -1){
			int quantita = Controller.getLog().getControllerAstronave().conversioneNumeri(visualizza_Lista.getSelectedIndex());
			text_Quantita.setText("Pezzi rotti: " + quantita);
		}
		else
			text_Quantita.setText(" ");
	}
	
	private void AddToModel(DefaultListModel<String> model, String[] aggiunte){
		for(int i = 0 ; i < aggiunte.length ; i++){
			model.addElement(aggiunte[i]);
		}
	}
	
	private void SetToolTip(){
		b_Indietro.setToolTipText("Torna alla schermata precedente.");
		text_Scudi.setToolTipText("Gli scudi permetteranno alla nave di ripartire per lo spazio incolume!");
		text_CelleEnergia.setToolTipText("Si consumano quando si ricaricano gli scudi dell'astronave.");
		b_Ricarica.setToolTipText("Ricarica gli scudi dell'astronave utilizzando celle di energia.");
		b_Sostituisci.setToolTipText("Sostituisci i pezzi rotti dell'astronave!");
		visualizza_Lista.setToolTipText("Questa è la lista dei pezzi rotti dell'astronave. Seleziona una tipologia per vedere quanti pezzi di questo tipo sono da sostituire.");
	}
}
