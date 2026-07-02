package view.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

/**
 * Enumerazione per la gestione di tutti le immagini da visualizzare.
 * 
 * @author Gnoli Mirco
 *
 */
public enum ImageViewObject {

    /**
     * 
     */
    SFONDO("Sfondo.jpg"),
    /**
     * 
     */
    LATERAL_WALL("Wall_Vert.png"),
    /**
     * 
     */
    HORIZONTAL_WALL("Wall_Oriz.png"),
    /**
     * 
     */
    VITTORIA("Vittoria.jpg"),
    /**
     * 
     */
    SCONFITTA("Sconfitta.jpg"),
    /**
     * 
     */
    BACK_ICON("Back_Icon.png");

    private final String path = "res" + System.getProperty("file.separator");
    private Image image;

    /** 
     * @param String nome del file
     */
    ImageViewObject(final String name) {
        try {
            this.image = new Image(new FileInputStream(path + name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return {@link Image}
     */
    public Image getImage() {
        return this.image;
    }
}
