package view.gamescreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.util.Duration;
import view.ViewImpl;
import view.dialogs.TanksMovementAfterAttackDiag;
import view.dialogs.TanksMovementDiag;
import view.gamescreen.handlers.GameStateButtonListener;
import view.gamescreen.handlers.StateListener;


/**
 * 
 * This class contains the code to display all states and continents on map.
 *
 */
public class WorldMap extends StackPane {

    private static final String DEFAULT_MAP = "/map/RiskClassicMap.txt";
    private static final String GSBTN_STYLE = "menu-button";
    private static final String BORDER_STYLE = "svg-border";
    private static final String BORDER_PREFIX = "BORDER";
    private static final double LBL_FONT_SIZE = 24;
    private static final double WIDTH = Screen.getPrimary().getBounds().getWidth() / 1.3;
    private static final double HEIGHT = Screen.getPrimary().getBounds().getHeight() / 1.3;

    private final JFXButton gameStateBtn = new JFXButton("End deploy phase");
    private final Label onScreenMessage = new Label();

    private final List<State> viewStateList = new ArrayList<>();
    private final TankChooserSlider tcSlider = new TankChooserSlider(WIDTH / 6);
    private final TankMoverSlider tmSlider = new TankMoverSlider(WIDTH / 6);

    private Optional<State> selectedCountry = Optional.empty();

    /**
     * 
     * Default class constructor.
     * 
     */
    public WorldMap() {
        this(DEFAULT_MAP);
    }
 
    /**
     * 
     * Class constructor, it takes as input the map to load.
     * 
     * @param map
     *              Map relative path.
     *
     */
    public WorldMap(final String map) {
        final Group worldMapContainer = new Group();
        final Pane worldMap = new Pane();
        final BufferedReader statesPath = new BufferedReader(new InputStreamReader(WorldMap.class.getResourceAsStream(map)));
        try {
            statesPath.lines().forEach(line -> {
                if (line.startsWith(BORDER_PREFIX)) {
                    final SVGPath svg = new SVGPath();
                    svg.setContent(line.substring(BORDER_PREFIX.length()));
                    svg.getStyleClass().add(BORDER_STYLE);
                    worldMap.getChildren().add(svg);
                } else {
                    final State state = new State(line.split("!"));
                    state.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e -> {
                        setSelectedCountry(state);
                    }));
                    viewStateList.add(state);
                    worldMap.getChildren().addAll(state, state.getIcon(), state.getnTanks());
                }
            });
        } finally {
            try {
                statesPath.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        worldMapContainer.getChildren().addAll(worldMap);

        worldMap.getTransforms().add(new Scale(WIDTH / worldMapContainer.getLayoutBounds().getWidth(), (HEIGHT) / worldMapContainer.getLayoutBounds().getHeight(), 0, 0));
        worldMapContainer.resize(WIDTH, HEIGHT);

        gameStateBtn.setOnMouseClicked(new GameStateButtonListener());
        gameStateBtn.getStyleClass().add(GSBTN_STYLE);
        gameStateBtn.setAlignment(Pos.TOP_CENTER);

        onScreenMessage.setMinWidth(WIDTH);
        onScreenMessage.setFont(new Font(LBL_FONT_SIZE));
        onScreenMessage.setTextAlignment(TextAlignment.CENTER);
        onScreenMessage.setAlignment(Pos.CENTER);
        onScreenMessage.setVisible(false);

        this.getChildren().addAll(worldMapContainer, gameStateBtn, tcSlider, tmSlider, onScreenMessage);
        WorldMap.setAlignment(tcSlider, Pos.BOTTOM_CENTER);
        WorldMap.setAlignment(tmSlider, Pos.BOTTOM_CENTER);
        WorldMap.setAlignment(gameStateBtn, Pos.TOP_CENTER);
        WorldMap.setAlignment(onScreenMessage, Pos.BOTTOM_CENTER);

        onScreenMessage.setLayoutY(tcSlider.getHeight());
        this.setBackground(new Background(new BackgroundImage(new Image(WorldMap.class.getResource("/images/bg.png").toExternalForm()),
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundPosition.CENTER,
                                                              new BackgroundSize(WIDTH, HEIGHT, false, false, false, true))));
    }

    private void resetStyle(final State country) {
        country.setId(null);
    }

    private void setSelectedCountry(final State country) {
        this.selectedCountry.ifPresent(o -> resetStyle(o));

        country.setId("selected");
        this.selectedCountry = Optional.of(country);
    }

    /**
     * 
     * Listener registerer for all state objects.
     * 
     * @param footerPlayerInfo
     *                  GameView Sidebar component.
     * @param footerBattleInfo
     *                  GameView footer component.
     * 
     */
    public void registerStateListener(final FooterPlayerInfo footerPlayerInfo, final FooterBattleInfo footerBattleInfo) {
        viewStateList.iterator().forEachRemaining(s -> s.addEventHandler(Event.ANY, new StateListener(footerPlayerInfo, footerBattleInfo)));
    }

    /**
     * 
     * State list getter.
     * 
     * @return
     *          State list.
     * 
     */
    public List<State> getStatelist() {
        return this.viewStateList;
    }

    /**
     * 
     * It makes clickable only states owned by current active player.
     * 
     */
    public void enablesActivePlayerStates() {
        getStatelist().forEach(s -> {
            if (s.getOwner().equals(ViewImpl.getIstance().getPlayerList().getHead().getName())) {
                s.setDisable(false);
            }
        });
    }

    /**
     * 
     * This function disable (or enable) all states.
     * 
     * @param bool
     *              If true disables all states, otherwise it enables all.
     * 
     */
    public void toggleStates(final boolean bool) {
        getStatelist().forEach(s -> {
            s.setDisable(bool);
            s.setId(null);
        });
    }
 
    /**
     * 
     * Print a text label on screen.
     * 
     * @param msg
     *              Text string to be print.
     * 
     */
    public void printMessageonScreen(final String msg) {
        onScreenMessage.setText(msg);
        onScreenMessage.setTextAlignment(TextAlignment.CENTER);
        onScreenMessage.setVisible(true);
        final FadeTransition anim = new FadeTransition(Duration.millis(2500), onScreenMessage);
        anim.setFromValue(100);
        anim.setToValue(0);
        anim.playFromStart();
        anim.setOnFinished(e -> onScreenMessage.setVisible(false));
    }

    /**
     * 
     * It shows the dialog to move tanks. It's the final movement phase movement.
     * 
     * @see TanksMovementDiag
     * 
     */
    public void showMoveTanksDiag() {
        new TanksMovementDiag(this).show();
    }

    /**
     * 
     * Slider getter.
     * 
     * @return
     *          tankChooser slider.
     * 
     */
    public TankChooserSlider getChooserSlider() {
        return this.tcSlider;
    }

    /**
     * @return the tmSlider
     */
    public TankMoverSlider getMoverSlider() {
        return tmSlider;
    }

    /**
     * Button GameStateBtn getter.
     * 
     * @return
     *          GameStateBtn button.
     */
    public JFXButton getGameStateBtn() {
        return gameStateBtn;
    }

    /**
     * 
     * Deselect the selected country.
     * 
     */
    public void resetSelectedCountry() {
        this.selectedCountry.ifPresent(s -> resetStyle(s));
        this.selectedCountry = Optional.empty();
    }

    /**
     * It shows the dialog to let user decide how much tanks move to conquered state.
     * 
     * @see TanksMovementAfterAttackDiag
     */
    public void showMoveTanksAfterAttackDiag() {
        new TanksMovementAfterAttackDiag(this).show();
    }

}
