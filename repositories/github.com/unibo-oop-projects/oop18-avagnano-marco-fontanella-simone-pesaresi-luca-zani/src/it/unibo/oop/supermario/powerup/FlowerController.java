package it.unibo.oop.supermario.powerup;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Class defines FlowerController.
 */
public class FlowerController implements PowerUpItem {
    private final FlowerView fView;
    private final Flower fModel;
    private MushroomController mController;

    /**
     * FLowerController constructor.
     * 
     * @param fModel Flower model
     * @param fView  Flower view
     */
    public FlowerController(final Flower fModel, final FlowerView fView) {
        this.fView = fView;
        this.fModel = fModel;
        fView.setFlower(fModel);
    }

    /**
     * Assigns mushroom controller of potential spawns mushroom to flower
     * controller.
     * 
     * @param mC mushroom controller
     */
    public void setMController(final MushroomController mC) {
        this.mController = mC;
    }

    @Override
    public final void draw(final Batch batch) {
        if (!fModel.isDestroyed()) {
            fView.draw(batch);
        }

    }

    @Override
    public final void update(final float dt) {
        if (!fModel.isDestroyed() && fModel.isToDestroy()) {
            destroy();
            mController.destroy();
        }
        if (!fModel.isDestroyed() && !fModel.isToDestroy()) {
            fModel.getBody().setActive(true);
            fView.update(dt);
            fView.render(dt);
        }
    }

    @Override
    public final boolean isToDestroy() {
        return fModel.isToDestroy();
    }

    @Override
    public final boolean isDestroyed() {
        return this.fModel.isDestroyed();
    }

    @Override
    public final void queueDestroy() {
        fModel.queueDestroy();
    }

    @Override
    public final void destroy() {
        fModel.getBody().setActive(false);
        fModel.destroyBody();
    }
}
