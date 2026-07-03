package breakout.view.graphics;

import javafx.scene.text.Font;

/**
 * 
 *
 */
public interface GraphicStyle {

    /**
     * @param size
     *            the size of the font
     * @return the font to use in the scene;
     */
    Font getFont(final double size);

    /**
     * @return the ID of the text style in the css (see {@link #getCSS()})
     */
    String getTextStyle();

    /**
     *
     * @return the ID of the main pane style in the css (see {@link #getCSS()})
     */
    String mainPaneStyle();

    /**
     * @return the ID style in the css of the top pane that contains player information during
     *         the game (see {@link #getCSS()})
     */
    String statsContainerStyle();

    /**
     * @return the css file that contains alle the style IDs
     */
    String getCSS();

}
