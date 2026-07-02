package zombieversity.view.entities;

import javafx.geometry.Point2D;
import javafx.scene.transform.Rotate;
import zombieversity.model.entities.EntityType;
import zombieversity.view.imagefactory.Sprite;
import zombieversity.view.imagefactory.SpriteImpl;

public class KnifeAttackView extends AbstractAttackView {

    private final Sprite sprite;
    private final Point2D pivot;

    public KnifeAttackView(final Point2D pivot) {
        this.sprite = new SpriteImpl(EntityType.MELEE_ATTACK);
        this.pivot = pivot;
    }

    @Override
    public final Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public final void setDirection(final double angle) {
        this.sprite.getImageView().getTransforms().clear();
        this.sprite.getImageView().getTransforms().add(new Rotate(angle, this.pivot.getX(), this.pivot.getY()));
    }


}
