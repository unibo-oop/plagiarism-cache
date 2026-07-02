package view;

import java.util.List;

import model.entities.GameEntity;

/**
 * Interface for a typical game scene.
 */
public interface GameSceneInterface {

    /**
     * Render the game scene. If the scene has nothing to render the method does
     * nothing.
     * 
     * @param gameEntities the entities of the game.
     */
    void render(List<GameEntity> gameEntities);

}
