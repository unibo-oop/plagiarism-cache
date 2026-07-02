package controller.resources;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import controller.gameoptions.Pack;
import model.attribute.*;
import model.character.*;
import model.character.Character;


class TestCharacterLoader {

    private static final int CHARACTERS = 24;
    private static final int GLASSES = 5;
    private static final int WHITE_HAIRS = 5;
    private static final int NMALE = 19;

    @Test
    public void testLoadingCharacter() {
        try {
            final Set<Character> characters = CharacterLoader.loadCharacters(Pack.CLASSIC.toString());
            final CharacterBuilder builder = new CharacterBuilder();

            builder.add(AttributeFactory.fromString("hairs long straight white"));
            builder.add(AttributeFactory.fromString("gender female"));
            builder.add(AttributeFactory.fromString("eyes brown"));
            builder.add(AttributeFactory.fromString("mouth big"));
            builder.add(AttributeFactory.fromString("nose small"));
            builder.setName("Susana");

            final CharacterBuilder builder2 = new CharacterBuilder();
            builder2.add(AttributeFactory.fromString("glasses"));
            builder2.add(AttributeFactory.fromString("gender male"));
            builder2.add(AttributeFactory.fromString("nose small"));
            builder2.setName("prova");
            final Character prova = builder2.build();

            final Character susana = builder.build();
            assertTrue(susana.getAttributes().stream().filter(a -> a.getTrait().equals(Trait.GENDER) && a.getFeatures().contains(Feature.Gender.FEMALE)).count() == 1);
            assertTrue(prova.getAttributes().stream().filter(a -> a.getTrait().equals(Trait.GENDER) && a.getFeatures().contains(Feature.Gender.MALE)).count() == 1);
            assertTrue(characters.stream().map(c -> c.getAttributes()).
                    filter(a -> a.stream().anyMatch(b -> b.getTrait().equals(Trait.GENDER)
                            && b.getFeatures().contains(Feature.Gender.MALE))).count() == NMALE);
            assertTrue(characters.size() == CHARACTERS);
            assertTrue(characters.stream().map(c -> c.getAttributes()).
                    filter(a -> a.stream().anyMatch(b -> b.getTrait().equals(Trait.HAIRS)
                            && b.getFeatures().contains(Feature.Color.WHITE))).count() == WHITE_HAIRS);
            assertTrue(characters.stream().filter(c -> c.getName().equals("Alejandro")).count() == 1);
            assertTrue(characters.contains(susana));
            assertFalse(characters.contains(prova));
            assertThrows(UnsupportedOperationException.class, () -> characters.add(prova));
            assertTrue(characters.stream().map(c -> c.getAttributes()).
                    filter(a -> a.stream().anyMatch(b -> b.getTrait().equals(Trait.HAIRS)
                            && b.getFeatures().contains(Feature.Color.WHITE))).count() == WHITE_HAIRS);
            assertTrue(characters.stream().map(c -> c.getAttributes()).
                    filter(a -> a.stream().anyMatch(b -> b.getTrait().equals(Trait.MOUSTACHE)
                            && b.getFeatures().contains(Feature.Color.BROWN))).count() == 2);
            assertTrue(characters.stream().map(c -> c.getAttributes()).
                    filter(a -> a.stream().anyMatch(b -> b.getTrait().equals(Trait.GLASSES)))
                    .count() == GLASSES);
        } catch (IOException | SAXException | ParserConfigurationException | URISyntaxException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testException() {
        assertThrows(IllegalArgumentException.class, () -> CharacterLoader.loadCharacters(null));
        assertThrows(Exception.class, () -> CharacterLoader.loadCharacters("prova.xml"));
    }

    @Test
    public void sequentialLoading() {
        try {
            final Set<Character> classicCharacters = CharacterLoader.loadCharacters(Pack.CLASSIC.toString());
            final Set<Character> pulpFictionCharacters = CharacterLoader.loadCharacters(Pack.PULP_FICTION.toString());
            assertTrue(classicCharacters.containsAll(CharacterLoader.loadCharacters(Pack.CLASSIC.toString())));
            assertTrue(classicCharacters.stream().anyMatch(c -> !pulpFictionCharacters.contains(c)));
        } catch (SAXException | IOException | ParserConfigurationException | URISyntaxException e) {
            fail();
            e.printStackTrace();
        }
    }
}
