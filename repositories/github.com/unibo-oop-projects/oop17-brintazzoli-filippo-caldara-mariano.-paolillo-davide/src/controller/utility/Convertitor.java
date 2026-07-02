package controller.utility;

import model.Model;
import model.utility.Pair;
import view.View;

/**
 * Utility class to convert from model dimension to view dimension.
 */
public final class Convertitor {
    private static Model world;
    private static View view;

    private Convertitor() { }

    /**
     * Initialize the fields.
     * 
     * @param model
     *            the game {@link Model}.
     * @param gameView
     *            the game {@link View}.
     */
    public static void initialize(final Model model, final View gameView) {
        world = model;
        view = gameView;
    }

    /**
     * Convert from the {@link View} dimension to the {@link Model} dimension.
     * 
     * @param toConvert
     *            a {@link Pair} to convert.
     * @return a {@link Pair} of the new values.
     */
    public static Pair<Double, Double> viewToModel(final Pair<Double, Double> toConvert) {
        return convertitor(toConvert, world.getBounds(), view.getBounds());
    }

    /**
     * Convert from the {@link Model} dimension to the {@link View} dimension.
     * 
     * @param toConvert
     *            a {@link Pair} to convert.
     * @return a {@link Pair} of the new values.
     */
    public static Pair<Double, Double> modelToView(final Pair<Double, Double> toConvert) {
        return convertitor(toConvert, view.getBounds(), world.getBounds());
    }

    /**
     * Convertitor of the position and dimension of an object.
     * 
     * @param toConvert
     *            the object to convert.
     * @param firstDimension
     *            the {@link Model} or the {@link View} dimension.
     * @param secondDimension
     *            the {@link Model} or the {@link View} dimension.
     * @return the converted object.
     */
    private static Pair<Double, Double> convertitor(final Pair<Double, Double> toConvert, final Pair<Double, Double> firstDimension,
            final Pair<Double, Double> secondDimension) {
        return new Pair<>(toConvert.getFirst() * firstDimension.getFirst() / secondDimension.getFirst(),
                (toConvert.getSecond() * firstDimension.getSecond() / secondDimension.getSecond()));
    }

}
