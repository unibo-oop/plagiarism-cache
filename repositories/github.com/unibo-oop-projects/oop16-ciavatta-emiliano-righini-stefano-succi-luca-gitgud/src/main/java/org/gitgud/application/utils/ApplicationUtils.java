package org.gitgud.application.utils;

import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.icons.IconLoader;
import org.gitgud.icons.IconType;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.utils.FileUtils;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 * Class that contains some application utils.
 */
public final class ApplicationUtils {

    private static final int CHANGE_TYPE_ICON_SIZE = 20;
    private static final ApplicationProperty APPLICATION_PROPERTIES = new ApplicationPropertyImpl();

    private ApplicationUtils() {
    }

    /**
     * Create a new image view that contains a change type image.
     *
     * @param changeType
     *            the change type of the icon to create
     * @return the new icon created
     */
    public static ImageView createChangeTypeIcon(final ChangeType changeType) {
        String imageName;

        switch (changeType) {
            case ADDED:
                imageName = "added";
                break;
            case COPIED:
                imageName = "copied";
                break;
            case DELETED:
                imageName = "deleted";
                break;
            case MODIFIED:
                imageName = "modified";
                break;
            case RENAMED:
                imageName = "renamed";
                break;
            default:
                imageName = "";
        }

        return createIcon(imageName, IconType.ICON_COLORED, CHANGE_TYPE_ICON_SIZE);
    }

    /**
     * Create a new label that contains the change type string.
     *
     * @param changeType
     *            the change type of the label to create
     * @param isShort
     *            true to show only the first letter
     * @return the new label created
     */
    public static Label createChangeTypeLabel(final ChangeType changeType, final boolean isShort) {
        String text = Utils.resolveString(ResourceType.LABELS, changeType.getLabelKey());
        if (isShort) {
            text = text.substring(0, 1); // Take only the first character
        }

        final Label l = new Label(text);
        l.getStyleClass().add("change-type-label");
        switch (changeType) {
            case ADDED:
                l.getStyleClass().add("change-type-added");
                break;
            case COPIED:
                l.getStyleClass().add("change-type-copied");
                break;
            case DELETED:
                l.getStyleClass().add("change-type-deleted");
                break;
            case MODIFIED:
                l.getStyleClass().add("change-type-modified");
                break;
            case RENAMED:
                l.getStyleClass().add("change-type-renamed");
                break;
            default:
        }

        return l;
    }

    /**
     * Create a new color manager.
     *
     * @return the color manager created
     */
    public static ColorManager createColorManager() {
        return new ColorManagerImpl();
    }

    /**
     * Create a label with an icon before.
     *
     * @param text
     *            the text of the label
     * @param image
     *            the image of the icon
     * @param gap
     *            the gap between label and icon
     * @return the new label created
     */
    public static Label createGraphicLabel(final String text, final Image image, final int gap) {
        final Label l = new Label(text);
        l.setGraphic(new ImageView(image));
        l.setGraphicTextGap(gap);

        return l;
    }

    /**
     * Create a label with an icon before.
     *
     * @param text
     *            the text of the label
     * @param imageView
     *            the image view of the icon
     * @param gap
     *            the gap between label and icon
     * @return the new label created
     */
    public static Label createGraphicLabel(final String text, final ImageView imageView, final int gap) {
        final Label l = new Label(text);
        l.setGraphic(imageView);
        l.setGraphicTextGap(gap);

        return l;
    }

    /**
     * Create a new image view from an image.
     *
     * @param image
     *            the image of the icon
     * @param size
     *            the icon size
     * @return the new image view created
     */
    public static ImageView createIcon(final Image image, final int size) {
        final ImageView icon = new ImageView(image);
        icon.setFitWidth(size);
        icon.setFitHeight(size);

        return icon;
    }

    /**
     * Create a new image view from an icon type.
     *
     * @param name
     *            the name of the icon
     * @param type
     *            the type of the icon
     * @param size
     *            the size of the icon
     * @return the new image view created
     */
    public static ImageView createIcon(final String name, final IconType type, final int size) {
        final ImageView icon = new ImageView(createImageIcon(name, type));
        icon.setFitWidth(size);
        icon.setFitHeight(size);

        return icon;
    }

    /**
     * Create a new image icon.
     *
     * @param name
     *            the name of the icon
     * @param type
     *            the type of the icon
     * @return the new image created
     */
    public static Image createImageIcon(final String name, final IconType type) {
        String imagePath;

        if (type == IconType.ICON_LIGHT) {
            imagePath = "light/";
        } else if (type == IconType.ICON_DARK) {
            imagePath = "dark/";
        } else if (type == IconType.ICON_COLORED) {
            imagePath = "colored/";
        } else {
            imagePath = "";
        }

        imagePath = imagePath.concat(name).concat(".png");

        try {
            return new Image(IconLoader.class.getResourceAsStream(imagePath));
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Can't find icon " + name + " of type " + type.name());
        }
    }

    /**
     * @return the application properties
     */
    public static ApplicationProperty getApplicationProperties() {
        return APPLICATION_PROPERTIES;
    }

    /**
     * Return the author initial of a name.
     *
     * @param name
     *            the name of the person
     * @return the initials
     */
    public static String getAuthorInitials(final String name) {
        if (!name.trim().contains(" ")) {
            return "";
        }

        final String[] parts = name.trim().split(" ");
        String returnValue = "";

        if (parts != null && parts.length > 0) {
            for (final String s : parts) {
                returnValue += s.substring(0, 1);
            }
        }
        return returnValue;
    }

    /**
     * Shorten a string of a certain font of a max length.
     *
     * @param text
     *            the text to shortener
     * @param font
     *            the font of the label that contains the text
     * @param maxLength
     *            the max text length, in pixel
     * @return the new string created
     */
    public static String pathLabelShortener(final String text, final Font font, final double maxLength) {
        final FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        String shortPath = text;

        int threshold = 10;
        while (threshold >= 0 && fontLoader.computeStringWidth(shortPath, font) > maxLength) {
            shortPath = FileUtils.pathShortener(text, threshold--);
        }

        return shortPath;
    }

    /**
     * @param nodes
     *            the nodes to set invisible
     */
    public static void setInvisible(final Node... nodes) {
        for (final Node n : nodes) {
            n.setVisible(false);
            n.setManaged(false);
        }
    }

    /**
     * @param nodes
     *            the nodes to set visible
     */
    public static void setVisible(final Node... nodes) {
        for (final Node n : nodes) {
            n.setVisible(true);
            n.setManaged(true);
        }
    }

}
