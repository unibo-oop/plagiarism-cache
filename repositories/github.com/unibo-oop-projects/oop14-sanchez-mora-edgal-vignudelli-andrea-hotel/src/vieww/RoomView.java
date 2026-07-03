/*
 * Una GUI che mostra una panoramica generale delle info relative alle stanza occupate/libere in una deteminata
 * data
 */

package vieww;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.Controller;
import controller.DayController;
import controller.interfaces.IDayController;
import model.Hotel;
import model.Room;

// TODO: Auto-generated Javadoc
/**
 * The Class RoomView.
 */
public class RoomView extends JFrame implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6187260814882691836L;
	
	/** The add day buttons. */
	private final JButton piu = new JButton(">");
	
	/** The detract one day button. */
	private final JButton meno = new JButton("<");
	
	/**  The add one moth buttons. */
	private final JButton piuMesi = new JButton(">>");
	
	/** The detract one day button. */
	private final JButton menoMesi = new JButton("<<");
	
	/** The controller for the date. */
	private final IDayController controller = new DayController();
	
	/** The exit panel. */
	private final JPanel exitPanel = new JPanel();
	
	/** The back to home button . */
	private final JButton backToHome = new JButton("Torna alla Home");
	
	/** The panel room. */
	private final JPanel panelRoom = new JPanel();
	
	/** The search prenotationRoom. */
	private final JButton edit = new JButton("Cerca prenotazione");
	
	/** The booking panel. */
	private final JPanel bookingPanel = new JPanel();
	
	/**
	 * Instantiates a new room view.
	 */
	RoomView() {
		this.setTitle("Panoramica camere");
		this.setSize(1280, 800);
		this.getContentPane().setLayout(new BorderLayout());
		this.setVisible(true);
		JPanel calendarPanel = new JPanel();
		calendarPanel.setBorder(new TitledBorder("Data"));
		JLabel data = new JLabel(controller.getDateString());
		calendarPanel.add(menoMesi);
		calendarPanel.add(meno);
		calendarPanel.add(data);
		calendarPanel.add(piu);
		calendarPanel.add(piuMesi);
		menoMesi.addActionListener(e -> {
			controller.month(-1);
			data.setText(controller.getDateString());
			update(data.getText());
		});
		meno.addActionListener(e -> {
			controller.day(-1);
			data.setText(controller.getDateString());
			update(data.getText());
		});
		piu.addActionListener(e -> {
			controller.day(1);
			data.setText(controller.getDateString());
			update(data.getText());
		});
		piuMesi.addActionListener(e -> {
			controller.month(1);
			data.setText(controller.getDateString());
			update(data.getText());
		});
		calendarPanel.setSize(new Dimension(500, 100));
		this.add(calendarPanel, BorderLayout.NORTH);
		this.add(this.panelRoom, BorderLayout.CENTER);
		panelRoom.setLayout(new FlowLayout());
		
		this.panelRoom.setBorder(new TitledBorder("Stanze"));
		bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS));
		JButton addBooking = new JButton("Aggiungi Prenotazione");
		bookingPanel.add(addBooking);
		bookingPanel.add(edit);
		addBooking.addActionListener(e -> {
			new BookingManagement(this);
		});
		edit.addActionListener(e ->{
			new SearchBooking(this);
		});
		this.getContentPane().add(bookingPanel, BorderLayout.WEST);
		bookingPanel.setBorder(new TitledBorder("Gestione"));
		for (Room r : Hotel.getInstance().getRoomList()) {
			this.panelRoom.add(new ButtonRoom(r.getNumber(),
					r.isBusyThisDay(Controller.formatLocalDate(controller.getDateString())), r,
					Controller.formatLocalDate(controller.getDateString()),this));
		}
		exitPanel.add(backToHome);
		backToHome.addActionListener(e -> {
			this.dispose();
		});
		this.add(exitPanel, BorderLayout.SOUTH);
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Gets the actual day.
	 *
	 * @return the actual day
	 */
	public String getActualDay() {
		return this.controller.getDateString();
	}

	/**
	 * Update.
	 *
	 * @param date the date
	 */
	public void update(String date) {
		this.panelRoom.removeAll();
		for (Room r : Hotel.getInstance().getRoomList()) {
			this.panelRoom.add(new ButtonRoom(r.getNumber(), r.isBusyThisDay(Controller.formatLocalDate(date)), r,
					Controller.formatLocalDate(controller.getDateString()),this));
		}
		this.revalidate();
		this.repaint();
	}

}