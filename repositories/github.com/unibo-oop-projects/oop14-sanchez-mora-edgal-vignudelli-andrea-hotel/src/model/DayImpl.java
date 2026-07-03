package model;

import java.io.Serializable;

import model.interfaces.Day;

public class DayImpl implements Day, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5178243691098848421L;
	private Month month;
	private int day;
	private int year;

	public DayImpl(int day, Month month, int year) {
		super();
		this.month = month;
		if (day < 1 || day > month.getDays(year)) {
			throw new IllegalArgumentException("day illegal");
		}
		this.day = day;
		this.year = year;
	}

	@Override
	public Month getMonth() {
		// TODO Auto-generated method stub
		return month;
	}

	@Override
	public int getDays() {
		// TODO Auto-generated method stub
		return day;
	}

	@Override
	public int getYear() {
		// TODO Auto-generated method stub
		return year;
	}

	@Override
	public Day next() {
		if (this.day < month.getDays(year)) {
			return new DayImpl(this.day + 1, this.month, this.year);
		}
		if (month != Month.DECEMBER) {
			return new DayImpl(1, Month.values()[this.month.ordinal()+1],
					this.year);
		}
		return new DayImpl(1, Month.JANUARY, this.year + 1);
	}
	
	@Override
	public int compareTo(Day arg0) {
		if (this.equals(arg0)){
			return 0;
		}
		if (this.year < arg0.getYear()){
			return -1;
		} 
		if (this.year == arg0.getYear() && this.month.ordinal() < arg0.getMonth().ordinal()){
			return -1;
		}
		if (this.year == arg0.getYear() && this.month.ordinal() == arg0.getMonth().ordinal() && this.day < arg0.getDays()){ 
			return -1;
		}
		return 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DayImpl other = (DayImpl) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
}
