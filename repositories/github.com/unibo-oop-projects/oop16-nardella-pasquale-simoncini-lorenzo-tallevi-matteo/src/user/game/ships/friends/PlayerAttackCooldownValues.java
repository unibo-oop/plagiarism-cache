package user.game.ships.friends;

/**
 * This enum contains all the possible values for the field attackCooldown of
 * ObjPlayerShip.
 */
enum PlayerAttackCooldownValues {

    RANK_0(10), RANK_1(12), RANK_2(10), RANK_3(8), RANK_4(6), RANK_5(4);

    private double value;

    PlayerAttackCooldownValues(final double value) {
        this.value = value;
    }

    protected double getValue() {
        return this.value;
    }
}
