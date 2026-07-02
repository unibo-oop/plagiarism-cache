package model;

import static org.junit.jupiter.api.Assertions.*;
import static model.attribute.Feature.*;
import static model.attribute.Trait.*;
import org.junit.jupiter.api.Test;
import model.ai.*;
import model.attribute.*;
import model.character.*;
import model.character.Character;

class TestUtilities {

    @Test
    public void testAi() {
        assertThrows(IllegalArgumentException.class, () -> new Ai(Ability.ADVANCED).apply(null));
    }

    @Test
    public void testAttributes() {
        final Attribute blackShortHairs = AttributeFactory.from(HAIRS, Color.BLACK, Length.SHORT);
        assertTrue(blackShortHairs.equals(AttributeFactory.fromString("black short hairs")));
        assertTrue(blackShortHairs.equals(AttributeFactory.fromQuestion("Does he/she have black short hairs?")));
    }

    @Test
    public void testCharacterBuilder() {
       final CharacterBuilder cBuilder = new CharacterBuilder();
       assertThrows(IllegalStateException.class, () -> cBuilder.build());
       //assertThrows(IllegalArgumentException.class, () -> cBuilder.setName(null));
       //assertThrows(IllegalArgumentException.class, () -> cBuilder.add(null));
       cBuilder.setName("c");
       cBuilder.add(AttributeFactory.from(HAIRS, Color.BLACK, Feature.HairStyle.CURLY));
       cBuilder.add(AttributeFactory.from(BEARD));
       final Character c = cBuilder.build();
       assertThrows(IllegalStateException.class, () -> cBuilder.setName(""));
       assertThrows(IllegalStateException.class, () -> cBuilder.add(AttributeFactory.from(HAT)));
       assertThrows(IllegalStateException.class, () -> cBuilder.build());
       assertTrue(c.has(AttributeFactory.fromString("black curly hairs")));
       assertTrue(c.has(AttributeFactory.fromString("a beard")));
    }

}
