package it.dpg.minigames.molegame.view;

import it.dpg.minigames.base.view.AbstractMinigameView;
import it.dpg.minigames.molegame.controller.HitTheMoleCycle;
import it.dpg.minigames.molegame.model.Mole;
import it.dpg.minigames.molegame.model.Score;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class HitTheMoleViewImpl extends AbstractMinigameView implements HitTheMoleView {

    private static final double WIDTH = 790;
    private static final double HEIGHT = 500;
    private static final int NROW = 5;
    private static final int NCOLUMN = 5;
    private static final Color BG_COLOR = Color.GREEN;
    private String sep = File.separator;

    private String holeWithoutMole = "images" + sep + "molegame" + sep + "holewithoutmole.png";
    private String holeWithMole = "images" + sep + "molegame" + sep + "holewithmole.png";

    private volatile List<Pair<Integer, Label>> listMole = new ArrayList<>();
    private GridPane gp = new GridPane();
    private HitTheMoleCycle gameCycle;
    private Text scoreLbl;
    private Text timerLbl;


    public HitTheMoleViewImpl(HitTheMoleCycle gc) {
        gameCycle = gc;
        gc.setView(this);

    }

    @Override
    public Scene createScene() {

        BorderPane bp = new BorderPane();
        gp.setHgap(10);
        gp.setVgap(10);
        bp.setCenter(gp);
        HBox hb = new HBox(15);
        Scene scene = new Scene(bp, WIDTH, HEIGHT, BG_COLOR);
        timerLbl = new Text("20");
        scoreLbl = new Text("0");
        Button start = new Button("START");
        start.setOnMouseClicked(mouseEvent -> {
            gameCycle.startGame();
            start.setDisable(true);
        });

        Text score = new Text("Score");
        Text timer = new Text("Timer");


        bp.setTop(hb);

        hb.getChildren().add(start);
        hb.getChildren().add(score);
        hb.getChildren().add(scoreLbl);
        hb.getChildren().add(timer);
        hb.getChildren().add(timerLbl);

        int count = 0;
        EventHandler<MouseEvent> al = mouseEvent -> {
            for (var p : listMole) {
                if (p.getValue().equals(mouseEvent.getSource())) {
                    gameCycle.pressOnAMole(p.getKey());
                }

            }
        };

        for (int i = 0; i < NROW; i++) {
            for (int j = 0; j < NCOLUMN; j++) {
                final Label l = new Label();
                l.setGraphic(new ImageView(new Image(holeWithoutMole)));
                l.setOnMouseClicked(al);
                gp.add(l, j, i);
                listMole.add(new Pair<>(count, l));
                count++;
            }
        }
        return scene;

    }


    /**
     * update the score of the view
     */
    @Override
    public void updateScore(Score score) {

        Platform.runLater(() -> scoreLbl.setText(String.valueOf(score.finalScore())));


    }

    /**
     * decrease the timer in the view
     */
    @Override
    public void updateTimer(long time) {

        Platform.runLater(() -> timerLbl.setText(String.valueOf(time)));


    }

    /**
     * update the view of out moles
     *
     * @param moleOut list of the out mole
     */
    @Override
    public void updateMole(List<Pair<Integer, Mole>> moleOut) {
        Platform.runLater(() -> {
            for (var p : listMole) {
                for (var i : moleOut) {
                    if (p.getKey().equals(i.getKey())) {
                        p.getValue().setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream(holeWithMole))));
                    } else {
                        p.getValue().setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream(holeWithoutMole))));
                    }
                }
            }

        });
    }


}
