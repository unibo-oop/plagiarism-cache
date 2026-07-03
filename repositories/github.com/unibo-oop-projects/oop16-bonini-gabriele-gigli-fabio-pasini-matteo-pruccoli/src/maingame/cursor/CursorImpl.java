package maingame.cursor;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * Classe MyCursor, crea un cursore personalizzato.
 */
public final class CursorImpl {

    private Cursor c;
    private final int defaultCursor = 1;
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Cursor spinner = new CursorImpl(CURSOR.CURSORS_SNIPER).createCursor();

    /**
     * Costruttore carica l'immagine passata come parametro.
     * @param path
     *            percorso relativo dell'immagine da caricare
     */
    private CursorImpl(final CURSOR cursor) {
        try {
            final BufferedImage image = ImageIO.read(CursorImpl.class.getResource(cursor.getPath()));
            c = toolkit.createCustomCursor(image, new Point(image.getWidth() / 2, image.getHeight() / 2), "cursor");
        } catch (Exception e) {
            c = Cursor.getPredefinedCursor(this.defaultCursor);
        }
    }
    /**
     * @return Un cursore a forma di mirino
     */
    public static Cursor getCursorSpinner() {
        return spinner;
    }
    private Cursor createCursor() {
        return c;
    }
}
