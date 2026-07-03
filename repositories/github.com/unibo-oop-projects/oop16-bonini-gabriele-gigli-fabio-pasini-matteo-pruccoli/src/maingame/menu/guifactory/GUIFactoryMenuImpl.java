package maingame.menu.guifactory;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Implementazione dell'interfaccia GUIFactoryMenu.
 */
public class GUIFactoryMenuImpl implements GUIFactoryMenu {
    private static final Color FONT_COLOR = new Color(0x000000);
    private static final int FONT_SIZE = 38;
    //http://www.dafont.com/it/
    private static final String FONT_PATH = "/font/myFont.ttf";
    private static final Font DEFAULT_FONT = new Font("Algerian", Font.BOLD, FONT_SIZE);

    @Override
    public JLabel createLabel(final String text) {
        final JLabel label = new JLabel(text);
        Font font;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, GUIFactoryMenuImpl.class.getResourceAsStream(FONT_PATH)).deriveFont(Font.BOLD,
                    FONT_SIZE);
        } catch (Exception e) {
            font = DEFAULT_FONT;
            System.out.println(e);
        }
        label.setFont(font);
        label.setForeground(FONT_COLOR);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

}
