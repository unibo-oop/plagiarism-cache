package zombieversity.view.entities;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import zombieversity.model.entities.EntityType;
import zombieversity.view.imagefactory.Sprite;
import zombieversity.view.imagefactory.SpriteFactoryUtils;

/**
 * 
 * The implementation of {@link PlayerView}.
 *
 */
public class PlayerViewImpl implements PlayerView {

    private Point2D position;
    private double direction;
    private final Sprite playerSprite;
    private final EntityType type;
    private final ImageView image;

    /**
     * Construct a {@link PlayerView}.
     */
    public PlayerViewImpl() {
        this.position = Point2D.ZERO;
        this.type = EntityType.PLAYER;
        this.playerSprite = SpriteFactoryUtils.createSprite(type);
        this.direction = 0.0;
        this.image = this.playerSprite.getImageView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EntityType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setPosition(final Point2D position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setDirection(final double direction) {
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void render() {
        setFaceDirectionSprite();
        setPositionSprite();
    }

    private void setFaceDirectionSprite() {
        this.image.setRotate(getDirection());
    }

    private void setPositionSprite() {
        this.image.setLayoutX(getPosition().getX());
        this.image.setLayoutY(getPosition().getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ImageView getImageView() {
        return this.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Image getImage() {
        return this.playerSprite.getImage();
    }
}
