package it.unibo.bmbman.view.entities;


import java.awt.Image;
import java.util.EnumMap;
import java.util.Map;

import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.Direction;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.AnimationImpl;
import it.unibo.bmbman.view.utilities.AnimationIterator;
/**
 * View class for monsters.
 */
public class MonsterView extends AbstractEntityView {
    private static final String PATH_MONSTER_IMAGES = "/Monster/Monster";
    private static final int DIMENSION = 48;
    private static final int FRAME_PER_ANIMATION = 6;
    private final Map<Direction, AnimationIterator> sprites = new EnumMap<>(Direction.class);

    /**
     * Create a monster view.
     * @param position where the monster is located
     */
    public MonsterView(final Position position) {
        super(position, new Dimension(DIMENSION, DIMENSION), true, EntityType.MONSTER); 
        setMapDirection();
    }
    /**
     * Load the spritesheet according to the directions.
     */
    private void setMapDirection() {
        for (int i = 0; i < Direction.values().length - 1; i++) {
            setDirectionAnimation(PATH_MONSTER_IMAGES + Direction.values()[i].toString().charAt(0) + ".png", 
                    Direction.values()[i], DIMENSION, FRAME_PER_ANIMATION);
        }
    }
    /**
     * Create the animation according to the direction.
     * @param path of the spritesheet
     * @param direction
     * @param dimension dimension of the sprite
     * @param frame per animation
     */
    private void setDirectionAnimation(final String path, final Direction direction, final int dimension, final int frame) {
        this.sprites.put(direction, AnimationImpl.createAnimation(path, frame, dimension).createInfiniteIterator());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSprite() {
        return this.sprites.get(this.getDirection()).getNextImage();
    }
}

