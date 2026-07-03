package it.oop.project.view;

import java.awt.Color;

import com.google.common.base.Optional;

/**
 * Provides colors for components of 2048 view.
 *
 */
public interface ColorProvider {

    /**
     * Provides the background color for tiles of the specified value.
     * 
     * @param value
     *            the value you need information about.
     * @return the background color for the tile.
     */
    Color getTileBg(final Optional<Integer> value);

    /**
     * Provides the font color for tiles of the specified value.
     * 
     * @param value
     *            the value you need information about.
     * @return the font color for the tile.
     */
    Color getTileFont(final Optional<Integer> value);

    /**
     * Provides a dark font color.
     * 
     * @return a dark font color.
     */
    Color getDarkFont();

    /**
     * Provides a bright font color.
     * 
     * @return a bright font color.
     */
    Color getBrightFont();

    /**
     * Provides the font color for score title.
     * 
     * @return the font color for score title.
     */
    Color getScoreTitleFont();

    /**
     * Provides the font color for score.
     * 
     * @return the font color for score.
     */
    Color getScoreFont();

    /**
     * Provides the background color for score.
     * 
     * @return the background color for score.
     */
    Color getScoreBg();

    /**
     * Provides the font color for buttons.
     * 
     * @return the font color for buttons.
     */
    Color getButtonFont();

    /**
     * Provides the background color for buttons.
     * 
     * @return the background color for buttons.
     */
    Color getButtonBg();

    /**
     * Provides the background color for board.
     * 
     * @return the background color for board.
     */
    Color getBoardBg();

    /**
     * Provides the background color for frame.
     * 
     * @return the background color for frame.
     */
    Color getFrameBg();

    /**
     * Provides the saturation color for win dialog.
     * 
     * @return the saturation color for win dialog.
     */
    Color getWinDialogSaturation();

}
