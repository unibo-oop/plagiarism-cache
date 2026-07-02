package model.classes;

import java.io.Serializable;

import model.interfaces.IExhibitData;

/**
 * ExhibitData implementation.
 * 
 * @author Sofia Rosetti
 *
 */
public class ExhibitData implements IExhibitData, Serializable {
	
	private static final long serialVersionUID = -4146055545448415715L;
	private int availability;
	private double income;
	private static final int PRIME = 31;
	
	/**
	 * Constructor.
	 * @param tickets
	 *            the available tickets
	 * @param newIncome
	 *            the exhibit income
	 */            
	public ExhibitData(final int tickets, final double newIncome) {
		this.availability = tickets;
		this.income = newIncome;
	}
	
	@Override
	public int getAvailability() {
		return this.availability;
	}
	
	@Override
	public double getIncome() {
		return this.income;
	}
	
	@Override
	public void setAvailability(final int av) {
		this.availability = av;
	}
	
	@Override
	public void setIncome(final double inc) {
		this.income = inc;
	}
	
	@Override
	public String toString() {
		return "ExhibitData[availability=" + this.availability + ", income=" 
				+ this.income + "]\n";
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = PRIME * result + availability;
		long temp;
		temp = Double.doubleToLongBits(income);
		result = PRIME * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}			
		if (obj == null) {
			return false;
		}			
		if (getClass() != obj.getClass()) {
			return false;
		}			
		final ExhibitData other = (ExhibitData) obj;
		if (availability != other.availability) {
			return false;
		}			
		if (Double.doubleToLongBits(income) != Double
				.doubleToLongBits(other.income)) {
			return false;
		}		
		return true;
	}
}
