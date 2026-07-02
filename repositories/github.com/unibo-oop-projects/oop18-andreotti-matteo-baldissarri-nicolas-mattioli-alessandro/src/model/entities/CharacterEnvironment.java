package model.entities;

import java.util.Optional;

import utils.Pair;

/**
 * Models the environment in which the stuntman is situated.
 */
public final class CharacterEnvironment extends AbstractEnvironment {

    /**
     * @param initPos    The stuntman's initial position
     * @param height     The height of the stuntman
     * @param width      The width of the stuntman
     * @param windowSize The size of a window
     */
    public CharacterEnvironment(final Position initPos, final double height, final double width,
            final Pair<Double, Double> windowSize) {
        super(initPos, height, width, Optional.ofNullable(ComputeBorders.computeStuntmanBorder(initPos, windowSize)));
    }

    @Override
    public boolean canMove(final Position position) {
        return !this.getBorders().isPresent() || ((!this.getBorders().get().getTop().isPresent()
                || position.getY() <= this.getBorders().get().getTop().get())
                && (!this.getBorders().get().getBottom().isPresent()
                        || position.getY() <= this.getBorders().get().getBottom().get())
                && (!this.getBorders().get().getLeft().isPresent()
                        || position.getX() >= this.getBorders().get().getLeft().get())
                && (!this.getBorders().get().getRight().isPresent()
                        || position.getX() <= this.getBorders().get().getRight().get()));
    }

    /**
     * Calculate entity boundaries.
     */
    private static final class ComputeBorders {

        private static final int WINDOW_LEFT_OF_STUNTMAN = 3;
        private static final int WINDOW_RIGHT_OF_STUNTMAN = 2;

        private ComputeBorders() {

        }

        /**
         * Calculate Stuntman boundaries.
         * 
         * @param position   The stuntman position
         * @param windowSize The windows dimension
         * @return The border of the stuntman
         */
        public static Border computeStuntmanBorder(final Position position, final Pair<Double, Double> windowSize) {
            return new Border(Optional.empty(), Optional.of(position.getY() + windowSize.getY() / 2),
                    Optional.of(position.getX() - windowSize.getX() * WINDOW_LEFT_OF_STUNTMAN - windowSize.getX() / 2),
                    Optional.of(
                            position.getX() + windowSize.getX() * WINDOW_RIGHT_OF_STUNTMAN + windowSize.getX() / 2));
        }

    }

}
