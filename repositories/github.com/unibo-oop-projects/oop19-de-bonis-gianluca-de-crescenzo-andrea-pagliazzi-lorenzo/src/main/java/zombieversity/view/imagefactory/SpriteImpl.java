package zombieversity.view.imagefactory;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import zombieversity.model.entities.EntityType;

/**
 * 
 * The implementation of {@link Sprite}.
 *
 */
public class SpriteImpl implements Sprite {

    private static final int ZOMBIE_HEIGHT = 15;
    private static final int ZOMBIE_WIDTH = 15;

    private static final int BULLET_HEIGHT = 10;
    private static final int BULLET_WIDTH = 5;

    private static final int KNIFE_ATT_HEIGHT = 30;
    private static final int KNIFE_ATT_WIDTH = 10;

    private static final int PLAYER_HEIGHT = 20;
    private static final int PLAYER_WIDTH = 20;

    private static final int DEFAULT_HEIGHT = 20;
    private static final int DEFAULT_WIDTH = 20;

    private final EntityType type;
    private ImageView imageView;
    private final SnapshotParameters sp;

    /**
     * Construct a {@link Sprite}.
     * 
     * @param type the entity.
     */
    public SpriteImpl(final EntityType type) {
        this.type = type;
        this.sp = new SnapshotParameters();
        this.sp.setFill(Color.TRANSPARENT);
        this.setImageView();
    }

    private void setImageView() {
        this.imageView = new ImageView(new Image(getClass().getResourceAsStream(this.type.getURL())));
        switch (type) {
        case ZOMBIE:
            imageView.setFitHeight(ZOMBIE_HEIGHT);
            imageView.setFitWidth(ZOMBIE_WIDTH);
            break;
        case PLAYER:
            imageView.setFitHeight(PLAYER_HEIGHT);
            imageView.setFitWidth(PLAYER_WIDTH);
            break;
        case BULLET:
            imageView.setFitHeight(BULLET_HEIGHT);
            imageView.setFitWidth(BULLET_WIDTH);
            break;
        case MELEE_ATTACK:
            imageView.setFitHeight(KNIFE_ATT_HEIGHT);
            imageView.setFitWidth(KNIFE_ATT_WIDTH);
            break;
        default:
            imageView.setFitHeight(DEFAULT_HEIGHT);
            imageView.setFitWidth(DEFAULT_WIDTH);
            break;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ImageView getImageView() {
        return this.imageView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Image getImage() {
        return this.imageView.snapshot(sp, null);
    }
}
