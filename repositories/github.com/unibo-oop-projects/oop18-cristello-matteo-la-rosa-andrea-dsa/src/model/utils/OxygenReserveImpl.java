package model.utils;
/**
 * this class is userful for game play level of oxygen storage pourpose.
 */
public class OxygenReserveImpl implements OxygenReserve {

	private static final int FULLOXYGEN = 25;
	private int value = FULLOXYGEN;
	
	/**
	 * this method restore full oxygen in the reserve of the game
	 */
	@Override
	public void resetOxygen() {
		
		this.value = FULLOXYGEN;
		
	}

	/**
	 * this method decrease an amount of oxygen from the reserve.
	 * @param decreaseQuantity
	 */
	@Override
	public void decreaseOxygen(final int decreaseQuantity) {
		
		this.value -= decreaseQuantity;
		
	}

	/**
	 * this method return the total amount of oxygen in the reserve.
	 */
	@Override
	public int getOxygen() {
		
		return this.value;
	}

	/**
	 * this method return the class of the object
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		
		return this.getOxygen() <= 0;
	}
	

	public final String toString() {
		
		return "Oxygen Reserve of Deep Sea Adventure equals to:" +this.getOxygen();
	}
	
	

}
