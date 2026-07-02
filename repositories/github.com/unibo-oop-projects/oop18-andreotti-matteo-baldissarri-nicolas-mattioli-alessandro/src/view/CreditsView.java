package view;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.MenuController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * 
 * This class create the Options menu view.
 *
 */
public class CreditsView extends StackPane {
    private static final double FONT_SIZE = Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.025;
    private static final double PADDING = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 80;

    private final MenuTitle title = new MenuTitle(3.7, 4);
    private final MenuBox rolesBox = new MenuBox();
    private final MenuBox peopleBox = new MenuBox(2.5, 2);
    private final List<Label> labels = new ArrayList<>();
    private final List<Label> peopleLabels = new ArrayList<>();
    private final MenuButton back = new MenuButton("BACK");

    /**
     * 
     * @param controller Main Controller of the application.
     * @param scene      Main scene.
     */
    public CreditsView(final MenuController controller, final Scene scene) {
        super();
        this.labels.addAll(Arrays.asList(new Label("GRAPHICS"), new Label("STUNTMAN"), new Label("ENEMIES"),
                new Label("SCORE & BONUS")));
        this.labels.forEach(label -> {
            label.setPadding(new Insets(0, 0, PADDING, 0));
            label.setStyle(String.format("-fx-font-size: %dpx;", (int) ((FONT_SIZE))));
        });
        this.peopleLabels.addAll(Arrays.asList(new Label("Nicolas Baldassarri"), new Label("Matteo Andreotti"),
                new Label("Alessandro Mattioli"), new Label("Anna Mingaroni")));
        this.peopleLabels.forEach(label -> {
            label.setPadding(new Insets(0, 0, PADDING, 0));
            label.setStyle(String.format("-fx-font-size: %dpx;", (int) ((FONT_SIZE))));
        });

        this.back.setOnAction(e -> {
            controller.goToMainMenu();
        });

        this.peopleBox.getChildren().addAll(peopleLabels);
        this.rolesBox.getChildren().addAll(labels);
        this.rolesBox.getChildren().add(back);
        this.getChildren().addAll(title, rolesBox, peopleBox);
        this.setId("mainPane");
        this.getStylesheets().add("style.css");
        scene.setRoot(this);
    }
}
