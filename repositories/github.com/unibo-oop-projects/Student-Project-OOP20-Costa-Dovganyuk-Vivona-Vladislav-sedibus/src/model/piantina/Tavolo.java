package model.piantina;

public final class Tavolo  {
	
	private int name;
	private int maxPosti;
	
	public Tavolo(int name, int maxPosti) {
		this.name = name;
		this.maxPosti = maxPosti;
	}

	/**
	 * @return the name/id of the table
	 */
	public int getName() {
		return this.name;
	}

	/**
	 * @return the maximum seats that can be occupied
	 */
	public int getMaxPosti() {
		return this.maxPosti;
	}
	

}
