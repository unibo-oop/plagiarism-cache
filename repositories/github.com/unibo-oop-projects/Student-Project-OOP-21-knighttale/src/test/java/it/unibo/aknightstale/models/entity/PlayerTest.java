package it.unibo.aknightstale.models.entity;

import it.unibo.aknightstale.models.entity.factories.EntityFactory;
import it.unibo.aknightstale.models.entity.factories.EntityFactoryImpl;
import it.unibo.aknightstale.utils.Point;
import it.unibo.aknightstale.utils.Point2D;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private static final int ATTACK_TIMES = 40;
    private final EntityFactory factory = new EntityFactoryImpl();
    private static final int X = 50;
    private static final int Y = 50;
    private final Point spawnPosition = new Point2D(X, Y);

    @Test
    @DisplayName("Check type")
    void checkType() {
        final var player = this.factory.getPlayer(this.spawnPosition).getModel();
        Assertions.assertThat(player.getType()).isEqualTo(EntityType.PLAYER);
    }

    @Test
    @DisplayName("Move player")
    void checkMovement() {
        final var player = this.factory.getPlayer(this.spawnPosition).getModel();
        var position = player.getPosition();
        player.goRight();
        position = new Point2D(position.getX() + player.getSpeed(), position.getY());
        Assertions.assertThat(position).isEqualTo(player.getPosition());
    }

    @Test
    @DisplayName("Player attacks entity")
    void attack() {
        final var player = this.factory.getPlayer(this.spawnPosition).getModel();
        final LifeEntity entity = this.factory.getEnemy(new Point2D(player.getPosition().getX(), player.getPosition().getY()))
                .getModel();
        for (int i = 0; i < ATTACK_TIMES; i++) {
            player.attack(entity);
        }
        Assertions.assertThat(entity.isDead()).isTrue();
    }

    @Test
    @DisplayName("Check life of entity")
    void checklife() {
        final var player = this.factory.getPlayer(this.spawnPosition).getModel();
        final LifeEntity entity = this.factory.getEnemy(new Point2D(player.getPosition().getX(), player.getPosition().getY()))
                .getModel();
        final var life = entity.getHealth();
        player.attack(entity);
        Assertions.assertThat(entity.getHealth()).isEqualTo(life - (player.getDamage() * (entity.getDefense() / 100)));
    }

    @Test
    @DisplayName("Check maximum health of entity")
    void checkMaximumHealth() {
        final var player = this.factory.getPlayer(this.spawnPosition).getModel();
        final LifeEntity entity = this.factory.getEnemy(new Point2D(player.getPosition().getX(), player.getPosition().getY()))
                .getModel();
        final var initialHealth = entity.getHealth();
        player.attack(entity);
        Assertions.assertThat(entity.getMaxHealth()).isEqualTo(initialHealth);
    }

}
