package controller;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import model.ClassicGame;
import model.GameMode;
import model.IBasicGame;
import model.SurvivalGame;
import view.EndGameView;
import view.GameView;
import view.MainMenu;

public class Controller implements Runnable, ISubject {

    private IObserver obs;
    private GameMode mode;
    private IBasicGame game;
    private Stage root;

    public Controller(Stage stage, GameMode mode) {
        this.mode = mode;
        obs = new GameView(stage);
        this.root = stage;
    }

    @Override
    public final void run() {

        switch (this.mode) {
        case CLASSIC:
            this.game = new ClassicGame();
            break;
        case SURVIVAL:
            this.game = new SurvivalGame();
            break;
        default:
            System.out.println("errore");
            break;
        }

        IBasicGame.GameStatus status = null;
        while (status != IBasicGame.GameStatus.WON && status != IBasicGame.GameStatus.LOST) { //
            status = this.game.getStatus();

            switch (status) {
            case WON:
                System.out.println("Vittoria");
                //aggiorna con la schermata di vittoria.
                break;
            case LOST:
                System.out.println("Lost????");
                //fai diventare la schermata di sconfitta e registra i punteggi
                break;
            case START:
                this.game.initOnStart();
                obs.updateAllEntities(this.game.getAllEntities());
                //aspetta il click di qualcosa
                this.game.setPause(); //setPausa manda in running
                break;
            case RUNNING:
                this.game.updateModel();
                obs.updateAllEntities(this.game.getAllEntities());
                obs.updateScoreLives(this.game.getScore(), this.game.getLives());
                break;
            case PAUSE:
                this.game.setPause();
                //fai pausa finchè non cliccano
                break;
            default:
                break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Optional<String> name = new EndGameView(game.getScore(), root).show();
                if (name.isPresent()) {
                    System.out.println("Se chiudi lo salva!");
                    HighscoreManager.getInstance().addScore(name.get(), game.getScore(), mode);
                    HighscoreManager.getInstance().saveAllScores();
                }
                root.close(); //chiude la finestra di gioco
                new MainMenu(new Stage()); //apre la finestra del menu //se la faccio singleton e la nascondo?
            }
        });

        System.out.println("Applicazione chiusa");
    }
}
