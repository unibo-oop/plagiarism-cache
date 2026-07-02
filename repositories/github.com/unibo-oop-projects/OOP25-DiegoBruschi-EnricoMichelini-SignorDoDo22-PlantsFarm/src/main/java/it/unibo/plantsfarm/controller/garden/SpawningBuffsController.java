package it.unibo.plantsfarm.controller.garden;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import it.unibo.plantsfarm.model.garden.Buff;
import it.unibo.plantsfarm.model.tiles.SoilImpl;
import it.unibo.plantsfarm.model.tiles.SolidBlock;
import it.unibo.plantsfarm.model.tiles.TileMap;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;

/**
 * Controls the spawning of buffs in the game, managing their creation,
 * removal, and ensuring they do not spawn in invalid locations.
 */
public final class SpawningBuffsController {
    public static final int MAX_PRESENT_BUFFS = 3;
    public static final long SPAWN_COOLDOWN = 15_000L;
    public static final int BUFF_SIZE = 48;

    private final List<Buff> activeBuffs = new LinkedList<>(List.of());
    private final TileMap mappa;
    private long lastPickUp = System.currentTimeMillis();
    private final List<SoilImpl> soilList;
    private final List<SolidBlock> solidBlocks;

    /**
     * Creates a new SpawningBuffsController with the specified TileMap.
     *
     * @param map The TileMap used to determine valid spawning locations for buffs.
     */
    public SpawningBuffsController(final TileMap map) {
        this.mappa = map;
        this.soilList = mappa.getSoilList();
        this.solidBlocks = mappa.getSolidBlocks();
    }

    /**
     * Updates the spawning of buffs in the game, adding new buffs
     * if the number of active buffs is below the maximum and the cooldown has passed.
     * 
     * @param now The current time in milliseconds, used to determine if a new buff can be spawned based on the cooldown.
     */
    public void updateUpGrade(final long now) {
        if (activeBuffs.size() < MAX_PRESENT_BUFFS && lastPickUp + SPAWN_COOLDOWN < now) {
            final Rectangle buffPosition;

            final double randomPosX = (int) (Math.random() * (ImplViewGamePanel.WORLD_WIDTH + 1));
            final double randomPosY = (int) (Math.random() * (ImplViewGamePanel.WORLD_HEIGHT + 1));
            if (verifyPosUpgrade(randomPosX, randomPosY)) {
                buffPosition = new Rectangle((int) randomPosX, (int) randomPosY, BUFF_SIZE, BUFF_SIZE);
                final Buff buff = new Buff(buffPosition);
                activeBuffs.add(buff);
                lastPickUp = System.currentTimeMillis();
            }
        }
    }

    /**
     * Removes a buff from the active buffs list and updates the last pickup time.
     *
     * @param buff The buff to be removed from the active buffs list.
     */
    public void removeBuffFromMap(final Buff buff) {
        activeBuffs.remove(buff);
        lastPickUp = System.currentTimeMillis();
    }

    /**
     * Gets the list of active buffs currently present in the game.
     * 
     * @return A list of Buff objects representing the active buffs in the game.
     */
    public List<Buff> getBuffList() {
        return List.copyOf(activeBuffs);
    }

    /**
     * Verifies if the specified position is valid for spawning a buff.
     *
     * @param posX The x-coordinate of the position to be verified.
     * @param posY The y-coordinate of the position to be verified.
     * @return true if the position is valid for spawning a buff, false otherwise.
     */
    public boolean verifyPosUpgrade(final double posX, final double posY) {
        final Rectangle buffPosition = new Rectangle((int) posX, (int) posY, BUFF_SIZE, BUFF_SIZE);

        for (final SoilImpl soil : soilList) {
            if (buffPosition.intersects(soil.getCoordinate()) || soil.getCoordinate().contains(buffPosition)) {
                return false;
            }
        }

        for (final SolidBlock block : solidBlocks) {
            if (buffPosition.intersects(block.getCoordinate()) || block.getCoordinate().contains(buffPosition)) {
                return false;
            }
        }
        return true;
    }
}
