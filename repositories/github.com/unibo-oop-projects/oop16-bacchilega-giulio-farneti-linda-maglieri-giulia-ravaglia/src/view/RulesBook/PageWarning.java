package view.RulesBook;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.Utilities.Utilities;

/**
 * Class that implements an advertisement pop-up when the page is the first or the last
 * 
 * Author: Linda Farneti.
 *
 */
public final class PageWarning {

    private Stage stage;
    private GridPane pane;
    private Scene scene;
    private Label label;
    private Button button;
    private static final PageWarning PAGEWARNING = new PageWarning();

    /**
     * Constructor.
     */
    private PageWarning() {
        stage = new Stage();
        pane = new GridPane();
    }

    /**
     * Method that create the pop-up.
     * 
     * @param text of the message
     */
    public void display(final String text) {
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);

        scene = new Scene(pane, Utilities.POPUP_WIDTH, Utilities.POPUP_HEIGHT / 2);
        label = new Label(text);

        pane.setVgap(1);
        pane.setHgap(2);

        button = new Button("Ok");
        pane.add(label, 0, 0);
        pane.add(button, 0, 1);
        pane.setAlignment(Pos.CENTER);
        button.setOnAction(e -> {
            stage.close();
        });

        stage.setScene(scene);
        scene.getStylesheets().add(PageWarning.class.getResource("WarningStyle.css").toExternalForm());
        stage.show();

    }

    /**
     * Method that implements singleton pattern.
     * 
     * @return PAGEWARNING
     */
    public static synchronized PageWarning getLog() {
        return PAGEWARNING;
    }

}
