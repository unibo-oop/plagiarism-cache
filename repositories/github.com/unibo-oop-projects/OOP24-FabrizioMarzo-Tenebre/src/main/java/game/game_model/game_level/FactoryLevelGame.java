package game.game_model.game_level;

import model.level.types.ILevelFactory;
import model.level.types.LevelFactory;
import model.level.types.LevelType;
import view.graphics_component.level.GrahicsTutlevelComponent;

/**
 * Factory class responsible for creating instances of {@link IGameLevel}.
 * 
 * <p>
 * It uses an underlying {@link ILevelFactory} to create the model level,
 * then wraps it into a {@link GameLevel} with an associated graphics component.
 * </p>
 */
public class FactoryLevelGame implements IFactoryLevelGame{

    private final ILevelFactory lvlFactory = new LevelFactory();

    /**
     * {@inheritDoc}
     */
    public IGameLevel createLevelGame(final LevelType lvlType) {
        return new GameLevel(lvlFactory.createLevel(lvlType).get(),
                new GrahicsTutlevelComponent());

    }
}
