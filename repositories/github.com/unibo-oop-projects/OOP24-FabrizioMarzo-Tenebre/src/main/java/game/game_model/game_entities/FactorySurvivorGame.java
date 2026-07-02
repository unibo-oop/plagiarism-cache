package game.game_model.game_entities;

import input.input_component.InputCommonComponent;
import model.entities.survivor.Survivor;
import view.graphics_component.survivor.GraphicsCommonComponent;

/**
 * {@inheritDoc}
 */
public class FactorySurvivorGame implements IFactorySurvivorGame {

    /**
     * {@inheritDoc}
     */
    @Override
    public IGameSurvivor gameSurvivorCommon(final Survivor common) {
        return new GameSurvivor(common,
                new GraphicsCommonComponent(common.getClass().getSimpleName()),
                new InputCommonComponent());
    }
}
