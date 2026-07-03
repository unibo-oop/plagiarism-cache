/*Questa Gui gestisce l'inserimento nel sistema delle prenotazioni relative alle camere dell'hotel
 * Innanzitutto, il sistema richiede in input il periodo e il tipo di stanza. Una volta controllata la disponibilità
 * della camera, permette l'inserimento degli altri dati, quali tipo di pernottamento e dati del richiedente.
 * 
 */
package vieww;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.Booking;
import model.BookingType;
import model.Customer;
import model.Hotel;
import model.Room;
import model.RoomType;

// TODO: Auto-generated Javadoc
/**
 * The Class BookingManagement.
 */
public class BookingManagement extends JFrame implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5811804155432428600L;
	
	/** The available room. */
	
	private final JPanel availableRoom = new JPanel();
	
	/** The customer name. */
	private final JLabel customerName = new JLabel("Nome Titolare: ");
	
	/** The customer name text. */
	private final JTextField customerNameText = new JTextField();
	
	/** The customer surname. */
	private final JLabel customerSurname = new JLabel("Cognome titolare: ");
	
	/** The customer surname text. */
	private final JTextField customerSurnameText = new JTextField();
	
	/** The customer fiscal code. */
	private final JLabel customerCF = new JLabel("Codice Fiscale: ");
	
	/** The customer fiscal code text. */
	private final JTextField customerCFText = new JTextField();
	
	/** The customer birth of day . */
	private final JLabel customerBr = new JLabel("Data di nascita: ");
	
	/** The customer birth of day text. */
	private final JTextField customerBrText = new JTextField();
	
	/** The booking type label. */
	private final JLabel bookingTypeLabel = new JLabel("Inserisci il tipo di prenotazione: ");
	
	/** The type of  booking. */
	private Choice typeBooking = new Choice();
	
	/** The deposit label. */
	private final JLabel depositLabel = new JLabel("Inserire la caparra: ");
	
	/** The deposit. */
	private final JTextField deposit = new JTextField();
	
	/** The panel. */
	private final JPanel panel = new JPanel();
	
	/** The label of start date . */
	private final JLabel start = new JLabel("Data inizio: ");
	
	/** The start text. */
	private final JTextField startText = new JTextField();
	
	/** The endz. */
	private final JLabel end = new JLabel("Data fine:");
	
	/** The end text. */
	private final JTextField endText = new JTextField();
	
	/** The n guests. */
	private final JLabel nGuests = new JLabel("Numero ospiti adulti: ");
	
	/** The n guests text. */
	private final JTextField nGuestsText = new JTextField();
	
	/** The n guests child. */
	private final JLabel nGuestsChild = new JLabel("Numero ospiti bambini: ");
	
	/** The n guests child text. */
	private final JTextField nGuestsChildText = new JTextField();
	
	/** The n guests baby. */
	private final JLabel nGuestsBaby = new JLabel("Numero ospiti neonati: ");
	
	/** The n guests baby text. */
	private final JTextField nGuestsBabyText = new JTextField();
	
	/** The type. */
	private final JLabel type = new JLabel("Tipo: ");
	
	/** The choice. */
	private final Choice choice = new Choice();
	
	/** The confirm. */
	private final JButton confirm = new JButton("Aggiungi");
	
	/** The check. */
	private final JButton check = new JButton("Controlla stanza");
	
	/** The main label. */
	private final JLabel mainLabel = new JLabel();
	
	/**
	 * Instantiates a new booking management.
	 *
	 * @param rv the rv
	 */
	BookingManagement(RoomView rv) {
		
		this.getContentPane().add(mainLabel);
		mainLabel.setIcon(new ImageIcon("res/booking.jpg"));
		mainLabel.setLayout(new FlowLayout());
		this.setSize(1280, 800);
		this.setLayout(new GridLayout());
		this.setTitle("Inserimento Prenotazione");
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(start);
		panel.add(startText);
		startText.setMaximumSize(new Dimension(100, 200));
		panel.add(end);
		endText.setMaximumSize(new Dimension(100, 200));
		panel.add(endText);
		panel.add(nGuests);
		panel.add(nGuestsText);
		nGuestsText.setMaximumSize(new Dimension(100, 200));
		panel.add(nGuestsChild);
		panel.add(nGuestsChildText);
		nGuestsChildText.setMaximumSize(new Dimension(100, 200));
		panel.add(nGuestsBaby);
		nGuestsBabyText.setMaximumSize(new Dimension(100, 200));
		panel.add(nGuestsBabyText);
		panel.add(type);
		choice.add("---");
		choice.add("Suite");
		choice.add("Premium");
		choice.add("Standard");
		choice.setMaximumSize(new Dimension(100, 200));
		panel.add(choice);
		mainLabel.add(panel);
		typeBooking.add("Solo pernottamento");
		typeBooking.add("Solo colazione");
		typeBooking.add("Mezza pensione");
		typeBooking.add("Pensione completa");
		typeBooking.setMaximumSize(new Dimension(80, 500));
		availableRoom.setLayout(new BoxLayout(availableRoom, BoxLayout.Y_AXIS));
		availableRoom.add(customerName);
		availableRoom.add(customerNameText);
		availableRoom.add(customerSurname);
		availableRoom.add(customerSurnameText);
		availableRoom.add(customerCF);
		availableRoom.add(customerCFText);
		availableRoom.add(customerBr);
		availableRoom.add(customerBrText);
		availableRoom.add(depositLabel);
		availableRoom.add(deposit);
		deposit.setMaximumSize(new Dimension(100, 200));
		availableRoom.add(bookingTypeLabel);
		availableRoom.add(typeBooking);
		availableRoom.setVisible(true);
		panel.add(check);
		panel.setOpaque(false);
		availableRoom.setOpaque(false);
		mainLabel.add(availableRoom);
		availableRoom.setVisible(false);
		customerNameText.setMaximumSize(new Dimension(100, 200));
		customerSurnameText.setMaximumSize(new Dimension(100, 200));
		customerCFText.setMaximumSize(new Dimension(100, 200));
		customerBrText.setMaximumSize(new Dimension(100, 200));
		confirm.addActionListener(e -> {
			if (!Controller.checkIfContainsNumber(customerNameText.getText())) {
				if (!Controller.checkIfContainsNumber(customerSurnameText.getText())) {
					if (customerCFText.getText().length() == 16 && !customerCFText.getText().contains(" ")) {
						if (Controller.formatLocalDate(customerBrText.getText()) != null) {
							if (LocalDate.now().isBefore(Controller.formatLocalDate(startText.getText()))) {
								BookingType bt;
								if (typeBooking.getSelectedIndex() == 0) {
									bt = BookingType.OVERNIGHT;
								} else if (typeBooking.getSelectedIndex() == 1) {
									bt = BookingType.BB;
								} else if (typeBooking.getSelectedIndex() == 2) {
									bt = BookingType.HALFBOARD;
								} else {
									bt = BookingType.FULLBOARD;
								}
								Booking bk = new Booking(bt,
										Hotel.getInstance().getCatalog()
												.getSeason(Controller.formatLocalDate(startText.getText())),
										Controller.formatLocalDate(startText.getText()),
										Controller.formatLocalDate(endText.getText()), new Double(0),
										this.getOptimalRoom(), Integer.parseInt(nGuestsText.getText()),
										Integer.parseInt(nGuestsChildText.getText()),
										Integer.parseInt(nGuestsBabyText.getText()));
								Customer cust = new Customer(this.customerNameText.getText(),
										this.customerSurnameText.getText(), this.customerCFText.getText(),
										Controller.formatLocalDate(this.customerBrText.getText()), bk);
								Controller.getInstance().createBooking(cust, bk, this.getOptimalRoom());
								this.dispose();
								rv.update(rv.getActualDay());
							}
						}
					}
				}
			}
			this.dispose();
		});
		availableRoom.add(confirm);
		panel.setVisible(true);
		check.addActionListener(e -> {
			if (Controller.formatLocalDate(startText.getText()).isAfter(LocalDate.now())
					|| Controller.formatLocalDate(startText.getText()).isEqual(LocalDate.now())) {
				if (Controller.formatLocalDate(endText.getText()).isAfter(LocalDate.now())) {
					if (Controller.formatLocalDate(startText.getText())
							.isBefore(Controller.formatLocalDate(endText.getText()))) {
						if (Controller.checkIfIsNumber(nGuestsText.getText())
								&& Controller.checkIfIsNumber(nGuestsChildText.getText())
								&& Controller.checkIfIsNumber(nGuestsBabyText.getText())) {
							if (Integer.parseInt(nGuestsText.getText()) > 0
									&& Integer.parseInt(nGuestsText.getText()) < 5
									&& Integer.parseInt(nGuestsChildText.getText()) >= 0
									&& Integer.parseInt(nGuestsChildText.getText()) < 5
									&& Integer.parseInt(nGuestsBabyText.getText()) >= 0
									&& Integer.parseInt(nGuestsBabyText.getText()) < 5) {
								if (this.getOptimalRoom() != null) {
									availableRoom.setVisible(true);
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Errore", "Numero ospiti non valido!!",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null, "Errore", "Data non valida!!!", JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Errore", "Data non valida!!!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Errore", "Data non valida!!!", JOptionPane.ERROR_MESSAGE);
			}

		});
		this.setVisible(true);
		this.add(mainLabel);
		this.pack();
	}

	/**
	 * Gets the optimal room.
	 *
	 * @return the optimal room
	 */
	private Room getOptimalRoom() {
		Set<Room> set = new HashSet<>();
		Room room = null;
		if (choice.getSelectedItem().equals("Premium")) {
			if (Controller.formatLocalDate(startText.getText()) != null
					&& Controller.formatLocalDate(endText.getText()) != null) {
				set = Controller.getInstance().getFilteredBookingResults(
						Controller.formatLocalDate(startText.getText()), Controller.formatLocalDate(endText.getText()),
						Integer.parseInt(nGuestsText.getText()), RoomType.PREMIUM);
				if (set.size() > 0) {
					room = Controller.selectOptimalRoom(set);
				} else {
					JOptionPane.showMessageDialog(null, "ATTENZIONE", "Stanza non disponibile",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		} else if (choice.getSelectedItem().equals("Standard")) {
			if (Controller.formatLocalDate(startText.getText()) != null
					&& Controller.formatLocalDate(endText.getText()) != null) {
				set = Controller.getInstance().getFilteredBookingResults(
						Controller.formatLocalDate(startText.getText()), Controller.formatLocalDate(endText.getText()),
						Integer.parseInt(nGuestsText.getText()), RoomType.STANDARD);
				if (set.size() > 0) {
					room = Controller.selectOptimalRoom(set);
				} else {
					JOptionPane.showMessageDialog(null, "ATTENZIONE", "Stanza non disponibile",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		} else if (choice.getSelectedItem().equals("Suite")) {
			if (Controller.formatLocalDate(startText.getText()) != null
					&& Controller.formatLocalDate(endText.getText()) != null) {
				set = Controller.getInstance().getFilteredBookingResults(
						Controller.formatLocalDate(startText.getText()), Controller.formatLocalDate(endText.getText()),
						Integer.parseInt(nGuestsText.getText()), RoomType.SUITE);
				if (set != null) {
					room = Controller.selectOptimalRoom(set);
					availableRoom.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "ATTENZIONE", "Stanza non disponibile",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		} else {
			if (Controller.formatLocalDate(startText.getText()) != null
					&& Controller.formatLocalDate(endText.getText()) != null) {
				set = Controller.getInstance().getBookingResults(Controller.formatLocalDate(startText.getText()),
						Controller.formatLocalDate(endText.getText()), Integer.parseInt(nGuestsText.getText()));
				if (set != null) {
					room = Controller.selectOptimalRoom(set);
				} else {
					JOptionPane.showMessageDialog(null, "ATTENZIONE", "Stanza non disponibile",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		return room;
	}

}