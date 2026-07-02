package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.Control;

/**
 * Classe utilizzata per inserire l'URL di una repository da copiare
 * nella propria repository
 *
 * @author Filippo Solazzi
 *
 */
public class Clone_View extends JFrame{

	private static final long serialVersionUID = 5546526749878417013L;
	private JTextField url = new JTextField(25);
	private JLabel label = new JLabel();
	private JButton ok = new JButton("OK");
	private JButton exit = new JButton("Annulla");
	private URL sito;
	
	/**
	* Costruttore che inizializza la propria View
	* con i rispettivi eventi
	*
	* @param ctr
	*          Control utilizzato per l'intercetto degli eventi
	*/
	public Clone_View(Control ctr){
		this.setTitle("Choose the repository to be cloned");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(550,120);
		this.setLocationRelativeTo(null);
		
		label.setText("Inserire la repository da clonare");
		JPanel panel = new JPanel();
		JPanel bpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				//Controllo dell'esistenza e della sintassi dell'URL
				try {
					if(url.getText().indexOf(".") != -1 && url.getText().indexOf("\\") == -1){
						if(url.getText().indexOf(" ") == -1){
							sito = new URL(url.getText());
						}else{
							sito = new URL(url.getText().substring(0, url.getText().indexOf(" ")));
						}
						HttpURLConnection huc =  ( HttpURLConnection )  sito.openConnection (); 
						if(huc.getResponseCode() == 200 || huc.getResponseCode() == 202 || huc.getResponseCode() == 302){
							ctr.set_Repository(url.getText());
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "URL non trovato", "Errore", JOptionPane.ERROR_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Sintassi URL Errata", "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (MalformedURLException e1) {
					JOptionPane.showMessageDialog(null, "Sintassi URL Errata \n(Aggiungere http://)", "Errore", JOptionPane.ERROR_MESSAGE);
				}
				catch(IOException e1){
					JOptionPane.showMessageDialog(null, "Errore Generico", "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		panel.add(label);
		panel.add(url);
		bpanel.add(ok);
		bpanel.add(exit);
		this.add(panel, BorderLayout.NORTH);
		this.add(bpanel, BorderLayout.SOUTH);
	}
}
