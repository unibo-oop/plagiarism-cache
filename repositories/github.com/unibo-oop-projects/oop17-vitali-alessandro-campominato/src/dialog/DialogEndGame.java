package dialog;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaces.DialogEndGameInterface;


public class DialogEndGame extends JDialog implements ActionListener, DialogEndGameInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int seconds;
	private boolean finish;
	private boolean record;
	
	private String stringName;

	private JPanel panelDialog = new JPanel();
	
	private JLabel labelMessage;
	private JLabel labelInsertName;
	private JLabel labelTime;
	
	private JTextField textFieldName;
	
	private JButton buttonConfirm;
	
	/**
	 * costruttore che crea un dialog in base all'esito della partita
	 * Inoltre se non viene inserito un record alla chiusura del dialog viene attribuito il record senza nome (NoName)
	 * 
	 * @param frame
	 *     il frame da cui proviene la richiesta
	 * @param seconds
	 *     il tempo impiegato per finire la partita
	 * @param finish
	 *     se la partita è stata vinta o persa
	 * @param record
	 *     se il punteggio è un record o meno
	 */
	public DialogEndGame(JFrame frame, int seconds, boolean finish, boolean record) {
		super(frame, true);

		this.seconds = seconds;
		this.finish = finish;
		this.record = record;
		
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				stringName = "NoName";		
			}
			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowOpened(WindowEvent e) {}
		});
		
		if(this.finish) {
			createDialogWin();
		} else {
			createDialogLost();
		}
	}
	
	/**
	 * dialog creato in caso di vittoria, viene creata tutta la view del dialog per permettere di inserire il record
	 */
	private void createDialogWin() {
		labelMessage = new JLabel("Complimenti, hai vinto!");
		panelDialog.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelDialog.add(labelMessage, gbc);
		if(this.record == true) {
			int hour = this.seconds / 3600;
			int minut = this.seconds / 60 - hour * 60;
			int second = this.seconds - hour*3600 - minut*60;
			String stringTime = new String(hour + "h " + minut + "'" + second + "\"");
			
			this.labelTime = new JLabel(stringTime);
			gbc.gridx = 0;
			gbc.gridy = 1;
			this.panelDialog.add(labelTime, gbc);
			
			this.labelInsertName = new JLabel("Inserisci il tuo nome : ");
			gbc.gridx = 0;
			gbc.gridy = 2;
			this.panelDialog.add(labelInsertName, gbc);
			
			this.textFieldName = new JTextField();
			this.textFieldName.setPreferredSize(new Dimension(100,30));
			this.textFieldName.setFont(new Font("Arial", Font.PLAIN, 20));
			gbc.gridx = 0;
			gbc.gridy = 3;
			this.panelDialog.add(textFieldName, gbc);
			
			this.buttonConfirm = new JButton("Conferma");
			this.buttonConfirm.addActionListener(this);
			gbc.gridx = 0;
			gbc.gridy = 4;
			this.panelDialog.add(buttonConfirm, gbc);
		} else {
		  this.buttonConfirm = new JButton("Conferma");
		  this.buttonConfirm.addActionListener(this);
			gbc.gridx = 0;
			gbc.gridy = 1;
			this.panelDialog.add(buttonConfirm, gbc);
		}
		this.getRootPane().setDefaultButton(buttonConfirm);
		this.getContentPane().add(panelDialog);
		this.pack();
	}
	
	/**
	 * creazione del dialog in caso di sconfitta
	 */
	private void createDialogLost() {
		this.labelMessage = new JLabel("Hai Perso!");
		this.panelDialog.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelDialog.add(labelMessage, gbc);
		
		this.buttonConfirm = new JButton("Conferma");
		this.buttonConfirm.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.panelDialog.add(buttonConfirm, gbc);
	
		this.getRootPane().setDefaultButton(buttonConfirm);
		this.getContentPane().add(panelDialog);
		this.pack();
	}
	
	/**
	 * @return il nome inserito del record
	 */
	public String getStringName() {
		return this.stringName;
	}

	/**
	 * quando viene cliccato conferma viene inserito il nome dentro la stringa
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.finish && this.record) {
			stringName = new String(textFieldName.getText());	
		}
		dispose();	
	}
}
