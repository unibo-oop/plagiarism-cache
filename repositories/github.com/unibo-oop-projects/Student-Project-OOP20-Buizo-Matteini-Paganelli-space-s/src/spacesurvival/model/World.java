package spacesurvival.model;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.gui.settings.SkinSpaceShip;
import spacesurvival.controller.collision.CollisionController;
import spacesurvival.model.gameobject.Edge;
import spacesurvival.model.gameobject.GameObject;
import spacesurvival.model.gameobject.factories.AbstractFactoryGameObject;
import spacesurvival.model.gameobject.factories.ConcreteFactoryGameObject;
import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.model.gameobject.fireable.weapon.Weapon;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.gameobject.main.Status;
import spacesurvival.model.gameobject.moveable.Bullet;
import spacesurvival.model.gameobject.moveable.MoveableObject;
import spacesurvival.model.gameobject.takeable.TakeableGameObject;
import spacesurvival.model.gameobject.takeable.ammo.AmmoType;
import spacesurvival.model.worldevent.WorldEvent;
import spacesurvival.model.worldevent.WorldEventListener;
import spacesurvival.utilities.Delay;
import spacesurvival.utilities.RandomUtils;
import spacesurvival.utilities.SystemVariables;
import spacesurvival.utilities.ThreadUtils;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.utilities.path.SoundPath;

public class World {

    private AbstractFactoryGameObject factoryGameObject = new ConcreteFactoryGameObject();
    private Thread takeableFactoryThread;

    private final Set<MainObject> asteroids = new HashSet<>();
    private final Set<MainObject> chaseEnemies = new HashSet<>();
    private final Set<FireableObject> fireEnemies = new HashSet<>();
    private Optional<FireableObject> boss = Optional.empty();
    private final Set<TakeableGameObject> ammo = new HashSet<>();
    private final Set<TakeableGameObject> hearts = new HashSet<>();

    private final List<Integer> queueScore = new ArrayList<>();
    private final List<Integer> queueIncreaseLife = new ArrayList<>();
    private final List<Integer> queueDecreaseLife = new ArrayList<>();
    private final List<SoundPath> soundQueue = new LinkedList<>();

    private SpaceShipSingleton ship;
    private final RectBoundingBox mainBBox;
    private WorldEventListener evListener;
    private CollisionController collisionController;
    /**
     * Create a World given a RectBoundingBox.
     * @param mainBBox the bounding box on which to base the world limits
     */
    public World(final RectBoundingBox mainBBox) {
        this.ship = SpaceShipSingleton.getSpaceShip();
        this.ship.startMoving();
        this.ship.setWeapon(new Weapon(AmmoType.NORMAL, ship));
        this.mainBBox = mainBBox;

        createStartingObjects();
    }

    /**
     * Create a World given a Rectangle.
     * 
     * @param rectangle the rectangle on which to base the world limits
     */
    public World(final Rectangle rectangle) {
        this.ship = SpaceShipSingleton.getSpaceShip();
        this.ship.startMoving();
        this.ship.setWeapon(new Weapon(AmmoType.NORMAL, ship));
        this.mainBBox = new RectBoundingBox(rectangle);

        createStartingObjects();
    }

    /**
     * Pause animations if true is passed.
     * 
     * @param isPause the boolean representing the state of the pause
     */
    public void setPauseAnimationAllObject(final boolean isPause) {
        this.getAllObjects().forEach(obj -> {
            obj.setPauseAnimation(isPause);
        });
    }

    /**
     * Create the starting objects for the first round.
     */
    public final void createStartingObjects() {
        this.addAsteroid();
        this.addChaseEnemy();
        takeableFactoryThread = new Thread(() -> {
            while (ship.isAlive()) {
                if (RandomUtils.RANDOM.nextBoolean()) {
                    this.addHeart();
                } else {
                    this.addAmmo();
                }
                ThreadUtils.sleep(Delay.SPAWN_PERK);
            }
        });

    }

    /**
     * Set the WorldEventListener to the World.
     * 
     * @param listener the WorldEventListener to link
     */
    public void setEventListener(final WorldEventListener listener) {
        evListener = listener;
    }

    /**
     * Set the passed SpaceShipSingleton to the World.
     * 
     * @param ship the SpaceShipSingleton to set
     */
    public void setShip(final SpaceShipSingleton ship) {
        this.ship = ship;
    }

    /**
     * @return the static instance of the ship
     */
    public SpaceShipSingleton getShip() {
        return this.ship;
    }

    /**
     * Sets a new skin to the ship.
     * 
     * @param skin the skin to set
     */
    public void setSkin(final SkinSpaceShip skin) {
        this.ship.getEngineImage().setPath(skin.getSkin());
        this.ship.setMainAnimation(skin.getAnimation());
    }

    /**
     * @return the factory of game objects
     */
    public AbstractFactoryGameObject getFactoryGameObject() {
        return factoryGameObject;
    }

    /**
     * Sets a new game object factory.
     * 
     * @param factoryGameObject the new factory to set
     */
    public void setFactoryGameObject(final AbstractFactoryGameObject factoryGameObject) {
        this.factoryGameObject = factoryGameObject;
    }

    /**
     * Add an asteroid to the world.
     */
    public final void addAsteroid() {
        final MainObject asteroid = factoryGameObject.createAsteroid();
        asteroids.add(asteroid);
        asteroid.startMoving();
    }

    /**
     * Remove an asteroid from the world.
     * 
     * @param asteroid the asteroid to remove
     */
    public void removeAsteroid(final MainObject asteroid) {
        asteroid.stopMoving();
        asteroid.stopAnimation();
        asteroids.remove(asteroid);
    }

    /**
     * Add a chase enemy to the world.
     */
    public final void addChaseEnemy() {
        final MainObject chaseEnemy = factoryGameObject.createChaseEnemy();
        chaseEnemies.add(chaseEnemy);
        chaseEnemy.startMoving();
    }

    /**
     * Remove an chase enemy from the world.
     * 
     * @param chaseEnemy the chase enemy to remove
     */
    public final void removeChaseEnemy(final MainObject chaseEnemy) {
        chaseEnemy.stopMoving();
        chaseEnemy.stopAnimation();
        chaseEnemies.remove(chaseEnemy);
    }

    /**
     * Add a fire enemy to the world.
     */
    public final void addFireEnemy() {
        final FireableObject fireEnemy = factoryGameObject.createFireEnemy();
        fireEnemies.add(fireEnemy);
        fireEnemy.startMoving();
        fireEnemy.startFiring();
    }

    /**
     * Remove an fire enemy from the world.
     * 
     * @param fireEnemy the fire enemy to remove
     */
    public final void removeFireEnemy(final MainObject fireEnemy) {
        fireEnemy.stopMoving();
        fireEnemy.stopAnimation();
        fireEnemies.remove(fireEnemy);
    }

    /**
     * Add the boss to the world.
     */
    public final void addBoss() {
        this.boss = Optional.of(factoryGameObject.createBoss());
        this.boss.get().startMoving();
        this.boss.get().startFiring();
    }

    /**
     * Remove the boss from the world.
     */
    public final void removeBoss() {
        if (this.boss.isPresent()) {
            this.boss.get().stopAnimation();
            this.boss.get().stopMoving();
            this.boss = Optional.empty();
        }
    }

    /**
     * Add an ammo to the world.
     */
    public final void addAmmo() {
        ammo.add(factoryGameObject.createAmmo());
    }

    /**
     * Remove an ammo from the world.
     * 
     * @param ammoObj the ammo to remove
     */
    public final void removeAmmo(final TakeableGameObject ammoObj) {
        ammoObj.stopAnimation();
        ammo.remove(ammoObj);
    }

    /**
     * Add a heart to the world.
     */
    public final void addHeart() {
        hearts.add(factoryGameObject.createHeart());
    }

    /**
     * Remove an heart from the world.
     * 
     * @param heart the heart to remove
     */
    public final void removeHeart(final TakeableGameObject heart) {
        heart.stopAnimation();
        hearts.remove(heart);
    }

    /**
     * Remove a bullet from the world.
     * 
     * @param bullet the bullet to remove
     * @return true if the bullet has removed
     */
    public final boolean removeBullet(final Bullet bullet) {
        bullet.stopMoving();
        bullet.stopAnimation();
        if (getShipBullets().remove(bullet)) {
            return true;
        }
        if (getBossBullets().remove(bullet)) {
            return true;
        }
        final boolean found = false;
        final Iterator<FireableObject> fireEnemiesIterator = fireEnemies.iterator();
        while (fireEnemiesIterator.hasNext()) {
            getFireEnemyBullets(fireEnemiesIterator.next()).remove(bullet);
        }
        return found;
    }

    /**
     * Cycle all objects and update events for each.
     */
    public void updateState() {
        this.getAllObjects().forEach(entity -> {
            entity.updateEvents(this);
        });
    }

    /**
     * Set the current collision controller.
     * 
     * @param collisionController
     */
    public void setCollisionController(final CollisionController collisionController) {
       this.collisionController = collisionController;
    }

    /**
     * Return the current collision controller.
     * 
     * @return the current collision controller
     */
    public CollisionController getCollisionController() {
        return this.collisionController;
    }

    /**
     * Pacman Effect for the movable game object passed.
     * 
     * @param object the game object to move
     * @param edge edge where the collision with boundaries occurred
     */
    public void pacmanEffect(final MoveableObject object, final Edge edge) {
        AffineTransform newTransform = new AffineTransform();
        switch (edge) {
        case TOP:
            newTransform = new AffineTransform(object.getTransform().getScaleX(), 
                    object.getTransform().getShearY(), object.getTransform().getShearX(), 
                    object.getTransform().getScaleY(), object.getTransform().getTranslateX(), 
                    Screen.HEIGHT_FULLSCREEN * SystemVariables.SCALE_Y - 100);
            break;
        case BOTTOM:
            newTransform = new AffineTransform(object.getTransform().getScaleX(), 
                    object.getTransform().getShearY(), object.getTransform().getShearX(), 
                    object.getTransform().getScaleY(), object.getTransform().getTranslateX(), 
                    100);
            break;
        case LEFT:
            newTransform = new AffineTransform(object.getTransform().getScaleX(), 
                    object.getTransform().getShearY(), object.getTransform().getShearX(), 
                    object.getTransform().getScaleY(), Screen.WIDTH_FULLSCREEN * SystemVariables.SCALE_X - 100,
                    object.getTransform().getTranslateY());
            break;
        case RIGHT: 
            newTransform = new AffineTransform(object.getTransform().getScaleX(), 
                    object.getTransform().getShearY(), object.getTransform().getShearX(), 
                    object.getTransform().getScaleY(), 100, 
                    object.getTransform().getTranslateY());
            break;
            default:
                break;
        }
        object.setTransform(newTransform);
    }

    /**
     * Return the score queue to increase or decrease.
     * 
     * @return the current score queue
     */
    public List<Integer> getQueueScore() {
        return this.queueScore;
    }

    /**
     * Return the queue life to increase.
     * 
     * @return the current increase life queue
     */
    public List<Integer> getQueueIncreaseLife() {
        return this.queueIncreaseLife;
    }

    /**
     * Return the queue life to decrease.
     * 
     * @return the current decrease life queue
     */
    public List<Integer> getQueueDecreaseLife() {
        return this.queueDecreaseLife;
    }

    /**
     * Damages an object and applies it a status.
     * 
     * @param object the object to damage
     * @param damage the quantity of damage to inflict
     * @param status the status to apply to the object
     */
    public void damageObject(final MainObject object, final int damage, final Status status) {
        if (!object.isInvincible()) {
            if (object.getLife() - damage <= 0) {
                object.setLife(0);
            } else {
                object.decreaseLife(damage);
                object.setStatus(status);
            }
        }
    }

    /**
     * Notify the world that an WorldEvent occurred.
     * 
     * @param ev the WorldEvent event
     */
    public void notifyWorldEvent(final WorldEvent ev) {
        evListener.notifyEvent(ev);
    }

    /**
     * @return the world main bounding box
     */
    public RectBoundingBox getMainBBox() {
        return mainBBox;
    }

    /**
     * @return all asteroids in the world
     */
    public Set<MainObject> getAsteroids() {
        return this.asteroids;
    }

    /**
     * @return all chase enemies in the world
     */
    public Set<MainObject> getChaseEnemies() {
        return this.chaseEnemies;
    }

    /**
     * @return all fire enemies in the world
     */
    public Set<FireableObject> getFireEnemies() {
        return this.fireEnemies;
    }

    /**
     * @return the optional of boss
     */
    public Optional<FireableObject> getBoss() {
        return this.boss;
    }

    /**
     * @return all ammo in the world
     */
    public Set<TakeableGameObject> getAmmo() {
        return this.ammo;
    }

    /**
     * @return all hearts in the world
     */
    public Set<TakeableGameObject> getHearts() {
        return this.hearts;
    }

    /**
     * Return the FireableObject which has fired a specific bullet.
     * 
     * @param bullet the bullet whose shooter you want to find
     * @return the shooter of a bullet.
     */
    public Optional<FireableObject> getShooterFromBullet(final Bullet bullet) {
        Optional<FireableObject> bulletShooter = Optional.empty();
        for (final FireableObject fireableObject : this.getFireableObjects()) {
            if (fireableObject.getWeapon().getShootedBullets().contains(bullet)) {
                bulletShooter = Optional.of(fireableObject);
            }
        }
        return bulletShooter;
    }

    /**
     * @return the thread which handle the creation of TakeableObject
     */
    public Thread getTakeableFactoryThread() {
        return takeableFactoryThread;
    }

    /**
     * @return all main objects in the world
     */
    public Set<MainObject> getMainObjects() {
        final Set<MainObject> mainObjects = new HashSet<>();
        mainObjects.addAll(asteroids);
        mainObjects.addAll(chaseEnemies);
        mainObjects.addAll(fireEnemies);
        boss.ifPresent(mainObjects::add);
        mainObjects.add(ship);
        return mainObjects;
    }

    /**
     * @return all takeable objects in the world
     */
    public Set<TakeableGameObject> getTakeableObjects() {
        final Set<TakeableGameObject> takeableGameObjects = new HashSet<>();
        takeableGameObjects.addAll(ammo);
        takeableGameObjects.addAll(hearts);
        return takeableGameObjects;
    }

    /**
     * @return all bullets fired from the ship
     */
    public Set<Bullet> getShipBullets() {
            return this.ship.getWeapon().getShootedBullets();
    }

    /**
     * @return all bullets fired from a fire enemies
     * 
     * @param fireEnemy the fire enemy from which to return the fired bullets
     */
    public Set<Bullet> getFireEnemyBullets(final FireableObject fireEnemy) {
        return fireEnemy.getWeapon().getShootedBullets();
    }

    /**
     * @return all bullets fired from the boss
     */
    public Set<Bullet> getBossBullets() {
        if (this.boss.isPresent()) {
            return this.boss.get().getWeapon().getShootedBullets();
        }
        return new HashSet<>();
    }

    /**
     * @return all bullets fired from all objects in the world
     */
    public Set<Bullet> getAllBullets() {
        final HashSet<Bullet> allBullets = new HashSet<>();
        allBullets.addAll(getShipBullets());
//        this.fireEnemies.forEach(fireEnemy -> {
//            allBullets.addAll(getFireEnemyBullets(fireEnemy));
//        });
        this.fireEnemies.stream().forEach(fireEnemy -> {
            allBullets.addAll(getFireEnemyBullets(fireEnemy));
        });
        allBullets.addAll(getBossBullets());
        return allBullets;
    }

    /**
     * @return all enemies in the world
     */
    public Set<MainObject> getAllEnemies() {
        final HashSet<MainObject> allEnemies = new HashSet<>();
        allEnemies.addAll(chaseEnemies);
        allEnemies.addAll(fireEnemies);
        if (this.boss.isPresent()) {
            allEnemies.add(this.boss.get());
        }
        return allEnemies;
    }

    /**
     * @return all MoveableObjects in the world
     */
    public Set<MoveableObject> getMovableObjects() {
        final Set<MoveableObject> entities = new HashSet<>();
        entities.add(ship);
        entities.addAll(asteroids);
        entities.addAll(getAllEnemies());
        if (boss.isPresent()) {
            entities.add(boss.get());
        }
        entities.addAll(getAllBullets());

        return entities;
    }

    /**
     * @return all MoveableObjects in the world
     */
    public Set<FireableObject> getFireableObjects() {
        final Set<FireableObject> entities = new HashSet<>();
        entities.add(ship);
        entities.addAll(getFireEnemies());
        if (boss.isPresent()) {
            entities.add(boss.get());
        }

        return entities;
    }

    /**
     * @return all objects in the world except bullets
     */
    public Set<GameObject> getAllObjectsExceptBullets() {
        final Set<GameObject> entities = new HashSet<>();
        entities.add(ship);
        entities.addAll(asteroids);
        entities.addAll(getAllEnemies());
        if (boss.isPresent()) {
            entities.add(boss.get());
        }
        entities.addAll(ammo);
        entities.addAll(hearts);
        return entities;
    }

    /**
     * @return all objects in the world
     */
    public Set<GameObject> getAllObjects() {
        final Set<GameObject> entities = new HashSet<>();
        entities.add(ship);
        entities.addAll(asteroids);
        entities.addAll(getAllEnemies());
        if (boss.isPresent()) {
            entities.add(boss.get());
        }
        entities.addAll(getAllBullets());
        entities.addAll(ammo);
        entities.addAll(hearts);
        return entities;
    }

    /**
     * @return the number of enemies in the world
     */
    public int getCountEnemies() {
        return this.fireEnemies.size() 
                + this.chaseEnemies.size() 
                + (this.boss.isPresent() ? 1 : 0);
    }

    /**
     * @return the quantity of life ship
     */
    public int getLifeShip() {
        return this.ship.getLife();
    }

    /**
     * @return the quantity of life boss
     */
    public int getLifeBoss() {
        return this.boss.get().getLife();
    }

    /**
     * @return return the list of effect to play.
     */
    public List<SoundPath> getSoundQueue() {
        return this.soundQueue;
    }

}
