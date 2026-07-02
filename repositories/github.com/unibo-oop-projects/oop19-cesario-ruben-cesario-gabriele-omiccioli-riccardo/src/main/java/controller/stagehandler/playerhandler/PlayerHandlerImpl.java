package controller.stagehandler.playerhandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.Binder;
import controller.ControllerEvent;
import controller.stagehandler.Time;
import javafx.application.Platform;
import model.ship.PlayerShip;
import model.ship.basic.BasicPlayerShip;
import model.weapon.Weapon;
import utilities.math.Vector2DImpl;

/**
 * The controller that manages the player actions.
 * @see PlayerHandler
 */
public class PlayerHandlerImpl implements PlayerHandler {

    private final Map<PlayerAction, Boolean> keys = new EnumMap<>(PlayerAction.class);
    private final Map<Weapon, Time> turnToFire = new HashMap<>(); 
    private final Collection<Weapon.Projectile> fired;
    private final BasicPlayerShip player;

    public PlayerHandlerImpl(final PlayerShip player) {
        this.player = (BasicPlayerShip) player;
        this.keys.putAll(Stream.of(PlayerAction.values())
                .map(value -> Map.<PlayerAction, Boolean>entry(value, false))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        this.turnToFire.putAll(this.player.getWeapons().stream()
                .map(value -> Map.<Weapon, Time>entry(value, new Time()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        this.fired = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(final PlayerAction key) {
        this.keys.replace(key, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deactivate(final PlayerAction key) {
        this.keys.replace(key, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void computeAction() {
        this.keys.forEach((key, isActive) -> {
            if (isActive) {
                switch (key) {
                case ACCELERATE:
                    accelerate();
                    break;
                case DECELERATE:
                    decelerate();
                    break;
                case ROTATE_ANTICLOCKWISE:
                    rotateAnticlockwise();
                    break;
                case ROTATE_CLOCKWISE:
                    rotateClockwise();
                    break;
                case SHOOT:
                    shoot();
                    break;
                default:
                    break;
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addScore(final int scorePoint) {
        this.player.getPlayerScore().addScorePoints(scorePoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void beatLevel() {
        this.player.getPlayerScore().incrementLevelBeaten();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Weapon.Projectile> getFiredProjectiles() {
        final Collection<Weapon.Projectile> fired = new ArrayList<>(this.fired);
        this.fired.clear();
        return fired;
    }

    /**
     * Try shoot with all player's weapons. A weapon can only fire if more
     * turns than those associated with a weapon's rate of fire have passed.
     */
    private void shoot() {
        for (final Entry<Weapon, Time> weapon : this.turnToFire.entrySet()) {
            if (weapon.getValue().getElapsedTime() > weapon.getKey().getRateOfFire()) {
                this.fired.addAll(weapon.getKey().shoot(this.player.getPosition().translate(this.player.getSpeed()
                        .add(new Vector2DImpl(this.player.getRadialHitbox(), this.player.getRotation())).components()), this.player.getRotation()));
                weapon.setValue(new Time());
                Platform.runLater(() -> Binder.getView().receiveEvent(ControllerEvent.SHOOT));
            }
        }
    }

    /**
     * Rotate the player clockwise according to it's maximum angular speed.
     */
    private void rotateClockwise() {
        this.player.rotateClockwise(this.player.getMaxAngularSpeed());
    }

    /**
     * Rotate the player anticlockwise according to it's maximum angular speed.
     */
    private void rotateAnticlockwise() {
        this.player.rotateAnticlockwise(this.player.getMaxAngularSpeed());
    }

    /**
     * Decelerate the player according to it's maximum acceleration and in the
     * direction it's facing.
     */
    private void decelerate() {
        this.player.decelerate(new Vector2DImpl(this.player.getMaxAcceleration(), this.player.getRotation()));
    }

    /**
     * Accelerate the player according to it's maximum acceleration and in the
     * direction it's facing.
     */
    private void accelerate() {
        this.player.accelerate(new Vector2DImpl(this.player.getMaxAcceleration(), this.player.getRotation()));
    }

}
