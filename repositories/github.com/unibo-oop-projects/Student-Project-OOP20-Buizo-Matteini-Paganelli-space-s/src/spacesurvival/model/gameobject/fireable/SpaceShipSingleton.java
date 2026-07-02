package spacesurvival.model.gameobject.fireable;

import spacesurvival.model.gameobject.Edge;
import spacesurvival.model.gameobject.fireable.shootinglogic.FiringLogic;
import spacesurvival.model.gameobject.fireable.shootinglogic.implementation.NoFiringLogic;
import spacesurvival.model.gameobject.fireable.weapon.Weapon;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.gameobject.main.Status;
import spacesurvival.model.gameobject.moveable.movement.MovementLogic;
import spacesurvival.model.gameobject.moveable.movement.implementation.ControlledMovementLogic;
import spacesurvival.model.gameobject.takeable.ammo.Ammo;
import spacesurvival.model.worldevent.WorldEvent;
import spacesurvival.model.common.P2d;
import spacesurvival.model.common.V2d;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import spacesurvival.model.EngineImage;
import spacesurvival.model.World;
import spacesurvival.model.collision.bounding.BoundingBox;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.collision.event.EventType;
import spacesurvival.model.collision.event.hit.HitBorderEvent;
import spacesurvival.model.collision.event.hit.HitMainObject;
import spacesurvival.model.collision.eventgenerator.EventComponent;
import spacesurvival.model.collision.eventgenerator.ShipComponent;
import spacesurvival.utilities.Score;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.utilities.gameobject.DamageUtils;
import spacesurvival.utilities.gameobject.LifeUtils;
import spacesurvival.utilities.gameobject.VelocityUtils;
import spacesurvival.utilities.path.SoundPath;
import spacesurvival.utilities.path.animation.AnimationShip;

/**
 * Singleton creation of the spaceship.
 */
public final class SpaceShipSingleton extends FireableObject {

    private List<SoundPath> soundQueue = new LinkedList<>();

    // Eager and unique instance of this class to make it Thread-safe
    private static final SpaceShipSingleton SPACESHIP = new SpaceShipSingleton(
            new EngineImage(ScaleOf.GAME_OBJECT, Screen.WIDTH_FULLSCREEN, AnimationShip.NORMAL0),
            Screen.POINT_CENTER_ABSOLUTE,
            new RectBoundingBox(),
            new ShipComponent(),
            VelocityUtils.SPACESHIP_VEL,
            VelocityUtils.SPACESHIP_ACCELERATION,
            new ControlledMovementLogic(),
            LifeUtils.SPACESHIP_LIFE,
            DamageUtils.SPACESHIP_DAMAGE,
            Score.SHIP,
            null,
            new Weapon(),
            new NoFiringLogic()
            );

    /** 
    * Invisible class constructor specifying space ship initial position and image path.
    */
    private SpaceShipSingleton(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent phys, final V2d velocity, final double acceleration, final MovementLogic movementLogic, final int life,
            final int impactDamage, final int score, final P2d target, final Weapon weapon,
            final FiringLogic firingLogic) {
        super(engineImage, position, bb, phys, velocity, acceleration, movementLogic, life, impactDamage, score, target, weapon, firingLogic);
        this.setBoundingBox(RectBoundingBox.createRectBoundingBox(position, engineImage, this.getTransform()));
    }

    /**
     * @return space ship static instance
     */
    public static SpaceShipSingleton getSpaceShip() {
        return SPACESHIP;
    }

    /**
     * Sets the ammo type taken from ship to its weapon.
     *
     * @param ammo the ammo taken from ship 
     */
    public void takeAmmo(final Ammo ammo) {
        this.getWeapon().setAmmoType(ammo.getType());
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
                final HitMainObject asteroidEvent = (HitMainObject) ev;
                final MainObject collidedObject = asteroidEvent.getCollidedObject();
                if (!this.isInvincible()) {
                    world.getQueueDecreaseLife().add(collidedObject.getImpactDamage());
                    this.setStatus(Status.INVINCIBLE);
                }
                break;
            case DEAD_EVENT:
                world.getSoundQueue().add(SoundPath.LIFE_DOWN);
                break;
            default:
                break;
            }
        }
    }

    /**
     * @return a list of SoundPath that will be added queued in the World.
     */
    public List<SoundPath> getSoundQueue() {
        return soundQueue;
    }

    /**
     * Sets a new queue of sounds to be played.
     * 
     * @param soundQueue the new list of sounds
     */
    public void setSoundQueue(final List<SoundPath> soundQueue) {
        this.soundQueue = soundQueue;
    }

}

