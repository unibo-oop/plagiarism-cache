package controller.resources;

import java.awt.Image;
import java.io.IOException;
import java.util.*;
import javax.swing.ImageIcon;
import model.character.Character;
import utilities.Utilities;

/**
 * Utility class that allows to load and get the resources for the Game.
 * Note that to properly use this class "load" method should be called before anything else,
 * any the getter method throws an IllegalStateException if called before the loading of the resources.
 */
public final class Resources {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String IMAGES_FOLDER = FILE_SEPARATOR + "images" + FILE_SEPARATOR;
    private static final String IMAGE_EXTENSION = ".png";
    private static final String DEFAULT_IMAGE_NAME = "?";
    private static final int IMAGE_WIDTH_PROPORTION = (int) Math.round(Utilities.getScreenRatio() * 10);

    private static Image defaultImage;
    private static Map<Character, Image> res = new HashMap<>();

    private Resources() {
    }

    /**
     * Allows to get the default Image (i.e. "?").
     * @return the image
     */
    public static Image getDefaultImage() {
        Resources.checkLoaded();
        return defaultImage;
    }

    /**
     * Allows to get a Character's Image by giving his/her name.
     * @param name
     *              the name
     * @return the Image
     */
    public static Image getCharacterImage(final String name) {
        Utilities.requireNonNull(name);
        Resources.checkLoaded();
        return res.get(res.keySet().stream().filter(character -> character.getName().equals(name)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("the name does not match any character :" + name)));
    }

    /**
     * Allows to get the Map of all names and Images.
     * @return the map of names and images
     */
    public static Map<String, Image> getNamesAndImages() {
        Resources.checkLoaded();
        final Map<String, Image> out = new HashMap<>();
        res.entrySet().stream().forEach(entry -> out.put(entry.getKey().getName(), entry.getValue()));
        return out;
    }
    /**
     * Allows to get the full Set of Characters.
     * @return the Set of Character
     */
    public static Set<Character> getCharacters() {
        Resources.checkLoaded();
        return Collections.unmodifiableSet(res.keySet());
    }

    /**
     * Allows to properly load and store resources.
     * @param pack
     *              the pack by which load characters
     * @throws LoadingException in case of errors during the loading process.
     */
    public static void load(final String pack) throws LoadingException {
        res.clear();
        try {
            for (final Character character : CharacterLoader.loadCharacters(pack)) {
                res.put(character, Resources.loadAndScale(pack + FILE_SEPARATOR + character.getName(), IMAGE_WIDTH_PROPORTION));
            }
            final ImageIcon test = new ImageIcon(res.values().iterator().next());
            defaultImage = new ImageIcon(Resources.class.getResource(IMAGES_FOLDER + DEFAULT_IMAGE_NAME + IMAGE_EXTENSION)).getImage()
                .getScaledInstance(test.getIconWidth(), test.getIconHeight(), Image.SCALE_SMOOTH);
        } catch (Exception e) {
            throw new LoadingException("Error while loading resources" + System.lineSeparator() + e.getMessage());
        }
    }

    private static void checkLoaded() {
        if (res.isEmpty() || defaultImage == null) {
            throw new IllegalStateException("Resources not loaded: call loadResources method in order to get them");
        }
    }

    /*
     * Allows to load and scale an image, given the name of the image and the width proportion.
     */
    private static Image loadAndScale(final String name, final int widthProportion) throws IOException {
        return new ImageIcon(Resources.class.getResource(IMAGES_FOLDER + name + IMAGE_EXTENSION)).getImage()
                .getScaledInstance(Utilities.getScreenDimension().width / widthProportion, -1, Image.SCALE_SMOOTH);
    }

    /*----------- DEMO PACK ------------
    private static Set<Character> demoPack() {
        final Attribute rufyHairs = AttributeFactory.from(Trait.HAIRS, Feature.Color.BLACK, Feature.Length.SHORT);
        final Character rufy = new CharacterBuilderImpl().setName("rufy")
                .addAll(rufyHairs, AttributeFactory.from(Trait.GENDER, Feature.Gender.MALE), AttributeFactory.fromString("a hat"))
                .build();
        final Attribute zoroHairs = AttributeFactory.from(Trait.HAIRS, Feature.Color.GREEN, Feature.Length.SHORT);
        final Character zoro = new CharacterBuilderImpl().setName("zoro")
                .addAll(zoroHairs, AttributeFactory.from(Trait.GENDER, Feature.Gender.MALE))
                .build();
        final Attribute robinHairs = AttributeFactory.from(Trait.HAIRS, Feature.Color.BLACK, 
                Feature.Length.LONG, Feature.HairStyle.STRAIGHT);
        final Character robin = new CharacterBuilderImpl().setName("robin")
                .addAll(robinHairs, AttributeFactory.from(Trait.GENDER, Feature.Gender.FEMALE), AttributeFactory.fromString("glasses"))
                .build();
        return new HashSet<>(Arrays.asList(rufy, zoro, robin));
    }
    ----------------------------------*/

}
