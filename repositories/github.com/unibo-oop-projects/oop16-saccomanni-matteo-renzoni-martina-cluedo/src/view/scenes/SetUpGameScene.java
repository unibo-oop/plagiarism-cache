package view.scenes;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import application.Controller;
import application.ViewObserver;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import utilities.Pair;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.PlayerType;

/**
 * Scene to start a new game where you can choose the Players characteristics.
 */
public final class SetUpGameScene {
    private static final double SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int COL1_WIDTH = 30;
    private static final int COL2_WIDTH = 40;
    private static final double INNERPANE_H = 0.65;

    /**
     * Constructor.
     */
    private SetUpGameScene() {
    }

    /**
     * Create the set up game scene.
     * 
     * @return pane pane to show.
     */
    public static Parent createPane() {
        final ViewObserver observer = Controller.getController();

        final GridPane rootPane = new GridPane();
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setVgap(COL1_WIDTH);
        rootPane.setPrefSize(SCREEN_W, SCREEN_H);

        final Label title = new Label("Choose the game characters");
        title.getStyleClass().add("title");
        rootPane.add(title, 0, 0);
        GridPane.setHalignment(title, HPos.CENTER);
        final Label subtitle = new Label("Minimum 3 players required");
        subtitle.getStyleClass().add("subtitle");
        rootPane.add(subtitle, 0, 1);
        GridPane.setHalignment(subtitle, HPos.CENTER);

        final GridPane innerPane = new GridPane();
        innerPane.setAlignment(Pos.CENTER);
        final ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(COL1_WIDTH);
        final ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(COL2_WIDTH);
        final ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(COL1_WIDTH);
        innerPane.getColumnConstraints().addAll(c1, c2, c3);
        rootPane.add(innerPane, 0, 2);

        final Label lblParticipate = new Label("Participates:");
        GridPane.setHalignment(lblParticipate, HPos.CENTER);
        innerPane.add(lblParticipate, 1, 0);
        final Label lblPlayerType = new Label("Type of player:");
        GridPane.setHalignment(lblPlayerType, HPos.CENTER);
        innerPane.add(lblPlayerType, 2, 0);

        final Map<CharacterCard, Pair<ComboBox<String>, ComboBox<String>>> selections = new HashMap<>();

        int i = 1;
        for (final CharacterCard character : CharacterCard.getCharacterCards()) {
            final Label lblName = (new Label(character.toString()));
            final ImageView imgCard = new ImageView(
                    new Image(MenuScene.class.getResourceAsStream(character.getImagePath())));
            imgCard.setPreserveRatio(true);
            imgCard.setFitHeight(SCREEN_H * INNERPANE_H / CharacterCard.getCharacterCards().size());
            lblName.setGraphic(imgCard);
            GridPane.setHalignment(lblName, HPos.LEFT);
            innerPane.add(lblName, 0, i);

            final ComboBox<String> cmbParticipate = new ComboBox<String>();
            cmbParticipate.getItems().addAll("NO", "YES");
            cmbParticipate.setPrefWidth(100);
            cmbParticipate.setValue("NO");
            GridPane.setHalignment(cmbParticipate, HPos.CENTER);
            innerPane.add(cmbParticipate, 1, i);

            final ComboBox<String> cmbPlayerType = new ComboBox<String>();
            cmbPlayerType.getItems().addAll("HUMAN", "PC");
            cmbPlayerType.setValue("HUMAN");
            GridPane.setHalignment(cmbPlayerType, HPos.CENTER);
            innerPane.add(cmbPlayerType, 2, i);

            selections.put(character, new Pair<ComboBox<String>, ComboBox<String>>(cmbParticipate, cmbPlayerType));
            i++;
        }

        final Button btnBack = new Button("BACK");
        final Button btnStart = new Button("START");
        innerPane.add(btnBack, 0, i);
        GridPane.setMargin(btnBack, new Insets(COL1_WIDTH, 0, 0, 0));
        GridPane.setHalignment(btnBack, HPos.CENTER);
        innerPane.add(btnStart, 2, i);
        GridPane.setMargin(btnStart, new Insets(COL1_WIDTH, 0, 0, 0));
        GridPane.setHalignment(btnStart, HPos.CENTER);

        btnStart.setOnAction(e -> {
            final Map<CharacterCard, PlayerType> l = new HashMap<>();
            for (final CharacterCard chiave : selections.keySet()) {
                final Pair<ComboBox<String>, ComboBox<String>> scelteUtente = selections.get(chiave);
                if (scelteUtente.getX().getValue().equals("YES")) {
                    l.put(chiave, scelteUtente.getY().getValue().equals("HUMAN") ? PlayerType.HUMAN : PlayerType.AI);
                }
            }
            observer.setupNewGame(l);
        });

        btnBack.setOnAction(e -> {
            observer.backToMenu();
        });

        return rootPane;
    }
}