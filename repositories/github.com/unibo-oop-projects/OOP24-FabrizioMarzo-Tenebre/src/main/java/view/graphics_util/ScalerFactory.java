package view.graphics_util;

/**
 * Factory interface for creating Scaler instances.
 */
public interface ScalerFactory {

    /**
     * Creates a Scaler to convert model coordinates to view coordinates
     * based on model and view dimensions.
     *
     * @param modelH height of the model coordinate space
     * @param modelW width of the model coordinate space
     * @param viewH  height of the view (scaled) coordinate space
     * @param viewW  width of the view (scaled) coordinate space
     * @return a Scaler configured for the given dimensions
     */
    Scaler viewScaler(final int modelH, final int modelW, final int viewH, final int viewW);

}
