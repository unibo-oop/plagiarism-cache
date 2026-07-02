package it.unibo.isaccoop.model.collision;

import it.unibo.isaccoop.model.action.ShootingHitStrategy;
import it.unibo.isaccoop.model.common.RoomType;
import it.unibo.isaccoop.model.enemy.Enemy;
import it.unibo.isaccoop.model.item.Item;
import it.unibo.isaccoop.model.powerup.PowerUp;
import it.unibo.isaccoop.model.room.Shop;
import it.unibo.isaccoop.model.room.ShopImpl;
import it.unibo.isaccoop.model.weapon.WeaponShot;

/**
 * ConcreteEventFactory class which is an event factory implementation.
 * */
public final class ConcreteEventFactory implements EventFactory {

    private static final Shop SHOP = new ShopImpl();

    @Override
    public Event getItemPickUpEvent(final Item target) {
        return room -> room.getPlayer().ifPresent(player -> {
            if (room.getRoomType() == RoomType.SHOP) {
                if (SHOP.buyItem(player, (PowerUp) target)) {
                    room.remove(target);
                }
            } else {
                target.interact(player);
                room.remove(target);
            }
        });
    }

    @Override
    public Event getEnemyShotEvent(final Enemy enemy) {
        return room -> room.getPlayer().ifPresent(player -> {
            player.onHit(enemy);
            if (enemy.isDead()) {
                room.remove(enemy);
            }
        });
    }

    @Override
    public Event getEnemyHitEvent(final Enemy enemy) {
        return room -> room.getPlayer().ifPresent(enemy::onHit);
    }

    @Override
    public Event getShotToRemoveEvent(final WeaponShot shot) {
        return room -> {
            room.getPlayer().ifPresent(p -> p.removePlayerShot(shot));
            room.getEnemies()
            .ifPresent(enemies ->
                enemies.forEach(enemy ->
                    enemy.getWeaponShots().ifPresent(shots ->
                    ((ShootingHitStrategy) enemy.getHitStrategy()).removeShot(shot))));
        };
    }

}
