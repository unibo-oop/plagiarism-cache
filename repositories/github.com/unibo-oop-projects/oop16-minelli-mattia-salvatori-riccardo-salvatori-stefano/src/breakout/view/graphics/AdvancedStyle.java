package breakout.view.graphics;

import javafx.scene.text.Font;

/**
 * Advanced Style.
 */
public class AdvancedStyle implements GraphicStyle {

    @Override
    public Font getFont(final double size) {
        return Fonts.ADVANCED_FONT.get(size);
    }

    @Override
    public String getTextStyle() {
        return "advanced_text";
    }

    @Override
    public String mainPaneStyle() {
        return "advanced_pane";
    }

    @Override
    public String statsContainerStyle() {
        return "advanced_stats";
    }

    @Override
    public String getCSS() {
        return "stylesheet.css";
    }

}
