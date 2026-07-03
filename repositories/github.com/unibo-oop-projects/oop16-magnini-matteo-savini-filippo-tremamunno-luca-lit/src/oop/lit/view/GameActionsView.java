package oop.lit.view;

import java.util.stream.Collectors;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import oop.lit.controller.Controller;
import oop.lit.model.GameModel;
import oop.lit.util.Observer;

/**
 * A class used to show which actions can be performed by the current game.
 */
public class GameActionsView implements Observer {

    private final GameModel game;
    private final Controller controller;
    private final VBox showBox;

    /**
     * @param game
     *      the game the view refers to.
     * @param controller
     *      the game controller.
     * @param showBox
     *      where the possible actions are to be shown.
     */
    public GameActionsView(final GameModel game, final Controller controller, final VBox showBox) {
        this.game = game;
        this.controller = controller;
        this.showBox = showBox;
        this.game.attach(this);
        this.updateActions();
    }

    @Override
    public void notifyChange() {
        this.updateActions();
    }

    /**
     * A method to be called when this it's time to remove the observer.
     */
    public void removed() {
        this.game.detach(this);
    }

    private void updateActions() {
        this.showBox.getChildren().clear();
        this.showBox.getChildren().addAll(game.getActions().stream().map(action -> {
            final Button b = new Button(action.getLabel());
            b.setMaxWidth(Double.POSITIVE_INFINITY);
            b.setDisable(!action.canBePerformed());
            b.setOnMouseClicked(e -> this.controller.getSelectionAndAction().performAction(action));
            return b;
        }).collect(Collectors.toList()));
    }

}
