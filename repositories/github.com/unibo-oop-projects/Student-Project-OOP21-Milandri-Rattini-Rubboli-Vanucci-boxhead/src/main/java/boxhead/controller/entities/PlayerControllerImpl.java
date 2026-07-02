package boxhead.controller.entities;

import java.util.Set;

import boxhead.controller.game.GameLevel;
import boxhead.model.entities.Player;
import boxhead.view.entities.PlayerView;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class PlayerControllerImpl implements PlayerController {
	
	private final Player player;
	private final PlayerView playerView;
	private final InputHandler input;
	private final GameLevel gameLevel;
	public PlayerControllerImpl(final GameLevel gameLevel) {
		this.player=new Player();
		this.playerView=new PlayerView();
		this.playerView.getImageView();
		this.gameLevel=gameLevel;
		this.input=gameLevel.getInputHandler();
	}
	
	/**
     * {@inheritDoc}
     */
	public Player getPlayer() {
		return this.player;
	}

	/**
     * {@inheritDoc}
     */
	public PlayerView getPlayerView() {
		return this.playerView;
	}

	/**
     * {@inheritDoc}
     */
	public void update() {
		this.player.update();
		updateView();
	}
	
	/**
	 * Private method to update the view 
	 */
	private void updateView () {
		this.playerView.setPosition(this.player.getPosition());
		this.playerView.setDirection(this.player.getDirection());
		this.playerView.setGunType(this.player.getCurrentGun().getGunType());
	}

	/**
     * {@inheritDoc}
     */
	public void updateInput() {
		final Set<KeyCode> keys = this.input.getKeyPressed();
		if(!keys.isEmpty()) {
			this.player.checkCollision(PlayerInput.calculateDirection(keys));
		} else {
			this.player.setSpeed(Point2D.ZERO);
		}
		if(PlayerInput.shotInput(keys)) {
			this.player.getCurrentGun().attack(this.player.getPosition(), this.player.getDirection()).forEach(s -> this.gameLevel.getShotController().addShot(s));
		}
		if(PlayerInput.nextGun(keys)) {
			this.player.nextGun();
		}
		if(PlayerInput.previousGun(keys)) {
			this.player.previousGun();
		}
	}

}
