package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.utils.ImageViewObject;

public abstract class AbstractMenuView extends Scene{

    protected Stage stage;
    private static final Font TITLE_FONT = new Font("Algerian", 30);
    private static final Insets TITLE_PADDING = new Insets(14);
    private static final Font BACK_BUTTON_FONT = new Font("Serif", 20);
    //credits highscore
    public AbstractMenuView(BorderPane layout, Stage stage) {
        super(layout);
        this.stage = stage;
        this.stage.setTitle(getTitle());

        //aggiungiamoci uno sfondo
        layout.setCenter(centerPane());
        layout.setBottom(bottomPane());
        layout.setTop(topPane());
    }

    protected abstract Node centerPane();

    protected Node topPane() {
        FlowPane p = new FlowPane();
        p.setAlignment(Pos.CENTER);

        Label title = new Label(getTitle());
        title.setFont(TITLE_FONT);
        title.setTextFill(Color.DARKBLUE);
        title.setPadding(TITLE_PADDING);

        p.getChildren().add(title);

        return p;
    }

    private Node bottomPane() {
        FlowPane p = new FlowPane();
        p.setPadding(TITLE_PADDING);
        Button b = new Button("Back to menù");
        b.setFont(BACK_BUTTON_FONT);
        b.setContentDisplay(ContentDisplay.RIGHT);
        ImageView v = new ImageView(ImageViewObject.BACK_ICON.getImage());
        v.setFitHeight(TITLE_FONT.getSize());
        v.setPreserveRatio(true);
        b.setGraphic(v);

        b.setOnAction(e -> new MainMenu(this.stage));

        p.setAlignment(Pos.CENTER_RIGHT);
        p.getChildren().add(b);

        return p;
    }

    protected abstract String getTitle();
}
