package it.unibo.isaccoop.model.collision;

import java.util.List;

import it.unibo.isaccoop.model.boundingbox.CircleBoundingBox;
import it.unibo.isaccoop.model.boundingbox.RectBoundingBox;
import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.enemy.Enemy;
import it.unibo.isaccoop.model.item.Item;
import it.unibo.isaccoop.model.player.Player;
import it.unibo.isaccoop.model.weapon.WeaponShot;

/**
 * Factory for check Collision.
 */
public final class CollisionCheckFactoryImpl implements CollisionCheckFactory {

    @Override
    public CollisionCheck getCollisionWithItemChecker(final Player p, final List<? extends Item> i) {
        return room -> i.stream()
                .filter(elem -> p.getBox().isCollidingWithCricle(p.getCoords(), elem.getCoords(),
                        (CircleBoundingBox) elem.getBox()))
                .forEach(e -> room.notifyEvent(new ConcreteEventFactory().getItemPickUpEvent(e)));
    }

    @Override
    public CollisionCheck getCollisionPlayerShotChecker(final Player p, final List<Enemy> i) {
        return room -> i.stream()
                .filter(elem -> p.getWeaponShots().stream()
                .anyMatch(shot -> shot.getBox()
                        .isCollidingWithCricle(shot.getCoords(), elem.getCoords(), (CircleBoundingBox) elem.getBox())))
                .forEach(e -> room.notifyEvent(new ConcreteEventFactory().getEnemyShotEvent(e)));
    }

    @Override
    public CollisionCheck getCollisionWithEnemyChecker(final Player p, final List<Enemy> i) {
        return room -> i.stream()
                .filter(elem -> elem.getBox().isCollidingWithCricle(elem.getCoords(), p.getCoords(),
                        (CircleBoundingBox) p.getBox()))
                .forEach(e -> room.notifyEvent(new ConcreteEventFactory().getEnemyHitEvent(e)));
    }

    @Override
    public CollisionCheck getCollisionWithEnemyShotChecker(final Player p, final List<Enemy> i) {
        return room -> i.stream()
                .filter(enemy -> enemy.getWeaponShots().isPresent())
                .forEach(enemy -> enemy.getWeaponShots().get().stream()
                        .filter(shot -> shot.getBox()
                        .isCollidingWithCricle(shot.getCoords(), p.getCoords(), (CircleBoundingBox) p.getBox()))
                        .forEach(shot -> room.notifyEvent(new ConcreteEventFactory().getEnemyHitEvent(enemy))));
    }

    @Override
    public CollisionCheck getShotsCollisionToRemoveChecker(final List<WeaponShot> shots, final Point2D pos,
            final CircleBoundingBox box) {
        return room -> shots.stream()
                .filter(shot -> shot.getBox().isCollidingWithRecPerimeter(shot.getCoords(), (RectBoundingBox) room.getBox())
                        || shot.getBox().isCollidingWithCricle(shot.getCoords(), pos, box))
                .forEach(shot -> room.notifyEvent(new ConcreteEventFactory().getShotToRemoveEvent(shot)));
    }
}
