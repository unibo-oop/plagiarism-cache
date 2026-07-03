package elements;

import java.awt.Graphics2D;

import resources.LoadResources;

/**
 * This class implements {@link Element}.
 * This Abstract Class include all the principal attributes and methods that an
 * Element must have to be localized on map, and orient the sprite in the
 * correct side. 
 * 
 * @author Luca
 */
public abstract class ElementImpl implements Element {

	public static final boolean LEFT = false;
	public static final boolean RIGHT = true;	
	protected static final int ZERO = 0;
	protected boolean turnedSide;
	private final LoadResources resource;
	private int x;
	private int y;

	/**
	 * Build all the attributes of the Element.
	 * 
	 * @param x
	 *            a int value for X coordinate of Element.
	 * @param y
	 *            a int value for Y coordinate of Element.
	 */
	public ElementImpl(final int x, final int y) {
		this.x = x;
		this.y = y;
		this.turnedSide = RIGHT;
		this.resource = LoadResources.getInstance();
	}

	@Override
	public LoadResources getResources() {
		return this.resource;
	};

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setX(final int value) {
		this.x = value;
	}

	@Override
	public void setY(final int value) {
		this.y = value;
	}

	@Override
	public boolean isTurnedLeft() {
		return this.turnedSide == LEFT;
	}

	@Override
	public boolean isTurnedRight() {
		return this.turnedSide == RIGHT;
	}

	@Override
	public void turn(final boolean turnLeft, final boolean turnRight) {
		if (this.isTurnedRight() && turnLeft) {
			this.turnedSide = this.isTurnedRight() ? LEFT : RIGHT;
		}
		if (this.isTurnedLeft() && turnRight) {
			this.turnedSide = this.isTurnedLeft() ? RIGHT : LEFT;
		}
	}

	/**
	 * Call the relative methods to update the state of this Element.
	 */
	abstract void update();

	/**
	 * This method add elements ready to be print in Graphics2D.
	 * 
	 * @param g
	 *            a Graphics2D where print the graphic resource of Element.
	 */
	abstract void print(Graphics2D g);

	@Override
	public String toString() {		
		return "[ Element ]" + "\t[ X: " + this.getX() + " ]\t[ Y: " + this.getY() + " ]";
	}
}
