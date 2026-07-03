package user.game.ships.enemies;

enum EnemyRank3Stats {

    HEALTH(6), POWER(2), ATTACK_COOLDOWN(42), MAX_SPEED(8);

    private double value;

    EnemyRank3Stats(final double value) {
        this.value = value;
    }

    protected double getValue() {
        return this.value;
    }

}
