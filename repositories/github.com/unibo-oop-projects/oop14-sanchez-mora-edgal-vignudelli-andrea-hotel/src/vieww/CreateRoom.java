/*Questa GUI permette l'inserimento delle camere, sia in fase di creazione dell'aalbergo, che  in fase successiva
 * 
 */
package vieww;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Hotel;
import model.Room;
import model.RoomType;
import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateRoom.
 */
public class CreateRoom extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The n room. */
	private final JLabel nRoom = new JLabel("Numero stanza: ");
	
	/** The type room. */
	private final JLabel typeRoom = new JLabel("Tipo di stanza:  ");
	
	/** The n max guests. */
	private final JLabel nMaxGuests = new JLabel("Numero di ospiti massimi: ");
	
	/** The type of room . */
	private final Choice type = new Choice();
	
	/** The room. */
	private final JTextField room = new JTextField();
	
	/** The max guests. */
	private final JTextField maxGuests = new JTextField();
	
	/** The add button . */
	private final JButton add = new JButton("Aggiungi");
	
	/** The dimension of jtext. */
	private final Dimension d = new Dimension(100,200);
	
	/** The close frame. */
	private final JButton close = new JButton("Indietro");
	
	/**
	 * Instantiates a new creates the room.
	 */
	CreateRoom() {
		this.setTitle("Creazione nuova stanza");
		this.setSize(500, 200);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		type.add("Suite");
		type.add("Premium");
		type.add("Standard");
		this.add(nRoom);
		this.add(room);
		this.add(nMaxGuests);
		this.add(maxGuests);
		this.add(typeRoom);
		this.add(type);
		this.add(add);
		this.add(close);
		close.addActionListener(e ->{
			this.dispose();
		});
		room.setMaximumSize(d);
		maxGuests.setMaximumSize(d);
		type.setMaximumSize(d);
		add.addActionListener(e -> {
			if (Controller.checkIfIsNumber(room.getText()) && Controller.checkIfIsNumber(maxGuests.getText())) {
				if (!Controller.getInstance().checkIfRoomIsPresent(Integer.parseInt(room.getText()))) {
					if (Integer.parseInt(maxGuests.getText()) > 0 && Integer.parseInt(maxGuests.getText()) < 10) {
						if (type.getSelectedItem().equals("Premium")) {
							Hotel.getInstance().addRoom(new Room(Integer.parseInt(room.getText()), RoomType.PREMIUM,
									Integer.parseInt(maxGuests.getText())));
						}
						if (type.getSelectedItem().equals("Standard")) {
							Hotel.getInstance().addRoom(new Room(Integer.parseInt(room.getText()), RoomType.STANDARD,
									Integer.parseInt(maxGuests.getText())));
						}
						if (type.getSelectedItem().equals("Suite")) {
							Hotel.getInstance().addRoom(new Room(Integer.parseInt(room.getText()), RoomType.SUITE,
									Integer.parseInt(maxGuests.getText())));
									
						}
						JOptionPane.showMessageDialog(this, "Camera aggiunta!!");
						room.setText(null);
						maxGuests.setText(null);
						this.dispose();
						
					} else {
					
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Il valore del deve essere un numero");
			}

		});
		this.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JFrame#processWindowEvent(java.awt.event.WindowEvent)
	 */
	protected void processWindowEvent(WindowEvent e) {
	    if(e.getID() == WindowEvent.WINDOW_CLOSED){
	    	if(Hotel.getInstance().getRoomList().size()!=0){
				MainFrame.getInstance().updateButtons();
			}else{
				MainFrame.getInstance().updateButtons();
			}
	    }
	    super.processWindowEvent(e);
	}
}