package it.unibo.view.imageresizer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.commons.Constants;

/**
 * Implementation of the ImageResizer interface that resizes a list of BufferedImages.
 */
public class ImageResizerImpl implements ImageResizer {

    /**
     * Resizes a list of BufferedImages.
     *
     * @param images The list of BufferedImages to resize.
     * @return A list of resized BufferedImages.
     */
    @Override
    public List<BufferedImage> resize(final List<BufferedImage> images) {
        return IntStream.range(0, images.size())
            .mapToObj(i -> {
                final int scaledWidth = (int) (images.get(i).getWidth() * Constants.SCALE_PROPORTION);
                final int scaledHeight = (int) (images.get(i).getHeight() * Constants.SCALE_PROPORTION);
                final BufferedImage resizedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
                final Graphics2D g = resizedImage.createGraphics();
                g.drawImage(
                    images.get(i),
                    0,
                    0,
                    (int) (i == images.size() - 1 ? Constants.TILE_SIZE * Constants.SCALE_PROPORTION : scaledWidth),
                    (int) (i == images.size() - 1 ? Constants.TILE_SIZE * Constants.SCALE_PROPORTION : scaledHeight),
                    null);
                g.dispose();
                return resizedImage;
            })
            .collect(Collectors.toList());
    }

}
