package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Control.Control;

/**
 * Classe utilizzata per effettuare il login
 * ed impostare il proprio nome utente
 *
 * @author Filippo Solazzi
 *
 */
public class Login_View_Impl extends JFrame implements Login_View{
	private static final long serialVersionUID = -5645091822376651779L;
	final JTextField username = new JTextField(10);
	private Control ctr;
	
	/**
	* Costruttore che inizializza la propria View
	* con i rispettivi eventi
	*/
	public Login_View_Impl(){
		
				
		this.setTitle("Login");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(300,100);
		this.setLocationRelativeTo(null);
		
		//JPanel situato a sud ed a destra della Form
		//Dove verranno inseriti i Bottoni
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//JPanel situato a nord della Form
		//Dove verr� inserita la JLabel ed la JTextFiel
		JPanel nPanel = new JPanel();
		nPanel.setLayout(new FlowLayout());
		
		//Semplice Jlabel
		JLabel us = new JLabel("Username");
		username.setToolTipText("Inserire l'username per andare ad effettuare le operazioni su Mercurial");
		
		//Bottone per confermare "l'accesso"
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(username.getText().length() != 0){	//Controllo della JTextField
					ok.setEnabled(true);
					ctr.username(username.getText());
					dispose();	//Destroy the JFrame object
				}
				else{
					JOptionPane.showMessageDialog(null, "Inserire un Userame", "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Bottone per uscire dal programma
		JButton exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		//Aggiunta dei componenti alle JPanel ed alla Form
		sPanel.add(ok);
		sPanel.add(exit);
		nPanel.add(us);
		nPanel.add(username);
		this.add(sPanel,BorderLayout.SOUTH);
		this.add(nPanel,BorderLayout.NORTH);
	}
	
	@Override
	public void set_Control(Control ctr){
		this.ctr = ctr;
	}
}
