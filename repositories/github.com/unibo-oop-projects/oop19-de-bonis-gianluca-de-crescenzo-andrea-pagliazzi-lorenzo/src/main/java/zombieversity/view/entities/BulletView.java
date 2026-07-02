package zombieversity.view.entities;

import zombieversity.model.entities.EntityType;
import zombieversity.view.imagefactory.Sprite;
import zombieversity.view.imagefactory.SpriteImpl;

public class BulletView extends AbstractAttackView {

    private final Sprite sprite;

    public BulletView() {
        this.sprite = new SpriteImpl(EntityType.BULLET);
    }

    @Override
    public final Sprite getSprite() {
        return this.sprite;
    }
}
