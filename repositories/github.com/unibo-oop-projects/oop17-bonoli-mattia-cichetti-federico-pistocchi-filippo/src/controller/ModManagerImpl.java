package controller;

import utilities.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import model.mod.ModAlreadyPresentException;
import model.mod.ModEntity;
import model.mod.ModObstacle;
import model.mod.ModType;
import model.world.World;
import model.obstacle.GameObjectImpl.GameObjectType;

/**
 * The default implementation for the ModManager.
 *
 */
public class ModManagerImpl implements ModManager {

    private int modDelay;
    private long counter;
    private Map<ModObstacle, Integer> activeMods;
    private World w;

    /**
     * Constructor that adds two listeners to GameLoop and CollisionManager components.
     * @param g the GameLoop
     * @param defaultModDelay The delay between the spawning of two mods. It changes with difficulty.
     */
    public ModManagerImpl(final GameLoop g, final int defaultModDelay) {
        this.activeMods = new HashMap<>();
        g.registerObserver(i -> this.tick());
        /* g.getCollisionManager().registerObserver(m -> activateMod(m));*/
        this.w = g.getWorld();
        this.modDelay = defaultModDelay;
        this.counter = 0;
    }

    /**/
    @Override
    public void activateMod(final ModObstacle m) {
        this.activeMods.put(m, m.getModType().getTimeActive());
        /*m.getModType().getMod().execute(game);*/
    }

    /**/
    @Override
    public void tick() {
        counter++;
        if (!this.activeMods.isEmpty()) {
            this.activeMods.entrySet().forEach(e -> e.setValue(e.getValue() - 1));
            final Map<ModObstacle, Integer> toFix = this.activeMods.entrySet().stream()
                                                           .filter(e -> e.getValue() == 0)
                                                           .collect(Collectors.toMap(p -> p.getKey(), 
                                                                                   p -> p.getValue()));
            if (!toFix.isEmpty()) {
                /*toFix.entrySet().forEach(e -> { 
                 *     e.getKey().getModType().getFixer().execute(game));
                 *     activeMods.remove(e.getKey());
                 * }*/
            }
        }
        if (counter >= modDelay) {
            tryAddingMod();
        }
    }

    private void tryAddingMod() {
        double spawnChance = new Random().nextDouble();
        if (spawnChance > 0.50) {
            spawnChance = new Random().nextDouble();
            final ModType chosenType = Constants.MODARRAY[new Random().nextInt(Constants.MODARRAY.length)];
            if (spawnChance >= (1 - chosenType.getChance())) {
                final int chosenLane = new Random().nextInt(w.getLane().size());
                final double center = new Random().nextDouble() * 100;
                try {
                    w.getLane().get(chosenLane).addMod(
                            new ModEntity(GameObjectType.MOD.create(center), chosenType));
                } catch (ModAlreadyPresentException e) {
                }
            }
        }
    }

    /**/
    @Override
    public void setWorld(final World w) {
        this.w = w;
    }

    /**/
    @Override
    public void setModDelay(final int newModDelay) {
        this.modDelay = newModDelay;
    }

}
