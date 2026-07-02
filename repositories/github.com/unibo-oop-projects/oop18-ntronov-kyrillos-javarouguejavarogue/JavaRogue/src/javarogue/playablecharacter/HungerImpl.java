package javarogue.playablecharacter;

public class HungerImpl implements Hunger{

	private static final int MAX_HUNGER = 100;
	private int hungerLevel;
	
	public HungerImpl() {
		this.hungerLevel = MAX_HUNGER;
	}
	@Override
	public int getCurrentHunger() {
		return this.hungerLevel;
	}

	@Override
	public void decrease() {
		if (this.hungerLevel > 0)
			this.hungerLevel--;
	}

	@Override
	public void addFood(int toAdd) {
		final int uncappedReturnHungerLevel = this.hungerLevel + toAdd;
		this.hungerLevel = uncappedReturnHungerLevel > MAX_HUNGER ? MAX_HUNGER : uncappedReturnHungerLevel;
	}
	@Override
	public void refill() {
		this.hungerLevel = MAX_HUNGER;
		
	}

}
