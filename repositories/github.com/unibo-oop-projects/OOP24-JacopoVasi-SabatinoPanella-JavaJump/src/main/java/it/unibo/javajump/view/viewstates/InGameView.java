package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.view.renderers.RenderManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.GAMEPLAY_MESSAGE_TIME_TOGGLE;

/**
 * The type In game view.
 */
public final class InGameView implements GameViewState {

    private final RenderManager renderer;

    private boolean showHighScoreMessage = true;
    private long lastToggleTime = System.currentTimeMillis();

    /**
     * Instantiates a new In game view.
     *
     * @param renderer the renderer
     */
    public InGameView(final RenderManager renderer) {
        this.renderer = renderer;
    }

    @Override
    public void draw(final Graphics g, final GameModel model) {

        final List<GameObject> snapshot;
        synchronized (model.getGameObjects()) {
            snapshot = new ArrayList<>(model.getGameObjects());

        }
        if (g instanceof Graphics2D g2) {


            final float deltaTime = model.getDeltaTime();

            renderer.drawBackground1(g2, model, deltaTime);
            renderer.drawBackground2(g2, model, deltaTime);

            final float cameraOffsetY = model.getCameraManager().getCurrentOffset();

            for (final GameObject obj : snapshot) {
                if (obj instanceof Coin c) {
                    renderer.drawCoin(g2, c, cameraOffsetY, deltaTime);
                } else if (obj instanceof Platform p) {
                    renderer.drawPlatform(g2, p, cameraOffsetY);
                }

            }


            renderer.drawPlayer(g2, model.getPlayer(), cameraOffsetY, deltaTime);


            final long now = System.currentTimeMillis();
            if (now - lastToggleTime > GAMEPLAY_MESSAGE_TIME_TOGGLE) {
                showHighScoreMessage = !showHighScoreMessage;
                lastToggleTime = now;
            }
            final boolean isNewHighScore = false;
            renderer.drawScoreUI(g2, model, isNewHighScore, showHighScoreMessage);
        }
    }

    @Override
    public void startFade() {
    }

    @Override
    public void update() {
    }

    @Override
    public void stopFade() {
    }
}
