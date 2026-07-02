package boxhead.view.entities;

import boxhead.view.spriteutils.Sprite;

public interface ShotView {

	/**
	 * Used to set the direction of the shot sprite
	 * @param angle
	 */
	void setDirection(double angle);
	
	/**
	 * @return
	 * 			The sprite of the Shot
	 */
	Sprite getSprite();
}
