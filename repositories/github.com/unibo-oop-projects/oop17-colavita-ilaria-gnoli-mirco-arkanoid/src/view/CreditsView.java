package view;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CreditsView extends AbstractMenuView {

    private static final String TITLE = "Credits";
    private static final Font FONT = Font.font("Jokerman", 30);

    public CreditsView(BorderPane layout, Stage stage) {
        super(layout, stage);
    }

    @Override
    protected Node centerPane() {
        VBox p = new VBox();

        Label l = new Label("Gnoli Mirco\n\nColavita Ilaria");
        l.setFont(FONT);
        l.setTextFill(Color.DARKORCHID);

        RotateTransition rot = new RotateTransition();
        rot.setDuration(Duration.seconds(4));
        rot.setToAngle(360);
        rot.setNode(l);

        TranslateTransition trs = new TranslateTransition();
        trs.setDuration(Duration.seconds(4));

        trs.setToX(this.stage.getWidth() / 2);
        trs.setToY(this.stage.getWidth() / 2);
        trs.setNode(l);

        p.getChildren().add(l);

        rot.play();
        trs.play();
        return p;
    }

    @Override
    protected String getTitle() {
        return TITLE;
    }
}
