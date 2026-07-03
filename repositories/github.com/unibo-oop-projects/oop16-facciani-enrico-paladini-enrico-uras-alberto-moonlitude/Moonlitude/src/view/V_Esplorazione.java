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

public class V_Esplorazione implements V_Properties{
	
	private JPanel main, contenitore, contenitore2;
	
	private final JButton b_Indietro = new JButton("Indietro");
	private final JButton b_Less = new JButton("<");
	private final JButton b_Plus = new JButton(">");
	private final JButton b_Esplora = new JButton("Esplora");
	private final JLabel text_Contesto = new JLabel("Luoghi da esplorare:");
	
	private final GridBagConstraints cnst = new GridBagConstraints();
	
	private DefaultListModel<String> modello;
	private String[] lista_Luoghi;
	private JList<String> visualizza_Lista;
	private JScrollPane scorri_Lista;
	
	protected ViewImpl view;
	
	private JLabel text_Contatore;
	private int contatore, indice;
	
	private V_Parametri parametri;
	
	public V_Esplorazione(String[] l_Luoghi, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		view = master;
		contenitore = new JPanel();
		contenitore.setOpaque(false);
		contenitore.setLayout(new GridBagLayout());
		contenitore2 = new JPanel();
		contenitore2.setLayout(new BoxLayout(contenitore2, BoxLayout.Y_AXIS));
		contatore = 1;
		text_Contatore =new JLabel("Viaggia per " + contatore + " ore ");
		parametri = new V_Parametri(view);
		contenitore.setOpaque(false);
		contenitore2.setOpaque(false);
		
		modello = new DefaultListModel<String>();
		lista_Luoghi = l_Luoghi;
		AddToModel(modello, lista_Luoghi);
		visualizza_Lista = new JList<String>(modello);
		scorri_Lista = new JScrollPane(visualizza_Lista);
		scorri_Lista.setPreferredSize(new Dimension(200, 200));
		
		b_Indietro.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetPrincipale();
			}
			
		});
		
		b_Less.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Update_View(-1);
			}
			
		});
		
		b_Plus.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Update_View(1);
			}
			
		});
		
		b_Esplora.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(!visualizza_Lista.isSelectionEmpty()){
					indice = visualizza_Lista.getSelectedIndex();
					Controller.getLog().setView(view);
					Controller.getLog().getControllerEsplorazione().bottoneEsplora();
				}
			}
			
		});
		
		contenitore2.add(text_Contesto);
		contenitore2.add(scorri_Lista);
		
		cnst.gridy = 0;
		cnst.gridx = 0;
		cnst.insets = new Insets(50,50,50,50);
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(contenitore2, cnst);
		cnst.gridx++;
		contenitore.add(b_Less, cnst);
		cnst.gridx++;
		contenitore.add(text_Contatore, cnst);
		cnst.gridx++;
		contenitore.add(b_Plus, cnst);
		cnst.gridx++;
		contenitore.add(b_Esplora, cnst);
		cnst.gridx++;
		contenitore.add(b_Indietro, cnst);
		
		SetToolTip();
		
		main.add(contenitore, BorderLayout.CENTER);
		main.add(parametri.Get_View(), BorderLayout.NORTH);
		
	}
	
	public JPanel Get_View(){
		return main;
	}
	
	public int Get_Selected(){
		return indice;
	}
	
	public void Update_Parameters(int pFame, int pSete, int pOssigeno, int pGiorno, String sOra,String sMeteo) {
		parametri.Update_Parameters(pFame, pSete, pOssigeno, pGiorno, sOra, sMeteo);
	}
	
	private void AddToModel(DefaultListModel<String> model, String[] aggiunte){
		for(int i = 0 ; i < aggiunte.length ; i++){
			model.addElement(aggiunte[i]);
		}
	}
	
	public int Get_OreEsplorazione(){
		return contatore;
	}
	
	private void Update_View(int unita){
		contatore+=unita;
		if(contatore < 1)
			contatore = 1;
		if(contatore > 24)
			contatore = 24;
		text_Contatore.setText("Viaggia per " + contatore + " ore ");
	}
	
	public void Update_View(String[] l_Luoghi){
		lista_Luoghi = l_Luoghi;
		modello.removeAllElements();
		AddToModel(modello, lista_Luoghi);
		view.schermata.setTitle("Esplorazione");
	}
	
	private void SetToolTip(){
		b_Indietro.setToolTipText("Torna alla schermata precedente.");
		b_Esplora.setToolTipText("Parti alla ricerca di risorse!");
		text_Contatore.setToolTipText("Scegli il numero di ore che desideri viaggiare.");
		b_Plus.setToolTipText("Scegli il numero di ore che desideri viaggiare.");
		b_Less.setToolTipText("Scegli il numero di ore che desideri viaggiare.");
		visualizza_Lista.setToolTipText("Lista dei luoghi da esplorare.");
	}
}
