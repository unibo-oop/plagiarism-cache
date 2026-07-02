package hollowmen.view.juls.buttons;

import java.awt.Graphics;
import java.awt.Image;

import hollowmen.view.UtilitySingleton;

/**
 * The {@code ArrowButton} allows to draw on screen a button "arrow-shaped"
 * facing left or right.
 * @author Juls
 */
public class ArrowButton extends TranslucentButton {

	private static final long serialVersionUID = 2261116517867429353L;
	private Image rArrow;
	private Image rArrowOver;
	private Image rArrowNA;
	private Image lArrow;
	private Image lArrowOver;
	private Image lArrowNA;
	private Direction direction;
	
	public ArrowButton(Direction d) {
		super.setPreferences();
		this.setDirection(d);
		this.loadImages();
	}
	
	@Override
	protected void loadImages() {
		rArrow = UtilitySingleton.getInstance().getStorage().get("RArrow").getImage();
		rArrowOver = UtilitySingleton.getInstance().getStorage().get("RArrowOver").getImage();
		rArrowNA = UtilitySingleton.getInstance().getStorage().get("RArrowNA").getImage();
		lArrow = UtilitySingleton.getInstance().getStorage().get("LArrow").getImage();
		lArrowOver = UtilitySingleton.getInstance().getStorage().get("LArrowOver").getImage();
		lArrowNA = UtilitySingleton.getInstance().getStorage().get("LArrowNA").getImage();
	}
	
	/**
	 * The {@code setDirection} method declares the facing of the ArrowButton needed.
	 * @param d
	 */
	protected void setDirection(Direction d) {
		this.direction = d;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (direction.equals(Direction.LEFT)) {
			g.drawImage(lArrow, 0, 0, null);
			if(isOver) {
			g.drawImage(lArrowOver, 0, 0, null);
			}
			if(!isAvailable) {
				g.drawImage(lArrowNA, 0, 8, null);
			}
		} else {
			g.drawImage(rArrow, 0, 8, null);
			if(isOver) {
				g.drawImage(rArrowOver, 0, 8, null);
			}
			if(!isAvailable) {
				g.drawImage(rArrowNA, 0, 8, null);
			}
		}
		super.paintComponent(g);
	}
}
