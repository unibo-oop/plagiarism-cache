package utils;

import javafx.geometry.Rectangle2D;
import tmw.common.Rec2D;

public class RectangleConverterUtils {

    public static Rectangle2D getJavaFXRectangle(Rec2D rec) {
        return new Rectangle2D(rec.getMinX(), rec.getMinY(), rec.getWidth(), rec.getHeight());
    }
}
