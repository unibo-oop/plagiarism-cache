package oop.lit.model.util.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * A class used to load BufferedImages from a file, and store them for future use.
 */
public final class ImageLoader implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6771803771120324062L;
    private static final String DEFAULT_NAME = "Image";
    private final Map<String, WrappedImage> loadedImages = new HashMap<>();
    /**
     * @return
     *      a set of all images that were loaded.
     */
    public Set<WrappedImage> getImages() {
        return new HashSet<>(this.loadedImages.values());
    }

    /**
     * @return
     *      a map containing as keys all the loaded images names, and as value the corresponding bufferedImage and wrappedImage.
     */
    public Map<String, Pair<BufferedImage, WrappedImage>> getImagesMap() {
        return this.loadedImages.entrySet().stream().collect(
                Collectors.toMap(Entry::getKey, entry -> ImmutablePair.of(entry.getValue().get(), entry.getValue())));
    }

    /**
     * Adds an image to this, if it was not already present.
     * @param image
     *      an image to be added to this.
     * @param name
     *      a name for the image.
     */
    public void addImage(final WrappedImage image, final Optional<String> name) {
        Objects.requireNonNull(image);
        if (!this.loadedImages.values().contains(image)) {
            this.loadedImages.put(this.getUniqueName(name), image);
        }
    }

    /**
     * Decodes an image from a file; stores the BufferedImage for future use.
     * @param file
     *      the image file.
     * @param name
     *      a name for the loaded image.
     *
     * @throws IllegalArgumentException
     *      if the provided file is not an image or null.
     * @throws IOException
     *      if an error occurs during reading.
     */
    public void loadImage(final File file, final Optional<String> name) throws IOException {
        final BufferedImage image = ImageIO.read(file);
        if (image == null) {
            throw new IllegalArgumentException("Provided file is not an image");
        }
        this.loadedImages.put(this.getUniqueName(name), new WrappedImage(image));
    }

    private String getUniqueName(final Optional<String> name) {
        final String resName = name.orElse(DEFAULT_NAME);
        if (this.loadedImages.containsKey(resName)) {
            int i = 1;
            while (this.loadedImages.containsKey(resName + " (" + i + ")")) {
                i++;
            }
            return resName + " (" + i + ")";
        }
        return resName;
    }
}
