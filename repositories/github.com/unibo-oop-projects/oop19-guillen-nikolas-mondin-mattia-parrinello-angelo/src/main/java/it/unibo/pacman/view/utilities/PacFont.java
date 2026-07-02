package it.unibo.pacman.view.utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

/**
 * Class to load Pac font.
 */
public class PacFont {

    private static final String FONT_PATH = "/PAC-FONT.TTF";
    private static final double SCALE = 1.3;
    private final Float fontSize;
    private Font font;

    /**
     * Creates a new font from a file with the default scale.
     */
    public PacFont() {
        fontSize = (float) (32f * SCALE);
        this.createFont();
    }

    /**
     * Creates a new font from a file with the given scale.
     * 
     * @param scale is the new scale
     */
    public PacFont(final double scale) {
        fontSize = (float) (32f * scale);
        this.createFont();
    }

    private void createFont() {
        try {
            this.font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream(FONT_PATH)).deriveFont(fontSize);
        } catch (FontFormatException e) {
            System.out.println("Wrong font format");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    /**
     * Getter for the font.
     * 
     * @return the font
     */
    public Font getFont() {
        return this.font;
    }
}
