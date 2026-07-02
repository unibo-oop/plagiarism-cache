package it.unibo.biscia.view.actors;

import it.unibo.biscia.core.Level;
import it.unibo.biscia.utils.Pair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

/**
 * Implementation of {@link GameView}.
 *
 */
public class GameViewImpl implements GameView {
    private final Stage gameStage;
    private final EntityCrew entityCrew;
    private Pair<Float, Float> position;

    public GameViewImpl() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.gameStage = new Stage(
                new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        this.entityCrew = new EntityCrewImpl();
        this.gameStage.addActor((Actor) entityCrew);
        this.position = new Pair<>(0f, 0f);
    }

    @Override
    public final Stage getStage() {
        return this.gameStage;
    }

    @Override
    public final EntityCrew getEntityCrew() {
        return this.entityCrew;
    }

    @Override
    public final void setPosition(final float x, final float y) {
        this.position = new Pair<>(x, y);
        this.gameStage.getRoot().moveBy(x, y);
    }

    @Override
    public final void newLevel(final Level level) {
        this.entityCrew.reset((Gdx.graphics.getWidth() - position.getFirst()) / level.getCols(),
                (Gdx.graphics.getHeight() - position.getSecond()) / level.getRows(), level.getEntities());
    }

    @Override
    public final void dispose() {
        this.gameStage.clear();
        this.gameStage.dispose();
    }

}
