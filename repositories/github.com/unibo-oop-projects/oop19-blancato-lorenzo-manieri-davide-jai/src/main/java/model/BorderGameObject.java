package model;


/**
 * Class for entities who have to stay within window border
 *
 */
public interface BorderGameObject extends GameObject {
	
	/**
	 * 	move the entity within the GameWindow and stick it to border if the difference between
	 * 	the entity and the border position is less then deltaX
	 * 		@param deltaX
	 * 			horizontal movement delta
	 */
	public void checkMoveX(double deltaX);
	
	/**
	 * 	move the entity within the GameWindow and stick it to border if the difference between
	 * 	the entity and the border position is less then deltaY
	 * 		@param deltaY
	 * 			vertical movement delta
	 */
	public void checkMoveY(double deltaY);
}
