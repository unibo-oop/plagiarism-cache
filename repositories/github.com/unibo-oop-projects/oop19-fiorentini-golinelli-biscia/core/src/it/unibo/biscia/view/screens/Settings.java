package it.unibo.biscia.view.screens;

import it.unibo.biscia.core.Controller;
import it.unibo.biscia.utils.Pair;
import it.unibo.biscia.utils.fileIO.Settings.SettingsIO;
import it.unibo.biscia.utils.fileIO.Settings.SettingsIOImpl;
import it.unibo.biscia.view.actors.UI.ActionOverLabel;
import it.unibo.biscia.view.actors.UI.BooleanOverLabel;
import it.unibo.biscia.view.actors.UI.EnumerableOverLabel;
import it.unibo.biscia.view.actors.UI.ListenableIntOverLabel;
import it.unibo.biscia.view.actors.UI.OverLabel;
import it.unibo.biscia.view.actors.UI.StateOverLabel;
import it.unibo.biscia.view.actors.UI.WritableOverLabel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Settings Screen.
 * 
 * @see AbstractScreen
 *
 */
public class Settings extends AbstractScreen {
    private final OverLabel titleLabel;
    private final ListenableIntOverLabel playerLabel;
    private final List<Pair<OverLabel, StateOverLabel<?>>> entries;
    private final ActionOverLabel saveLabel;
    private final Table table;
    private final int padding = 6;
    private final SettingsIO settingsIO;

    public Settings() {
        this.settingsIO = new SettingsIOImpl();
        this.titleLabel = new OverLabel("Settings", getSkin());
        this.playerLabel = new ListenableIntOverLabel(1, 2, this.settingsIO.getNumberOfPlayers().getSecond(),
                getSkin());
        this.playerLabel.addListener(() -> {
            this.build();
            this.getStage().setKeyboardFocus(getStage().getFocusedActor());
        });
        this.entries = new ArrayList<>() {
            private static final long serialVersionUID = -5221265808457769819L;
            {
                this.add(new Pair<>(new OverLabel(settingsIO.getNumberOfPlayers().getFirst(), getSkin()), playerLabel));
                this.add(new Pair<>(new OverLabel(settingsIO.getNamePlayer1().getFirst(), getSkin()),
                        new WritableOverLabel(settingsIO.getNamePlayer1().getSecond(), 3, getSkin())));
                this.add(new Pair<>(new OverLabel(settingsIO.getNamePlayer2().getFirst(), getSkin()),
                        new WritableOverLabel(settingsIO.getNamePlayer2().getSecond(), 3, getSkin())));
                this.add(new Pair<>(new OverLabel(settingsIO.getInitialSpeed().getFirst(), getSkin()),
                        new EnumerableOverLabel<Controller.Speed>(Controller.Speed.class,
                                settingsIO.getInitialSpeed().getSecond(), getSkin())));
                this.add(new Pair<>(new OverLabel(settingsIO.getIncreasingSpeed().getFirst(), getSkin()),
                        new BooleanOverLabel(settingsIO.getIncreasingSpeed().getSecond(), getSkin())));
                this.add(new Pair<>(new OverLabel(settingsIO.getMusic().getFirst(), getSkin()),
                        new BooleanOverLabel(settingsIO.getMusic().getSecond(), getSkin())));
                this.add(new Pair<>(new OverLabel(settingsIO.getSounds().getFirst(), getSkin()),
                        new BooleanOverLabel(settingsIO.getSounds().getSecond(), getSkin())));
            }
        };
        this.table = new Table();
        this.saveLabel = new ActionOverLabel("<- save and go back", getSkin(), () -> {
            System.out.println("salvato");
            this.settingsIO.addSettings(entries.stream().collect(Collectors
                    .toMap(p -> p.getFirst().getText().toString(), p -> p.getSecond().getCurrentStateValue())));
            getBiscia().setScreen(new MainMenu());
        });
    }

    @Override
    public final void show() {
        this.table.debugAll();
        this.table.setFillParent(true);
        entries.stream().map(p -> p.getSecond()).forEach(a -> getStage().addFocusableActor(a));
        this.getStage().addFocusableActor(saveLabel);
        this.getStage().addActor(table);
        this.build();
        this.getStage().setFocusedActor(this.entries.get(0).getSecond());
    }

    private void build() {
        this.table.reset();
        this.table.add(titleLabel).colspan(2);
        this.table.row();
        for (Pair<OverLabel, StateOverLabel<?>> p : this.entries) {
            if (playerLabel.getCurrentStateValue() == 2 || !p.equals(entries.get(2))) {
                this.table.add(p.getFirst()).left().pad(padding).fill().uniform();
                this.table.add(p.getSecond()).right().fill().uniform();
                p.getSecond().setAlignment(Align.right);
                this.getStage().addFocusableActor(p.getSecond());
                this.table.row();
            }
        }
        this.table.add(saveLabel).colspan(2).fill().pad(padding);
        this.table.validate();
    }
}
