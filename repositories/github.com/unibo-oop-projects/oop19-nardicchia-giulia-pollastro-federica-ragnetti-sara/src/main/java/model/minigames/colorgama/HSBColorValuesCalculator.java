package model.minigames.colorgama;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.PrimitiveIterator.OfDouble;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.tuple.Triple;

import model.DifficultyLevel;

/**
 * This class implements {@link ColorValuesCalculator}.
 *
 */
public class HSBColorValuesCalculator implements ColorValuesCalculator {

    private static final String NULL_ARG = " passed is null";
    private static final String BAD_SIZE = "size must be greater than 0";
    private static final int DEGREE = 361;

    private final HSBValuesSetting valueSettings;
    private final OfDouble values;
    private Number hue;

    /**
     * Constructor of {@link ColorValuesCalculator}.
     * 
     * @param difficulty 
     *          constants according to the difficulty.
     */
    public HSBColorValuesCalculator(final DifficultyLevel difficulty) {
        Objects.requireNonNull(difficulty, "DifficultyLevel" + NULL_ARG);
        this.valueSettings = new HSBValuesSettingImpl(difficulty);
        this.values = new Random().doubles(this.valueSettings.getRandomColorOrigin().doubleValue(), this.valueSettings.getRandomColorBound().doubleValue()).iterator();
    }

    private void check(final QuestionType question, final Set<Triple<Number, Number, Number>> colorValuesSet) {
        Objects.requireNonNull(question, "QuestionType" + NULL_ARG);
        Objects.requireNonNull(colorValuesSet, "Set<Triple<Number, Number, Number>>" + NULL_ARG);
        if (colorValuesSet.isEmpty()) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
    }

    private double calulateRandomValue() {
        return this.values.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorMethod getColorMethod() {
        return ColorMethod.HSB_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Triple<Number, Number, Number>> calculateRightColor(final QuestionType question, final Set<Triple<Number, Number, Number>> colorValuesSet) {
        this.check(question, colorValuesSet);
        final Comparator<Triple<Number, Number, Number>> bComparator = (c1, c2) -> Double.compare(c1.getRight().doubleValue(), c2.getRight().doubleValue());
        final Comparator<Triple<Number, Number, Number>> sComparator = (c1, c2) -> Double.compare(c1.getMiddle().doubleValue(), c2.getMiddle().doubleValue());
        switch (question) {
        case DARKEST:
            return Optional.of(Triple.of(this.hue,
                    colorValuesSet.stream()
                                       .max(sComparator)
                                       .get()
                                       .getMiddle().doubleValue() + this.valueSettings.getValueRange().doubleValue(),
                    colorValuesSet.stream()
                                       .min(bComparator)
                                       .get()
                                       .getRight().doubleValue() - this.valueSettings.getValueRange().doubleValue()));
        case LIGHTEST:
            return Optional.of(Triple.of(this.hue,
                    colorValuesSet.stream()
                                       .min(sComparator)
                                       .get()
                                       .getMiddle().doubleValue() - this.valueSettings.getValueRange().doubleValue(),
                    colorValuesSet.stream()
                                        .max(bComparator)
                                        .get()
                                        .getRight().doubleValue() + this.valueSettings.getValueRange().doubleValue()));
        case COLOR:
            return Optional.of(Triple.of(this.hue.doubleValue() + this.valueSettings.getHueRange().doubleValue(), 
                                             this.calulateRandomValue(),
                                             this.calulateRandomValue()));
        default:
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.hue = new Random().nextInt(DEGREE - this.valueSettings.getHueRange().intValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Triple<Number, Number, Number> calculateRandomColorValue() {
        return Triple.of(this.hue, this.calulateRandomValue(), this.calulateRandomValue());
    }
}
