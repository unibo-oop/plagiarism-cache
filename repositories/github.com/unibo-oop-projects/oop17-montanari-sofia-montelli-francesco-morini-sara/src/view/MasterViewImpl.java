package view;

import java.io.IOException;
import java.util.Optional;

import controller.after_battle.AfterBattleUIController;
import controller.global_statistics.GlobalStatisticsUIController;
import view.statistics.StatisticUI;
import view.statistics.GlobalStatisticsUIImpl;
import view.statistics.AfterBattleUIImpl;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 * Implementation of {@link MasterView}.
 */
public final class MasterViewImpl implements MasterView {
    /**
     * The singleton instance of the {@link MasterViewImpl}.
     */
    private static final MasterView INSTANCE = new MasterViewImpl();
    /**
     * the reference of the only primary stage of the program.
     */
    private Optional<Stage> primaryStage = Optional.empty();

    private MasterViewImpl() { }

    /**
     * getter for the singleton instance.
     * @return the instance.
     */
    public static MasterView getInstance() {
        return INSTANCE;
    }

    @Override
    public void setPrimaryStage(final Stage stage) {
        if (!primaryStage.isPresent()) {
            primaryStage = Optional.of(stage);
            primaryStage.get().setResizable(false);
        }
    }

    @Override
    public Stage getPrimaryStage() {
        return primaryStage.get();
    }


    private void load(final Parent root) {
        final Scene scene = new Scene(root, primaryStage.get().getWidth(), primaryStage.get().getHeight());
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        MasterViewImpl.getInstance().getPrimaryStage().setScene(scene);
        MasterViewImpl.getInstance().getPrimaryStage().show();


        final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.get().setX(primaryScreenBounds.getMinX());
        primaryStage.get().setY(primaryScreenBounds.getMinY());

        primaryStage.get().setMaxWidth(primaryScreenBounds.getWidth());
        primaryStage.get().setMinWidth(primaryScreenBounds.getWidth());

        primaryStage.get().setMaxHeight(primaryScreenBounds.getHeight());
        primaryStage.get().setMinHeight(primaryScreenBounds.getHeight());
        primaryStage.get().setMaximized(true);
    }

    private void loadFromFxml(final String path) {
        final FXMLLoader loader =  new FXMLLoader(getClass().getResource(path));
        try {
            load(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    @Override
    public void showMainMenu() {
        loadFromFxml("/menu.fxml");
    }

    @Override
    public void showNavySetup() {
        loadFromFxml("/navySetup.fxml");
    }


    @Override
    public void showSelectPlayer() {
        loadFromFxml("/selectPlayer.fxml");
    }


    @Override
    public void showNavyDisposal() {
        loadFromFxml("/navyBuilderInterface.fxml");
    }

    @Override
    public void showEndBattle(final AfterBattleUIController controller) {
        final FXMLLoader loader =  new FXMLLoader(getClass().getResource("/afterBattleView.fxml"));
        final StatisticUI<AfterBattleUIController> ui = new AfterBattleUIImpl();
        ui.setController(controller);
        loader.setController(ui);
        try {
            final Parent p = loader.load();
            load(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showGameUI() {
        primaryStage.get().hide();
        loadFromFxml("/match.fxml");
    }

        @Override
        public void showGlobalStatistics(final GlobalStatisticsUIController controller) {
            final FXMLLoader loader =  new FXMLLoader(getClass().getResource("/globalStatisticsView.fxml"));
            final StatisticUI<GlobalStatisticsUIController> ui = new GlobalStatisticsUIImpl();
            ui.setController(controller);
            loader.setController(ui);
            try {
                final Parent p = loader.load();
                load(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
    public void credits() {
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        alert.setTitle("Who we are");
        alert.setHeaderText(null);
        alert.setContentText("\u2022 Montanari Sofia - 789197" + "\n"
                + "\u2022 Montelli Francesco - 789233" + "\n" 
                + "\u2022 Morini Sara - 792688");
        alert.getDialogPane().setMinWidth(Screen.getPrimary().getVisualBounds().getWidth() / 2);
        alert.getDialogPane().setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        alert.showAndWait();
    }

    @Override
    public void initialSetup() {
        loadFromFxml("/pathInput.fxml");
    }

}
