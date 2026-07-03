package oop.lit.model.simplegame.elements.builders;

import oop.lit.model.simplegame.actions.GroupActionFactory;
import oop.lit.model.simplegame.elements.GroupSBE;
import oop.lit.model.simplegame.elements.GroupSBEImpl;
import oop.lit.model.simplegame.elements.actions.GroupSBEActionFactory;
import oop.lit.model.util.images.WrappedImage;
import oop.lit.util.Vector2D;

/**
 * A GroupSBE builder implementation.
 * Does not set any default value for parameters that are not set.
 */
public class GroupSBEBuilderImpl implements GroupSBEBuilder {
    private final SimpleBuilderCommonData sb = new SimpleBuilderCommonData();
    private boolean used; //= false;
    private final GroupSBEActionFactory actionFactory;
    private final GroupActionFactory groupActionFactory;

    /**
     * @param actionFactory
     *      the action factory for the built element.
     * @param groupActionFactory
     *      the action factory for the inner group of the built element.
     */
    public GroupSBEBuilderImpl(final GroupSBEActionFactory actionFactory, final GroupActionFactory groupActionFactory) {
        this.actionFactory = actionFactory;
        this.groupActionFactory = groupActionFactory;
    }
    @Override
    public GroupSBEBuilder setName(final String name) {
        sb.setName(name);
        return this;
    }
    @Override
    public GroupSBEBuilder setPosition(final Vector2D position) {
        sb.setPosition(position);
        return this;
    }
    @Override
    public GroupSBEBuilder setScale(final double scale) {
        sb.setScale(scale);
        return this;
    }
    @Override
    public GroupSBEBuilder setRotation(final Double rotation) {
        sb.setRotation(rotation);
        return this;
    }
    @Override
    public GroupSBEBuilder setImage(final WrappedImage image) {
        sb.setImage(image);
        return this;
    }
    @Override
    public GroupSBE build() {
        if (this.used) {
            throw new IllegalStateException("Builder already used");
        }
        this.used = true;
        return new GroupSBEImpl(sb.getName(), sb.getPosition(), sb.getScale(), sb.getRotation(), sb.getImage(),
                this.actionFactory, this.groupActionFactory);
    }
}
