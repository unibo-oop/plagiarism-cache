
package controller;

/**
*
* Class for player control, where commands and movements rules are set up.
*
*@author Diego
*@author Andrea
*
*
*/

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.entity.components.RotationComponent;
import com.almasb.fxgl.entity.components.ViewComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import factory.MowerType;
import javafx.util.Duration;
import launcher.MowerApp;

import java.util.List;

public class PlayerControl extends Component {
	private PositionComponent position;
	private BoundingBoxComponent bbox;
	private ViewComponent view;
	private RotationComponent rotation;

	private MoveDirection moveDir = MoveDirection.UP;

	private AnimatedTexture texture;
	private AnimationChannel animIdleY, animWalkY;

	/**
	 *
	 * Set up player image and animations
	 *
	 */

	public PlayerControl() {

		animIdleY = new AnimationChannel("mower.png", 3, 40, 40, Duration.seconds(0.2), 0, 2);
		animWalkY = new AnimationChannel("mower.png", 3, 40, 40, Duration.seconds(0.2), 0, 2);
		texture = new AnimatedTexture(animIdleY);

	}

	/**
	 * Gets move direction from MoveDirection.java
	 *
	 * @return moveDir
	 */

	public MoveDirection getMoveDirection() {
		return moveDir;
	}

	private double speed = 0;

	/**
	 *
	 * @param tpf
	 *This method updates player position after a direction is defined
	 *
	 *
	 */
	@Override
	public void onUpdate(double tpf) {
		speed = tpf * 60;

		if (speed != 0) {

            if (texture.getAnimationChannel() == animIdleY) {
                texture.loopAnimationChannel(animWalkY);
            }
		}

		if (position.getX() < 0) {
			position.setX(MowerApp.BLOCK_SIZE * MowerApp.MAP_SIZE_X - bbox.getWidth() - 5);
		}

		if (bbox.getMaxXWorld() > MowerApp.BLOCK_SIZE * MowerApp.MAP_SIZE_X) {
			position.setX(0);
		}
	}

	/**
	 *
	 * Setting the right entity and his texture when the game starts
	 *
	 */

	@Override
	public void onAdded() {
	    entity.setView(texture);
	}

	/**
	 *
	 *
	 * Set up the "Turbo" mechanics that is a boost of the others direction buttons
	 *
	 */

	public void turbo() {

		speed = 1; //this is the turbo boost!

		switch (moveDir){

		case UP:
			move(0, -3 * speed);
			break;
		case DOWN:
			move(0, 3 * speed);
			break;
		case LEFT:
			move(-3 * speed, 0);
			break;
		case RIGHT:
			move(3 * speed, 0);
			break;
		}

	}

	/**
	 *
	 * This method gets the button UP call and moves player is the right direction
	 *
	 */

	public void up() {
		moveDir = MoveDirection.UP;
		move(0, -3 * speed);

		rotation.setValue(180);
		view.getView().setScaleX(1);
	}

	/**
	 *
	 * This method gets the button DOWN call and moves player is the right direction
	 *
	 */

	public void down() {
		moveDir = MoveDirection.DOWN;

		move(0, 3 * speed);

		rotation.setValue(0);
		view.getView().setScaleX(1);
	}

	/**
	 *
	 * This method gets the button LEFT call and moves player is the right direction
	 *
	 */

	public void left() {
		moveDir = MoveDirection.LEFT;

		move(-3 * speed,0);

		view.getView().setScaleX(-1);
		rotation.setValue(90);
	}

	/**
	 *
	 * This method gets the button RIGHT call and moves player is the right direction
	 *
	 */

	public void right() {
		moveDir = MoveDirection.RIGHT;

		move(3 * speed,0);

		view.getView().setScaleX(1);
		rotation.setValue(270);
	}


	private List<Entity> blocks;

	/**
	* This method verify that the movement requested by the player is possible or if there is a wall or an UNWALKABLE object
	 * @param dx
	 * @param dy
	 */

	private void move(double dx, double dy) {
		if (!getEntity().isActive())
			return;

		if (blocks == null) {
			blocks = FXGL.getApp().getGameWorld().getEntitiesByType(MowerType.BLOCK);
		}

		double mag = Math.sqrt(dx * dx + dy * dy);
		long length = Math.round(mag);

		double unitX = dx / mag;
		double unitY = dy / mag;

		for (int i = 0; i < length; i++) {
			position.translate(unitX, unitY);

			boolean collision = false;

			for (int j = 0; j < blocks.size(); j++) {
				if (blocks.get(j).getBoundingBoxComponent().isCollidingWith(bbox)) {
					collision = true;
					break;
				}
			}

			if (collision) {
				position.translate(-unitX, -unitY);
				break;
			}
		}
	}
}
