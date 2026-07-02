package it.unibo.plantsfarm.model.player;

import java.awt.Rectangle;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.plant.Rarity;
import it.unibo.plantsfarm.model.player.api.Player;

/**
 * Implementation Base Entity Player.
 */
public class ImplPlayer implements Player {

    public static final int FARMER_SPEED = 400;
    public static final int OFF_SET_HITBOX = 32;
    public static final int WIDTH_HITBOX = 32;
    public static final int HEIGH_HITBOX = 32;
    public static final int EXPERT_FARMER_SPEED = 650;
    public static final int SPAWN_X = 192;
    public static final int SPAWN_Y = 720;
    private double speed;
    private double posX = SPAWN_X;
    private double posY = SPAWN_Y;
    private double nextPosX;
    private double nextPosY;
    private UserInput direction = UserInput.FERMO;
    private ModelInventario inventory;
    private final PlayersTypes playersType;

    /**
     * Create the player and inventory.
     * to try all features select {@link ExpertFarmer}.
     * to try normal gameplay select {@link FarmerPlayer}.
     *
     * @param inventory that will be set based on player type.
     * @param playersTypes set the player type
     * @throws NullPointerException if {@code inventory} is {@code null}
     */
    public ImplPlayer(final ModelInventario inventory, final PlayersTypes playersTypes) {
        setIventario(inventory);
        this.playersType = playersTypes;
    }

    @Override
    public final void updatePlayer(final long time) {
        final double delta = speed * time / 1000.0;
        nextPosX = posX;
        nextPosY = posY;
        switch (direction) {
            case LEFT -> nextPosX -= delta;
            case RIGHT -> nextPosX += delta;
            case UP -> nextPosY -= delta;
            case DOWN -> nextPosY += delta;
            case ACTIONHOE, ACTIONWATER, REMOVE_PLANT, SELECT_SEED -> { }
            case FERMO -> { }
        }
    }

    @Override
    public final void setDirection(final UserInput direction) {
        this.direction = direction;
    }

    @Override
    public final double getPosx() {
        return this.posX;
    }

    @Override
    public final double getPosy() {
        return this.posY;
    }

    @Override
    public final double getNextPosX() {
        return this.nextPosX;
    }

    @Override
    public final double getNextPosY() {
        return this.nextPosY;
    }

    @Override
    public final UserInput getDirection() {
        return this.direction;
    }

    @Override
    public final ModelInventario getInventory() {
        return new ModelInventario(inventory.getInventorySnapshot());
    }

    @Override
    public final Rectangle getHitBox() {
        return new Rectangle((int) posX + OFF_SET_HITBOX, (int) posY + OFF_SET_HITBOX, WIDTH_HITBOX, HEIGH_HITBOX);
    }

    @Override
    public final void applyMovement() {
        this.posX = nextPosX;
        this.posY = nextPosY;
    }

    /**
     * Set the player's speed based on player type.
     *
     * @param speed speed to be set.
     */
    protected final void setSpeed(final double speed) {
        this.speed = speed;
    }

    @Override
    public final PlayersTypes getPlayerType() {
        return this.playersType;
    }

    /**
     * Sets the player's inventory to the specified ModelInventario.
     *
     * @param givenInventory the ModelInventario to be set as the player's inventory
     */
    private void setIventario(final ModelInventario givenInventory) {
        this.inventory = givenInventory;
    }

    @Override
    public final void useItem(final Tooltype tool, final Rarity plant) {
        inventory.useItem(tool, plant);
    }

    @Override
    public final void upgradeItemRarityFromPlayer(final Tooltype tool) {
        inventory.upgrade(tool);
    }
}
