package user.game.ships.enemies;

enum EnemyRank1Stats {

    HEALTH(1), POWER(1), ATTACK_COOLDOWN(46), MAX_SPEED(4);

    private double value;

    EnemyRank1Stats(final double value) {
        this.value = value;
    }

    protected double getValue() {
        return this.value;
    }

}
