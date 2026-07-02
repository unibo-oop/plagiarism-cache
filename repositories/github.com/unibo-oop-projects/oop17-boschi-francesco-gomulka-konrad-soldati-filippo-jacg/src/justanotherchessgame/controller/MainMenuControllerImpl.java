package justanotherchessgame.controller;

import java.io.File;
import javafx.scene.layout.Pane;
import justanotherchessgame.model.MinMaxAIPlayer;
import justanotherchessgame.util.FileManagement;
import justanotherchessgame.view.main.MainMenuView;
import justanotherchessgame.view.main.MainMenuViewImpl;
import justanotherchessgame.model.LocalPlayer;
import justanotherchessgame.model.GameModel;
import justanotherchessgame.model.GameModelImpl;

/**
 * This class is interposed between user and main menu view, and it's able to manage all the interactions between user and view.
 */
public class MainMenuControllerImpl implements MainMenuController {

    private final MainMenuView view;

    /**
     * Class constructor.
     */
    public MainMenuControllerImpl() {
        view = new MainMenuViewImpl(this);
    }

    @Override
    public final Pane createMainMenuView() {
        return view.createContent();
    }

    @Override
    public final void createNewGame() {
        final LocalPlayer white = new LocalPlayer(true);
        final LocalPlayer black = new LocalPlayer(false);
        new GameModelImpl(white, black);
        //setup view
        new ControllerImpl(white, black);
        //TODO: actually , the controller creates the gameView by himself.
        //not sure it's the right approach but other stuff was uglier otherwise...
    }

    @Override
    public final void resize() {
        view.resize();
    }

    @Override
    public final void createNewAIGame() {
        final LocalPlayer white = new LocalPlayer(true);
        final MinMaxAIPlayer black = new MinMaxAIPlayer(false);
        new GameModelImpl(white, black);
        new ControllerImpl(white, null);
    }

    @Override
    public final void loadGame(final File file) {
        final LocalPlayer white = new LocalPlayer(true);
        final LocalPlayer black = new LocalPlayer(false);
        final GameModel game = new GameModelImpl(white, black);
        //setup view
        new ControllerImpl(white, black);
        game.loadGame(FileManagement.loadFromFile(file));
    }

}
