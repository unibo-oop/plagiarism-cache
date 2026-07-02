package it.unibo.monoopoly.view.position.impl;

import java.awt.Color;

/**
 * Create a Builder of position of circle and number in the game
 * board using pattern builder, creating an innested static class
 * Builder.
 */
public final class NumberAndCirclePosition {

    private final int x;
    private final int y;
    private final boolean isCircle;
    private final Color color;
    private final String number;

    private NumberAndCirclePosition(final Builder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.isCircle = builder.isCircle;
        this.color = builder.color;
        this.number = builder.number;
    }

    /**
     * Gets the x of position.
     * 
     * @return the x of position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the y of position.
     * 
     * @return return the y of position
     */
    public int getY() {
        return this.y;
    }

    /**
     * Return whether is a circle or a number.
     * 
     * @return true if is a circle, false if is a number
     */
    public boolean isCircle() {
        return this.isCircle;
    }

    /**
     * Gets the color of the circle.
     * 
     * @return the color of the circle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets the number of house to show.
     * 
     * @return the number of house to show
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Builder of class NumberAndCirclePosition.
     * Use the Builder pattern to allow the creation of objects with custom
     * parameters.
     */
    public static class Builder {
        private int x;
        private int y;
        private boolean isCircle;
        private Color color;
        private String number;

        /**
         * The parameter to be setted in builder.
         * 
         * @param x to set in Builder.
         * @return the actual Builder.
         */
        public Builder x(final int x) {
            this.x = x;
            return this;
        }

        /**
         * The parameter to be setted in builder.
         * 
         * @param y to set in Builder.
         * @return the actual Builder.
         */
        public Builder y(final int y) {
            this.y = y;
            return this;
        }

        /**
         * The parameter to be setted in builder.
         * 
         * @param isCircle to set in Builder.
         * @return the actual Builder.
         */
        public Builder circle(final boolean isCircle) {
            this.isCircle = isCircle;
            return this;
        }

        /**
         * The parameter to be setted in builder.
         * 
         * @param color to set in Builder.
         * @return the actual Builder.
         */
        public Builder color(final Color color) {
            this.color = color;
            return this;
        }

        /**
         * The parameter to be setted in builder.
         * 
         * @param number to set in Builder.
         * @return the actual Builder.
         */
        public Builder number(final String number) {
            this.number = number;
            return this;
        }

        /**
         * create the effective object whit data setted.
         * 
         * @return the object created with builder.
         */
        public NumberAndCirclePosition build() {
            return new NumberAndCirclePosition(this);
        }
    }

}
