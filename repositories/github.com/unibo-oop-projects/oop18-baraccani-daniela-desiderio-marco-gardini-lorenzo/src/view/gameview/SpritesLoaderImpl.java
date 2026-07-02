package view.gameview;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import controller.gameloop.GameController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityDirection;
import model.entitiesutil.EntityMovable;
import view.viewmanager.ViewManager;

/**
 * The class has to implements the concept of sprite change in a specific
 * interval of time. It preloads all sprites in a specific folder and It can
 * return them in the form of {@link ImageView}.
 *
 */
public class SpritesLoaderImpl implements SpritesLoader {

    private static final int DELAY = 1000 / GameController.FPS;
    private static final int TIME_TO_UPDATE = 100;
    private static final String FILE_EXTENSION = ".png";
    private static final int FIRST_SPRITE = 0;
    private static final String FOLDER = "/sprites/";
    private final ViewManager loader;
    private final Map<EntityMovable, EntityDirection> entitiesStates;
    private final Map<Entity, Integer> entitiesTimers;

    private Map<String, Image> images;

    /**
     * The constructor loads all files.
     * 
     * @param loader The {@link ViewManager} used to call the {@link View} if there
     *               are errors.
     */
    public SpritesLoaderImpl(final ViewManager loader) {
        this.loader = Objects.requireNonNull(loader);
        this.entitiesStates = new HashMap<>();
        this.entitiesTimers = new HashMap<>();
        this.images = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageView getSprite(final Entity entity) {
        final StringBuilder spriteNameBuilder = new StringBuilder();
        spriteNameBuilder.append(entity.getType().toLowerCase(Locale.ENGLISH));
        if (entity instanceof EntityMovable) {
            spriteNameBuilder
                    .append('_' + ((EntityMovable) entity).getDirection().toString().toLowerCase(Locale.ENGLISH));
            this.updateStates((EntityMovable) entity);
        }
        try {
            spriteNameBuilder.append('_'
                    + entity.getClass().getMethod("getState").invoke(entity).toString().toLowerCase(Locale.ENGLISH));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            // Adds nothing to prevent "Avoid empty catch blocks" suggestion
            spriteNameBuilder.append("");
        }

        final String spriteName = this.checkSpriteExistance(entity, spriteNameBuilder.toString());
        final Image image = this.images.get(spriteName);
        if (image == null) {
            this.loader.getView().getController()
                    .handleError("Can't open " + entity.getType() + " sprite file: " + spriteName, true);
        }

        return new ImageView(image);
    }

    /*
     * Update Sprite timer if the direction is equals to the previous
     */
    private void updateStates(final EntityMovable entity) {
        if (!this.entitiesStates.containsKey(entity)) {
            this.entitiesStates.put(entity, entity.getDirection());
            this.entitiesTimers.put(entity, DELAY);
            return;
        }

        final EntityDirection oldSpriteDirection = this.entitiesStates.get(entity);
        if (entity.getDirection().equals(oldSpriteDirection)) {
            this.entitiesTimers.put(entity, this.entitiesTimers.get(entity) + DELAY);
        } else {
            this.entitiesStates.put(entity, entity.getDirection());
            this.entitiesTimers.put(entity, DELAY);
            return;
        }
    }

    /*
     * Check if there are more sprites for an {@link Entity}. If there are any
     * returns the next sprite name.
     */
    private String checkSpriteExistance(final Entity entity, final String path) {
        if (!this.images.containsKey(path) && !this.images.containsKey(path + "_" + FIRST_SPRITE)) {
            this.loadSprite(path);
        }

        if (!(entity instanceof EntityMovable)) {
            return path;
        }

        final int spriteNumber = (int) ((double) this.entitiesTimers.get(entity) / TIME_TO_UPDATE);
        if (!this.images.containsKey(path + "_" + spriteNumber) && spriteNumber == 0) {
            return path;
        }

        if (!this.images.containsKey(path + "_" + spriteNumber) && spriteNumber > 0) {
            if (!this.images.containsKey(path + "_" + FIRST_SPRITE)) {
                return path;
            }
            this.entitiesTimers.put(entity, DELAY);
            return path + "_" + FIRST_SPRITE;
        }
        return path + "_" + spriteNumber;
    }

    /*
     * Load all sprites of a specific {@link Entity}.
     * 
     */
    private void loadSprite(final String name) {
        if (Objects.nonNull(this.getClass().getResourceAsStream(FOLDER + name + FILE_EXTENSION))) { // Unique-sprite
                                                                                                    // entities
            this.images.put(name, new Image(this.getClass().getResourceAsStream(FOLDER + name + FILE_EXTENSION)));
        } else { // Multi-sprite entities
            for (int i = FIRST_SPRITE; Objects
                    .nonNull(this.getClass().getResourceAsStream(FOLDER + name + "_" + i + FILE_EXTENSION)); i++) {
                this.images.put(name + "_" + i,
                        new Image(this.getClass().getResourceAsStream(FOLDER + name + "_" + i + FILE_EXTENSION)));
            }
        }
    }
}
