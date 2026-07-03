package characters;

public abstract class CrossingCharacter extends CharacterImpl implements Removable{
	
	private boolean removable = false;
	
	/**
	 * 
	 * @param position
	 */
	public CrossingCharacter(final Position position) {
		super(position);
	}

	/**
	 * It sets if the character is removable from the game or not
	 */
	public void setRemovable(final boolean removable) {
		this.removable = removable;
	}

	/**
	 * It indicates if the character is removable
	 */
	public boolean isRemovable() {
		return removable;
	}

	/**
	 * It removes the character
	 */
	public void remove() {
		setRemovable(true);
	}

	/**
	 * @param dodge
	 * It controls when Dodge collides with a character (when the coordinates will coincide)
	 */
	public boolean collidesWith(final CharacterImpl dodge) {
		return (dodge.getPosition().getX() + dodge.getWidth() >= this.getX()
				&& dodge.getPosition().getY() + dodge.getHeight() >= this.getY()
				&& dodge.getPosition().getX() <= this.getX() + this.getWidth()
				&& dodge.getPosition().getY() <= this.getY() + this.getHeight());
	}
	
}
