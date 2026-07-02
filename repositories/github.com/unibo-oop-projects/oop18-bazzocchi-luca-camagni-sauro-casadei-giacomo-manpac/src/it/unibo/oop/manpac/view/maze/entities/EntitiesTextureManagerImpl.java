package it.unibo.oop.manpac.view.maze.entities;

import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.utils.Pair;
import it.unibo.oop.manpac.utils.PairImpl;

/**
 * Class to get a texture based on a pair of entity and direction.
 */
public final class EntitiesTextureManagerImpl implements EntitiesTextureManager<Entity, Directions> {

    private final Map<Pair<Entity, Directions>, Texture> textureMap;
    private final Texture frightenedPhantom; 
    private final Texture frightenedPhantomEnd; 

    /**
     * Constructor of the PairImpl class.
     */
    public EntitiesTextureManagerImpl() {
        this.textureMap = new LinkedHashMap<>();

        this.textureMap.put(new PairImpl<>(Entity.PACMAN, Directions.UP), new Texture("Sprites/Pacman_Up.png"));
        this.textureMap.put(new PairImpl<>(Entity.PACMAN, Directions.DOWN), new Texture("Sprites/Pacman_Down.png"));
        this.textureMap.put(new PairImpl<>(Entity.PACMAN, Directions.LEFT), new Texture("Sprites/Pacman_Left.png"));
        this.textureMap.put(new PairImpl<>(Entity.PACMAN, Directions.RIGHT), new Texture("Sprites/Pacman_Right.png"));
        this.textureMap.put(new PairImpl<>(Entity.PACMAN, Directions.STOP), new Texture("Sprites/Pacman_Start.png"));

        this.textureMap.put(new PairImpl<>(Entity.BLINKY, Directions.UP), new Texture("Sprites/Blinky_Up.png"));
        this.textureMap.put(new PairImpl<>(Entity.BLINKY, Directions.DOWN), new Texture("Sprites/Blinky_Down.png"));
        this.textureMap.put(new PairImpl<>(Entity.BLINKY, Directions.LEFT), new Texture("Sprites/Blinky_Left.png"));
        this.textureMap.put(new PairImpl<>(Entity.BLINKY, Directions.RIGHT), new Texture("Sprites/Blinky_Right.png"));

        this.textureMap.put(new PairImpl<>(Entity.INKY, Directions.UP), new Texture("Sprites/Inky_Up.png"));
        this.textureMap.put(new PairImpl<>(Entity.INKY, Directions.DOWN), new Texture("Sprites/Inky_Down.png"));
        this.textureMap.put(new PairImpl<>(Entity.INKY, Directions.LEFT), new Texture("Sprites/Inky_Left.png"));
        this.textureMap.put(new PairImpl<>(Entity.INKY, Directions.RIGHT), new Texture("Sprites/Inky_Right.png"));

        this.textureMap.put(new PairImpl<>(Entity.PINKY, Directions.UP), new Texture("Sprites/Pinky_Up.png"));
        this.textureMap.put(new PairImpl<>(Entity.PINKY, Directions.DOWN), new Texture("Sprites/Pinky_Down.png"));
        this.textureMap.put(new PairImpl<>(Entity.PINKY, Directions.LEFT), new Texture("Sprites/Pinky_Left.png"));
        this.textureMap.put(new PairImpl<>(Entity.PINKY, Directions.RIGHT), new Texture("Sprites/Pinky_Right.png"));

        this.textureMap.put(new PairImpl<>(Entity.CLYDE, Directions.UP), new Texture("Sprites/Clyde_Up.png"));
        this.textureMap.put(new PairImpl<>(Entity.CLYDE, Directions.DOWN), new Texture("Sprites/Clyde_Down.png"));
        this.textureMap.put(new PairImpl<>(Entity.CLYDE, Directions.LEFT), new Texture("Sprites/Clyde_Left.png"));
        this.textureMap.put(new PairImpl<>(Entity.CLYDE, Directions.RIGHT), new Texture("Sprites/Clyde_Right.png"));

        this.frightenedPhantom = new Texture("Sprites/Frightened_Phantom.png");
        this.frightenedPhantomEnd = new Texture("Sprites/Frightened_End_Phantom.png");

    }

    @Override
    public Texture getEntityTexture(final Pair<Entity, Directions> pair) {
        return textureMap.get(pair);
    }

    @Override
    public Texture getFrightenedPhantomTexture() {
        return this.frightenedPhantom;
    }

    @Override
    public Texture getFrightenedEndPhantomTexture() {
        return this.frightenedPhantomEnd;
    }

}
