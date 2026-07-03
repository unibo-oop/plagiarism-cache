package user.game.ships.friends;

/**
 * This enum contains all the possible values for the field maxSpeed of
 * ObjPlayerShip.
 */
enum PlayerMaxSpeedValues {

    RANK_0(14), RANK_1(16), RANK_2(18), RANK_3(20), RANK_4(21), RANK_5(22);

    private double value;

    PlayerMaxSpeedValues(final double value) {
        this.value = value;
    }

    protected double getValue() {
        return this.value;
    }

}
