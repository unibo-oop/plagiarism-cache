package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.Audio;
import utilities.Audio.Song;
import utilities.ResourceLoader;
import utilities.ScreenResolution;

/***/
public final class GUIFactoryImpl implements GUIFactory {
    private static final String FONT_URL = "/font/DoctorJekyllNF.ttf";
    private static final double FONT_PROP = 0.025;
    private static final Font FONT = loadFont();
    private static final GUIFactory SINGLETON = new GUIFactoryImpl();

    private GUIFactoryImpl() {
        super();
    }

    @Override
    public JPanel createPanel(final Optional<LayoutManager> layout) {
        final JPanel panel;
        if (layout.isPresent()) {
            panel = new JPanel(layout.get());
        } else {
            panel = new JPanel();
        }
        panel.setOpaque(false);
        return panel;
    }

    @Override
    public JButton createButton(final String text) {
        final JButton btn = new JButton(text);
        btn.setBorderPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setFont(FONT);
        btn.setForeground(Color.WHITE);
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.BLACK);
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.WHITE);
            }
        });
        btn.addActionListener(l-> Audio.playSound(Song.BUTTON_CLICK, false));
        return btn;
    }

    @Override
    public JLabel createLabel(final String text) {
        final JLabel lbl = new JLabel(text);
        lbl.setFont(FONT);
        lbl.setForeground(Color.WHITE);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;

    }

    @Override
    public JComboBox<String> createComboBox(final List<String> elements) {
        final JComboBox<String> cmb = new JComboBox<>();
        elements.forEach(elem -> cmb.addItem(elem));
        cmb.setBackground(Color.RED);
        cmb.setFont(FONT);
        return cmb;
    }

    /**
     * @return A GuiFactory object.
     */
    public static GUIFactory getFactory() {
        return SINGLETON;
    }

    private static Font loadFont() {
        final double fontSize = ScreenResolution.getScreenHeight() * FONT_PROP;
        try {
            return Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.load(FONT_URL)).deriveFont((float) fontSize);
        } catch (FontFormatException | IOException ex) {
            return new Font("Times New Roman", Font.PLAIN, (int) fontSize);
        }
    }

}
