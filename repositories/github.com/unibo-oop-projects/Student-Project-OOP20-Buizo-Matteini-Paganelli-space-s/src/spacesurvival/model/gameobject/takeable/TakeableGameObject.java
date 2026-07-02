package spacesurvival.model.gameobject.takeable;

import spacesurvival.model.common.P2d;
import spacesurvival.model.gameobject.GameObject;

import java.util.List;

import spacesurvival.model.EngineImage;
import spacesurvival.model.collision.bounding.BoundingBox;
import spacesurvival.model.collision.bounding.CircleBoundingBox;
import spacesurvival.model.collision.eventgenerator.EventComponent;

/**
 * A stationary object in game, which can be taken from ship.
 */
public abstract class TakeableGameObject extends GameObject {

    public TakeableGameObject(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent phys) {
        super(engineImage, position, bb, phys);
        this.setBoundingBox(CircleBoundingBox.createCircleBoundingBox(position, engineImage, this.getTransform()));
    }

    public TakeableGameObject(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent phys, final List<String> animation) {
        super(engineImage, position, bb, phys);
        this.setBoundingBox(CircleBoundingBox.createCircleBoundingBox(position, engineImage, this.getTransform()));
        super.setMainAnimation(animation);
    }

}
