package vg.view.entity;

import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import vg.utils.ImageFXUtils;
import vg.utils.V2D;

import java.util.List;

/**
 * This class is the implementation of the entity view.
 */
public class EntityBlockImpl extends Rectangle implements EntityBlock {
    private List<String> animationPathList;
    private int indexImage;
    private final Rectangle rectangleOverlay;

    public EntityBlockImpl(final V2D position, final Dimension2D dimension, final List<String> animationPathList)  {
        super(dimension.getWidth(), dimension.getHeight());
        this.animationPathList = animationPathList;
        this.rectangleOverlay = new Rectangle(dimension.getWidth(), dimension.getHeight());
        this.setPosition(this.getCenterPosition(position));
        this.rectangleOverlay.setVisible(false);
        this.indexImage = 0;
        this.setImage(this.animationPathList.get(this.indexImage));
    }

    private V2D getCenterPosition(final V2D position) {
        return new V2D(position.getX() - this.getWidth() / 2, position.getY() - this.getHeight() / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V2D getPosition() {
        return new V2D(this.getX(), this.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final V2D position) {
        final V2D centerPos = new V2D(position.getX() - this.getWidth() / 2, position.getY() - this.getHeight() / 2);
        this.setX(centerPos.getX());
        this.setY(centerPos.getY());
        this.rectangleOverlay.setX(centerPos.getX());
        this.rectangleOverlay.setY(centerPos.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShow(final boolean show) {
        this.setVisible(show);
        final boolean isVisible = this.rectangleOverlay.isVisible() && show;
        this.rectangleOverlay.setVisible(isVisible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAnimation(final List<String> animationPathList) {
        this.animationPathList = animationPathList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInParentNode(final ObservableList<Node> gameAreaNode) {
        gameAreaNode.add(this);
        gameAreaNode.add(this.rectangleOverlay);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setImageOverlay(final String pathImage) {
        this.rectangleOverlay.setFill(ImageFXUtils.createImagePatternFrom(pathImage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showImageOverlay() {
        this.rectangleOverlay.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideImageOverlay() {
        this.rectangleOverlay.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimation() {
        if (this.animationPathList.isEmpty()) {
            return;
        }
        this.setImage(this.animationPathList.get(this.indexImage));
        this.indexImage = (this.indexImage + 1) % this.animationPathList.size();
    }

    /**
     * Getter for the overlay.
     * @return the overlay of the {@link EntityBlock}
     */
    public Rectangle getRectangleOverlay() {
        return this.rectangleOverlay;
    }
    private void setImage(final String pathImage) {
        this.setFill(ImageFXUtils.createImagePatternFrom(pathImage));
    }
}
