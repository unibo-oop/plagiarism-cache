package view.dialogs;

import java.util.Set;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.cards.Card;
import utilities.enumerations.IconType;

/**
 * Dialog which show your cards.
 */
public final class CardsDialog extends Dialog {

    private static final CardsDialog SINGLETON = new CardsDialog();

    /**
     * Instance of CardsDialog.
     * 
     * @return the instance
     */
    public static CardsDialog getDialog() {
        return SINGLETON;
    }

    /**
     * Creation of the pane for the dialog.
     * 
     * @param set
     *            set of cards associated to a player.
     */
    public void createCardsDialog(final Set<Card> set) {
        final Stage dialog = setStage();
        final GridPane rootPane = new GridPane();
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setPadding(new Insets(getPadding()));
        rootPane.setHgap(getPadding());
        rootPane.setVgap(getPadding());
        rootPane.setMinWidth(getScreenW() * 0.5);
        rootPane.setMaxWidth(getScreenW() * 0.5);
        rootPane.setStyle("-fx-background-color: #" + IconType.WINNER.getBackgound() + ";");

        final Label title = new Label("Your cards");
        GridPane.setHalignment(title, HPos.CENTER);
        rootPane.add(title, 0, 0);
        title.getStyleClass().add("title");

        final FlowPane body = new FlowPane();
        body.setMinWidth(getScreenW() * 0.5);
        body.setAlignment(Pos.CENTER);
        for (final Card c : set) {
            final ImageView imgCard = new ImageView();
            imgCard.setPreserveRatio(true);
            imgCard.setFitHeight(getImgCard());
            imgCard.setImage(new Image(c.getImagePath()));
            final Tooltip tooltip = new Tooltip();
            tooltip.setText(c.toString());
            Tooltip.install(imgCard, tooltip);
            body.getChildren().add(imgCard);
        }
        rootPane.add(body, 0, 1);
        GridPane.setHalignment(body, HPos.CENTER);

        final Button btnOk = new Button("CLOSE");
        GridPane.setHalignment(btnOk, HPos.CENTER);
        rootPane.add(btnOk, 0, 2);

        btnOk.setOnAction(e -> {
            dialog.close();
        });

        final Scene scene = new Scene(rootPane);
        scene.getStylesheets().add("/style/style.css");
        dialog.setScene(scene);
        dialog.show();
    }
}