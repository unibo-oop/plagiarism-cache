package it.unibo.oop.supermario.screens;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.utils.CharacterAnimation;

/**
 * 
 * this is the hud of the game, on the top of the screen.
 * contains the global score, the game time, the current level and the collected coins
 */
public class Hud extends CharacterAnimation {
    /** 
     * The label whith the "play" string.
     * */
    private final Stage stage;
    /**
     * the initial time .
     *  */
    private int worldTimer;
    /**
     * keeps the world time.
     *  */
    private float timeCount;
    /**
     * the global score.
     *  */
    private int score;
    /**
     * the coins counter.
     *  */
    private int coinsCounter;

    /** 
     * the label containing the time.
     *  */
    private final Label countdownlabel;
    /**
     * the label containing the score.
     * */
    private final Label scoreLabel;
    /**
     * the label containing the coins count.
     * */
    private final Label coinsCounterLabel;

    /**
     * 
     * @param sb is the spritebatch for draw items on the screen
     */
    public Hud(final SpriteBatch sb) {
        final GameManager manager = GameManager.instance;
        final TextureRegion  coin = new TextureRegion(atlas.findRegion("FlippingCoin"), 16, 0, 16, 16);
        final Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(atlas.findRegion("FlippingCoin"), i * 16, 0, 16, 16));
        }
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        coinsCounter = 0;
        final Viewport viewport = new FitViewport(GameManager.V_WIDTH, GameManager.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        final Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownlabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        coinsCounterLabel = new Label(String.format("%02d", coinsCounter), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        final Label timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        final Label levelLabel =  new Label("1-" + manager.getCurrentLevel(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        final Label worldLabel =  new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        final Label marioLabel =  new Label("MARIO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        sb.begin();
        sb.draw(coin, 10, 10);
        sb.end();
        table.add(coinsCounterLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownlabel).expandX();

        stage.addActor(table);

    }

    /**
     * @param dt is the delta time 
     */
    public void update(final float dt) {
        timeCount += dt;
        while (timeCount >= 1) {
            worldTimer--;
            timeCount -= 1;
        }
        countdownlabel.setText(String.format("%03d", worldTimer));
    }

    /** -. 
     * @param value is how much points will be added to the score label
     */
    public void addScore(final int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    /** 
     * used everytime mario collects a coin
     *  */
    public void addCoin() {
        coinsCounter++;
        coinsCounterLabel.setText(String.format("%02d", coinsCounter));
    }

    /** -. 
     * @return 
     */
    public void dispose() {
        stage.dispose();
    }

    /** -. 
     * @return worldTimer -.
     */
    public float getTime() {
        return worldTimer;
    }

    /** -. 
     * @return stage -.
     */
    public Stage getStage() {
        return this.stage;
    }
    
    public int getScore() {
        return score;
    }
    
    public int getCoinCount() {
        return coinsCounter;
    }
}
