/*
 * Questa è la GUI di avviamento dell'applicativo, con il bottone di creazione dell'hotel, e successivamente
 * vengono abilitati bottoni di gestione prenotazioni e gestione hotel
 */

package vieww;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Hotel;
import model.Room;
import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class MainFrame.
 */
public class MainFrame extends JFrame {
	
	/** The my instance. */
	private static MainFrame myInstance;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The create new hotel button. */
	private final JButton createNewHotel = new JButton("Crea Nuovo Albergo");
	
	/** The main label. */
	private final JLabel mainLabel;
	
	/** The title. */
	private final JLabel title = new JLabel();
	
	/** The title panel. */
	private final JPanel titlePanel = new JPanel();
	
	/** The buttons panel. */
	private final JPanel buttonPanel = new JPanel();
	
	/** The hotel management button . */
	private final JButton hotelManagement = new JButton("Gestione Hotel");
	
	/** The booking management button . */
	private final JButton bookingManagement = new JButton("Gestione Prenotazioni");
	
	/**
	 * Instantiates a new main frame.
	 */
	public MainFrame() {
		
		title.setFont(new Font("Courier", Font.BOLD, 100));
		title.setText("My Hotel Wizard");
		this.setSize(1280, 800);
		// this.getContentPane().setLayout(new BorderLayout());
		mainLabel = new JLabel();
		mainLabel.setLayout(new BoxLayout(mainLabel, BoxLayout.Y_AXIS));
		mainLabel.setIcon(new ImageIcon("res/hotel.jpg"));
		createNewHotel.setAlignmentX(CENTER_ALIGNMENT);
		// mainLabel.setIcon(new ImageIcon("res/black-frost-ripples-1280.jpg"));
		titlePanel.add(title);
		titlePanel.setOpaque(false);
		buttonPanel.setOpaque(false);
		mainLabel.add(titlePanel, BorderLayout.NORTH);
		mainLabel.add(buttonPanel);
		buttonPanel.add(createNewHotel);
		createNewHotel.setEnabled(false);
		bookingManagement.setEnabled(false);
		hotelManagement.setEnabled(false);
		buttonPanel.add(bookingManagement);
		bookingManagement.addActionListener(e -> {
			new RoomView();
		});
		buttonPanel.add(hotelManagement);
		buttonPanel.add(bookingManagement);
		hotelManagement.addActionListener(e -> {
			new HotelManagement();
		});
		List<Room> list = Hotel.getInstance().getRoomList();
		if (list.size() == 0) {
			createNewHotel.setEnabled(true);
		}else{
			bookingManagement.setEnabled(true);
			hotelManagement.setEnabled(true);
		}
		createNewHotel.addActionListener(e -> {
			Hotel.getInstance();
			new CreateRoom();
		});
		this.getContentPane().add(mainLabel);
		this.setVisible(true);
		
	}
	
	/**
	 * Update buttons.
	 *
	 * @param b the b
	 */
	public void updateButtons(Boolean b){
		if(b){
			this.hotelManagement.setEnabled(true);
			this.createNewHotel.setVisible(false);
			if(Hotel.getInstance().getExtraList().size()>0 && !Hotel.getInstance().getCatalog().getSeasonMap().isEmpty()) {
				this.bookingManagement.setEnabled(true);
			} else 
				this.bookingManagement.setEnabled(false);
		}else{
			this.bookingManagement.setEnabled(true);
			this.hotelManagement.setEnabled(true);
			this.createNewHotel.setEnabled(false);
		}	
	}
	
	/**
	 * Gets the single instance of MainFrame.
	 *
	 * @return single instance of MainFrame
	 */
	public static MainFrame getInstance() {
		if (myInstance == null) {
			myInstance = new MainFrame();
		}
		return myInstance;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JFrame#processWindowEvent(java.awt.event.WindowEvent)
	 */
	protected void processWindowEvent(WindowEvent e) {
	    if(e.getID() == WindowEvent.WINDOW_CLOSING){
	    	Controller.saveModel();
	    }
	    super.processWindowEvent(e);
	}	
}