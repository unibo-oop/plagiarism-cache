package thatlevelagain;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * CaricatoreImmagini.
 *
 */
public final class CaricatoreImmagini {

    private static final CaricatoreImmagini SINGLETON = new CaricatoreImmagini();

    private CaricatoreImmagini() {
        //NOT USED
    }
    /**
     * 
     * @return
     *         return instance of CaricatoreImmagini
     */
    public static CaricatoreImmagini getCaricatoreImmagini() {
         return SINGLETON;
    }
        /**
         * 
         * @param percorso
         *         represent path's image 
         * @return
         *         return buffered image from path passing in param percorso
         */

        public BufferedImage carica(final String percorso) {
            BufferedImage image = null; /*read temporary image*/
             try {
                   image = ImageIO.read(getClass().getResource(percorso)); /*search image identified from path and "load" in image*/
             } catch (IOException e) { /*Throw exception in case path isn't right*/
                   System.out.println("Immagine non Caricata");
                   e.printStackTrace();
             }
        return image; /*ritorna l'immagine caricata*/
    }
}
