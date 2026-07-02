package model.player;

import static model.attribute.Trait.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

import controller.resources.Resources;
import model.attribute.*;
import static model.attribute.Feature.*;
import model.character.*;
import model.character.Character;

class TestPlayer {

    @Test
    public void testPlayer() {
        assertThrows(IllegalArgumentException.class, () -> new PlayerImpl(Resources.getCharacters(), null));
        //assertThrows(IllegalArgumentException.class, () -> new PlayerImpl(null, 1));
        assertThrows(IllegalArgumentException.class, () -> new PlayerImpl(new HashSet<>(), 1));
        final Player p = new PlayerImpl(Resources.getCharacters(), 3);
        assertThrows(IllegalStateException.class, () -> p.getSelected());
        assertThrows(IllegalArgumentException.class, () -> p.select(null));
        p.select("rufy");
        assertThrows(IllegalStateException.class, () -> p.select("robin"));
        p.decreaseAttempts();
        assertEquals(2, p.getRemainingAttempts());
        System.out.println("Test Player");
        System.out.println("Selected: " + System.lineSeparator() + p.getSelected());
        System.out.println("Availables: " + System.lineSeparator() + p.getAvailables());
        final Attribute rufyHairs = AttributeFactory.from(HAIRS, Color.BLACK, Length.SHORT);
        final Character rufy = new CharacterBuilder().setName("rufy")
                .addAll(rufyHairs, AttributeFactory.from(GENDER, Gender.MALE), AttributeFactory.fromString("a hat")).build();
        assertTrue(p.getSelected().equals(rufy));
        assertTrue(p.getAvailables().equals(Resources.getCharacters()));
        p.filter(AttributeFactory.from(HAIRS, Length.SHORT), true);
        System.out.println("Filter from short hairs: ");
        System.out.println("Availables: " + System.lineSeparator() + p.getAvailables());
        final Attribute zoroHairs = AttributeFactory.from(HAIRS, Color.GREEN, Length.SHORT);
        final Character zoro = new CharacterBuilder().setName("zoro")
                .addAll(zoroHairs, AttributeFactory.from(GENDER, Gender.MALE)).build();
        assertTrue(p.getAvailables().contains(rufy) && p.getAvailables().contains(zoro));
        p.remove("zoro");
        System.out.println("Remove zoro: ");
        System.out.println("Availables: " + System.lineSeparator() + p.getAvailables() + System.lineSeparator());
    }

}
