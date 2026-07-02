package view.animations;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utilities.Directions;
import utilities.Utilities;

/**
 * Class that manages common methods for animations.
 *
 */
public class GeneralAnimationImpl implements GeneralAnimation {
    private double x;
    private double y;
    private final ImageView image;
    private Directions directionImage;

    /**
     * Constructor that selects the image's path.
     * 
     * @param path
     *            of the image
     */
    public GeneralAnimationImpl(final String path) {
        this.image = new ImageView(new Image(path));
    }

    @Override
    public final void setImageDimension(final double wTile, final double hTile) {
        this.getImage().setFitWidth(Utilities.W / (wTile));
        this.getImage().setFitHeight(Utilities.H / (hTile));
    }

    @Override
    public final void addImageToPane(final Pane root) {
        root.getChildren().add(this.getImage());
    }

    @Override
    public final double getX() {
        return x;
    }

    @Override
    public final void setX(final double x) {
        this.x = x;
    }

    @Override
    public final double getY() {
        return this.y;
    }

    @Override
    public final void setY(final double y) {
        this.y = y;
    }

    @Override
    public final Directions getDirectionImage() {
        return this.directionImage;
    }

    @Override
    public final void setDirectionImage(final Directions directionImage) {
        this.directionImage = directionImage;
    }

    @Override
    public final ImageView getImage() {
        return image;
    }
}
