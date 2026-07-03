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

public class V_Cucina implements V_Properties{
	private JPanel main, contenitore, contenitore2;
	
	private DefaultListModel<String> modello;
	private String[] lista_Cibo;
	private JList<String> visualizza_Lista;
	private JScrollPane scorri_Lista;
	private JLabel text_Quantita;
	private final JButton b_Indietro = new JButton("Indietro");
	private final JButton b_Mangia = new JButton("Mangia");
	private final GridBagConstraints cnst = new GridBagConstraints();
	
	private V_Parametri parametri;
	
	ViewImpl view;
	
	public V_Cucina(String[] l_cibo, ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		view = master;
		parametri = new V_Parametri(view);
		
		modello = new DefaultListModel<String>();
		lista_Cibo = l_cibo;
		AddToModel(modello, lista_Cibo);
		visualizza_Lista = new JList<String>(modello);
		scorri_Lista = new JScrollPane(visualizza_Lista);
		scorri_Lista.setPreferredSize(new Dimension(200, 200));
		
		text_Quantita = new JLabel(" ");
		contenitore = new JPanel();
		main.setLayout(new BorderLayout());
		contenitore.setLayout(new GridBagLayout());
		contenitore2 = new JPanel();
		contenitore2.setLayout(new BoxLayout(contenitore2, BoxLayout.X_AXIS));
		contenitore.setOpaque(false);
		contenitore2.setOpaque(false);
		
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
		
		b_Mangia.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(!visualizza_Lista.isSelectionEmpty()){
					Controller.getLog().setView(view);
					Controller.getLog().getControllerCucina().mangiaCucina(visualizza_Lista.getSelectedIndex());
				}
			}
			
		});
		
		SetToolTip();
		
		cnst.gridy = 0;
		cnst.gridx = 0;
		cnst.insets = new Insets(10,10,10,10);
		cnst.fill = GridBagConstraints.VERTICAL;		
		contenitore.add(scorri_Lista, cnst);
		cnst.gridx++;
		contenitore.add(text_Quantita, cnst);
		cnst.gridx--;
		cnst.gridy++;
		
		
		contenitore2.add(b_Mangia);
		contenitore2.add(b_Indietro);
		contenitore.add(contenitore2, cnst);
		
		main.add(parametri.Get_View(), BorderLayout.NORTH);
		main.add(contenitore, BorderLayout.CENTER);
	}
	
	public JPanel Get_View(){
		return main;
	}
	
	public String Get_Selected(){
		return visualizza_Lista.getSelectedValue().toString();
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
			int quantita = Controller.getLog().getControllerCucina().conversioneNumeri(visualizza_Lista.getSelectedIndex());
			text_Quantita.setText("Quantità : " + quantita);
		}
		else
			text_Quantita.setText(" ");
	}
	
	public void Update_View(String[] l_cibo){
		lista_Cibo= l_cibo;
		modello.removeAllElements();
		AddToModel(modello, lista_Cibo);
		text_Quantita.setText(" ");
		view.schermata.setTitle("Cucina");
	}
	
	private void SetToolTip(){
		visualizza_Lista.setToolTipText("Questa è la lista dei cibi che possiedi.");
		b_Indietro.setToolTipText("Torna alla schermata precedente.");
		b_Mangia.setToolTipText("Consuma il cibo selezionato, ripristinando la fame.");
	}
	
}
