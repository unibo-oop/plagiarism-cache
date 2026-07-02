package view;

import java.awt.Toolkit;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * 
 * Implementation of View.
 *
 */
public final class ViewImpl implements View {

    private final Stage stage = new Stage();
    private final Scene scene = new Scene(new Pane());


    /**
     * ViewImpl builder.
     * 
     */
    public ViewImpl() {
        this.stage.setTitle("CRAZY CLIMBER");
        this.stage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        this.stage.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        this.stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.stage.setScene(scene);
        this.stage.setFullScreen(true);
        stage.show();
    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    public void setMenu(final StackPane root) {
        scene.setRoot(root);
    }

    @Override
    public GameView setGameView() {
        return new GameView(stage);
    }
}
