package it.unibo.oop.supermario.world;

/**
 * Class defines BrickController.
 */
public class BrickControllerImpl implements BrickController {
    private final Brick bModel;

    /**
     * BrickController constructor.
     * 
     * @param bModel brick model
     */
    public BrickControllerImpl(final Brick bModel) {
        this.bModel = bModel;
    }

    @Override
    public final boolean isToDestroy() {
        return bModel.isToDestroy();
    }

    @Override
    public final boolean isDestroyed() {
        return bModel.isDestroyed();
    }

    @Override
    public final void update(final float dt) {
        if (bModel.isToDestroy()) {
            bModel.waitToSetNull();
            bModel.getBody().setActive(false);
            bModel.destroyBody();
        }
    }
}
