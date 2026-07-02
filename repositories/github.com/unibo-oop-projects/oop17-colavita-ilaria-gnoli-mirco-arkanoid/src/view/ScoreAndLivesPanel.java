package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.ModelCostant;
/**
 * Pannello che estende {@link HBox}, che visualizza punteggi e vite.
 * 
 * @author Mirco Gnoli
 *
 */
public class ScoreAndLivesPanel extends HBox {

    private static final Font FONT = Font.font("Harrington", FontWeight.BOLD, 22);
    private static final Insets PADDING = new Insets(20);

    private Label scoreL;
    private Label livesL;

    /**
     *
     */
    public ScoreAndLivesPanel() {
        super();

        scoreL = new Label("Score: 0");
        setLabel(scoreL);
        livesL = new Label("Lives: 3");
        setLabel(livesL);
        this.getChildren().addAll(scoreL, livesL);
    }

    private void setLabel(final Label l) {
        l.setFont(FONT);
        l.setPadding(PADDING);
        l.setTextFill(Color.BLUE);
        l.setPrefWidth(ModelCostant.WORLD_WIDTH / 2);
        l.setAlignment(Pos.CENTER);
    }

    /**
     * Metodo di aggiornamento del pannello.
     * @param score
     *          nuovo punteggio
     * @param lives
     *          vite rimanenti
     */
    public void refresh(final int score, final int lives) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scoreL.setText("Score: " + score);
                livesL.setText("Lives: " + lives);
            }
         });
    }

}
