package it.unibo.isaccoop.model.creator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.isaccoop.model.enemy.Boss;
import it.unibo.isaccoop.model.enemy.Enemy;
import it.unibo.isaccoop.model.enemy.NonShootingEnemy;
import it.unibo.isaccoop.model.enemy.ShootingEnemy;
import it.unibo.isaccoop.model.item.Coin;
import it.unibo.isaccoop.model.item.Heart;
import it.unibo.isaccoop.model.item.Item;
import it.unibo.isaccoop.model.powerup.CoinUp;
import it.unibo.isaccoop.model.powerup.DamageUp;
import it.unibo.isaccoop.model.powerup.HealthUp;
import it.unibo.isaccoop.model.powerup.PowerUp;
import it.unibo.isaccoop.model.powerup.SpeedUp;
import it.unibo.isaccoop.model.powerup.TearsUp;

/**
 * ConcreteCreatorFactory class which is a ConcreteFactory implementation.
 * */
public final class ConcreteCreatorFactory implements CreatorFactory {
    private static final Logger LOGGER = Logger.getLogger(ConcreteCreatorFactory.class.getName());
    private static final int ITEMS_IN_ROOM = 3;
    private static final List<Class<? extends Item>> ITEM_LIST = new ArrayList<>(List.of(Coin.class, Heart.class));
    private static final List<Class<? extends PowerUp>> POWER_UPS_LIST = new ArrayList<>(List.of(CoinUp.class,
            DamageUp.class, HealthUp.class, SpeedUp.class, TearsUp.class));

    private static final int MAX_ENEMIES = 5;

    @Override
    public Creator<Enemy> createEnemies() {
        return () -> Stream.iterate(0, i -> i + 1)
                .limit(ThreadLocalRandom.current().nextInt(ConcreteCreatorFactory.MAX_ENEMIES) + 1)
                .map(i -> ThreadLocalRandom.current().nextBoolean() ? new NonShootingEnemy() : new ShootingEnemy())
                .collect(Collectors.toList());
    }

    @Override
    public Creator<Enemy> createBoss() {
        return () -> Stream.of(new Boss()).collect(Collectors.toList());
    }

    @Override
    public Creator<PowerUp> createShopPowerUps() {
        return () -> Stream.iterate(0, x -> x + 1)
                .limit(ConcreteCreatorFactory.ITEMS_IN_ROOM)
                .map(e -> this.generatePowerUp().get())
                .collect(Collectors.toList());
    }

    @Override
    public Creator<PowerUp> createTreasurePowerUps() {
        return () -> Stream.of(this.generatePowerUp().get()).collect(Collectors.toList());
    }

    @Override
    public Creator<Item> createItems() {
        return () -> Stream.iterate(0, x -> x + 1)
        .limit(ThreadLocalRandom.current().nextInt(ConcreteCreatorFactory.ITEMS_IN_ROOM) + 1)
        .map(e -> this.generateRandomItem().get())
        .collect(Collectors.toList());
    }

    /**
     * Generate random power up among the available.
     * @return a random power up.
     * */
    private Optional<PowerUp> generatePowerUp() {
        final var random = ThreadLocalRandom.current().nextInt(POWER_UPS_LIST.size());
        try {
            final var powerUp = POWER_UPS_LIST.get(random).getDeclaredConstructor().newInstance();
            powerUp.setSuperItem(ThreadLocalRandom.current().nextBoolean());
            return Optional.of(powerUp);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            LOGGER.severe(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Generate random item.
     * @return random Item.
     * */
    private Optional<Item> generateRandomItem() {
        final var random = ThreadLocalRandom.current().nextInt(ITEM_LIST.size());
        try {
            final var item = ITEM_LIST.get(random).getDeclaredConstructor().newInstance();
            return Optional.of(item);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            LOGGER.severe(e.getMessage());
        }
        return Optional.empty();

    }

}
