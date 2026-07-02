package controller.resources;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.IntStream;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import model.attribute.*;
import model.character.*;
import model.character.Character;
import utilities.Utilities;

final class CharacterLoader {

    private static final String FILE_EXTENSION = ".xml";

    private CharacterLoader() {
    }

    public static Set<Character> loadCharacters(final String file) throws SAXException, IOException, ParserConfigurationException, URISyntaxException {
        Utilities.requireNonNull(file);
        final Set<Character> characters = new HashSet<>();
        final Element rootElement = (Element) DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .parse(new File(CharacterLoader.class.getResource(System.getProperty("file.separator") + file + FILE_EXTENSION).toURI()))
                        .getElementsByTagName("characters")
                        .item(0);
        final NodeList charactersList = rootElement.getElementsByTagName("character");
        IntStream.range(0, charactersList.getLength()).forEach(n -> {
            final Element character = (Element) charactersList.item(n);
            characters.add(new CharacterBuilder()
                        .setName(character.getAttribute("name"))
                        .addAll(getAttributes(character))
                        .build());
        });
        return Collections.unmodifiableSet(characters);
    }

    private static List<Attribute> getAttributes(final Element characterElement) {
        final List<Attribute> attributes = new LinkedList<>();
        Arrays.stream(Trait.values()).forEach(trait -> {
            final String features = characterElement.getAttribute(trait.toString());
            if (!features.isEmpty()) {
                attributes.add(AttributeFactory.from(trait, features.equals("yes") ? Collections.emptySet() 
                        : AttributeFactory.getFeatures(features, trait)));
            }
        });
        return attributes;
    }

}
