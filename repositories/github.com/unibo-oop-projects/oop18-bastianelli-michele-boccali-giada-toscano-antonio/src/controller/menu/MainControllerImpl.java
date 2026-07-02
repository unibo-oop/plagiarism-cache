package controller.menu;

import common.CommonStrings;
import common.EventBusConnection;
import common.events.SceneEvent;
import controller.GameController;
import enumerators.FileAudio;
import enumerators.SceneType;
import javafx.stage.Stage;
import model.user.CurrentUser;
import view.BackgroundMusic;

/**
 * MainController implementation. This class handle SceneEvent.
 * Set the correct scene as Stage scene
 */
public class MainControllerImpl extends EventBusConnection implements MainController {

    private final Stage stage;
    private Controller menu;

    /**
     * Create the mail controller and call EventBusConnection constructor.
     * @param stage : the application stage
     */
    public MainControllerImpl(final Stage stage) {
        super();
        this.stage = stage;
    }

    @Override
    public final void handleSceneEvent(final SceneEvent event) {
        switch (event.getSceneType()) {
        case LOGIN:
            this.setMenu(new LoginMenu());
            break;
        case MENU:
            this.setMenu(new MainMenu());
            break;
        case NEW_GAME:
            GameController.getInstance().initNewGame(
                    CurrentUser.getInstance().getUser().getUserData().getCurrentCharacter(),
                    CurrentUser.getInstance().getCurrentLevel());
            this.setMenu(GameController.getInstance());
            break;
        case RESUME_GAME:
            this.setMenu(GameController.getInstance());
            GameController.getInstance().resumeGame();
            break;
        case GAME_MENU:
            this.setMenu(new PauseMenu());
            break;
        case STATISTICS:
            this.setMenu(new StatisticMenu());
            break;
        case SHOP:
            this.setMenu(new ShopMenu());
            break;
        case END_GAME:
            this.setMenu(new EndGameMenu());
            break;
        default:
            throw new IllegalAccessError("Scene not present");
        }
        stage.setScene(this.getMenu().getScene());
    }

    @Override
    public final void start() {
        BackgroundMusic.getInstance().loadTune(FileAudio.TRACK_1.getPath());
        getBus().post(new SceneEvent(SceneType.LOGIN));
        stage.setWidth(CommonStrings.WINDOW_WIDTH);
        stage.setHeight(CommonStrings.WINDOW_HEIGHT);
        stage.show();
    }

    private Controller getMenu() {
        return menu;
    }

    private void setMenu(final Controller menu) {
        this.menu = menu;
    }

}
