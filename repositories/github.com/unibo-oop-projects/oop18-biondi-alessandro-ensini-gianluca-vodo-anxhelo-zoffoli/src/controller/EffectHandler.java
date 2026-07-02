package controller;

import java.awt.image.BufferedImage;

import controller.exceptions.ImageNotInitializedException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import model.effects.Effect;
import model.util.history.ComponentImpl;
import view.MainWindowController;

/**
 * Class to manage the effects.
 */
public final class EffectHandler {

    private EffectHandler() {
    }

    /**
     * Apply an Effect, show it in the ImageView and add it to history.
     * 
     * @param effect to apply
     */
    public static void applyEffect(final Effect effect) {
        try {
            final Image img = showEffect(effect, HistoryHandler.getHistoryHandler().getCurrentImage());
            addToHistory(img, effect);
        } catch (ImageNotInitializedException e) {
            doNothing();
        }
    }

    /**
     * Apply an Effect and show it in the ImageView.
     * 
     * @param effect to apply
     * @param image  to witch the effec will be applyed
     */
    public static void applyEffect(final Effect effect, final Image image) {
        try {
            final Image img = showEffect(effect, image);
            addToHistory(img, effect);
        } catch (Exception e) {
            doNothing();
        }
    }

    /**
     * Show an Effect in the ImageView.
     * 
     * @param effect to show
     * @return old Image
     */
    public static Image showEffect(final Effect effect) {
        try {
            final Image oldImage = HistoryHandler.getHistoryHandler().getCurrentImage();
            showEffect(effect, oldImage);
            return oldImage;
        } catch (ImageNotInitializedException e) {
            return null;
        }
    }

    /**
     * Show the given effect applying in to the given image.
     * 
     * @param effect to shoe
     * @param image  to witch the effec will be applyed
     * @return the modified image
     * @throws ImageNotInitializedException if the image is not
     */
    public static Image showEffect(final Effect effect, final Image image) throws ImageNotInitializedException {
        if (image == null) {
            throw new ImageNotInitializedException("no image to show");
        } else {
            final BufferedImage bimg = SwingFXUtils.fromFXImage(image, null);
            final Image img = SwingFXUtils.toFXImage(effect.apply(bimg), null);
            MainWindowController.setImage(img);
            return img;
        }
    }

    /**
     * Add an image to the shistory.
     * 
     * @param image  to add to history
     * @param effect of the image
     */
    public static void addToHistory(final Image image, final Effect effect) {
        final ComponentImpl component = new ComponentImpl(image, effect);
        try {
            HistoryHandler.getHistoryHandler().addChange(component);
        } catch (Exception e) {
            doNothing();
        }
    }

    // prevent PMD warning
    private static void doNothing() {

    }

}
