package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import controller.Controller;
import model.interfaces.IBooking;

public class Booking implements IBooking, Serializable {
	private static final long serialVersionUID = -3689255814451312786L;
	private final Room room;
	private LocalDate startDate;
	private LocalDate endDate;
	private final BookingType bType;
	private final SeasonType sType;
	private final List<Guest> guestsList;
	private final Set<Extra> extraList;
	private Double price;
	private  Double payed;
	private Boolean hasEaten;
	private LocalDate today;
	private Double caparra;
	private int declaredAdults;
	private int declaredChildren;
	private int declaredBabies;

	public Booking(final BookingType bType, final SeasonType sType, final LocalDate startDate, final LocalDate endDate,
			final Double caparra, Room r, int declaredAdults, int declaredChildren, int declaredBabies) {
		this.bType = bType;
		this.sType = sType;
		this.room = r;
		this.startDate = startDate;
		this.endDate = endDate;
		this.guestsList = new ArrayList<>();
		this.extraList = new HashSet<>();
		pay(caparra);
		this.hasEaten = false;
		this.today = LocalDate.now();
		this.price = calculatePrice();
		this.declaredAdults = declaredAdults;
		this.declaredBabies = declaredBabies;
		this.declaredChildren = declaredChildren;
	}

	public Room getRoom() {
		return this.room;
	}

	public Double getPrice() {
		return this.price;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public List<Guest> getGuestsList() {
		return this.guestsList;
	}

	public BookingType getBookingType() {
		return this.bType;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public void setStartDate(final LocalDate date) {
		this.startDate = date;
		this.setPrice(calculatePrice());
	}

	public void setEndDate(LocalDate date) {
		this.startDate = date;
		this.setPrice(calculatePrice());
	}

	public boolean hasPayed() {
		return (this.payed == this.price);
	}

	public double getToPay() {
		return this.price - this.payed;
	}

	public void changeAmount(final double amount) {
		this.price += amount;
	}
	
	public void addActualCustomer(Customer c) {
		this.guestsList.add(c);
	}

	

	public Set<Extra> getExtraList() {
		return this.extraList;
	}

	public int getDeclaredAdults() {
		return this.declaredAdults;
	}

	public int getDeclaredBabies() {
		return this.declaredBabies;
	}

	public int getDeclaredChildren() {
		return this.declaredChildren;
	}
	
	public Double getDeposit() {
		return this.caparra;
	}

	private int getJourneyDays() {
		return Period.between(startDate, endDate).getDays();
	}

	public Customer getBookingCustomer() {
		for (Guest g : this.guestsList) {
			if (g instanceof Customer)
				return (Customer) g;
		}
		return null;
	}
	public void eat() {
		this.hasEaten = true;
	}

	public boolean canEat() {
		if (this.bType.equals(BookingType.BB) || this.bType.equals(BookingType.OVERNIGHT)) {
			return false;
		} else {
			if (bType.equals(BookingType.HALFBOARD) && this.hasEatenToday() == true) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean hasEatenToday() {
		return this.hasEaten;
	}
	public void pay(double amount) {
		this.payed = amount;
		/*
		 * StringBuilder sb = new StringBuilder( "[PAGAMENTO]: " +
		 * this.getRoom().toString() +
		 * this.getRoom().getActualCustomer().toString());
		 * Hotel.getInstance().addBalance(LocalDate.now(), new String(sb),
		 * amount, true);
		 */
	}

	public void addExtra(Extra extra) {
		this.extraList.add(extra);
		this.changeAmount(extra.getCost());
	}

	public void removeExtra() {
		for (Extra e : this.extraList) {
			this.changeAmount(-e.getCost());
		}
		this.extraList.clear();
	}

	public void updateEatenDate() {
		if (!today.equals(LocalDate.now())) {
			this.hasEaten = false;
			this.today = LocalDate.now();
		}
	}


	private double calculatePrice() {
		double price = Hotel.getInstance().getCatalog().getDay();
		if (sType.equals(SeasonType.LOWSEASON)) {

		} else if (sType.equals(SeasonType.MIDSEASON)) {
			price *= Hotel.getInstance().getCatalog().getMidSeasonOverPrice();
		} else {
			price *= Hotel.getInstance().getCatalog().getHighSeasonOverPrice();
		}
		if (bType.equals(BookingType.BB)) {
			price *= Hotel.getInstance().getCatalog().getBBOverPrice();
		} else if (bType.equals(BookingType.HALFBOARD)) {
			price *= Hotel.getInstance().getCatalog().getHalfBoardOverPrice();
		} else if (bType.equals(BookingType.FULLBOARD)) {
			price *= Hotel.getInstance().getCatalog().getFullBoardOverPrice();
		}
		if (this.room.getType().equals(RoomType.PREMIUM)) {
			price *= Hotel.getInstance().getCatalog().getPremiumPercentage();
		} else if (this.room.getType().equals(RoomType.SUITE)) {
			price *= Hotel.getInstance().getCatalog().getSuitePercentage();
		}
		// daily price for adult, which is the customer
		price *= declaredAdults;
		if (this.declaredChildren > 0) {
			price += (price * declaredChildren * Hotel.getInstance().getCatalog().getChildPercentage());
		}
		// ending price counting the journeydays;
		price *= this.getJourneyDays();
		return price;
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[BOOKING ROOM]: " + this.getRoom() + " [START/END DATE] :"
				+ Controller.fromLocalDateToString(this.getStartDate()) + "   "
				+ Controller.fromLocalDateToString(this.getEndDate()));
		return new String(sb);
	}
}
