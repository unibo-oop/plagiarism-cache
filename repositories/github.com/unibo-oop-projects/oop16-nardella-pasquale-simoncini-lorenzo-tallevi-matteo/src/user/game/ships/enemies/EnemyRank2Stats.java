package user.game.ships.enemies;

enum EnemyRank2Stats {

    HEALTH(2), POWER(2), ATTACK_COOLDOWN(44), MAX_SPEED(6);

    private double value;

    EnemyRank2Stats(final double value) {
        this.value = value;
    }

    protected double getValue() {
        return this.value;
    }

}
