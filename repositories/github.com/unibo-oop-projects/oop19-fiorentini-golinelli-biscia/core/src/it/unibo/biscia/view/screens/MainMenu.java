package it.unibo.biscia.view.screens;

import it.unibo.biscia.core.Controller;
import it.unibo.biscia.core.ControllerImpl;
import it.unibo.biscia.core.Player;
import it.unibo.biscia.events.ActionObserver;
import it.unibo.biscia.events.GenericEventSubject;
import it.unibo.biscia.events.StateObserver;
import it.unibo.biscia.utils.fileIO.Settings.SettingsIO;
import it.unibo.biscia.utils.fileIO.Settings.SettingsIOImpl;
import it.unibo.biscia.view.View;
import it.unibo.biscia.view.actors.UI.ActionOverLabel;
import it.unibo.biscia.view.managers.FontManager;
import it.unibo.biscia.view.utils.RandomColor;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;;

/**
 * The Main Menu Screen. It has an utility method {@link MainMenu#gameSetup()}
 * for synchronizing {@link View} and {@link Controller}.
 *
 */
public class MainMenu extends AbstractScreen {
    private final Label logo;
    private final List<ActionOverLabel> buttons;
    private final int padding = 6;
    private Table table;
    private final Label warning;

    public MainMenu() {
        this.buttons = new ArrayList<>();
        this.logo = new Label("Biscia", new LabelStyle() {
            {
                this.font = getManager().get(FontManager.LOGO.getName(), BitmapFont.class);
                this.fontColor = RandomColor.light();
            }
        });
        this.logo.setAlignment(Align.center);

        buttons.add(new ActionOverLabel("New Game", this.getSkin(), () -> this.gameSetup()));
        buttons.add(new ActionOverLabel("Continue", this.getSkin(), null));
        buttons.add(new ActionOverLabel("Settings", this.getSkin(), () -> getBiscia().setScreen(new Settings())));
        buttons.add(new ActionOverLabel("Leaderboard", this.getSkin(), () -> getBiscia().setScreen(new Leaderboard())));
        buttons.add(new ActionOverLabel("Credits", this.getSkin(), null));
        buttons.add(new ActionOverLabel("Quit", this.getSkin(), () -> Gdx.app.exit()));
        this.warning = new Label("", this.getSkin());
        this.table = new Table();
    }

    @Override
    public final void show() {
        this.table.setFillParent(true);
        this.table.add(this.logo).fill().pad(padding);
        this.table.row();
        for (int i = 0; i < buttons.size(); i++) {
            this.table.add(buttons.get(i)).pad(padding);
            this.table.row();
            if (i == 0 || i == 2 || i == 3 || i == 5) {
                this.getStage().addFocusableActor(buttons.get(i));
            }
        }
        this.table.add(warning).pad(padding);
        this.getStage().addActor(this.table);
        this.table.validate();
        this.getStage().setFocusedActor(buttons.get(0));
        this.getStage().setEscapeActor(this.buttons.get(this.buttons.size() - 1));
    }

    /**
     * utility method for synchronizing {@link View} and {@link Controller} via
     * {@link GenericEventSubject} with their respective {@link StateObserver} and
     * {@link ActionObserver} as well as get {@link Player}s via {@link SettingsIO}.
     * This method is called by selecting the "new game" button. After all it calls
     * {@link Controller#start()}.
     * 
     */
    public final void gameSetup() {
        SettingsIO settingsIO = new SettingsIOImpl();
        int numberOfPlayers = settingsIO.getNumberOfPlayers().getSecond();
        String firstPlayer = settingsIO.getNamePlayer1().getSecond();
        String secondPlayer = settingsIO.getNamePlayer2().getSecond();
        if (numberOfPlayers == 2 && firstPlayer.equals(secondPlayer)) {
            this.warning.setText("not unique player's names");
        } else {
            it.unibo.biscia.view.screens.Game gameView = new it.unibo.biscia.view.screens.Game();
            List<String> players = new ArrayList<>();
            players.add(firstPlayer);
            if (numberOfPlayers == 2) {
                players.add(secondPlayer);
            }
            Controller controller = new ControllerImpl(players, settingsIO.getInitialSpeed().getSecond().ordinal(),
                    settingsIO.getIncreasingSpeed().getSecond());
            controller.attachStateObserver(gameView);
            gameView.attachActionObserver(controller);
            gameView.setPlayers(controller.getPlayers());
            getBiscia().setScreen(gameView);
            controller.start();
        }
    }
}
