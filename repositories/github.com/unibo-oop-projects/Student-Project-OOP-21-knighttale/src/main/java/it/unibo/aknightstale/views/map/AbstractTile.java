package it.unibo.aknightstale.views.map;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.aknightstale.models.entity.Direction;
import it.unibo.aknightstale.models.entity.EntityType;
import it.unibo.aknightstale.views.entity.Status;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Objects;

public abstract class AbstractTile implements Tile {

    private final EntityType entityType;
    private final String url;
    private Image img;
    private final int index;
    private boolean collidable;


    public AbstractTile(final String url, final int index, final EntityType entityType) {
        this.url = url;
        this.img = new Image(Objects.requireNonNull(AbstractTile.class.getResourceAsStream(url)));
        this.index = index;
        this.entityType = entityType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return entityType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndex() {
        return this.index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings("EI_EXPOSE_REP")       //must return a reference because it will be modified
    public Image getImage() {
        return img;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidth(final double width) {
        this.img = new Image(Objects.requireNonNull(AbstractTile.class.getResourceAsStream(this.url)), width, this.img.getHeight(), false, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHeight(final double height) {
        this.img = new Image(Objects.requireNonNull(AbstractTile.class.getResourceAsStream(this.url)), this.img.getWidth(), height, false, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.img.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.img.getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCollidable(final boolean collidable) {
        this.collidable = collidable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCollidable() {
        return this.collidable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reposition() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(final Status s) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawHealthBar(final GraphicsContext gc, final double x, final double y, final double health, final double maxHealth) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Direction d) {

    }

}
