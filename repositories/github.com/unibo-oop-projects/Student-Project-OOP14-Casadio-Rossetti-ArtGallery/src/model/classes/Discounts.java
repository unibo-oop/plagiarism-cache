package model.classes;

/**
 * This enumeration collects all the possible discounts applicable to the tickets.
 * @author Sofia Rosetti
 *
 */
public enum Discounts {
	
	/**
	 * No discount.
	 */
	NODISCOUNT("Nessuno", 0),
	/**
	 * Every student has a 40% discount.
	 */
	STUDENT("Studente", 40),
	/**
	 * Every disabled or handicapped person has a 70% discount.
	 */
	HANDICAP("Disabile", 70),
	/**
	 * Every person with more than 60 years has a 25% discount.
	 */
	OVER60("Over 60", 25),
	/**
	 * Every child with less than 10 years has a 30% discount.
	 */
	UNDER10("Under 10", 30);
	
	
	private final String desc;
	private final double perc;
	
	private Discounts(final String newDesc, final double newPerc) {
		this.desc = newDesc;
		this.perc = newPerc;
	}
	
	/**
	 * This method gets the description of the discount.
	 * 
	 * @return the discount description
	 */
	public String getDescription() {
		return this.desc;
	}
	
	/**
	 * This method gets the percentage of the discount.
	 * 
	 * @return the discount percentage
	 */
	public double getPercentage() {
		return this.perc;
	}

}
