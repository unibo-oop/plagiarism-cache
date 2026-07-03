package oop.lit.model.simplegame.elements.builders;

import oop.lit.model.simplegame.SimpleBoard;
import oop.lit.model.simplegame.elements.BasicSBE;
import oop.lit.model.simplegame.elements.BasicSBEImpl;
import oop.lit.model.simplegame.elements.actions.BasicSBEActionFactory;
import oop.lit.model.util.images.WrappedImage;
import oop.lit.util.Vector2D;

/**
 * A BasicSBE builder implementation.
 * Does not set any default value for parameters that are not set.
 */
public class BasicSBEBuilderImpl implements BasicSBEBuilder {
    private final SimpleBuilderCommonData sb = new SimpleBuilderCommonData();
    private boolean used; //= false;
    private final SimpleBoard board;
    private final BasicSBEActionFactory actionFactory;

    /**
     * @param board
     *      the board the built element belongs to.
     * @param actionFactory
     *      the action factory for the built element.
     */
    public BasicSBEBuilderImpl(final SimpleBoard board, final BasicSBEActionFactory actionFactory) {
        this.board = board;
        this.actionFactory = actionFactory;
    }

    @Override
    public BasicSBEBuilder setName(final String name) {
        sb.setName(name);
        return this;
    }
    @Override
    public BasicSBEBuilder setPosition(final Vector2D position) {
        sb.setPosition(position);
        return this;
    }
    @Override
    public BasicSBEBuilder setScale(final double scale) {
        sb.setScale(scale);
        return this;
    }
    @Override
    public BasicSBEBuilder setRotation(final Double rotation) {
        sb.setRotation(rotation);
        return this;
    }
    @Override
    public BasicSBEBuilder setImage(final WrappedImage image) {
        sb.setImage(image);
        return this;
    }
    @Override
    public BasicSBE build() {
        if (this.used) {
            throw new IllegalStateException("Builder already used");
        }
        this.used = true;
        return new BasicSBEImpl(sb.getName(), sb.getPosition(), sb.getScale(), sb.getRotation(), sb.getImage(), this.board,
                this.actionFactory);
    }
}
