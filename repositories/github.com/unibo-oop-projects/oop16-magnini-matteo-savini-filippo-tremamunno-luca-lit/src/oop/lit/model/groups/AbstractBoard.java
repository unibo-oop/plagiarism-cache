package oop.lit.model.groups;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import oop.lit.model.PlayerModel;
import oop.lit.model.elements.BoardElement;
import oop.lit.util.Vector2D;

/**
 * A partial implementation of a Board. 
 * Full implementation need only to implement the getSelectedActions method.
 * @param <H>
 *      the type of BoardElements held.
 */
public abstract class AbstractBoard<H extends BoardElement> extends AbstractSelectableElementGroup<H>
        implements Board<H> {
    /**
     * 
     */
    private static final long serialVersionUID = -1462523295845637651L;

    /**
     *
     */
    protected AbstractBoard() {
        super(Optional.of("Board"));
    }

    @Override
    public boolean canMoveSelected(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return this.checkPredicate(el -> el.canMove(playingPlayer, turnPlayers));
    }

    @Override
    public void moveSelected(final Vector2D delta) {
        this.checkSelected();
        this.getSelected().forEach(el -> el.move(delta));
    }

    @Override
    public boolean canScaleSelected(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return this.checkPredicate(el -> el.canScale(playingPlayer, turnPlayers));
    }

    @Override
    public void scaleSelected(final double scalar) {
        this.checkSelected();
        this.getSelected().forEach(el -> el.scale(scalar));
    }

    @Override
    public void scaleSelected(final double scalar, final double minScale, final double maxScale) {
        this.scaleSelected(scalar);
        this.getSelected().forEach(el -> el.clampScale(minScale, maxScale));
    }

    @Override
    public boolean canRotateSelected(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return this.checkPredicate(el -> el.canRotate(playingPlayer, turnPlayers));
    }

    @Override
    public void rotateSelected(final double angle) {
        this.checkSelected();
        this.getSelected().forEach(el -> el.rotate(angle));
    }

    /**
     * @param predicate
     *      the predicate to be checked
     * @return
     *      true if all selected elements match the predicate.
     *      If there are no selected elements or not all selected elements match the predicate returns false
     */
    private boolean checkPredicate(final Predicate<H> predicate) {
        final List<H> selected = this.getSelected();
        if (selected.isEmpty()) {
            return false;
        }
        return selected.stream().allMatch(predicate);
    }

    //throws an illegalStateException if no elements are selected.
    private void checkSelected() {
        if (this.getSelected().isEmpty()) {
            throw new IllegalStateException("There are no element selected.");
        }
    }
    @Override
    public void removed() {
        throw new UnsupportedOperationException("The board can't be removed");
    }
}
