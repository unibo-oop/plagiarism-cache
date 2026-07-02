package vg.view.gameBoard;

import javafx.application.Application;
import javafx.stage.Stage;
import vg.sound.manager.ESoundBackground;
import vg.sound.manager.SoundManager;
import vg.sound.manager.SoundManagerImpl;
import vg.view.ViewFactory;
import vg.view.ViewManager;
import vg.view.ViewManagerImpl;
import vg.view.menu.MenuView;
import vg.view.utils.KeyEventHandler;

public class GameBoard extends Application {

    @Override
    public void start(final Stage stage) {
        ViewManager viewManager = new ViewManagerImpl(stage, new KeyEventHandler());
        SoundManager soundManager = new SoundManagerImpl();
        soundManager.playBackground(ESoundBackground.START);

        MenuView menuView = ViewFactory.menuView(viewManager, soundManager);
        viewManager.addView(menuView);

        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        stage.setTitle("GameBoard");
        stage.setResizable(false);
        stage.show();
    }
}
