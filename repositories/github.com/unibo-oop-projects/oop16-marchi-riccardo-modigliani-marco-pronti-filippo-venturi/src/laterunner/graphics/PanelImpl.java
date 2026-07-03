package laterunner.graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel Implementation.
 */
public class PanelImpl extends JPanel implements Panel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private PicturesFunction pics = new PicturesFunction();

    @Override
    public JButton createButton(final ImageIcon img) {
        JButton button = new JButton(img);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    @Override
    public Font createFont(final String name, final float size) {
        Optional<Font> customFont = Optional.empty();
        try (InputStream in = Stats.class.getResourceAsStream("Digital Dot Roadsign.otf")) {
            customFont = Optional.of(Font.createFont(Font.TRUETYPE_FONT, in));
            customFont = Optional.of(customFont.get().deriveFont(size));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont.get());
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return customFont.get();

    }

    /**
     * Gets an instance of IconsFunction.
     * 
     * @return
     *          an instance of IconsFunction class
     */
    protected PicturesFunction getPics() {
        return this.pics;
    }
}