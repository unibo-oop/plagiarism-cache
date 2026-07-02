package it.unibo.aknightstale.views.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.image.Image;

import java.util.Objects;

public class EntityViewImpl implements EntityView {

    /**
     * The image of entity.
     */
    private Image image;

    @SuppressFBWarnings("EI_EXPOSE_REP2")       //impossible clone the image
    public EntityViewImpl(final String name, final double width, final double height) {
        super();
        this.image = new Image(Objects.requireNonNull(EntityViewImpl.class.getResourceAsStream(name)), width, height, true, false);
    }

    /**
     * Set the new image of entity.
     * 
     * @param image
     */
    @SuppressFBWarnings("EI_EXPOSE_REP2")       //impossible clone the image
    public void setImage(final Image image) {
        this.image = image;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")       //impossible clone the image
    @Override
    public Image getImage() {
        return this.image;
    }

}
