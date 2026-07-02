package model.character;

import static model.attribute.Trait.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import model.attribute.*;
import static model.attribute.Feature.*;

class TestCharacter {

    @Test
    public void testCharacter() {
        final Attribute rufyHairs = AttributeFactory.from(HAIRS, Color.BLACK, Length.SHORT);
        assertThrows(IllegalArgumentException.class, 
                () -> new CharacterImpl(null, new HashSet<>(Arrays.asList(rufyHairs))));
        //assertThrows(IllegalArgumentException.class, () -> new CharacterImpl("null", null));
        final Character rufy = new CharacterImpl("rufy", 
                            new HashSet<>(Arrays.asList(rufyHairs, AttributeFactory.fromString("male"), 
                                    AttributeFactory.fromString("a hat"))));
        assertTrue(rufy.getName().equals("rufy"));
        assertTrue(rufy.has(rufyHairs));
        assertTrue(rufy.has(AttributeFactory.fromString("black hairs")));
        assertTrue(rufy.has(AttributeFactory.from(HAT)));
        assertTrue(rufy.has(AttributeFactory.fromString("male")));
        assertFalse(rufy.has(AttributeFactory.from(GLASSES)));
        System.out.println("Test Character" + System.lineSeparator() + rufy + System.lineSeparator());
    }

}
