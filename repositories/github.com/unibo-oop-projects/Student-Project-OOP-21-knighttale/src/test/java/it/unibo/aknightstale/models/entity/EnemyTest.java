package it.unibo.aknightstale.models.entity;

import it.unibo.aknightstale.models.entity.factories.EntityFactory;
import it.unibo.aknightstale.models.entity.factories.EntityFactoryImpl;
import it.unibo.aknightstale.utils.Point2D;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnemyTest {
    private static final int SAMPLE_COORDINATE = 30;
    private final EntityFactory factory = new EntityFactoryImpl();

    @Test
    @DisplayName("Check type")
    void checkType() {
        final var enemy = this.factory.getEnemy(new Point2D(10, 10)).getModel();
        Assertions.assertThat(enemy.getType()).isEqualTo(EntityType.ENEMY);
    }

    @Test
    @DisplayName("Move player Axis X")
    void checkMovementAxisX() {
        final var enemy = this.factory.getEnemy(new Point2D(SAMPLE_COORDINATE, SAMPLE_COORDINATE)).getModel();
        var position = enemy.getPosition();
        enemy.goLeft();
        position = new Point2D(position.getX() - enemy.getSpeed(), position.getY());
        Assertions.assertThat(position).isEqualTo(enemy.getPosition());
    }

    @Test
    @DisplayName("Move player Axis Y")
    void checkMovementAxisY() {
        final var enemy = this.factory.getEnemy(new Point2D(SAMPLE_COORDINATE, SAMPLE_COORDINATE)).getModel();
        var position = enemy.getPosition();
        enemy.goUp();
        position = new Point2D(position.getX(), position.getY() - enemy.getSpeed());
        Assertions.assertThat(position).isEqualTo(enemy.getPosition());
    }

    @Test
    @DisplayName("Check position")
    void checkPosition() {
        final var player = this.factory.getEnemy(new Point2D(10, 10)).getModel();
        Assertions.assertThat(player.getPosition()).isEqualTo(new Point2D(10, 10));
    }


}
