package it.tbt.view.javafx;

import it.tbt.commons.resourceloader.ImageLoader;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.entities.MovableEntity;
import it.tbt.model.entities.SpatialEntity;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * An implementation of a GameView that describes the Explore game state.
 */

public class JavaFxExploreView extends AbstractJavaFxView {

    private final ExploreState exploreState;
    private final Pane movingSpace = new Pane();
    private final StackPane total = new StackPane();
    private Map<MovableEntity, ImageView> images = new HashMap<>(); //Images of objects who can move
    private Set<SpatialEntity> currentEntitiesInRoom = new HashSet<>();
    private final Pane staticImages = new Pane();

    /**
     * @param exploreController The exploreController that provides the input to this view
     * @param exploreState The model data used to render the current game state
     * @param stage JavaFx main graphic component
     * @param scene JavaFx graphic component
     */
    public JavaFxExploreView(final ViewController exploreController,
                             final ExploreState exploreState,
                             final Stage stage,
                             final Scene scene) {
        super(exploreController, stage, scene);
        this.exploreState = exploreState;
        this.movingSpace.setPrefSize(exploreState.getRoom().getWidth(), exploreState.getRoom().getHeight());
        this.movingSpace.setMinSize(exploreState.getRoom().getWidth(), exploreState.getRoom().getHeight());
        this.movingSpace.setMaxSize(exploreState.getRoom().getWidth(), exploreState.getRoom().getHeight());
        loadBackground();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        if (!currentEntitiesInRoom.equals(this.exploreState.getRoom().getEntities())) {
            loadAllImages();
            this.currentEntitiesInRoom = Set.copyOf(this.exploreState.getRoom().getEntities());
        }
        Platform.runLater(() -> {
            this.total.getChildren().clear();
            loadImagesToRegion(this.movingSpace, this.images);
            this.movingSpace.getChildren().addAll(this.staticImages);
            this.total.getChildren().add(this.movingSpace);
            this.getScene().setRoot(this.total);
        });
    }

    /**
     * Creates the background.
     */
    private void loadBackground() {
        final Background backgroundSpace = new Background(
                        new BackgroundImage(
                        new Image(ImageLoader.getInstance().getFilePath(this.exploreState.getRoom().getClass())),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(1.0, 1.0, true, true, false, false)));
        this.movingSpace.setBackground(backgroundSpace);
        final Background backgroundScene = new Background(
                        new BackgroundImage(
                        new Image(ImageLoader.getInstance().getFilePath(this.exploreState.getClass())),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(
                                        1.0,
                                        1.0,
                                        true,
                                        true,
                                        false,
                                        false)));
        this.total.setBackground(backgroundScene);

    }

    /**
     * Loads all the images.
     */
    private void loadAllImages() {
        final var allEntities = getMapEntitiesImagesBasedOnPredicate(l -> true,
                Stream.concat(this.exploreState.getRoom().getEntities().stream(),
                        Stream.of(this.exploreState.getParty())).collect(Collectors.toSet()));
        loadImagesToRegion(this.staticImages,
                allEntities.entrySet().
                        stream().
                        filter(l -> !(l.getKey() instanceof MovableEntity)).
                        map(l -> Map.entry(l.getKey(), l.getValue())).
                        collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        this.images = allEntities.entrySet().
                stream().
                filter(l -> l.getKey() instanceof MovableEntity).
                map(l -> Map.entry((MovableEntity) l.getKey(), l.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Adds the SpatialEntities ImageViews to the Pane, making it sure to not exceed the Pane boundaries.
     * @param r pane on to which add the ImageViews
     * @param images Map of the images
     */
    private void loadImagesToRegion(final Pane r, final Map<? extends SpatialEntity, ImageView> images) {
        r.getChildren().clear();
        images.entrySet().stream().forEach(l -> {
            l.getValue().setX(l.getKey().getX() - l.getKey().getWidth() / 2);
            l.getValue().setY(l.getKey().getY() - l.getKey().getWidth() / 2);
            r.getChildren().add(l.getValue());
        });
    }

    /**
     * @param predicate based on which a set of SpatialEntity must be filtered.
     * @param entitySet the SpatialEntity set to be filtered and its entities to be mapped to the ImageViews
     *                  which images are being taken thanks to the {@link ImageLoader}
     * @return a map of SpatialEntity and their respective imageView obtained by {@link ImageLoader}
     */
    private Map<SpatialEntity, ImageView> getMapEntitiesImagesBasedOnPredicate(final Predicate<SpatialEntity> predicate,
                                                                               final Set<SpatialEntity> entitySet) {
        final var map = entitySet.stream().
                filter(predicate).
                collect(Collectors.toMap(l -> l,
                l -> new ImageView(ImageLoader.getInstance().getFilePath(l.getClass()))));
        map.entrySet().stream().forEach(l -> {
            l.getValue().setFitWidth(l.getKey().getWidth());
            l.getValue().setFitHeight(l.getKey().getHeight());
        });
        return map;
    }
}


