package clashclass.ecs;

import clashclass.commons.HealthComponent;
import clashclass.commons.HealthComponentImpl;
import clashclass.commons.Vector2D;
import clashclass.commons.Transform2D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameObjectTest {

    @Test
    void testComponentIsNotPresentInGameObject() {
        final var gameObject = new GameObjectImpl();
        final var transform = gameObject.getComponentOfType(Transform2D.class);

        assertTrue(transform.isEmpty());
    }

    @Test
    void testComponentIsPresentInGameObject() {
        final var gameObject = new GameObjectImpl();
        gameObject.addComponent(new Transform2D());

        final var transform = gameObject.getComponentOfType(Transform2D.class);

        assertTrue(transform.isPresent());
    }

    @Test
    void testOnlyFirstComponentInGameObjectIsReturned() {
        final var gameObject = new GameObjectImpl();

        final var component1 = new Transform2D();
        final var component2 = new Transform2D();

        component1.setPosition(new Vector2D(0, 0));
        component2.setPosition(new Vector2D(1, 1));

        gameObject.addComponent(component1);
        gameObject.addComponent(component2);

        final var returnedComponent = gameObject.getComponentOfType(Transform2D.class);

        assertTrue(returnedComponent.isPresent());
        assertEquals(component1.getPosition(), returnedComponent.get().getPosition());
    }

    @Test
    void testGetComponentFromGameObjectAsInterfaceType() {
        final var gameObject = new GameObjectImpl();

        gameObject.addComponent(new HealthComponentImpl(100));

        assertTrue(gameObject.getComponentOfType(HealthComponent.class).isPresent());
    }

    @Test
    void testGameObjectBuilder() {
        final var gameObject = new GameObjectImpl.BuilderImpl()
                .addComponent(new Transform2D())
                .addComponent(new HealthComponentImpl(100))
                .build();

        assertEquals(2, gameObject.getComponents().stream().count());
    }
}
