package vg.controller.entity.boss;

import vg.controller.gameBoard.GameBoardController;
import vg.model.entity.ShapedEntity;
import vg.model.entity.boss.BossModel;
import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;
import vg.view.entity.EntityBlock;

/**
 * Class that controls the behavior of a boss.
 */
public class BossControllerImpl implements BossController {
    private final BossModel model;
    private final EntityBlock view;
    public BossControllerImpl(final BossModel model, final EntityBlock view) {
        this.model = model;
        this.view = view;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setInParentNode(final GameBoardController gameController) {
        this.view.setInParentNode(gameController.getGameAreaNode());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        this.model.move();
        this.view.setPosition(this.model.getPosition());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveMySpeed() {
        this.model.saveMySpeed();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final V2D v2D) {
        this.model.setSpeed(v2D);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void restoreMySpeed() {
        this.model.restoreMySpeed();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final V2D v2dPosition) {
        this.model.setPosition(v2dPosition);
        this.view.setPosition(this.model.getPosition());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public V2D getPosition() {
        return this.model.getPosition();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInShape(final V2D position) {
        return this.model.isInShape(position);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInShape(final ShapedEntity entity) {
        return this.model.isInShape(entity);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public V2D getSpeed() {
        return this.model.getSpeed();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Shape getShape() {
        return this.model.getShape();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public MassTier getMassTier() {
        return this.model.getMassTier();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setMassTier(final MassTier toSet) {
        this.model.setMassTier(toSet);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimation() {
        this.view.updateAnimation();
    }
}
