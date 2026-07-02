package View;

import java.awt.*;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Control.Control;

/**
 * Classe utilizzata per suggerire all'utente di installare mercurial
 *
 * @author Filippo Solazzi
 *
 */
public class Existence extends JFrame{

	private static final long serialVersionUID = -9071346791132865143L;
	private URL download;
	private JLabel label1 = new JLabel();
	private JLabel label2 = new JLabel();
	private JLabel sito = new JLabel();
	private JButton ok = new JButton("OK");
	private JButton exit = new JButton("Annulla");
	final GridBagConstraints  cnst = new  GridBagConstraints ();
	
	/**
	* Costruttore che inizializza la propria View
	* con i rispettivi eventi
	*
	* @param ctr
	*          Control utilizzato per l'intercetto degli eventi
	*/
	public Existence(Control ctr){
		this.setTitle("Installare Mercurial");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(500,150);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JPanel gridPanel = new JPanel(new GridBagLayout());
		JPanel bpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(gridPanel);
		
		try {
			download = new URL("https://www.mercurial-scm.org/wiki/Download");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		sito.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sito.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	                try {
	                	Desktop.getDesktop().browse(new URI(download.toString()));
	                	sito.setForeground(Color.RED);
	                } catch (URISyntaxException | IOException ex) {
	                	
	                }
	        }
        });
		
		cnst.gridy = 0;                     // 1-a riga
		cnst.insets = new  Insets(3,3,3,3);          //  spazio  attorno  al comp.
		cnst.fill = GridBagConstraints.HORIZONTAL;     //  estensione  in  orizzont.
		gridPanel.add((label1),cnst);
		cnst.gridy ++;                     //  prossima  riga
		gridPanel.add((label2),cnst);
		cnst.gridy ++;
		gridPanel.add((sito),cnst);
		
		label1.setText("Mercurial non installato");
		label2.setText("Premere il seguente link per installarlo");
		sito.setText(download.toString());
		sito.setForeground(Color.BLUE);
		
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(ctr.hg_Existence()){
					ctr.login();
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Mercurial non Installato, Installare per eseguire!", "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		bpanel.add(ok);
		bpanel.add(exit);
		this.add(panel, BorderLayout.NORTH);
		this.add(bpanel, BorderLayout.SOUTH);
	}
}

