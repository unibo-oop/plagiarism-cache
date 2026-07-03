package characters;

import java.util.Optional;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class CharacterImpl implements Character {

	private static final double STANDARD_SPEED = 2.0;

	private double x;
	private double y;
	private double moveRateX;
	private double moveRateY;
	private boolean canMove = true;
	private Optional<Image> image;
	private Optional<ImageView> imageView;
	private double width;
	private double height;

	/**
	 * 
	 * @param position
	 */
	public CharacterImpl(final Position position) {
		this.x = position.getX();
		this.y = position.getY();
		this.moveRateX = STANDARD_SPEED;

	}

	/**
	 * 
	 * @param mrx
	 * It sets the rate at which a character moves horizontally
	 */
	public void setMoveRateX(double mrx) {
		this.moveRateX = mrx;
	}

	/**
	 * 
	 * @param mrx
	 * It sets the rate at which a character moves vertically
	 */
	public void setMoveRateY(final double mry) {
		this.moveRateY = mry;
	}

	/**
	 * 
	 * @return moveRatex
	 */
	public double getMoveRateX() {
		return this.moveRateX;
	}

	/**
	 * 
	 * @return moveRateY
	 */
	public double getMoveRateY() {
		return this.moveRateY;
	}

	/**
	 * 
	 * @return x
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * 
	 * @return y
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * 
	 * @param x
	 * It sets position (x)
	 */
	public void setX(final double x) {
		this.x = x;
	}

	/**
	 * 
	 * @param x
	 * It sets position (y)
	 */
	public void setY(final double y) {
		this.y = y;
	}

	/**
	 * 
	 * @return canMove
	 * It indicates if character can move or not
	 */
	public boolean charCanMove() {
		return this.canMove;
	}

	@Override
	public PositionImpl getPosition() {
		return new PositionImpl(this.x, this.y);
	}

	/**
	 * If character can move x=y=0 (position), else It moves at a certain speed (across x) from right to left  
	 */
	public void move() {

		// if, for any reasons, a character can't move, its coordinates will be 0
		if (canMove == false) {
			this.x = 0;
			this.y = 0;
		} else {
			// the general movement will be on X axis and from left to right side
			this.x -= STANDARD_SPEED;
		}
	}

	/**
	 * 
	 * @return imageView.get()
	 */
	public ImageView getView() {
		return imageView.get();
	}

	/**
	 * 
	 * @param newImage
	 * It sets images, relative ImageView and its width and height.
	 * Furthermore, it sets the image for the character
	 * 
	 */
	public void setImage(final Image newImage) {
		this.image = Optional.ofNullable(newImage);
		this.imageView = Optional.ofNullable(new ImageView(this.image.get()));
		
		// to relocate the character's image on the game's environment
		this.imageView.get().relocate(this.x, this.y);
		this.width = this.image.get().getWidth();
		this.height = this.image.get().getHeight();
		this.imageView.get().setImage(this.image.get());
	}

	/**
	 * 
	 * @return image.get()
	 */
	public Image getImage() {
		return this.image.get();
	}

	/**
	 * 
	 * @return imageView.get()
	 */
	public ImageView getImageView() {
		return this.imageView.get();
	}

	/**
	 * 
	 * @param newImage
	 */
	public void setImageView(final Image newImage) {
		this.imageView.get().setImage(newImage);
	}

	/**
	 * its communicates with the view to relocate the image if there is a movement
	 */
	public void updateUI() {
		this.imageView.get().relocate(this.x, this.y);
	}

	/**
	 * 
	 * @return width
	 */
	public double getWidth() {
		return this.width;
	}

	/**
	 * 
	 * @return height
	 */
	public double getHeight() {
		return this.height;
	}

	/**
	 * 
	 * @param width
	 */
	public void setWidth(final double width) {
		this.width = width;
	}

	/**
	 * 
	 * @param height
	 */
	public void setHeight(final double height) {
		this.height = height;
	}

	/**
	 * 
	 * @return x + width * 0.5
	 * It sets the character at the center
	 */
	public double getCenterX() {
		return this.x + this.width * 0.5;
	}

	public double getCenterY() {
		return this.y + this.height * 0.5;
	}

}
