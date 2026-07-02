package it.unibo.ninjafrog.screens.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.ninjafrog.game.utilities.GameConst;

public final class HudImpl implements Hud {
    private static final int BONUSTIMER = 10;
    private static final String FORMAT = "%02d";
    private final Stage stage;
    private Integer score;
    private Integer life = 1;
    private Integer bonusTimer;
    private float timeCount;
    private boolean canInit;
    private boolean timerOn;

    private final Label countdownLabel;
    private final Label pointLabel;
    private final Label lifeCounterLabel;

    /**
     * public constructor for the Hud.
     * 
     * @param sb the spriteBatch.
     */
    public HudImpl(final SpriteBatch sb) {

        this.timeCount = 0;
        this.score = 0;
        this.canInit = true;
        this.timerOn = false;
        this.bonusTimer = BONUSTIMER;
        final Viewport viewport = new FitViewport(GameConst.WIDTH, GameConst.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, sb);

        final Table table = new Table();
        table.top();
        table.setFillParent(true);

        this.countdownLabel = new Label(String.format(FORMAT, 00), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        this.pointLabel = new Label(String.format("%06d", this.score),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        this.lifeCounterLabel = new Label(String.format(FORMAT, life),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        final Label bonusLabel = new Label("BONUS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        final Label scoreLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        final Label lifeLabel = new Label("LIFE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreLabel).padTop(10);
        table.add(lifeLabel).expandX().padTop(10);
        table.add(bonusLabel).expandX().padTop(10);
        table.row();
        table.add(this.pointLabel).expandX();
        table.add(this.lifeCounterLabel).expandX();
        table.add(this.countdownLabel).expandX();

        stage.addActor(table);
    }

    @Override
    public void addScore(final int value) {
        score += value;
        pointLabel.setText(String.format("%06d", this.score));
    }

    @Override
    public Integer getScore() {
        return this.score;
    }

    @Override
    public void addLife() {
        life += 1;
        lifeCounterLabel.setText(String.format(FORMAT, life));
    }

    @Override
    public void removeLife() {
        life -= 1;
        lifeCounterLabel.setText(String.format(FORMAT, life));
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public boolean isTimerOn() {
        return this.timerOn;
    }

    @Override
    public void update(final float dt) {
        if (this.canInit) {
            this.countdownLabel.setText(String.format("%02d", this.bonusTimer));
            this.canInit = false;
        }
        this.timeCount += dt;
        if (this.timeCount >= 1) {
            this.bonusTimer--;
            this.countdownLabel.setText(String.format(FORMAT, this.bonusTimer));
            this.timeCount = 0;
            if (this.bonusTimer == 0) {
                this.bonusTimer = BONUSTIMER;
                this.canInit = true;
                this.timerOn = false;
                return;
            }
        }
        this.timerOn = true;
    }

}
