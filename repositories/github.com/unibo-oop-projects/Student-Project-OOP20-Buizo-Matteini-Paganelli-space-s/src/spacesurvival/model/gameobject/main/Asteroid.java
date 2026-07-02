package spacesurvival.model.gameobject.main;

import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Optional;

import spacesurvival.model.common.P2d;
import spacesurvival.model.common.V2d;
import spacesurvival.model.gameobject.Edge;
import spacesurvival.model.gameobject.moveable.movement.MovementLogic;
import spacesurvival.model.worldevent.WorldEvent;
import spacesurvival.utilities.RandomUtils;
import spacesurvival.utilities.path.SoundPath;
import spacesurvival.model.EngineImage;
import spacesurvival.model.World;
import spacesurvival.model.collision.bounding.BoundingBox;
import spacesurvival.model.collision.bounding.CircleBoundingBox;
import spacesurvival.model.collision.event.EventType;
import spacesurvival.model.collision.event.hit.HitBorderEvent;
import spacesurvival.model.collision.event.hit.HitMainObject;
import spacesurvival.model.collision.eventgenerator.EventComponent;

/**
 * A neutral object with a fixed direction and velocity, which can hurt with space ship and enemies.
 */
public class Asteroid extends MainObject {

    public Asteroid(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent phys, final V2d velocity, final double acceleration, final MovementLogic movementLogic,
            final int life, final int impactDamage, final int score, final P2d target) {
        super(engineImage, position, bb, phys, velocity, acceleration, movementLogic, life, impactDamage, score, target);
        this.setBoundingBox(CircleBoundingBox.createCircleBoundingBox(position, engineImage, this.getTransform()));
        initializeRotation();
    }

    public Asteroid(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent phys, final V2d velocity, final double acceleration, final MovementLogic movementLogic,
            final int life, final int impactDamage, final int score, final P2d target, final List<String> animation) {
        super(engineImage, position, bb, phys, velocity, acceleration, movementLogic, life, impactDamage, score, target);
        this.setBoundingBox(CircleBoundingBox.createCircleBoundingBox(position, engineImage, this.getTransform()));
        this.setMainAnimation(animation);
        initializeRotation();
    }

    /**
     * Initialize a random rotation for the asteroid.
     */
    private void initializeRotation() {
        final int randomAngle = RandomUtils.RANDOM.nextInt(360);
        final AffineTransform at = getTransform();
        at.rotate(Math.toRadians(randomAngle), getSize().getWidth() / 2, getSize().getHeight() / 2);
        setTransform(at);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collided(final World world, final WorldEvent event) {
        final Optional<EventType> evType = EventType.getEventFromHit(event);
        if (evType.isPresent()) {
            switch (EventType.getEventFromHit(event).get()) {
            case HIT_BORDER:
                final HitBorderEvent hitEvent = (HitBorderEvent) event;
                final Edge edge = hitEvent.getEdge();
                world.pacmanEffect(this, edge);
                break;
            case HIT_MAIN_OBJECT:
                final HitMainObject mainEvent = (HitMainObject) event;
                final MainObject collidedObj = mainEvent.getObject();
                world.damageObject(this, collidedObj.getImpactDamage(), Status.INVINCIBLE);
                break;
            case DEAD_EVENT:
                world.getSoundQueue().add(SoundPath.ASTEROID_EXPL);
                world.removeAsteroid(this);
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
        return "Asteroid [ " + super.toString() + "]";
    }

}
