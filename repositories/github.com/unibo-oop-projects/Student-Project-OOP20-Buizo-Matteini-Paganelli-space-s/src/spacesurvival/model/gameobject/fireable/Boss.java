package spacesurvival.model.gameobject.fireable;

import java.util.List;
import java.util.Optional;

import spacesurvival.model.gameobject.Edge;
import spacesurvival.model.gameobject.fireable.shootinglogic.FiringLogic;
import spacesurvival.model.gameobject.fireable.weapon.Weapon;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.gameobject.main.Status;
import spacesurvival.model.gameobject.moveable.movement.MovementLogic;
import spacesurvival.model.common.P2d;
import spacesurvival.model.common.V2d;
import spacesurvival.model.worldevent.WorldEvent;
import spacesurvival.utilities.path.SoundPath;
import spacesurvival.model.EngineImage;
import spacesurvival.model.World;
import spacesurvival.model.collision.bounding.BoundingBox;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.collision.event.EventType;
import spacesurvival.model.collision.event.hit.HitBorderEvent;
import spacesurvival.model.collision.event.hit.HitMainObject;
import spacesurvival.model.collision.eventgenerator.EventComponent;

/**
 * An enemy able to fire, it has more life than normal and can change the ammo type of its weapon.
 */
public class Boss extends FireableObject {

    public Boss(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent phys, final V2d velocity, final double acceleration, final MovementLogic movementLogic,
            final int life, final int impactDamage, final int score, final P2d target, final Weapon weapon,
            final FiringLogic firingLogic, final List<String> animation) {
        super(engineImage, position, bb, phys, velocity, acceleration, movementLogic, life, impactDamage, score,
                target, weapon, firingLogic);
        this.setBoundingBox(RectBoundingBox.createRectBoundingBox(position, engineImage, this.getTransform()));
        this.setMainAnimation(animation);
    }

    public Boss(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent phys, final V2d velocity, final double acceleration, final MovementLogic movementLogic,
            final int life, final int impactDamage, final int score, final P2d target, final Weapon weapon,
            final FiringLogic firingLogic) {
        super(engineImage, position, bb, phys, velocity, acceleration, movementLogic, life, impactDamage, score,
                target, weapon, firingLogic);
        this.setBoundingBox(RectBoundingBox.createRectBoundingBox(position, engineImage, this.getTransform()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collided(final World world, final WorldEvent ev) {
        final Optional<EventType> evType = EventType.getEventFromHit(ev);
        if (evType.isPresent()) {
            switch (EventType.getEventFromHit(ev).get()) {
            case HIT_BORDER:
                final HitBorderEvent hitBorderEvent = (HitBorderEvent) ev;
                final Edge edge = hitBorderEvent.getEdge();
                world.getSoundQueue().add(SoundPath.WALL_COLLISION);
                world.pacmanEffect(this, edge);
                break;
            case HIT_MAIN_OBJECT:
                final HitMainObject mainEvent = (HitMainObject) ev;
                final MainObject collidedObj = mainEvent.getObject();
                world.damageObject(this, collidedObj.getImpactDamage(), Status.INVINCIBLE);
                break;
            case DEAD_EVENT:
                world.getSoundQueue().add(SoundPath.BOSS_EXPL);
                world.removeBoss();
                break;
            default:
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Boss [" + super.toString() + "]";
    }

}
