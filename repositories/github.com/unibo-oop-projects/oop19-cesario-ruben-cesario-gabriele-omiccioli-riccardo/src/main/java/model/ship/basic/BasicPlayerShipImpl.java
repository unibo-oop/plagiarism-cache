package model.ship.basic;

import utilities.math.Point2D;
import utilities.math.Vector2D;

/**
 * Standard implementation for BasicPlayerShip.
 */
class BasicPlayerShipImpl extends BasicSpaceShipImpl implements BasicPlayerShip {

    private final PlayerScore playerScore;

    BasicPlayerShipImpl(final Point2D position, final double radiantAngle, final Vector2D speed) {
        super(position, radiantAngle, speed);
        playerScore = new PlayerScore();
    }

    BasicPlayerShipImpl(final Point2D position, final double radiantAngle, final Vector2D speed, 
                           final BasicSpaceShipTemplate model, final String playerName) {
        super(position, radiantAngle, speed, model);
        playerScore = new PlayerScore(playerName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerScore getPlayerScore() {
        return this.playerScore;
    }

}
