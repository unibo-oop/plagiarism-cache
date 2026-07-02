package view.map;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import util.image.ImageFactory;
import util.image.ImageViewPane;
import util.mapbuilder.AbstractCaseBuilder;

/**
 * package protected.
 */
class StackPaneCaseBuilder extends AbstractCaseBuilder<StackPane> {
    private final Image background;
    private boolean built;
    private final ImageFactory imageFactory;

    /**
     * 
     * @param backgroung the already generated background image, to improve performance.
     * @param imageFactory took in input to delegate the image creation, for example to improve performance cache can be used by the factory.
     */
    StackPaneCaseBuilder(final Image backgroung, final ImageFactory imageFactory) {
        super();
        this.background = backgroung;
        this.imageFactory = imageFactory;
    }

    /** {@inheritDoc} **/
    @Override
    public StackPane build() {
        if (this.built) {
            throw new IllegalStateException();
        }
        this.built = true;
        final StackPane result = new StackPane();

        final ImageViewPane background = new ImageViewPane(new ImageView(this.background));
        result.getChildren().add(background);

        if (super.getBottom().isPresent()) {
            final ImageViewPane bottom = new ImageViewPane(
                    new ImageView(imageFactory.getImageFromRelativePath(super.getBottom().get())));
            result.getChildren().add(bottom);
        }

        if (super.getTop().isPresent()) {
            final ImageViewPane top = new ImageViewPane(
                    new ImageView(imageFactory.getImageFromRelativePath(super.getTop().get())));
            result.getChildren().add(top);
        }

        if (super.getBorder().isPresent()) {
            final ImageViewPane border = new ImageViewPane(
                    new ImageView(imageFactory.getImageFromRelativePath(super.getBorder().get())));
            result.getChildren().add(border);
        }
        result.getChildren().forEach(c -> {
            ((Region) c).prefHeightProperty().bind(result.heightProperty());
            ((Region) c).prefWidthProperty().bind(result.widthProperty());
        });
        return result;
    }


}
