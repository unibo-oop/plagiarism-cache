package view;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import controller.Controller;
import controller.file.ViewFile;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utility.Direction;
import utility.Driver;
import utility.Pair;
import utility.Position;
import utility.TyreType;
import view.game.InfoDriver;
import view.game.ViewGame;
import view.game.ViewGameImpl;
import view.menu.ViewMenu;
/**
 * 
 *
 */
public class ViewGuiImpl implements View {

    private static final String FXML_PATH = "/view/menu/FxmlMenu.fxml";
    private static final String FXML_PATH2 = "/view/game/FxmlView.fxml";
    private static final String FXML_STRING = "Fantarace One";
    private static final String ICON = "/icon.png";

    private Controller ctr;
    private ViewGame game;
    private ViewMenu menu;
    private ViewFile fileContainer;
    private Stage stage;
    private final Wait<Boolean> wait = new Wait<>();
    private final Wait<TyreType> waitBox = new Wait<>();


    @Override
    public void startMenu(final Stage stage) throws IOException {
        this.stage = stage;
        this.startView(FXML_PATH);
    }

    @Override
    public void start() {
        this.menu.waitMenu();
        final Wait<Boolean> wait = new Wait<>();
        Platform.runLater(() -> {
            try {
                this.startView(FXML_PATH2);
                wait.actionPerformed(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        wait.waitForUser();
        ctr.startWeekend();
    }


    @Override
    public Direction throwDice(final int number, final Pair<Boolean, Boolean> canDir, final boolean canBox) {
        return this.game.throwDice(number, canDir, canBox);
    }


    @Override
    public void update(final Driver drv, final Position pos, final boolean block) {
        this.game.update(drv, pos, block);
    }


    @Override
    public void updatePlayer(final InfoDriver info) {
        Platform.runLater(() -> {
            this.game.updateRound(info);
        });
    }

    @Override
    public void crash(final Map<Driver, Optional<String>> crashedPlayers) {
        Platform.runLater(() -> {
            this.game.crash(crashedPlayers);
            wait.actionPerformed(true);
        });
        wait.waitForUser();
    }


    @Override
    public void retire(final Map<Driver, Optional<String>> retiredPlayers) {
        Platform.runLater(() -> {
            this.game.retire(retiredPlayers);
            wait.actionPerformed(true);
        });
        wait.waitForUser();
    }

    @Override
    public void disqualify(final Map<Driver, Optional<String>> disqualifiedPlayers) {
        Platform.runLater(() -> {
            this.game.disqualify(disqualifiedPlayers);
            wait.actionPerformed(true);
        });
        wait.waitForUser();
    }


    @Override
    public void rankQualifying(final List<Pair<Driver, Integer>> rank, final boolean isEnded) {
        if (!isEnded) {
            Platform.runLater(() -> {
                final List<Driver> rankDriver = new ArrayList<>();
                rank.forEach(a -> rankDriver.add(a.getX()));
                this.game.rankUpdate(rankDriver);
            });
        } else {
            Platform.runLater(() -> {
                this.game.rankQualifying(rank);
                wait.actionPerformed(true);
            });
            wait.waitForUser();
        }
    }


    @Override
    public void rankRace(final List<Driver> rank, final boolean isEnded) {
        if (!isEnded) {
            Platform.runLater(() -> {
                this.game.rankUpdate(rank);
            });
        } else {
            Platform.runLater(() -> {
                wait.actionPerformed(this.game.rankRace(rank));
            });
            wait.waitForUser();
            if (wait.getValue()) {
                this.restart();
            } else {
                Platform.exit();
            }
        }
    }

    @Override
    public TyreType box(final String name) {
        Platform.runLater(() -> {
            waitBox.actionPerformed(this.game.box(name));
        });
        waitBox.waitForUser();
        return waitBox.getValue();
    }

    @Override
    public void setController(final Controller ctr) {
        this.ctr = ctr;
        this.menu.setController(ctr);
    }

    @Override
    public void setViewFile(final ViewFile fileContainer) {
        this.fileContainer = fileContainer;
    }

    @Override
    public void startRace() {
        this.game.startRace();
    }


    private void restart() {
        Platform.runLater(() -> {
            try {
                this.startMenu(this.stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            wait.actionPerformed(true);
        });
        wait.waitForUser();
        this.ctr.restart();
    }

    private void startView(final String path) throws IOException  {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        final Parent root = loader.load();
        stage.setTitle(FXML_STRING);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream(ICON)));
        if (stage.getScene() == null) {
            stage.setScene(new Scene(root));
        } else {
            stage.getScene().setRoot(root);
        }
        if (loader.getController() instanceof ViewGameImpl) {
            this.game = loader.getController();
            this.game.setControllerAndFile(this.ctr, fileContainer);
            stage.setFullScreen(true);
        } else {
            this.menu = loader.getController();
            stage.setFullScreen(false);
            stage.centerOnScreen();
            stage.sizeToScene();
        }
        stage.show();
    }
}