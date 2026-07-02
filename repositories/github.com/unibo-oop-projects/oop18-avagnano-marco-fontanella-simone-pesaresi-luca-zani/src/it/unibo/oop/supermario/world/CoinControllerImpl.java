package it.unibo.oop.supermario.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

/**
 * Class defines CoinController.
 */
public class CoinControllerImpl implements CoinController {
    private final Coin cModel;
    private final CoinView cView;

    /**
     * CoinController constructor.
     * 
     * @param cModel coin model
     * @param cView  coin view
     */
    public CoinControllerImpl(final Coin cModel, final CoinView cView) {
        this.cModel = cModel;
        this.cView = cView;
        cView.setCoin(cModel);
    }

    @Override
    public final boolean isToDestroy() {
        return cModel.isToDestroy();
    }

    @Override
    public final boolean isDestroyed() {
        return cModel.isDestroyed();
    }

    @Override
    public final void waitToDestroy() {
        final float delay = 0.4f;
        final Task destroy = new Task() {
            public void run() {
                cModel.queueDestroy();
            }
        };
        Timer.schedule(destroy, delay);
    }

    @Override
    public final void update(final float dt) {
        if (!cModel.isDestroyed() && cModel.isToDestroy()) {
            cModel.getBody().setActive(false);
            cModel.destroyBody();
        }
        if (!cModel.isDestroyed() && !cModel.isToDestroy()) {
            cModel.getBody().setActive(true);
            cView.update(dt);
            cView.render(dt);
        }
    }

    @Override
    public final void draw(final Batch batch) {
        if (!cModel.isDestroyed()) {
            cView.draw(batch);
        }
        waitToDestroy();
    }

}
