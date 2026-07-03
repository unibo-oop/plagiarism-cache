package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import model.interfaces.IHotel;

public class Hotel implements IHotel, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1032910340360807559L;
	private static final String path = "res/model.ser";
	private static Hotel myInstance;
	private Catalog catalog;
	private String name;
	private List<Room> roomList;
	private HashSet<Guest> guestsList;
	private List<Booking> bookingList;
	private List<Extra> extraList;
	private Double actualBalance;
	private DailyMenu menu;
	private HashMap<LocalDate, List<Pair<String, Double>>> incoming;
	private HashMap<LocalDate, List<Pair<String, Double>>> outcoming;

	private Hotel() {
		this.catalog = new Catalog();
		this.name = "fuffa";
		this.roomList = new LinkedList<>();
		this.guestsList = new HashSet<Guest>();
		this.bookingList = new ArrayList<Booking>();
		this.extraList = new LinkedList<Extra>();
		this.actualBalance = new Double(0);
		this.incoming = new HashMap<LocalDate, List<Pair<String, Double>>>();
		this.outcoming = new HashMap<LocalDate, List<Pair<String, Double>>>();
		this.menu = new DailyMenu();
	}

	public Catalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog(final Catalog catalog) {
		this.catalog = catalog;
	}

	public String getName() {
		return new String(this.name);
	}

	public HashSet<Guest> getCustomerList() {
		return this.guestsList;
	}

	public List<Room> getRoomList() {
		return this.roomList;
	}

	public List<Booking> getBookingList() {
		return this.bookingList;
	}

	public List<Extra> getExtraList() {
		return this.extraList;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void addGuest(final Guest guest) {
		this.guestsList.add(guest);
	}

	public void removeGuest(final Guest guest) {
		this.guestsList.remove(guest);
	}

	public void addRoom(final Room room) {
		this.roomList.add(room);
	}

	public void removeRoom(final Room room) {
		if (this.roomList.contains(room)) {
			this.roomList.remove(room);
		}
	}

	public void addBooking(final Booking booking) {
		if (!this.bookingList.contains(booking)) {
			this.bookingList.add(booking);
		}
	}

	public void removeBooking(final Booking booking) {
		if (this.bookingList.contains(booking)) {
			this.bookingList.remove(booking);
			Customer cust = null;
			for (Customer c : booking.getRoom().getCustomerList()) {
				if (c.getBooking().equals(booking)) {
					cust = c;
				}
			}
			Room room = null;
			for (Room r : this.getRoomList()) {
				if (r.getCustomerList().contains(cust)) {
					room = r;
				}
			}
			if (room != null && cust != null) {
				if (cust.getBooking().getGuestsList() != null) {
					if (cust.getBooking().getGuestsList().size() > 0) {
						for (Guest g : cust.getBooking().getGuestsList()) {
							removeGuest(g);
						}
						room.getCustomerList().remove(cust);
					}
				}
			}
		}
	}

	public void addExtra(final Extra extra) {
		if (!this.extraList.contains(extra)) {
			this.extraList.add(extra);
		}
	}

	public void removeExtra(final Extra extra) {
		if (this.extraList.contains(extra)) {
			this.extraList.remove(extra);
		}
	}

	public void addBalance(final LocalDate date, final String voiceOfChange, final double amount, final boolean isIncoming) {
		List<Pair<String, Double>> tmp = new LinkedList<Pair<String, Double>>();

		if (isIncoming == true) {
			if (this.incoming.containsKey(date)) {
				tmp = this.incoming.get(date);
			}
			tmp.add(new Pair<String, Double>(voiceOfChange, amount));
			this.incoming.put(date, tmp);
		} else {
			if (this.outcoming.containsKey(date)) {
				tmp = this.outcoming.get(date);
			}
			tmp.add(new Pair<String, Double>(voiceOfChange, amount));
			this.outcoming.put(date, tmp);
		}
		this.updateBalance(amount);
	}

	public void removeBalance(final String date, final int index, final boolean isIncoming) {
		if (isIncoming) {
			this.incoming.get(date).remove(index);
		} else {

			this.outcoming.get(date).remove(index);
		}
	}

	public HashMap<LocalDate, List<Pair<String, Double>>> getIncoming() {
		if (!this.incoming.isEmpty())
			return new HashMap<LocalDate, List<Pair<String, Double>>>(this.incoming);
		else
			return new HashMap<LocalDate, List<Pair<String, Double>>>();
	}

	public HashMap<LocalDate, List<Pair<String, Double>>> getOutcoming() {
		if (!this.outcoming.isEmpty())
			return new HashMap<LocalDate, List<Pair<String, Double>>>(this.outcoming);
		else
			return new HashMap<LocalDate, List<Pair<String, Double>>>();
	}

	public double getActualBalance() {
		return this.actualBalance;
	}

	public List<Guest> findSomeone(final LocalDate begin, final LocalDate end) {
		ArrayList<Guest> ar = new ArrayList<>();
		for (Guest g : this.guestsList) {
			if (g instanceof Customer) {
				if (((Customer) g).getBooking().getStartDate().compareTo(begin) > 0
						&& ((Customer) g).getBooking().getStartDate().compareTo(end) < 0) {
					ar.add(g);
					ar.addAll(((Customer) g).getBooking().getGuestsList());
				}
			}
		}
		return ar;
	}

	public List<Booking> findCustomer(final String name, final String surname) {
		ArrayList<Booking> ar = new ArrayList<>();
		for (Guest g : this.guestsList) {
			if (g instanceof Customer) {
				if ((((Customer) g).getBooking().getStartDate().isAfter(LocalDate.now()))) {
					ar.add(((Customer) g).getBooking());
				}
			}
		}
		if (ar.size() > 0)
			return ar;
		else
			return null;
	}

	public DailyMenu getDailyMenu() {
		return this.menu;
	}

	public List<Guest> findSomeone(final String name, final String surname) {
		ArrayList<Guest> ar = new ArrayList<>();
		for (Guest g : this.guestsList) {
			if (g.getName().equals(name) && g.getSurname().equals(surname)) {
				ar.add(g);
			}
		}
		return ar;
	}

	public String balanceVoiceToString(final String voice, double amount) {
		return "[VOCE]: " + voice + "\n[IMPORTO]: " + amount + "\n";
	}

	public String dailyBalanceVoices(final LocalDate date) {
		StringBuilder sb = new StringBuilder();
		sb.append("[DATA]: " + date.toString() + "\n");
		for (Pair<String, Double> hm : this.incoming.get(date)) {
			sb.append(balanceVoiceToString(hm.getKey(), hm.getValue()));
		}
		for (Pair<String, Double> hm : this.outcoming.get(date)) {
			sb.append(balanceVoiceToString(hm.getKey(), hm.getValue()));
		}
		return new String(sb);
	}

	public String intervalBalanceVoices(final LocalDate start, final LocalDate end) {
		StringBuilder sb = new StringBuilder();
		sb.append("[VOCI DAL " + start.toString() + " AL " + end.toString() + "]\n");
		for (LocalDate date : this.incoming.keySet()) {
			if (date.compareTo(start) <= 0 && date.compareTo(end) >= 0) {
				sb.append(dailyBalanceVoices(date) + "\n");
			}
		}
		return new String(sb);
	}

	public String totalBalanceVoices() {
		StringBuilder sb = new StringBuilder();
		sb.append("[VOCI TOTALI]\n");
		for (LocalDate date : this.incoming.keySet()) {
			sb.append(dailyBalanceVoices(date) + "\n");
		}
		return new String(sb);
	}

	public static void saveInstance() {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(myInstance);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Hotel getInstance() {

		if (myInstance == null) {
			try {
				FileInputStream fileIn = new FileInputStream(path);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				myInstance = (Hotel) in.readObject();
				in.close();
				fileIn.close();
			} catch (FileNotFoundException e) {
				File f = new File(path);
				f.getParentFile().mkdirs();
				try {
					f.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				myInstance = new Hotel();
			} catch (ClassNotFoundException c) {
				System.out.println("Class not found");
				c.printStackTrace();
				myInstance = new Hotel();

			} catch (EOFException ef) {
				myInstance = new Hotel();
			} catch (IOException i) {
				i.printStackTrace();
				myInstance = new Hotel();
			}
		}
		return myInstance;
	}

	private void updateBalance(final double amount) {
		this.actualBalance += amount;
	}

}