package oop.lit.model.simplegame.elements.builders;

import java.util.Objects;
import java.util.Optional;

import oop.lit.model.simplegame.SimpleBoard;
import oop.lit.model.simplegame.elements.FlippableSBE;
import oop.lit.model.simplegame.elements.FlippableSBEImpl;
import oop.lit.model.simplegame.elements.actions.FlippableSBEActionFactory;
import oop.lit.model.util.images.WrappedImage;
import oop.lit.util.Vector2D;

/**
 * A FlippableSBE builder implementation.
 * Does not set any default value for parameters that are not set.
 */
public class FlippableSBEBuilderImpl implements FlippableSBEBuilder {
    private final SimpleBuilderCommonData sb = new SimpleBuilderCommonData();
    private boolean used; //= false;
    private final SimpleBoard board;
    private final FlippableSBEActionFactory actionFactory;
    private Optional<WrappedImage> backFace = Optional.empty();

    /**
     * @param board
     *      the board the built element belongs to.
     * @param actionFactory
     *      the action factory for the built element.
     */
    public FlippableSBEBuilderImpl(final SimpleBoard board, final FlippableSBEActionFactory actionFactory) {
        this.board = board;
        this.actionFactory = actionFactory;
    }

    @Override
    public FlippableSBEBuilder setName(final String name) {
        sb.setName(name);
        return this;
    }
    @Override
    public FlippableSBEBuilder setPosition(final Vector2D position) {
        sb.setPosition(position);
        return this;
    }
    @Override
    public FlippableSBEBuilder setSize(final double scale) {
        sb.setScale(scale);
        return this;
    }
    @Override
    public FlippableSBEBuilder setRotation(final Double rotation) {
        sb.setRotation(rotation);
        return this;
    }
    @Override
    public FlippableSBEBuilder setFrontImage(final WrappedImage image) {
        sb.setImage(image);
        return this;
    }
    @Override
    public FlippableSBEBuilder setBackImage(final WrappedImage image) {
        Objects.requireNonNull(image);
        this.backFace = Optional.of(image);
        return this;
    }
    @Override
    public FlippableSBE build() {
        if (this.used) {
            throw new IllegalStateException("Builder already used");
        }
        this.used = true;
        return new FlippableSBEImpl(sb.getName(), sb.getPosition(), sb.getScale(), sb.getRotation(), sb.getImage(),
                this.backFace, this.board, this.actionFactory);
    }
}
