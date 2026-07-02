package boxhead.view.entities;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import boxhead.model.entities.EntityType;
import boxhead.model.entities.utils.Direction;
import boxhead.view.spriteutils.Sprite;
import boxhead.view.spriteutils.SpriteFactory;
import boxhead.model.entities.gun.Gun.GunType;

/**
 * View class of the player.
 */
public class PlayerView {
	
	private Point2D position;
	private Direction direction;
	private final EntityType type;
	private final Sprite playerSprite;
	private ImageView image;
	private GunType gunType;
	
	public PlayerView() {
		this.position=Point2D.ZERO;
		this.type=EntityType.PLAYER;
		this.gunType=GunType.PISTOL;
		this.direction=Direction.EAST;
		this.playerSprite=SpriteFactory.createSprite(EntityType.PLAYER);
		this.image=this.playerSprite.getImageView();
	}
	
	/**
	 * Return EntityType
	 * @return EntityType type
	 */
	public final EntityType getyType() {
		return this.type;
	}
	
	/**
	 * Set position of the view
	 * @param Point2D position
	 */
	public final void setPosition(final Point2D position) {
        this.position = position;
    }
	
	/**
	 * Get the position of the view
	 * @return Point2D 
	 */
	public final Point2D getPosition() {
        return this.position;
    }
	
	/**
	 * Set the direction for the sprite change
	 * @param Direction direction
	 */
	public final void setDirection(final Direction direction) {
        this.direction = direction;
    }
	
	/**
	 * Return the actual direction
	 * @return Direction 
	 */
	public final Direction getDirection() {
        return this.direction;
    }
	
	/**
	 * Set sprite position
	 */
	private void setSpritePosition() {
        this.image.setLayoutX(getPosition().getX());
        this.image.setLayoutY(getPosition().getY());
    } 
	
	/**
	 * Final render of the view
	 */
	public final void render() {
		setSpritePosition();
		Sprite.updatePlayerImage(this.image,this.direction,this.gunType);
	}
	
	/**
	 * Return the ImageView
	 * @return ImageView
	 */
	public final ImageView getImageView() {
        return this.image;
    }
	
	/**
	 * Set GunType
	 */
	public final void setGunType(GunType gun) {
		this.gunType=gun;
	}
}
