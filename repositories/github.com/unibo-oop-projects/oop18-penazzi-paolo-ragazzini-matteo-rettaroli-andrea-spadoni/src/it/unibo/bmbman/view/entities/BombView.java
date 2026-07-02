package it.unibo.bmbman.view.entities;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import it.unibo.bmbman.model.TerrainFactoryImpl;
import it.unibo.bmbman.model.utilities.BombState;
import it.unibo.bmbman.model.utilities.Dimension;

import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.Animation;
import it.unibo.bmbman.view.utilities.AnimationImpl;
import it.unibo.bmbman.view.utilities.AnimationIterator;
/**
 * Class to manage the view of Bomb.
 *
 */
public class BombView extends AbstractEntityView {
    private static final String PATH_BOMB_IMAGES = "/bombs/bomb.png";
    private static final String PATH_EXPLOSION_IMAGES = "/bombs/explosion.png";
    private static final int DIMENSION = 50;
    private static final int DIMENSION_EXPLOSION = 150;
    private static final int FRAME_PER_ANIMATION_BOMB = 6;
    private static final int FRAME_PER_ANIMATION_EXPLOSION = 7;
    private final Animation spriteBomb;
    private final Animation spriteExplosion;
    private final Map<BombState, AnimationIterator> sprite = new HashMap<>();
    private BombState state; 
    /**
     * Create a bomb view.
     * @param position in which bomb is located
     */
    public BombView(final Position position) {
        super(position, new Dimension(DIMENSION, DIMENSION), true, EntityType.BOMB); 
        this.spriteBomb = AnimationImpl.createAnimation(PATH_BOMB_IMAGES, FRAME_PER_ANIMATION_BOMB, DIMENSION);
        this.spriteExplosion = AnimationImpl.createAnimation(PATH_EXPLOSION_IMAGES, FRAME_PER_ANIMATION_EXPLOSION, DIMENSION_EXPLOSION);
        this.state = BombState.PLANTED; 
        fillMap();
    }
    /**
     * Method to set the bomb state.
     * @param state the actual state of the bomb
     */
    public void setBombState(final BombState state) {
        this.state = state;
        if (state == BombState.IN_EXPLOSION) {
            setDimension(new Dimension(DIMENSION_EXPLOSION, DIMENSION_EXPLOSION));
            setPosition(new Position(this.getPosition().getX() - TerrainFactoryImpl.CELL_DIMENSION, 
                    this.getPosition().getY() - TerrainFactoryImpl.CELL_DIMENSION));
        }
    }
    /** 
     * Method to fill the map <state, animation>.
     */
    private void fillMap() {
        sprite.put(BombState.PLANTED, spriteBomb.createInfiniteIterator());
        sprite.put(BombState.IN_EXPLOSION, spriteExplosion.createInfiniteIterator());
        sprite.put(BombState.EXPLODED, spriteExplosion.createInfiniteIterator());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSprite() {
        return this.sprite.get(state).getNextImage();
    }
}

