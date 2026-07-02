package it.unibo.javacrush.model.api;

import java.util.Map;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.powerup.api.PowerUpManager;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents the configuration for a game level.
 * 
 * @param rows the number of rows in the game board
 * @param cols the number of columns in the game board
 * @param moves the number of moves available to the player
 * @param goals a map of cell types to their respective goal counts
 * @param gravity the gravity engine to be used in the level
 * @param powerUpManager the manager for power-ups in the level
 */
@SuppressFBWarnings(
    value = "EI_EXPOSE_REP", 
    justification = "Shared state is intentional for MVC"
)
public record LevelConfig(
    int rows,
    int cols,
    int moves,
    Map<CellType, Integer> goals,
    GravityEngine gravity,
    PowerUpManager powerUpManager
) { }
