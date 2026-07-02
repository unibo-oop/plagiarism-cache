package view.controllers.minigames.colorgama;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Triple;

import javafx.scene.paint.Color;
import model.minigames.colorgama.ColorMethod;

/**
 * This class implements {@link ColorConverter}.
 *
 */
public class ColorConverterImpl implements ColorConverter {

    private final ColorMethod method;

    /**
     * Constructor of {@link ColorConverter}.
     * @param method
     *          the method to represent colors
     */
    public ColorConverterImpl(final ColorMethod method) {
        this.method = method;
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Color> convertToColor(final Triple<Number, Number, Number> values) {
        switch (this.method) {
        case HSB_VALUE:
            return Optional.of(Color.hsb(values.getLeft().doubleValue(), values.getMiddle().doubleValue(), values.getRight().doubleValue()));
        case RGB_VALUE:
            return Optional.of(Color.rgb(values.getLeft().intValue(), values.getMiddle().intValue(), values.getRight().intValue()));
        default:
            return Optional.empty();
        }
    }
}
