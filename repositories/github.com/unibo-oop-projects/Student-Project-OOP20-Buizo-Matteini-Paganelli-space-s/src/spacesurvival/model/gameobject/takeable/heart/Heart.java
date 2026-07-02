package spacesurvival.model.gameobject.takeable.heart;

import java.util.Optional;

import spacesurvival.model.EngineImage;
import spacesurvival.model.World;
import spacesurvival.model.collision.bounding.BoundingBox;
import spacesurvival.model.collision.event.EventType;
import spacesurvival.model.collision.eventgenerator.EventComponent;
import spacesurvival.model.common.P2d;
import spacesurvival.model.gameobject.takeable.TakeableGameObject;
import spacesurvival.model.worldevent.WorldEvent;
import spacesurvival.utilities.path.SoundPath;

/**
 * An object which gives life to the ship.
 */
public class Heart extends TakeableGameObject {

    private HeartType type;

    public Heart(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent phys, final HeartType type) {
        super(engineImage, position, bb, phys, type.getAnimation());
        this.type = type;
    }

    /**
     * @return the heart type
     */
    public HeartType getType() {
        return type;
    }

    /**
     * Sets the heart type.
     * 
     * @param type the heart type to set
     */
    public void setType(final HeartType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collided(final World world, final WorldEvent ev) {
        final Optional<EventType> evType = EventType.getEventFromHit(ev);
        if (evType.isPresent()) {
            switch (EventType.getEventFromHit(ev).get()) {
            case HIT_TAKEABLE_OBJECT:
                world.getSoundQueue().add(SoundPath.PERK);
                world.getQueueIncreaseLife().add(this.getType().getAmount());
                world.getShip().setStatus(this.getType().getStatus());
                break;
            default:
                break;
            }
            world.removeHeart(this);
        }
    }

}
