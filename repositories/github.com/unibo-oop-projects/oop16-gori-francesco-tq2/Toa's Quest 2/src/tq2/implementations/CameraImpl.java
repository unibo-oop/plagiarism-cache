package tq2.implementations;

import tq2.interfaces.Camera;
import tq2.interfaces.Entity;
import tq2.interfaces.Game;

/**
 * The Class CameraImpl implements the interface Camera.
 * 
 * @author Francesco Gori
 */
public class CameraImpl implements Camera {

	/** The X coordinate. */
	public Integer x;
	
	/** The Y coordinate. */
	public Integer y;
	
	/** The game the camera will move in. */
	public Game game;
	
	/**
	 * Instantiates a new Camera at coordinates 0, 0.
	 *
	 * @param game the Game the camera will move in
	 */
	public CameraImpl(Game game) {
		this.x = 0;
		this.y = 0;
		this.game = game;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Camera#follow(com.tq2.interfaces.Entity)
	 */
	@Override
	public void follow (Entity e) {
		//set the center of the camera where the target is, unless it's null
		if (e != null) {
			this.setX(e.getX() - (game.getGameWidth()/2) );
			this.setY(e.getY() - (game.getGameHeight()/2) );
		}
	}
	

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Camera#getX()
	 */
	@Override
	public Integer getX() {
		return this.x;
	}
	

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Camera#getY()
	 */
	@Override
	public Integer getY() {
		return this.y;
	}
	

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Camera#setX(java.lang.Integer)
	 */
	@Override
	public void setX(Integer x) {
		
		//the camera can't get outside of the level
		this.x = Math.max(0, Math.min(x,(this.game.getCurrentLevel().getWidth() - this.game.getGameWidth())));

	}
	

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Camera#setY(java.lang.Integer)
	 */
	@Override
	public void setY(Integer y) {

		//the camera can't get outside of the level
		this.y = Math.max(0, Math.min(y,(this.game.getCurrentLevel().getHeight() - this.game.getGameHeight())));
		
	}
}
