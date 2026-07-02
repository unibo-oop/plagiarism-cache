package core;

import java.util.List;
import java.util.Queue;

import enums.Stage;
import model.enemy.Enemy;
import model.entities.Block;

/**
 * Class that models a level game with all its block and all its enemies.
 */
public final class LevelImpl implements Level {

    private final Stage stage;
    private final List<Block> blocks;
    private final Queue<Enemy> enemies;

    /**
     * Constructor method of the level.
     * 
     * @param stage   the stage which the level is referred.
     * @param blocks  all the blocks of the level.
     * @param enemies all the enemies that will spawn in the level.
     */
    public LevelImpl(final Stage stage, final List<Block> blocks, final Queue<Enemy> enemies) {
        this.stage = stage;
        this.blocks = blocks;
        this.enemies = enemies;
    }

    @Override
    public List<Block> getMap() {
        return blocks;
    }

    @Override
    public Queue<Enemy> getEnemy() {
        return enemies;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

}
