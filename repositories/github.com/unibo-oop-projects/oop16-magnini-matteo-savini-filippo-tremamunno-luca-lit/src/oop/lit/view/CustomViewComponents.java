package oop.lit.view;

import java.util.stream.Collectors;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import oop.lit.util.FileType;

/**
 * A factory for view components.
 */
public final class CustomViewComponents {
    private static final double BUTTON_FONT_SIZE = 18.0;
    private static final double HBOX_SEPARATION = 5;

    private CustomViewComponents() {
    }

    /**
     * @param fileType
     *            the type of file needed.
     * @param title
     *      the chooser window title.
     * @return
     *      a file chooser containing a filter for the specified file type
     */
    public static FileChooser getFileChooser(final FileType fileType, final String title) {
        final FileChooser chooser = new FileChooser();
        chooser.setTitle(title);
        chooser.getExtensionFilters().addAll(
                new ExtensionFilter(fileType.getTypeName(), fileType.getNameExtensions().stream()
                        .map(ext -> "*." + ext).collect(Collectors.toList())),
                new ExtensionFilter("Show all", "*"));
        return chooser;
    }

    /**
     * Get a HBox containing a label with the provided text, and the provided nodes.
     * @param label
     *      the label text.
     * @param nodes
     *      some nodes to be labelled.
     * @return
     *      the HBox.
     */
    public static HBox labelNode(final String label, final Node... nodes) {
        final HBox box = new HBox(HBOX_SEPARATION);
        final Label fxLabel = new Label(label + ":");
        box.getChildren().addAll(fxLabel);
        box.getChildren().addAll(nodes);
        return box;
    }

    /**
     * @param text
     *      the button text.
     * @return
     *      a button with custom graphic.
     */
    public static Button mainButton(final String text) {
        final Button b = new Button(text);
        b.setBackground(new Background(new BackgroundFill(Color.WHEAT, null, null)));
        b.setFont(new Font("Bauhaus 93", BUTTON_FONT_SIZE));
        b.setEffect(new DropShadow());
        return b;
    }
}
