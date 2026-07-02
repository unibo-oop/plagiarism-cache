package javawulf.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javawulf.model.map.Map;
import javawulf.model.player.Player;

/**
 * Implementation of PlayerStatus.
 */
public final class PlayerStatusImpl implements PlayerStatus {

    private final Player player;
    private final Map map;

    /**
     * @param player The Player whose status must be considered
     * @param map The map the player is on
     */
    @SuppressFBWarnings(
        value = {
            "M", "V", "EI2"
        },
        justification = "Player is passed through this class in order to get read only values"
            + "coming from the model"
    )
    public PlayerStatusImpl(final Player player, final Map map) {
        this.player = player;
        this.map = map;
    }

    @Override
    public int getHealth() {
        return this.player.getPlayerHealth().getHealth();
    }

    @Override
    public int getMaxHealth() {
        return this.player.getPlayerHealth().getMaxHealth();
    }

    @Override
    public int getShield() {
        return this.player.getPlayerHealth().getShieldStatus().getStrength();
    }

    @Override
    public String getColor() {
        return this.player.getColor();
    }

    @Override
    public String getDirection() {
        return this.player.getDirection().name();
    }

    @Override
    public int getPlayerX() {
        return (int) this.player.getBounds().getCollisionArea().getX();
    }

    @Override
    public int getPlayerY() {
        return (int) this.player.getBounds().getCollisionArea().getY();
    }

    @Override
    public int getScore() {
        return this.player.getScore().getPoints();
    }

    @Override
    public int getSwordX() {
        return (int) this.player.getSword().getBounds().getCollisionArea().getX();
    }

    @Override
    public int getSwordY() {
        return (int) this.player.getSword().getBounds().getCollisionArea().getY();
    }

    @Override
    public int getSwordWidth() {
        return (int) this.player.getSword().getBounds().getCollisionArea().getWidth();
    }

    @Override
    public int getSwordHeight() {
        return (int) this.player.getSword().getBounds().getCollisionArea().getHeight();
    }

    @Override
    public String getSwordType() {
        return this.player.getSword().getSwordType().name();
    }

    @Override
    public int getAmuletPieces() {
        return this.player.getNumberOfPieces();
    }

    @Override
    public String getPlayerCollision() {
        return this.player.getBounds().getCollisionType().name();
    }

    @Override
    public String getSwordCollision() {
        return this.player.getSword().getBounds().getCollisionType().name();
    }

    @Override
    public boolean hasPlayerWon() {
        return this.player.hasPlayerWon(this.map);
    }

}
