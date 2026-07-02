package vg.controller.entity.mystery_box;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import vg.controller.gameBoard.GameBoardController;
import vg.model.Map;
import vg.model.MapImpl;
import vg.model.Stage;
import vg.model.mystery_box.AbilityDurable;
import vg.model.mystery_box.AbilityInTheBox;
import vg.model.mystery_box.ETypeAbility;
import vg.model.mystery_box.data_round.DataRound;
import vg.sound.manager.ESoundEffect;
import vg.sound.manager.SoundManager;
import vg.utils.V2D;
import vg.view.entity.EntityBlock;

import java.util.List;

/**
 * This class is the controller of the mystery box.
 */
public class MysteryBoxControllerImpl implements MysteryBoxController {
    private final AbilityInTheBox model;
    private final EntityBlock view;
    public MysteryBoxControllerImpl(final AbilityInTheBox model, final EntityBlock view) {
        this.model = model;
        this.view = view;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public AbilityDurable getDurability() {
        return (AbilityDurable) this.model;
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
    public int getRadius() {
        return this.model.getRadius();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final V2D position) {
        this.model.setPosition(position);
        this.view.setPosition(this.model.getPosition());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlinking(final boolean blinking) {
        this.model.setBlinking(blinking);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setInParentNode(final ObservableList<Node> gameAreaNode) {
        this.view.setInParentNode(gameAreaNode);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setDataRound(final DataRound dataRound) {
        this.setPosition(dataRound.getPosition());
        this.setBlinking(dataRound.isBlinking());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBlinking(final long elapsedTime) {
        this.model.updateBlinking(elapsedTime);
        this.view.setShow(this.model.isShow());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAnimation(final List<String> animation) {
        this.view.setAnimation(animation);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isType(final ETypeAbility type) {
        return this.model.getTypeAbility() == type;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShow() {
        return this.model.isShow();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void checkOnBorder(final Stage<V2D> stage, final GameBoardController gameController, final SoundManager soundManager) {
        if (!this.model.isShow() || this.model.isActivated()) {
            return;
        }

        final Map<V2D> map = stage.getMap();
        final double posX = (int) (this.getPosition().getX() * MapImpl.MAXBORDERX / gameController.getGameAreaDimension().getWidth());
        final double posY = (int) (this.getPosition().getY() * MapImpl.MAXBORDERY / gameController.getGameAreaDimension().getHeight());
        final V2D position = new V2D(posX, posY);
        final boolean isOnBorder = map.isInBorders(position);

        if (isOnBorder) {
            return;
        }

        soundManager.playEffect(ESoundEffect.PICK_BOX);
        this.activate(stage);
    }

    private void activate(final Stage<V2D> stage) {
        this.model.activate(stage);
        this.view.setAnimation(List.of(this.model.getPathReveled()));
        this.model.setBlinking(false);
        if (this.isType(ETypeAbility.INSTANT)) {
            this.model.setActiveBlinkPickUp();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showPickUpMysteryBox(final long elapsedTime) {
        if (!this.model.isShow() && this.model.isActivated()) {
            return;
        }
        this.model.updateBlinkingPickUp(elapsedTime);
        this.view.setShow(this.model.isShow());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        this.model.hide();
        this.view.setShow(this.model.isShow());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimation() {
        this.view.updateAnimation();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActivated() {
        return this.model.isActivated();
    }
}
