package view;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

/**
 * Classe utility di image-processing
 * @author redim
 *
 */

public final class FilterUtilities {

    private FilterUtilities() {
        
    }
    
    /**
     * Applica il ColorSpace.CS_GRAY all'immagine
     * @param image
     * @return l'immagine processata
     * 
     */
    
    public static BufferedImage gray(BufferedImage image) {
        ColorConvertOp colorConvert = 
            new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        colorConvert.filter(image, image);

        return image;
    }
}
