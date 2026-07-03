package dodge;


import java.util.Optional;

import characters.CharacterImpl;
import characters.Position;
import javafx.scene.image.Image;

public class DodgeImpl extends CharacterImpl implements Dodge {

	private static final double BOUND_UP = 110;
	private static final double BOUND_DOWN = 618;

	private static final int INIT_LIFES = 5;

	private static final double DODGE_SPEED = 1.0;

	/* necessary wumpas to get an extra-life */
	private static final int MAX_WUMPAS = 10;

	/*
	 * starting wumpas after an extra-life (avoids magic numbers)
	 */
	private static final int NO_WUMPAS = 0;

	/*
	 * n. of extra-lives gained every MAX_WUMPAS wumpas (avoids magic numbers,
	 * again)
	 */
	private static final int EXTRA_LIFE = 1;

	private Input input;

	private Optional<Image> shieldedImage;
	private Optional<Image> normalImage;

	private boolean immunity;
	private int life;
	private int wumpas;

	public DodgeImpl(final Position position) {
		super(position);
		this.normalImage = Optional.empty();
		this.shieldedImage = Optional.empty();
		setMoveRateY(DODGE_SPEED);
		this.life = INIT_LIFES;
	}

	public void setInput(final Input input) {
		this.input = input;
	}

	public void processInput(Input input) {
		input = this.input;
		if (input.isMoveUp()) {
			setMoveRateY(getMoveRateY() - DODGE_SPEED);
		} else if (input.isMoveDown()) {
			setMoveRateY(getMoveRateY() + DODGE_SPEED);
		} else {
			setMoveRateY(getMoveRateY() + 0d);
		}
	}

	public int getLife() {
		return this.life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getWumpas() {
		return wumpas;
	}

	/*
	 * add a wumpa fruit and check if there's enough to get an extra life
	 */
	public void addWumpas() {
		/*
		 * asserting that wumpas < MAX_ WUMPAS everytime this method is called
		 */

		this.wumpas++;

		/*
		 * Dodge gets an extra life if all the necessary wumpas have been collected
		 */
		if (this.getWumpas() == MAX_WUMPAS) {

			this.wumpas = NO_WUMPAS; /* clear wumpas counter */
			this.setLife(this.getLife() + EXTRA_LIFE); /* get extra life */

		}
	}

	/*
	 * CURRENTLY UNUSED set the limits beyond which Dodge shouldn't go
	 * 
	 * public void setBounds(double upperBound, double lowerBound) {
	 * 
	 * playerShipMinY = lowerBound;
	 * 
	 * playerShipMaxY = upperBound;
	 * 
	 * }
	 */

	/**
	 * It verifies that Dodge doesn't pass through the bounds (up and down). 
	 * It uses "Double.compare" to compare the Dodge's y with BOUND_UP and with BOUND_DOWN
	 */
	private void checkBounds() {

		if (Double.compare(getY(), BOUND_UP) < 0) {
			setY(BOUND_UP);
		} else if (Double.compare(getY(), BOUND_DOWN) > 0) {
			setY(BOUND_DOWN);
		}

	}

	/**
	 * @return BOUND_UP
	 */
	public double getBoundUp() {
		return DodgeImpl.BOUND_UP;
	}

	/**
	 * @return BOUND_DOWN
	 */
	public double getBoundDown() {
		return DodgeImpl.BOUND_DOWN;
	}

	/**
	 * Dodge can move (or not) but It has to always verify if can do this between the limits
	 */
	public void move() {
		if (!charCanMove())
			return;

		setY(getY() + getMoveRateY());
		checkBounds();
	}

	/**
	 * 
	 */
	public boolean getImmunity() {
		return Boolean.valueOf(this.immunity);
	}

	/**
	 * 
	 */
	public void setImmunity() {
		this.immunity = true;
		if(this.shieldedImage.isPresent()) {
			this.setImageView(this.shieldedImage.get());
		}
		
	}

	/**
	 * 
	 */
	public void removeImmunity() {
		this.immunity = false;
		if(this.normalImage.isPresent()) {
			this.setImageView(this.normalImage.get());
		}
	}

	
	public void setImage(final Image newImage, final Image shieldImage) {
		super.setImage(newImage);
		this.normalImage = Optional.ofNullable(newImage);
		this.shieldedImage = Optional.ofNullable(shieldImage);
	}
}