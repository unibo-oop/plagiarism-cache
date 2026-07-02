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
 * Class to create Hero view.
 */
public class HeroView extends AbstractEntityView {

    private static final String PATH_HERO_IMAGES = "/Hero/hero";
    private static final int DIMENSION = 48;
    private static final int FRAME_PER_ANIMATION = 6;
    private final Map<Direction, AnimationIterator> sprites = new EnumMap<>(Direction.class);
    /**
     * Construct an {@link HeroView}.
     * @param position position of enitity
     */
    public HeroView(final Position position) {
        super(position, new Dimension(DIMENSION, DIMENSION), true, EntityType.HERO);
        setMapDirection();
    }

    private void setMapDirection() {
        this.sprites.put(Direction.IDLE, AnimationImpl.createAnimation(PATH_HERO_IMAGES + "I.png", 1, DIMENSION).createInfiniteIterator());
        for (int i = 0; i < Direction.values().length - 1; i++) {
            setDirectionAnimation(PATH_HERO_IMAGES + Direction.values()[i].toString().charAt(0) + ".png", 
                    Direction.values()[i], DIMENSION, FRAME_PER_ANIMATION);
        }
    }

    private void setDirectionAnimation(final String path, final Direction d, final int dimension, final int frame) {
        this.sprites.put(d, AnimationImpl.createAnimation(path, frame, dimension).createInfiniteIterator());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSprite() {
        return this.sprites.get(this.getDirection()).getNextImage();
    }


}
