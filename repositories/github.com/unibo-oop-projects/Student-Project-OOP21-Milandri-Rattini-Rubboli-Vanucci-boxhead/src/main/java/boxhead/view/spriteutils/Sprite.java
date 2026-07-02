package boxhead.view.spriteutils;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import boxhead.model.entities.EntityType;
import boxhead.model.entities.gun.Gun.GunType;
import boxhead.model.entities.utils.Direction;

/**
 * Class to model a single sprite, with all the entities specs.
 */
public class Sprite {
	
	private static final int ZOMBIE_HEIGHT = 40;
    private static final int ZOMBIE_WIDTH = 40;
    
    private static final int PLAYER_HEIGHT = 40;
    private static final int PLAYER_WIDTH = 40;
    
    private static final int BULLET_HEIGHT = 8;
    private static final int BULLET_WIDTH = 3;
    
    private static final int AMMO_HEIGHT = 30;
    private static final int AMMO_WIDTH = 40;
    
    private static final int PASSIVE_HEIGHT = 20;
    private static final int PASSIVE_WIDTH = 20;
    
    
    private final EntityType type;
    private ImageView imageView;
    private final SnapshotParameters snapshot;
    
    public Sprite(final EntityType type) {
        this.type = type;
        this.setImageView();
        this.snapshot = new SnapshotParameters();
        this.snapshot.setFill(Color.TRANSPARENT);
    }

    /**
     * Method to set the image of the single entity.
     */
	private void setImageView() {
		this.imageView=new ImageView();
		switch(this.type) {
		case ZOMBIE:
			this.imageView.setFitHeight(ZOMBIE_HEIGHT);
			this.imageView.setFitWidth(ZOMBIE_WIDTH);
			updateZombieImage(this.imageView,Direction.EAST);
			break;
		case PLAYER:
			this.imageView.setFitHeight(PLAYER_HEIGHT);
			this.imageView.setFitWidth(PLAYER_WIDTH);
			updatePlayerImage(this.imageView,Direction.EAST,GunType.PISTOL);
			break;
		case BULLET:
			this.imageView.setFitHeight(BULLET_HEIGHT);
			this.imageView.setFitWidth(BULLET_WIDTH);
			this.imageView.setImage(new Image(getClass().getResourceAsStream("/bullet.png")));
			break;
		case AMMO:
			this.imageView.setFitHeight(AMMO_HEIGHT);
			this.imageView.setFitWidth(AMMO_WIDTH);
			this.imageView.setImage(new Image(getClass().getResourceAsStream("/ammo.png")));
			break;
		case WALL:
			this.imageView.setFitHeight(PASSIVE_HEIGHT);
			this.imageView.setFitWidth(PASSIVE_WIDTH);
			this.imageView.setImage(new Image(getClass().getResourceAsStream("/wall.png")));
			break;
		default:
			this.imageView.setFitHeight(PASSIVE_HEIGHT);
			this.imageView.setFitWidth(PASSIVE_WIDTH);
			this.imageView.setImage(null);	
			break;
		}
	}
	/**
	 * Update player image
	 * @param image
	 * @param direction
	 * @param gun
	 */
	public static void updatePlayerImage(final ImageView image, Direction direction, GunType gun) {
		String filename=gun.toString().toLowerCase() + "-" + direction.toString().toLowerCase();
		image.setImage(new Image(Sprite.class.getResourceAsStream("/player/" + filename + ".png")));
	}
		
	/**
	 * Update zombie image
	 * @param image
	 * @param direction
	 */
	public static void updateZombieImage(final ImageView image, Direction direction) {
		if(!direction.equals(Direction.NULL)) {
			String filename="zombie-"+direction.toString().toLowerCase();
			image.setImage(new Image(Sprite.class.getResourceAsStream("/zombie/" + filename + ".png")));
		}
	}
	
	/**
	 * Return the image view
	 * @return ImageView
	 */
	public final ImageView getImageView() {
        return this.imageView;
    }
	
	/**
	 * Return the snapshot of the image
	 * @return Image
	 */
	public final Image getImage() {
        return this.imageView.snapshot(this.snapshot, null);
    }
	
}
