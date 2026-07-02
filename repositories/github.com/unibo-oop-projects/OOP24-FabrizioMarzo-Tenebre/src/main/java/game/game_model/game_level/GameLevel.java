package game.game_model.game_level;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import game.game_model.game_armory.FactoryMunitionGame;
import game.game_model.game_armory.IFactoryMunitionGame;
import game.game_model.game_armory.IGameMunition;
import game.game_model.game_entities.IFactorySurvivorGame;
import game.game_model.game_entities.FactorySurvivorGame;
import game.game_model.game_entities.FactoryZombieGame;
import game.game_model.game_entities.IFactoryZombieGame;
import game.game_model.game_entities.IGameSurvivor;
import game.game_model.game_entities.IGameZombie;
import model.armory.munition.Munition;
import model.entities.zombie.Zombie;
import model.level.types.Level;
import view.graphics.GraphicsLevel;
import view.graphics_component.level.GraphicsLevelComponent;

/**
 * Concrete implementation of {@link IGameLevel} representing a playable game
 * level.
 * 
 * <p>
 * This class manages the internal state of the level, including the player
 * survivor,
 * zombies, and munitions. It synchronizes the game entities with the underlying
 * model
 * {@link Level} and updates graphical components accordingly.
 * </p>
 */
public class GameLevel implements IGameLevel {

    private Level lvl;
    private GraphicsLevelComponent imgLvl;

    private IFactorySurvivorGame factSurGam;
    private IFactoryZombieGame factZobGam;
    private IFactoryMunitionGame factMunGam;

    private final Map<Zombie, IGameZombie> gameZombieMap = new LinkedHashMap<>();
    private final Map<Munition, IGameMunition> gameMunitionMap = new LinkedHashMap<>();
    private IGameSurvivor gamSur;

    /**
     * Constructs a {@code GameLevel} instance for the given level and graphical
     * component.
     *
     * @param lvl    the {@link Level} model representing the game level data
     * @param imgLvl the {@link GraphicsLevelComponent} used to render the level
     *               graphics
     */
    public GameLevel(final Level lvl, final GraphicsLevelComponent imgLvl) {
        this.lvl = lvl;
        this.imgLvl = imgLvl;
        this.factSurGam = new FactorySurvivorGame();
        this.factZobGam = new FactoryZombieGame();
        this.factMunGam = new FactoryMunitionGame();
        this.gamSur = setGameSurvivor();
        this.syncGameZombies();
    }

    /**
     * Initializes and returns the {@link IGameSurvivor} for the player survivor on
     * the level.
     *
     * @return the game survivor entity
     */
    private IGameSurvivor setGameSurvivor() {
        return factSurGam.gameSurvivorCommon(this.lvl.getSurvivorOnLevel());
    }

    /**
     * Synchronizes the internal zombie map with the current zombies present in the
     * model level.
     * Removes zombies no longer present and adds newly spawned zombies.
     */
    private void syncGameZombies() {
        List<Zombie> currentZombies = this.lvl.getZombieOnLevel();

        // Remove Zombie if there are dead
        gameZombieMap.keySet().removeIf(z -> !currentZombies.contains(z));

        // Add new Zombie if there are born
        for (Zombie z : currentZombies) {
            gameZombieMap.computeIfAbsent(z, factZobGam::gameZombieClicker);
        }
    }

    /**
     * Synchronizes the internal munition map with the current munitions present in
     * the model level.
     * Removes munitions no longer present and adds newly created munitions.
     */
    private void syncGameMunitions() {
        List<Munition> currentMunitions = lvl.getProjectilesOnLevel();

        // Remove all Munitions deleted
        gameMunitionMap.keySet().removeIf(m -> !currentMunitions.contains(m));

        // Add new Munitions
        for (Munition m : currentMunitions) {
            gameMunitionMap.computeIfAbsent(m, factMunGam::gameMunition);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Level getLevel() {
        return this.lvl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGraphics(final GraphicsLevel graphicLvl) {
        imgLvl.update(this.lvl, graphicLvl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IGameSurvivor getGameSurvivor() {
        return this.gamSur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IGameZombie> getGameZombie() {
        return Collections.unmodifiableList(lvl.getZombieOnLevel().stream()
                .map(gameZombieMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IGameMunition> getGameMunitions() {
        return Collections.unmodifiableList(lvl.getProjectilesOnLevel().stream()
                .map(gameMunitionMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     * 
     * Synchronizes the state of game entities (zombies and munitions)
     * with the underlying model level entities.
     */
    @Override
    public void updateStateGameLevel() {
        this.syncGameMunitions();
        this.syncGameZombies();
    }

}
