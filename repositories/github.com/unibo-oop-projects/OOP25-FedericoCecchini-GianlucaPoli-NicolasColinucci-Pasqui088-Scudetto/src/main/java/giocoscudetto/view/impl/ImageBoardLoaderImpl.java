package giocoscudetto.view.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.view.api.ImageBoardLoader;

/**
 * This class implements the ImageBoardLoader interface, 
 * it loads the images for the board and provides 
 * a method to get the image at a specific position.
 */
public class ImageBoardLoaderImpl implements ImageBoardLoader {

    private static final int NUMBER_OF_IMAGES = 32;
    private final MatchController matchController;
    private final List<Image> images = new ArrayList<>();

    /**
     * Constructor of the ImageBoardLoaderImpl class.
     * 
     * @param matchController the match controller to get the image paths from.
     * @throws IOException if loading an image fails.
     */
    @SuppressFBWarnings
    public ImageBoardLoaderImpl(final MatchController matchController) throws IOException {
        this.matchController = matchController;
        loadImages();
    }

    /**
     * Loads the images for the board from the paths provided by the match controller.
     * 
     * @throws IOException if loading an image fails.
     */
    private void loadImages() throws IOException {
        for (int i = 0; i < NUMBER_OF_IMAGES; i++) {

            try {
                final BufferedImage img = ImageIO.read(new File("src/main/resources/images/backgrounds/boxes_image/"
                                 + this.matchController.getBoxImage(i)));
                this.images.add(img);
            } catch (final IOException e) {
                throw new IOException("Failed to load image", e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage(final int position) {
        return images.get(position);
    }
}
