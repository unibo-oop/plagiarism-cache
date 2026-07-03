/*Questa GUI contiene le informazioni di ogni singola camera, quali stato, numero di persone attualmente presente, 
 * e eventuali servizi extrs
 * 
 */

package vieww;

import java.awt.Dimension;
import java.io.Serializable;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;
import model.Customer;
import model.Guest;
import model.Hotel;
import model.Room;

// TODO: Auto-generated Javadoc
/**
 * The Class RoomInformation.
 */
public class RoomInformation extends JFrame implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6876034575916029409L;

	/** The number room. */
	private final JLabel numRoom = new JLabel("Numero camera: ");
	
	/** The type of room. */
	private final JLabel typeOf = new JLabel("Tipo di camera: ");
	
	/** The room status. */
	private final JLabel roomStatus = new JLabel("Stato della camera ");
	
	/** The actual customers in that room. */
	private final JLabel actualCustomers = new JLabel("Numero attuali ospiti: ");
	
	/** The extra associated at one booking. */
	private final JButton extra = new JButton("Visualizza servizi extra ");
	
	/** The actual customers list. */
	private final JLabel customersList = new JLabel("Elenco ospiti attuali: ");
	
	/** The add guest buttons. */
	private JButton addGuest = new JButton("Aggiungi un ospite");
	
	/** The remove booking. */
	private final JButton removeBooking = new JButton ("Rimuovi prenotazione");
	
	/** The close. */
	private final JButton close = new JButton("Indietro");
	
	

	/**
	 * Instantiates a new room information.
	 *
	 * @param r the room 
	 * @param date the selected date
	 * @param rv the roomView for update
	 */
	RoomInformation(final Room r,final LocalDate date,final  RoomView rv) {
		super();
		this.setTitle("Visulizza stato camera");
		this.setSize(new Dimension(400, 300));
		this.setVisible(true);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		numRoom.setText(numRoom.getText() + r.getNumber());
		typeOf.setText(typeOf.getText() + r.getType().toString());
		if (r.isBusyThisDay(date)) {
			roomStatus.setText(roomStatus.getText() + "OCCUPATA");
		} else
			roomStatus.setText(roomStatus.getText() + "LIBERA");
		StringBuilder sb = new StringBuilder(customersList.getText());
		if (r.isBusyThisDay(date)) {
			for (Customer c : r.getCustomerList()) {
				if ((c.getBooking().getStartDate().isEqual(date) || (c.getBooking().getStartDate().isBefore(date)
						&& c.getBooking().getEndDate().isAfter(date)))) {
					sb.append(c.toString());
					if (c.getBooking().getGuestsList() != null) {
						if (c.getBooking().getGuestsList().size() > 0) {
							this.actualCustomers.setText(
									this.actualCustomers.getText() + "" + c.getBooking().getGuestsList().size());
							for (Guest g : c.getBooking().getGuestsList()) {
								if (!(g instanceof Customer)) {
									sb.append(g.toString());
								}
							}
						}
					}
				}
			}
			this.customersList.setText(new String(sb));
		}

		this.add(numRoom);
		this.add(typeOf);
		this.add(roomStatus);
		this.add(customersList);
		this.add(actualCustomers);
		if (r.isBusyThisDay(date)) {
			this.add(extra);
			this.add(removeBooking);
			this.add(addGuest);
		}
		this.add(close);
		addGuest.addActionListener(e -> {
			new Guests(r.getBookingDay(date));
		});

		extra.addActionListener(e -> {
			new ExtraBooking(r.getBookingDay(date));
		});
		
		removeBooking.addActionListener(e-> {
			Hotel.getInstance().removeBooking(r.getBookingDay(date));
			rv.update(Controller.fromLocalDateToString(date));
			this.dispose();
		});
		close.addActionListener( e ->{
			this.dispose();
			
		});
	}

}
