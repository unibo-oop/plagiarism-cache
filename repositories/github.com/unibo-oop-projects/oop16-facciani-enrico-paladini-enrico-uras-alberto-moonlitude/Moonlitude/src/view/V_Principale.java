package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;

public class V_Principale implements V_Properties{
	
	private JPanel main, contenitore, contenitore2;
	
	private final JButton b_Laboratorio = new JButton("Laboratorio");
	private final JButton b_Esplora = new JButton("Esplora");
	private final JButton b_Giardino = new JButton("Giardino");
	private final JButton b_Magazzino = new JButton("Magazzino");
	private final JButton b_Astronave = new JButton("Astronave");
	private final JButton b_Cucina = new JButton("Cucina");
	private final JButton b_Filtratore = new JButton("Filtratore");
	private final JButton b_Generatore = new JButton("Generatore");
	private final JButton b_Refrigeratore = new JButton("Refrigeratore");
	private final JButton b_Radar = new JButton("Radar");
	private final JButton b_Equipaggiamento = new JButton("Equipaggiamento");
	private final JButton b_Zaino = new JButton("Zaino");
	private final JButton b_Salva = new JButton("Salva");
	
	private final GridBagConstraints cnst = new GridBagConstraints();
	private final GridBagConstraints cnst2  = new GridBagConstraints();
	
	private V_Parametri parametri;
	
	private ViewImpl view;
	
	public V_Principale(ViewImpl master){
		
		main = V_Background.Create_Sfondo("/Untitled_1.png");
		view = master;
		parametri = new V_Parametri(view);
		
		contenitore =  V_Background.Create_Sfondo("/metallo_YY.png");
		//contenitore.setOpaque(false);
		contenitore2 =  V_Background.Create_Sfondo("/metallo_YY.png");
		//contenitore2.setOpaque(false);
		
		contenitore.setLayout(new GridBagLayout());
		contenitore2.setLayout(new GridBagLayout());
		main.setLayout(new BorderLayout());
		
		SetToolTip();
		
		b_Astronave.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetAstronave();
			}
			
		});
		
		b_Laboratorio.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetLaboratorio();
			}
			
		});
		
		b_Magazzino.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetMagazzino();
			}
			
		});
		
		b_Equipaggiamento.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetEquipaggiamento();
			}
			
		});
		
		b_Cucina.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetCucina();
			}
			
		});
		
		b_Refrigeratore.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetRefrigeratore();
			}
			
		});
		
		b_Giardino.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetGiardino();
			}
			
		});
		
		b_Radar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetRadar();
			}
			
		});
		
		b_Filtratore.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetFiltratore();
			}
			
		});
		
		b_Generatore.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetGeneratore();
			}
			
		});
		
		b_Esplora.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetEsplorazione();
			}
			
		});
		
		b_Zaino.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				view.SetZaino();
			}
			
		});
		
		b_Salva.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getLog().setView(view);
				try {
					Controller.getLog().getControllerUtility().salvaGioco();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		cnst.gridy = 0;
		cnst.insets = new Insets(30,30,30,30);
		cnst.fill = GridBagConstraints.VERTICAL;
		contenitore.add(b_Laboratorio, cnst);
		cnst.gridy++;
		contenitore.add(b_Giardino,cnst);
		cnst.gridy++;
		contenitore.add(b_Magazzino, cnst);
		cnst.gridy++;
		contenitore.add(b_Radar, cnst);
		cnst.gridy++;
		contenitore.add(b_Esplora, cnst);
		cnst.gridy++;
		contenitore.add(b_Salva, cnst);
		cnst.gridy++;
		contenitore.add(b_Equipaggiamento, cnst);
		
		cnst2.gridy = 0;
		cnst2.insets = new Insets(30,30,30,30);
		cnst2.fill = GridBagConstraints.VERTICAL;
		contenitore2.add(b_Astronave, cnst2);
		cnst2.gridy++;
		contenitore2.add(b_Cucina, cnst2);
		cnst2.gridy++;
		contenitore2.add(b_Generatore, cnst2);
		cnst2.gridy++;
		contenitore2.add(b_Filtratore, cnst2);
		cnst2.gridy++;
		contenitore2.add(b_Refrigeratore, cnst2);
		cnst2.gridy++;
		contenitore2.add(b_Zaino, cnst2);
		
		main.add(contenitore,BorderLayout.WEST);
		main.add(parametri.Get_View(), BorderLayout.NORTH);
		main.add(contenitore2, BorderLayout.EAST);
		//this.add(main);
	}
	
	public JPanel Get_View(){
		return main;
	}

	public void Update_Parameters(int pFame, int pSete, int pOssigeno, int pGiorno, String sOra,String sMeteo) {
		parametri.Update_Parameters(pFame, pSete, pOssigeno, pGiorno, sOra, sMeteo);
	}
	
	private void SetToolTip(){
		b_Astronave.setToolTipText("Qui puoi verificare lo stato della tua astronave, i pezzi da riparare e gli scudi da ricaricare.");
		b_Laboratorio.setToolTipText("Qui puoi ricercare nuovi strumenti o elementi oppure craftare quelli che hai già scoperto e di cui ne hai possibilità.");
		b_Giardino.setToolTipText("Qui puoi gestire un giardino con orto, piantare e innaffiare piante e raccogliere i loro frutti.");
		b_Cucina.setToolTipText("Qui puoi mangiare il cibo che hai trovato o preparato. Nutrirsi è essenziale per sopravvivere!");
		b_Generatore.setToolTipText("Qui puoi regolare la luminosità che tieni per le piante in giardino.");
		b_Filtratore.setToolTipText("Qui puoi vedere l'acqua collezionata dalle piogge, puoi controllare il suo stato e berla se potabile.");
		b_Radar.setToolTipText("Qui puoi lanciare droni per scoprire nuove zone da esplorare.");
		b_Refrigeratore.setToolTipText("Qui puoi conservare i cibi e gli strumenti al freddo.");
		b_Zaino.setToolTipText("Qui puoi controllare il contenuto del tuo zaino");
		b_Esplora.setToolTipText("Qui puoi andare in esplorazione alla ricerca di materiali e cibo nei luoghi scoperti dai droni.");
		b_Magazzino.setToolTipText("Qui puoi controllare i materiali in tuo possesso.");
		b_Salva.setToolTipText("Qui puoi salvare i progressi di gioco.");
		b_Equipaggiamento.setToolTipText("Qui puoi controllare il tuo equipaggiamento e cambiarlo.");
	}
}
