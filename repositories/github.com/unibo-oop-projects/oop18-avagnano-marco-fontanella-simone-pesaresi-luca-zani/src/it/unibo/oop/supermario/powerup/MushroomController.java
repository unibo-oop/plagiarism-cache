package it.unibo.oop.supermario.powerup;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Class defines MushroomController.
 */
public class MushroomController implements PowerUpItem {
    private final MushroomView mView;
    private final Mushroom mModel;
    private FlowerController fController;

    /**
     * MushroomController constructor.
     * 
     * @param mModel Mushroom model
     * @param mView  Mushroom view
     */
    public MushroomController(final Mushroom mModel, final MushroomView mView) {
        this.mView = mView;
        this.mModel = mModel;
        mView.setMushroom(mModel);
    }

    /**
     * Assigns FlowerController of potential spawns flower to MushroomController.
     * 
     * @param fC flower controller
     */
    public void setFController(final FlowerController fC) {
        this.fController = fC;
    }

    @Override
    public final void draw(final Batch batch) {
        if (!mModel.isDestroyed()) {
            mView.draw(batch);
        }

    }

    @Override
    public final void update(final float dt) {
        if (!mModel.isDestroyed() && mModel.isToDestroy()) {
            destroy();
            fController.destroy();
        }
        if (!mModel.isDestroyed() && !mModel.isToDestroy()) {
            mModel.getBody().setActive(true);
            mView.update(dt);
        }
    }

    @Override
    public final boolean isDestroyed() {
        return this.mModel.isDestroyed();
    }

    @Override
    public final boolean isToDestroy() {
        return mModel.isToDestroy();
    }

    @Override
    public final void queueDestroy() {
        mModel.queueDestroy();
    }

    @Override
    public final void destroy() {
        mModel.getBody().setActive(false);
        mModel.destroyBody();
    }
}
