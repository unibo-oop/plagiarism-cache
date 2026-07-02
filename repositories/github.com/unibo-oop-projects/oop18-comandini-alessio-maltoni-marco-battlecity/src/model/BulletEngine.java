package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.command.Direction;
import model.common.Movement;
import model.common.MovementImpl;
import model.common.Position;
import model.entities.Bullet;
import model.entities.BulletImpl;
import model.entities.Tank;
import model.entities.tankcomponents.BulletComponent;

public class BulletEngine {
    private static final double DEFAULT_TANK_TO_BULLET_OFFSET = 2.6;
    private static final double DEFAULT_BULLET_DELTA_MOVEMENT = 0.2;
    private final Map<Tank, List<Bullet>> currentBullets;
    private final World world;

    public BulletEngine(final World attachedWorld) {
        super();
        this.currentBullets = new HashMap<Tank, List<Bullet>>();
        this.world = attachedWorld;
    }

    public void addBullet(final Tank attacchedTank) {
        //TODO manage different bullet speed
        //TODO manage different bullet power
        if (!currentBullets.containsKey(attacchedTank)) {
            this.currentBullets.put(attacchedTank, new ArrayList<>());
        }
        if (this.currentBullets.get(attacchedTank).size() < attacchedTank.getComponents().stream()
                .filter(e -> e instanceof BulletComponent).count()) {
            this.world.addBullet(this.getBullet(attacchedTank));
        }
    }

    public void removeBullet(final Bullet bullet) {
        if (currentBullets.containsKey(bullet.getAttachedTank())) {
            currentBullets.get(bullet.getAttachedTank()).remove(bullet);
        }

    }

    private Bullet getBullet(final Tank attacchedTank) {
        final Position position = this.getBulletPosition(attacchedTank);
        final Movement movement = this.getBulletMovement(attacchedTank.getDirection());
        final Bullet bullet = new BulletImpl(position, movement, Bullet.Power.NORMAL, attacchedTank);
        if (attacchedTank.getDirection().isPresent()) {
            bullet.setDirection(attacchedTank.getDirection().get());
        }

        this.currentBullets.get(attacchedTank).add(bullet);
        return bullet;
    }

    private Movement getBulletMovement(final Optional<Direction> direction) {
        if (direction.isPresent()) {
            switch (direction.get()) {
            case UP:
                return new MovementImpl(0, -DEFAULT_BULLET_DELTA_MOVEMENT);
            case DOWN:
                return new MovementImpl(0, DEFAULT_BULLET_DELTA_MOVEMENT);

            case LEFT:
                return new MovementImpl(-DEFAULT_BULLET_DELTA_MOVEMENT, 0);
            case RIGHT:
                return new MovementImpl(DEFAULT_BULLET_DELTA_MOVEMENT, 0);

            default:

            }
        }
        return new MovementImpl(0, -DEFAULT_BULLET_DELTA_MOVEMENT);

    }

    private Position getBulletPosition(final Tank attacchedTank) {
        final Position bulletPosition = attacchedTank.getActualPosition();
        double deltaX = 0;
        double deltaY = 0;
        if (attacchedTank.getDirection().isPresent()) {
            switch (attacchedTank.getDirection().get()) {
            case UP:
                deltaX = attacchedTank.getDimension().getWidth() / DEFAULT_TANK_TO_BULLET_OFFSET;
                break;
            case DOWN:
                deltaX = attacchedTank.getDimension().getWidth() / DEFAULT_TANK_TO_BULLET_OFFSET;
                deltaY = attacchedTank.getDimension().getHeight();
                break;
            case LEFT:
                deltaY = attacchedTank.getDimension().getHeight() / DEFAULT_TANK_TO_BULLET_OFFSET;
                break;
            case RIGHT:
                deltaX = attacchedTank.getDimension().getWidth();
                deltaY = attacchedTank.getDimension().getHeight() / DEFAULT_TANK_TO_BULLET_OFFSET;
                break;
            default:
                break;
            }
        } else {
            deltaX = attacchedTank.getDimension().getHeight() / DEFAULT_TANK_TO_BULLET_OFFSET;
        }

        return bulletPosition.getSumPosition(deltaX, deltaY);
    }

}
