package spacesurvival.model.gameobject.takeable.ammo;

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
 * Ammo class.
 */
public class Ammo extends TakeableGameObject {

    private AmmoType type;

    public Ammo(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent phys, final AmmoType type) {
        super(engineImage, position, bb, phys, type.getAnimation());
        this.type = type;
    }

    /**
     * @return the type of ammo
     */
    public AmmoType getType() {
        return type;
    }

    /**
     * Sets the type to the ammo.
     * @param type the type to set
     */
    public void setType(final AmmoType type) {
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
                world.getShip().takeAmmo(this);
                break;
            default:
                break;
            }
            world.removeAmmo(this);
        }
    }

}
