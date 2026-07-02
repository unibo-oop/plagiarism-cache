package zombieversity.view.entities;

import javafx.geometry.Point2D;
import zombieversity.view.imagefactory.Sprite;

public abstract class AbstractAttackView implements AttackView {

    /**
     * 
     */
    @Override
    public final void setRotation(final Point2D pivot, final Point2D second) {
        final double distanceX = second.getX() - (pivot.getX());
        final double distanceY = second.getY() - (pivot.getY());
        final double angle = Math.atan2(distanceY, distanceX);
        this.setDirection(Math.toDegrees(angle));
    }

    /**
     * 
     */
    @Override
    public void setDirection(final double angle) {
        this.getSprite().getImageView().setRotate(this.getSprite().getImageView().getRotate() + angle);
    }

    @Override
    public abstract Sprite getSprite();

}
