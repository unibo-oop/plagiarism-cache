package org.gitgud.application.about;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * The implementation of about view.
 */
public class AboutBoxFxmlController implements AboutBoxView {

    private static final int PADDING = 50;
    private AboutBoxController ctrl;
    @FXML
    private ImageView image; // NOPMD
    @FXML
    private VBox infoVbox;

    @Override
    public void attachController(final AboutBoxController ctrl) {
        this.ctrl = ctrl;
    }

    @FXML
    private void btnCloseOnAction(final ActionEvent event) { // NOPMD
        ctrl.closeBox();
    }

    @Override
    public void showLink(final String title, final String link) {
        final HBox repo = new HBox();
        repo.setPadding(new Insets(0, PADDING, 0, PADDING));
        final Region reg = new Region();
        final Hyperlink hl = new Hyperlink(link);
        hl.setOnAction(event -> ctrl.openWebLink(link));
        hl.getStyleClass().add("hyperlink");
        final Label t = new Label(title);
        t.setAlignment(Pos.BASELINE_CENTER);
        reg.setPrefWidth(10);
        repo.getChildren().addAll(t, reg, new Region(), hl);
        infoVbox.getChildren().add(repo);
    }

}
