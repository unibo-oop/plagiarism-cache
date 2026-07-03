package it.oop.project.view;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

/**
 * Provides classic colors for 2048 view.
 *
 */
public class ClassicColorProvider implements ColorProvider {

    private static final Optional<Integer> DEFAULT = Optional.of((int) Math
            .pow(2, 12));
    private final Map<Optional<Integer>, ClassicPowerOfTwoColor> tileColorMap;

    /**
     * Constructs an object that provides classic colors for 2048 view.
     */
    public ClassicColorProvider() {
        this.tileColorMap = new HashMap<>();
        for (ClassicPowerOfTwoColor c : ClassicPowerOfTwoColor.values()) {
            Optional<Integer> k;
            if (c.getPower().isPresent()) {
                k = Optional.of((int) Math.pow(2, c.getPower().get()));
            } else {
                k = Optional.absent();
            }
            this.tileColorMap.put(k, c);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getTileBg(Optional<Integer> value) {
        if (this.tileColorMap.containsKey(value)) {
            return this.tileColorMap.get(value).getBgColor();
        } else {
            return this.tileColorMap.get(DEFAULT).getBgColor();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getTileFont(Optional<Integer> value) {
        if (this.tileColorMap.containsKey(value)) {
            return this.tileColorMap.get(value).getFontColor();
        } else {
            return this.tileColorMap.get(DEFAULT).getFontColor();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getDarkFont() {
        return ClassicComponentColor.getDarkFont();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getBrightFont() {
        return ClassicComponentColor.getBrightFont();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getScoreTitleFont() {
        return ClassicComponentColor.SCORE_TITLE.getFontColor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getScoreFont() {
        return ClassicComponentColor.SCORE.getFontColor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getScoreBg() {
        return ClassicComponentColor.SCORE.getBgColor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getButtonFont() {
        return ClassicComponentColor.BUTTON.getFontColor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getButtonBg() {
        return ClassicComponentColor.BUTTON.getBgColor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getBoardBg() {
        return ClassicComponentColor.BOARD.getBgColor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getFrameBg() {
        return ClassicComponentColor.FRAME.getBgColor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getWinDialogSaturation() {
        return ClassicComponentColor.getWinDialogSaturation();
    }

}
