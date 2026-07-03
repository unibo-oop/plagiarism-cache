package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class V_Parametri implements V_Properties{
	
	private JPanel main, parametri, a1;
	
	private JProgressBar fame = new JProgressBar(0,100);
	private final JLabel  text_Fame = new JLabel("Fame");
	private final JLabel  text_Sete = new JLabel("Sete");
	private JProgressBar sete = new JProgressBar(0,100);
	
	private JLabel text_Ossigeno, text_Data, text_Ora, text_Meteo;
	
	protected ViewImpl view;
	
	private final GridBagConstraints cnst = new GridBagConstraints();
	private final GridBagConstraints cnst2 = new GridBagConstraints();
	
	public V_Parametri(int pFame, int pSete, int pOssigeno, int pOra, String sGiorno, String sMeteo, ViewImpl master){
		
		parametri = new JPanel();
		parametri.setOpaque(false);
		a1 = new JPanel();
		a1.setOpaque(false);
		
		view = master;
		
		parametri.setLayout(new GridBagLayout());
		a1.setLayout(new GridBagLayout());
		
		fame.setMaximumSize(new Dimension(75,25));
		sete.setMaximumSize(new Dimension(75,25));
		
		fame.setValue(pFame);
		sete.setValue(pSete);
		
		text_Ossigeno = new JLabel("OSSIGENO: " + ((double)pOssigeno/10) + " % ");
		text_Data = new JLabel(sGiorno + "  ");
		text_Ora = new JLabel("ORA : " + pOra + "");
		text_Meteo = new JLabel("Condizione Atmosferica: " + sMeteo);

		cnst.gridx = 0;
		cnst.gridy = 0;
		cnst.insets = new Insets(5,5,5,5);
		cnst.fill = GridBagConstraints.VERTICAL;
		parametri.add(text_Fame, cnst);
		cnst.gridx++;
		parametri.add(fame, cnst);
		cnst.gridy++;
		cnst.gridx--;
		parametri.add(text_Sete, cnst);
		cnst.gridx++;
		parametri.add(sete, cnst);
		
		cnst2.gridx = 0;
		cnst2.insets = new Insets(5,5,5,5);
		cnst2.fill = GridBagConstraints.VERTICAL;
		a1.add(text_Data, cnst2);
		cnst2.gridx++;
		a1.add(text_Ora, cnst2);
		cnst2.gridx++;
		a1.add(text_Ossigeno, cnst2);
		cnst2.gridx++;
		
		SetToolTip();
		
		main =  V_Background.Create_Sfondo("/metallo_XX.png");
		main.setLayout(new BorderLayout());
		main.add(parametri, BorderLayout.WEST);
		main.add(a1, BorderLayout.CENTER);
		main.add(text_Meteo, BorderLayout.EAST);
	}
	
	public V_Parametri(ViewImpl master){
		
		parametri = new JPanel();
		parametri.setOpaque(false);
		a1 = new JPanel();
		a1.setOpaque(false);
		
		view = master;
		
		parametri.setLayout(new GridBagLayout());
		a1.setLayout(new GridBagLayout());
		
		fame.setMaximumSize(new Dimension(75,25));
		sete.setMaximumSize(new Dimension(75,25));
		
		fame.setValue(10);
		sete.setValue(30);
		
		text_Ossigeno = new JLabel(" ");
		text_Data = new JLabel(" ");
		text_Ora = new JLabel(" ");
		text_Meteo = new JLabel(" ");
		
		cnst.gridx = 0;
		cnst.gridy = 0;
		cnst.insets = new Insets(5,5,5,5);
		cnst.fill = GridBagConstraints.VERTICAL;
		parametri.add(text_Fame, cnst);
		cnst.gridx++;
		parametri.add(fame, cnst);
		cnst.gridy++;
		cnst.gridx--;
		parametri.add(text_Sete, cnst);
		cnst.gridx++;
		parametri.add(sete, cnst);
		
		cnst2.gridx = 0;
		cnst2.insets = new Insets(5,5,5,5);
		cnst2.fill = GridBagConstraints.VERTICAL;
		a1.add(text_Data, cnst2);
		cnst2.gridx++;
		a1.add(text_Ora, cnst2);
		cnst2.gridx++;
		a1.add(text_Ossigeno, cnst2);
		cnst2.gridx++;
		
		SetToolTip();
		
		main =  V_Background.Create_Sfondo("/metallo_XX.png");
		main.setLayout(new BorderLayout());
		main.add(parametri, BorderLayout.WEST);
		main.add(a1, BorderLayout.CENTER);
		main.add(text_Meteo, BorderLayout.EAST);
	}
	
	public JPanel Get_View(){
		return main;
	}

	public void Update_Parameters(int pFame, int pSete, int pOssigeno, int pOra, String sGiorno, String sMeteo) {
		fame.setValue(pFame);
		sete.setValue(pSete);
		text_Ossigeno.setText("OSSIGENO: " + ((double)pOssigeno/10) + " % ");
		text_Data.setText(sGiorno + "  ");
		text_Ora.setText("ORA : " + pOra + "");
		text_Meteo.setText("Condizione Atmosferica: " + sMeteo);
	}
	
	public void SetToolTip(){
		fame.setToolTipText("Barra della fame, se è bassa mangia qualcosa!");
		text_Fame.setToolTipText("Barra della fame, se è bassa mangia qualcosa!");
		sete.setToolTipText("Barra della sete, se è bassa bevi dell'acqua!");
		text_Sete.setToolTipText("Barra della sete, se è bassa bevi dell'acqua!");
		text_Ossigeno.setToolTipText("Percentuale di ossigeno nella tua base, non farlo mai mancare!");
		text_Ora.setToolTipText("Tempo passato da quando sei atterrato sul pianeta.");
		text_Data.setToolTipText("Tempo passato da quando sei atterrato sul pianeta.");
	}
}
