package view.entity;

import java.util.Collections;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.entity.DynamicEntity;

/**
 * 
 * Class that render the entity during the game.
 *
 */
public final class EntityViewImpl implements EntityView {

    private final List<DynamicEntity> entities;
    private final Pane pane; 

    /**
     * 
     * @param pane the pane where to draw the entity. 
     * @param entities the list of entity to render. 
     */
    public EntityViewImpl(final Pane pane, final List<DynamicEntity> entities) {
        this.pane = pane;
        this.entities = entities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        Collections.unmodifiableList(entities).forEach(e -> {
            this.pane.getChildren().add(this.createImage(e));
        });
    }

    /**
     * Create a new {@link ImageView} from the specified entity.
     * @param entity the entity whose image we want to create.
     * @return the ImageView representing the entity.
     */
    private ImageView createImage(final DynamicEntity entity) {
        final ImageView image = new ImageView(entity.getImage());
        image.setPreserveRatio(true);
        image.setLayoutX(entity.getBounds().getMinX());
        image.setLayoutY(entity.getBounds().getMinY());
        image.setFitHeight(entity.getBounds().getHeight());
        image.setFitWidth(entity.getBounds().getWidth());
        return image;
    }

}
