package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import model.interfaces.IRoom;

public class Room implements IRoom, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1095691499913769657L;
	private final int number;
	private RoomType type;
	private final int maxGuestsNumber;
	private List<Customer> customersList;
	private Customer actualCustomer = null;

	public Room(final int number, final RoomType type, final int maxGuestsNumber) {
		this.number = number;
		this.type = type;
		this.customersList = new LinkedList<>();
		this.maxGuestsNumber = maxGuestsNumber;
	}

	public Integer getNumber() {
		return new Integer(this.number);
	}

	public Integer getMaxGuestsNumber() {
		return new Integer(this.maxGuestsNumber);
	}

	public RoomType getType() {
		return this.type;
	}

	public List<Customer> getCustomerList() {
		return this.customersList;
	}

	public void addCustomer(final Customer customer) {
		this.customersList.add(customer);
		Collections.sort(this.customersList, new Comparator<Customer>() {
			public int compare(Customer c1, Customer c2) {
				return c1.getBooking().getStartDate().compareTo(c2.getBooking().getStartDate());
			}
		});
	}

	public void deleteCustomer(final Customer customer) {
		this.customersList.remove(customer);
	}

	public Customer getActualCustomer() {
		return this.actualCustomer;
	}

	public Customer getNextCustomer() {
		boolean next = false;
		for (Customer c : this.customersList) {
			if (next == true)
				return c;
			if (this.actualCustomer.equals(c)) {
				next = true;
			}
		}
		return null;
	}

	public void setActualCustomer(final Customer customer) {
		this.actualCustomer = customer;
		this.customersList.add(customer);
	}

	public void deleteActualCustomer() {
		this.actualCustomer = null;
	}

	public boolean isBusy() {
		if (actualCustomer == null)
			return false;
		return true;
	}

	public boolean isBusyThisDay(final LocalDate date) {
		if (this.getCustomerList() != null) {
			if (this.getCustomerList().size() > 0) {
				for (Customer c : this.getCustomerList()) {
					if ((c.getBooking().getStartDate().isBefore(date) && c.getBooking().getEndDate().isAfter(date))
							|| c.getBooking().getStartDate().isEqual(date))
						return true;
				}
			}
			return false;
		} else
			return false;
	}

	public Booking getBookingDay(LocalDate date) {
		if (isBusyThisDay(date)) {
			for (Customer c : this.getCustomerList()) {
				if ((c.getBooking().getStartDate().isBefore(date) && c.getBooking().getEndDate().isAfter(date))
						|| c.getBooking().getStartDate().isEqual(date)) {
					return c.getBooking();
				}
			}
		}
		return null;
	}

	public String toString() {
		return "[ROOM]: " + this.getNumber();
	}
}
