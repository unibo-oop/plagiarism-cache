package controller;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.objects.GameObject;
import model.objects.structures.Structure;
import model.objects.terrains.Terrain;
import model.objects.unit.Unit;
import controller.selection.Selection;

/**
 * This class is package protected.
 * 
 * The images to be drawn in the view are retrieved from model object ID's in
 * the following way: the model objects are identified by an ID with the
 * following form: (Terrain/Pg),(specific Pg/Terrain class name); the selection
 * type is identified by its enum name and by the enum toString; resources to
 * draw are contained in the sprites folder which contains: -a pg folder; -a
 * terrain folder; -a selection folder. In each of them there are images named
 * after the class they represent whit the following form: ClassName ->
 * class_name
 */
public final class ModelToViewConverterUtils {

    // info about the directory containing the sprites and the sprites file
    // extension
    private static final String SEPARATOR = "file.separator";
    private static final String SPRITES_PATH = "sprites" + System.getProperty(SEPARATOR);
    private static final String SPRITES_EXTENSION = ".png";
    private static final String MENU_PATH = "menu" + System.getProperty(SEPARATOR);
    private static final String MAIN_MENU_PATH = MENU_PATH + "main" + System.getProperty(SEPARATOR);
    private static final String SELECTION_PATH = "selection" + System.getProperty(SEPARATOR);

    private ModelToViewConverterUtils() {
    }

    static String modelMainMenuToViewId(final String name, final String extension) {
        final StringBuilder result = new StringBuilder(MAIN_MENU_PATH);
        result.append(getSpacedFromCamelCase(name));
        result.append(extension);
        return result.toString();
    }

    /**
<<<<<<< HEAD
     * This method can be used to take the id in string form.
     * @param from is the GameObject.
     * @return the id to string
=======
     * 
     * @param from the GameObject to convert in string for the view
     * @return the string to be passed to the view
>>>>>>> 581e898fbf295d463f624d5cc2f2d3b7256c88d9
     */
    public static String modelObjectToViewId(final GameObject from) {
        final StringBuilder result = new StringBuilder(SPRITES_PATH);
        if (from instanceof Terrain) {
            result.append("terrain");
        } else {
            if (from.getOwner().isPresent()) {
                result.append(getSpacedFromCamelCase(from.getOwner().get().getRace().getClass().getSimpleName()));
            } else {
                result.append("neutral");
            }
            result.append(System.getProperty("file.separator"));
            if (from instanceof Unit) {
                result.append(getSpacedFromCamelCase(Unit.class.getSimpleName()));
            } else if (from instanceof Structure) {
                result.append(getSpacedFromCamelCase(Structure.class.getSimpleName()));
            }
        }
        result.append(System.getProperty("file.separator"));
        result.append(getSpacedFromCamelCase(from.getClass().getSimpleName()));
        result.append(SPRITES_EXTENSION);

        return result.toString();
    }

    /**
<<<<<<< HEAD
     * This method can be used to take the id in string form.
     * @param from is the Selection.
     * @return the id to string
=======
     * 
     * @param from the GameObject to convert in string for the view
     * @return the string to be passed to the view
>>>>>>> 581e898fbf295d463f624d5cc2f2d3b7256c88d9
     */
    public static String modelSelectionToViewId(final Selection from) {
        final StringBuilder result = new StringBuilder(SPRITES_PATH);
        result.append(SELECTION_PATH);
        result.append(from.getId());
        result.append(SPRITES_EXTENSION);
        return result.toString();
    }

    /**
     * @param camelCase CamelCase
     * @return camel_case
     */
    static String getSpacedFromCamelCase(final String camelCase) {
        return Stream.of(camelCase.split("(?=\\p{Upper})")).collect(Collectors.joining("_")).toLowerCase();
    }
}
