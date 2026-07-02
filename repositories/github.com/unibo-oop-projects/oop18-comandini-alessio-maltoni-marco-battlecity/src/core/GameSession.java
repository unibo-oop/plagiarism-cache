package core;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import model.Player;
import model.PlayerFactory;
import model.World;
import model.command.Command;
import model.common.Counter;
import model.enemy.AiMAnagerImpl;
import model.enemy.AiManager;
import model.enemy.DummyCommmandGenerator;
import model.enemy.Enemy;
import model.enemy.EnemyFactory;
import model.entities.Tank;
import model.entities.tankcomponents.InputComponent;

/**
 * Game session manage current session of game, his player, levels.
 */
public final class GameSession {

    private static final int DEFAUL_SIMULTANEOUS_TANK = 3;
    private final World world;
    private final Queue<Level> levels;
    private final List<Player> players;
    private Level currentLevel;
    private final AiManager aiManager;
    private final State state;

    /**
     * 
     * @param world           Game world where game entity interact
     * @param levels          queue of game level
     * @param playersCommands A list of command queue, inputs from players
     */

    public GameSession(final World world, final Queue<Level> levels, final List<Queue<Command>> playersCommands) {
        this.world = world;
        this.levels = levels;
        this.players = PlayerFactory.generate(playersCommands);
        this.aiManager = new AiMAnagerImpl();
        this.state = State.RUN;
    }

    /**
     * Setup next level setting up players in start position.
     */
    public void nextLevelSetup() {
        if (this.hasOtherLevel()) {
            currentLevel = levels.poll();
            this.players.stream().forEach(p -> p.initializeTankPosition()); // Initialize tank Player position
            this.world.setup(players.stream().map(Player::getTank).collect(Collectors.toList()), currentLevel.getMap());
            this.aiManager.resetAll();
        }
    }

    /**
     * 
     * @return true if the level Queue have an other level inside
     */
    public boolean hasOtherLevel() {
        return !levels.isEmpty();
    }

    /**
     * A method to know if current level has finish.
     * 
     * @return true if the level is finished
     */
    public boolean isLevelFinished() {
        return this.currentLevel.getEnemy().isEmpty() || this.world.getEnemy().isEmpty();
    }

    /**
     * Method that populate the world of enemy if the current level have other enemy
     * and tank in the world aren't so much.
     */
    public void populateOfEnemy() {
        if (this.world.getEnemy().size() < DEFAUL_SIMULTANEOUS_TANK && !this.currentLevel.getEnemy().isEmpty()) {
            final Tank temp = EnemyFactory.getEnemy(currentLevel.getEnemy().poll());
            temp.attach(
                    new InputComponent(aiManager.getNewEnemyAI(new DummyCommmandGenerator()).getCommandQueue(), temp));
            this.world.addEnemy(temp);
        }
    }

    /**
     * A method that update the current state of the game Artificial Intelligences.
     */
    public void updateAI() {
        this.aiManager.generateAiCommands();
    }

    /**
     * 
     * @return a new game status object make as inner class.
     */
    public GameStatus getGameStatus() {
        return new GameStatus() {

            @Override
            public int getResidueTank() {
                return currentLevel.getEnemy().size();
            }

            @Override
            public List<Integer> getPlayerPoints() {
                return players.stream().map(Player::getPoints).collect(Collectors.toList());
            }

            @Override
            public List<Integer> getPlayerLife() {
                return players.stream().map(Player::getLife).collect(Collectors.toList());
            }

            @Override
            public int getLevel() {
                return currentLevel.getStage().getStageNumber();
            }

            @Override
            public List<Map<Enemy, Counter>> getKilledTank() {
                return players.stream().map(Player::getKilledTank).collect(Collectors.toList());
            }

            @Override
            public State getGameState() {
                return state;
            }
        };
    }

}
