package clashclass.ai.behaviourtree.blackboard;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.troops.BattleTroopFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BlackboardTest {
    private static final String PROP_1 = "Prop1";
    private static final String PROP_2 = "Prop2";
    private static final String PROP_3 = "Prop3";
    
    @Test
    void testBlackboardProperties() {
        final var blackboard = new BlackboardImpl();
        final var troopFactory = new BattleTroopFactoryImpl();

        final var property1 = new BlackboardPropertyImpl<>(1, Integer.class);
        final var property2 = new BlackboardPropertyImpl<>("Test", String.class);
        final var property3 = new BlackboardPropertyImpl<>(troopFactory.createBarbarian(Vector2D.zero()), GameObject.class);

        assertFalse(blackboard.hasProperty(PROP_1));

        blackboard.setProperty(PROP_1, property1);

        assertTrue(blackboard.hasProperty(PROP_1));

        blackboard.setProperty(PROP_2, property2);
        blackboard.setProperty(PROP_3, property3);

        assertEquals(1, blackboard.getProperty(PROP_1, Integer.class).getValue());
        assertEquals("Test", blackboard.getProperty(PROP_2, String.class).getValue());
    }
}
