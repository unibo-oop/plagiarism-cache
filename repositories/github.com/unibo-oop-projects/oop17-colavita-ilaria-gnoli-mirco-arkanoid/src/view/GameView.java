package view;

import java.util.List;

import controller.IObserver;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.entities.IEntity;
import view.utils.ImageViewObject;

/**
 * Questa classe si occupa di fornire la schermata di visualizzazione del gioco.
 * Il pannello centrale è un {@link ArenaView} mentre in fondo è presente un {@link ScoreAndLivesPanel}
 * 
 * @author Mirco Gnoli
 *
 */
public class GameView implements IObserver {

    private BorderPane layout;

    /**
     * 
     * @param primaryStage - Stage principale
     */
    //da modificare (usare stage anzichè primaryStage) se tengo sta strada.
    //mettere stage o estendere la classe. Così per il gioco crea 1 altra finestra lasciando il menu in background
    public GameView(final Stage primaryStage) {

        primaryStage.setTitle("PrimaryStage");

        Pane bottom = new ScoreAndLivesPanel();
        Pane central = new ArenaView();

        layout = new BorderPane();
        layout.setCenter(central);
        layout.setBottom(bottom);

        layout.setRight(lateralBorder());
        layout.setLeft(lateralBorder());
        layout.setTop(horizontalBorder());

        Scene scene = new Scene(layout);

        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.sizeToScene(); //con setResizable aggiunge  dei pixel extra. in questo modo torna alla dimensione originale
        primaryStage.show();
    }

    /**
     * 
     * @return {@link ArenaView}
     */
    public ArenaView getArena() {
        return (ArenaView) layout.getCenter();
    }

    /**
     * 
     * @return {@link ScoreAndLivesPanel}
     */
    public ScoreAndLivesPanel getInfoPanel() {
        return (ScoreAndLivesPanel) layout.getBottom();
    }
    /**
     * Creazione del pannello laterale con l'immagine del muro.
     * @return {@link Pane}
     */
    private Pane lateralBorder() {
        Pane p = new Pane();
        BackgroundImage bgimg = new BackgroundImage(ImageViewObject.LATERAL_WALL.getImage(),  BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        p.setBackground(new Background(bgimg));
        p.setPrefWidth(ImageViewObject.LATERAL_WALL.getImage().getWidth());
        return p;
    }

    /**
     * Creazione del pannello superiore con l'immagine del muro.
     * @return {@link Pane}
     */
    private Pane horizontalBorder() {
        Pane p = new Pane();
        BackgroundImage bgimg = new BackgroundImage(ImageViewObject.HORIZONTAL_WALL.getImage(),  BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        p.setBackground(new Background(bgimg));
        p.setPrefHeight(ImageViewObject.HORIZONTAL_WALL.getImage().getHeight());
        return p;
    }
  //layout.setBorder(new Borde);
  //layout.setBorder(new Border(new BorderImage(ImageViewObject.LATERAL_WALL.getImage(), new BorderWidths(0), Insets.EMPTY, new BorderWidths(0), true, BorderRepeat., BorderRepeat.ROUND)));
  //layoutMaggiore.setBorder(new Border(new BorderStroke(Color.DARKSLATEBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));
  /*così mi crea la colonna laterale!
  BackgroundImage bgimg = new BackgroundImage(ImageViewObject.LATERAL_WALL.getImage(),  BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
  layout.setBackground(new Background(bgimg));*/

    @Override
    public void updateScoreLives(final int score, final int lives) {
        this.getInfoPanel().refresh(score, lives);
    }

    @Override
    public final void updateAllEntities(final List<IEntity> entities) {
        this.getArena().refresh(entities);
    }
}
