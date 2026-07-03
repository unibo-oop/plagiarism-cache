package user.game.ships.enemies;

enum BossRank2Stats {

    HEALTH(80), POWER(3), ATTACK_COOLDOWN(45), MAX_SPEED(4);

    private double value;

    BossRank2Stats(final double value) {
        this.value = value;
    }

    protected double getValue() {
        return this.value;
    }

}
