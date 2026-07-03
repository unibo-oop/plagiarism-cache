package elements;

import java.awt.Graphics2D;

import resources.LoadResources;
import view.ViewImpl;

/**
 * This Class extend {@link ElementImpl}. It represent the Background of this
 * game, It include all the attributes and the static sprite presents in the
 * map. The position coordinates of Background will be updated when the
 * {@link Player} moves on map. It is formed by two images, the sKy (static) and
 * the background (dynamic).
 * 
 * @author Luca
 */
public class Background extends ElementImpl {

	private static final int WORLD_LIMIT_LEFT = (ViewImpl.WIDTH / ViewImpl.SCALE) / 2 - (LoadResources.SP_WIDTH);
	private static final int WORLD_LIMIT_RIGHT = LoadResources.MAP_WIDTH_LARGE - WORLD_LIMIT_LEFT - (LoadResources.SP_WIDTH * ViewImpl.SCALE);

	private final int worldLimitLeft;
	private final int worldLimitRight;

	/**
	 * Build all the attributes of Background. It also It also get the instance
	 * of the {@link Player}.
	 * 
	 * @param x
	 *            a int value for X coordinate of Background.
	 * @param y
	 *            a int value for Y coordinate of Background.
	 */
	public Background(final int x, final int y) {
		super(x, y);
		this.worldLimitLeft = WORLD_LIMIT_LEFT;
		this.worldLimitRight = WORLD_LIMIT_RIGHT;
	}

	/**
	 * World left limit.
	 * 
	 * @return a int value who is the limit left that the {@link Player} can't
	 *         surpass.
	 */
	public int getWorldLimitLeft() {
		return worldLimitLeft;
	}

	/**
	 * World right limit.
	 * 
	 * @return a int value who is the limit left that the {@link Player} can't
	 *         surpass.
	 */
	public int getWorldLimitRight() {
		return worldLimitRight;
	}

	/**
	 * Move the background to opposite direction respect the {@link Player} to
	 * simulate the movement on map.
	 * 
	 * @param value
	 *            a int value to subtract from current X coordinate of
	 *            Background.
	 */
	public void moveBackground(final int value) {
		super.setX(super.getX() - value);
	};

	@Override
	public void update() {
	}

	@Override
	public void print(Graphics2D g) {
		g.drawImage(super.getResources().getSky(), ZERO, ZERO, null);
		g.drawImage(super.getResources().getBackground(), super.getX(), super.getY(), null);
	}

	/**
	 * Background informations.
	 * 
	 * @return a String with all the principal information about Background.
	 */
	@Override
	public String toString() {
		return "[ BACKGROUND ]" + "\t[ X: " + this.getX() + " ]\t[ Y: " + this.getY() + " ]";
	}
}
