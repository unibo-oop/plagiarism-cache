package it.unibo.aknightstale.utils;

import it.unibo.aknightstale.models.entity.factories.EntityFactory;
import it.unibo.aknightstale.models.entity.factories.EntityFactoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EntityManagerTest {

    private final EntityFactory factory = new EntityFactoryImpl();

    @Test
    @DisplayName("Create entity")
    void create() {
        final var player = this.factory.getPlayer(new Point2D(50, 50));
        final var manager = this.factory.getEntityManager();
        Assertions.assertThat(manager.getEntities().stream().count()).isEqualTo(1);
        manager.removeEntity(player);
        Assertions.assertThat(manager.getEntities().stream().count()).isEqualTo(0);
    }

}
