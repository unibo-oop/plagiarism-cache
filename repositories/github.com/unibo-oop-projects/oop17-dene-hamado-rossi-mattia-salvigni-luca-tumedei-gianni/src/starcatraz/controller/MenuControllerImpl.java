package starcatraz.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import starcatraz.util.AppFXML;
import starcatraz.util.AppSound;

/**
 * Menu controller implementation.
 */
public class MenuControllerImpl extends StarcatrazController implements MenuController, Initializable {

    @FXML
    private Button settingsButton;
    @FXML
    private Button manualButton;
    @FXML
    private Button creditsButton;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button achievementsButton;
    @FXML
    private Button newGameButton;
    @FXML
    private Button exitButton;
    @FXML
    private BorderPane rootPane;

    // Anchor panes for all the views
    private AnchorPane settingsView;
    private AnchorPane manualView;
    private AnchorPane aboutView;
    private AnchorPane statisticsView;
    private AnchorPane achievementsView;

    /**
     * @param path
     * @return AnchorPane of a fxml file
     */
    private AnchorPane createWindow(final String path) {
        AnchorPane pane = new AnchorPane();
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    /**
     * @param path
     * @return AnchorPane of settings 
     */
    private AnchorPane createSettingsWindow(final String path) {
        AnchorPane pane = new AnchorPane();
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            pane = (AnchorPane) loader.load();
            final Initializable controller = loader.getController();
            ((StarcatrazController) controller).setStarcatrazApp(getStarcatrazApp());
            ((SettingsController) controller).setSettings(getStarcatrazApp().getSettings());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    /**
     * @param path
     * @return AnchorPane of statistics
     */
    private AnchorPane createStatisticsWindow(final String path) {
        AnchorPane pane = new AnchorPane();
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            pane = (AnchorPane) loader.load();
            final StatisticsController controller = loader.getController();
            controller.setStatistics(getStarcatrazApp().getStatistics());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    /**
     * @param path
     * @return AnchorPane of achievements
     */
    private AnchorPane createAchievementsWindow(final String path) {
        AnchorPane pane = new AnchorPane();
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            pane = (AnchorPane) loader.load();
            final AchievementsController controller = loader.getController();
            controller.setAchievements(getStarcatrazApp().getAchievements());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    @Override
    public void buttonClickEvent(final ActionEvent event) {
        AppSound.BUTTON_SOUND.play();
        if (event.getTarget() == settingsButton) {
            if (this.settingsView == null) {
                this.settingsView = createSettingsWindow(AppFXML.SETTINGS_VIEW);
            }
            rootPane.setCenter(settingsView);
            settingsView.setVisible(true);
        } else if (event.getTarget() == manualButton) {
            rootPane.setCenter(manualView);
            manualView.setVisible(true);
        } else if (event.getTarget() == creditsButton) {
            rootPane.setCenter(aboutView);
            aboutView.setVisible(true);
        } else if (event.getTarget() == statisticsButton) {
            if (this.statisticsView == null) {
                this.statisticsView = createStatisticsWindow(AppFXML.STATISTICS_VIEW);
            }
            rootPane.setCenter(statisticsView);
            statisticsView.setVisible(true);
        } else if (event.getTarget() == achievementsButton) {
            if (this.achievementsView == null) {
                this.achievementsView = createAchievementsWindow(AppFXML.ACHIEVEMENTS_VIEW);
            }
            rootPane.setCenter(achievementsView);
            achievementsView.setVisible(true);
        } else if (event.getTarget() == exitButton) {
            Platform.exit();
        }

    }

    /**
     * @return Number of pixels for padding
     */
    private int getPadding() {
        return getStarcatrazApp().getSettings().getResolutionHeight() * 43 / 1080;
    }

    @Override
    public void buttonMouseEntered(final MouseEvent event) {
        if (event.getTarget() == settingsButton) {
            settingsButton.setPadding(new Insets(getPadding()));
        } else if (event.getTarget() == manualButton) {
            manualButton.setPadding(new Insets(getPadding()));
        } else if (event.getTarget() == creditsButton) {
            creditsButton.setPadding(new Insets(getPadding()));
        } else if (event.getTarget() == statisticsButton) {
            statisticsButton.setPadding(new Insets(getPadding()));
        } else if (event.getTarget() == achievementsButton) {
            achievementsButton.setPadding(new Insets(getPadding()));
        } else if (event.getTarget() == exitButton) {
            exitButton.setPadding(new Insets(getPadding()));
        } else if (event.getTarget() == newGameButton) {
            newGameButton.setPadding(new Insets(getPadding()));
        }
    }

    @Override
    public void buttonMouseExited(final MouseEvent event) {
        if (event.getTarget() == settingsButton) {
            settingsButton.setPadding(new Insets(0));
        } else if (event.getTarget() == manualButton) {
            manualButton.setPadding(new Insets(0));
        } else if (event.getTarget() == creditsButton) {
            creditsButton.setPadding(new Insets(0));
        } else if (event.getTarget() == statisticsButton) {
            statisticsButton.setPadding(new Insets(0));
        } else if (event.getTarget() == achievementsButton) {
            achievementsButton.setPadding(new Insets(0));
        } else if (event.getTarget() == exitButton) {
            exitButton.setPadding(new Insets(0));
        } else if (event.getTarget() == newGameButton) {
            newGameButton.setPadding(new Insets(0));
        }
    } 

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        AppSound.MENU_MUSIC.play();
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                AppSound.MENU_MUSIC.stop();
                AppSound.BUTTON_SOUND.play();
                newGameButtonClik(event);

            }
        });

        settingsButton.setOnMouseEntered(e -> buttonMouseEntered(e));
        manualButton.setOnMouseEntered(e -> buttonMouseEntered(e));
        creditsButton.setOnMouseEntered(e -> buttonMouseEntered(e));
        statisticsButton.setOnMouseEntered(e -> buttonMouseEntered(e));
        achievementsButton.setOnMouseEntered(e -> buttonMouseEntered(e));
        exitButton.setOnMouseEntered(e -> buttonMouseEntered(e));
        newGameButton.setOnMouseEntered(e -> buttonMouseEntered(e));

        this.manualView = createWindow(AppFXML.MANUAL_VIEW);
        this.aboutView = createWindow(AppFXML.ABOUT_VIEW);
    }

    @Override
    public void newGameButtonClik(final ActionEvent event) {
        getStarcatrazApp().showGameView();
    }

}
