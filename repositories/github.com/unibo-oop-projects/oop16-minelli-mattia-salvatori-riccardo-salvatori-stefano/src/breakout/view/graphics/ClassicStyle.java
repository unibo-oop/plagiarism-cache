package breakout.view.graphics;

import javafx.scene.text.Font;

/**
 * Classic Style.
 */
public class ClassicStyle implements GraphicStyle {

    @Override
    public Font getFont(final double size) {
        return Fonts.CLASSIC_FONT.get(size);
    }

    @Override
    public String getTextStyle() {
        return "classic_text";
    }

    @Override
    public String mainPaneStyle() {
        return "classic_pane";
    }

    @Override
    public String statsContainerStyle() {
        return "";
    }

    @Override
    public String getCSS() {
        return "stylesheet.css";
    }

}
