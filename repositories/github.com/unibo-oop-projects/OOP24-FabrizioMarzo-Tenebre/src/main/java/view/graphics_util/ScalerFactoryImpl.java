package view.graphics_util;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Implementation of {@link ScalerFactory} that creates {@link Scaler} instances
 * for scaling coordinates between model and view dimensions.
 */
public class ScalerFactoryImpl implements ScalerFactory {

    /**
     * Private implementation of the {@link Scaler} interface.
     * Performs scaling of coordinates based on source and target dimensions.
     */
    private class ScalerTemplate implements Scaler {

        private int sourceHeight;
        private int sourceWidth;
        private int targetHeight;
        private double ratioX;
        private double ratioY;

        /**
         * Constructs a ScalerTemplate with given source and target dimensions.
         *
         * @param sourceHeight the height of the model space
         * @param sourceWidth  the width of the model space
         * @param targetHeight the height of the view space
         * @param targetWidth  the width of the view space
         */
        protected ScalerTemplate(final int sourceHeight, final int sourceWidth, final int targetHeight,
                final int targetWidth) {
            this.sourceHeight = sourceHeight;
            this.sourceWidth = sourceWidth;
            this.targetHeight = targetHeight;
            this.ratioX = (double) targetWidth / sourceWidth;
            this.ratioY = (double) targetHeight / sourceHeight;
        }

        @Override
        public void setScaleDimensions(final int height, final int width) {
            this.targetHeight = height;
            this.ratioX = (double) width / sourceWidth;
            this.ratioY = (double) height / sourceHeight;
        }

        @Override
        public int scaleX(final Pair<Double, Double> posX) {
            return (int) Math.round(posX.getLeft() * ratioX);
        }

        @Override
        public int scaleY(final Pair<Double, Double> posY) {
            return (int) Math.round(posY.getRight() * ratioY);
        }

        @Override
        public int getScaledHeight() {
            return this.targetHeight;
        }

        @Override
        public double getRatioX() {
            return this.ratioX;
        }

        @Override
        public double getRatioY() {
            return this.ratioY;
        }

    }

    /**
     * Creates a new {@link Scaler} configured to scale coordinates from the model
     * dimensions to the view dimensions.
     *
     * @param modelH the height of the model coordinate space
     * @param modelW the width of the model coordinate space
     * @param viewH  the height of the view (scaled) coordinate space
     * @param viewW  the width of the view (scaled) coordinate space
     * @return a Scaler instance for scaling coordinates
     */
    @Override
    public Scaler viewScaler(final int modelH, final int modelW, final int viewH, final int viewW) {
        return new ScalerTemplate(modelH, modelW, viewH, viewW);
    }

}
