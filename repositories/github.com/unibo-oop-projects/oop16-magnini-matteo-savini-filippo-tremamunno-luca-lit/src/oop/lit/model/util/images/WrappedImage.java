package oop.lit.model.util.images;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

import javax.imageio.ImageIO;

/**
 * A wrapper for a bufferedImage, making it serializable.
 */
public final class WrappedImage implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2263820473543819221L;
    private transient BufferedImage image;

    /**
     * @param image
     *      the image to be wrapped.
     */
    protected WrappedImage(final BufferedImage image) {
        Objects.requireNonNull(image);
        this.image = image;
    }

    /**
     * @return
     *      the wrapped image.
     */
    public BufferedImage get() {
        return this.image;
    }

    private void readObject(final ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.image = ImageIO.read(in);
    }
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(image, "png", out);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        return result;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WrappedImage)) {
            return false;
        }
        return this.image == ((WrappedImage) obj).image;
    }
}