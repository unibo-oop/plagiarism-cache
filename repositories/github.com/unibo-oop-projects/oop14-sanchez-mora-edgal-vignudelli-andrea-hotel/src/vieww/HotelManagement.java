/*
 * Questa GUI permette di gestire le prinicpali operazioni di gestione dell'applicativo, quali gestione del
 * catalogo, aggiunta di una nuova stanza e  stampa su file delle informazioni relative al tipo di pulizie
 * da effettuare ogni singola stanza 
 */

package vieww;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.FileWrite;
import model.CleanService;
import model.Hotel;

// TODO: Auto-generated Javadoc
/**
 * The Class HotelManagement.
 */
public class HotelManagement extends JFrame implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1432217778127342242L;
	
	/** The add room button . */
	private final JButton addRoom = new JButton("Aggiungi nuova stanza");
	
	/** Menu management. */
	private final JButton kitchen = new JButton("Gestione menù");
	
	/** The clean service for each room . */
	private final JButton cleanService = new JButton("Stampa Programma Pulizie");
	
	/** The back to home. */
	private final JButton backToHome = new JButton("Torna a Home");
	
	/** The catalog management. */
	private final JButton catalog = new JButton("Gestione Prezzi Soggiorno");
	
	/** The extra management */
	private final JButton extraManage = new JButton("Gestione extra");

	/**
	 * Instantiates a new hotel management.
	 */
	HotelManagement() {
		this.setTitle("Gestione");
		this.setSize(new Dimension(250, 300));
		this.setLayout(new GridLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addRoom);
		buttonPanel.add(catalog);
		buttonPanel.add(kitchen);
		buttonPanel.add(cleanService);
		buttonPanel.add(extraManage);
		buttonPanel.add(backToHome);
		this.add(buttonPanel);

		catalog.addActionListener(e -> {
			new Catalogue();
		});
		backToHome.addActionListener(e -> {
			this.dispose();
		});
		addRoom.addActionListener(e -> {
			new CreateRoom();
		});
		kitchen.addActionListener(e -> {
			new MenuView();
		});
		cleanService.addActionListener(e -> {
			FileWrite.createFileOnDesktop(CleanService.fileName,
					CleanService.getCleanService(Hotel.getInstance().getRoomList()));
		});
		extraManage.addActionListener(e -> {
			new AddExtra();
		});
		this.setVisible(true);
	}

}
