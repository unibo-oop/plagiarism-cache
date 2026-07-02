package it.unibo.biscia.view.screens;

import it.unibo.biscia.utils.fileIO.leaderboard.LeaderboardImpl;
import it.unibo.biscia.view.actors.UI.ActionOverLabel;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Leaderboard screen.
 * 
 * @see AbstractScreen
 * 
 *
 */
public class Leaderboard extends AbstractScreen {
    private final static int PADDING = 6;
    private final Table table;
    private final it.unibo.biscia.utils.fileIO.leaderboard.Leaderboard leaderboard;
    private final ActionOverLabel backLabel;

    public Leaderboard() {
        this.table = new Table();
        this.leaderboard = new LeaderboardImpl();
        this.backLabel = new ActionOverLabel("<- Back", getSkin(), () -> getBiscia().setScreen(new MainMenu()));
    }

    @Override
    public final void show() {
        this.table.debugAll();
        this.table.setFillParent(true);
        this.table.add(new Label("Leaderboard", getSkin())).colspan(2).pad(PADDING);
        this.table.row();
        leaderboard.getScores().forEach((p, s) -> {
            table.add(new Label(p, getSkin())).expandX();
            table.add(new Label(s.toString(), getSkin())).expandX();
            table.row();
        });
        this.table.add(backLabel).colspan(2);
        this.getStage().addFocusableActor(backLabel);
        this.getStage().addActor(table);
        this.table.validate();
        this.getStage().setFocusedActor(backLabel);

    }

}
