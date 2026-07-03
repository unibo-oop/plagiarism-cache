package user.game.ships.enemies;

enum BossRank1Stats {

    HEALTH(40), POWER(2), ATTACK_COOLDOWN(50), MAX_SPEED(4);

    private double value;

    BossRank1Stats(final double value) {
        this.value = value;
    }

    protected double getValue() {
        return this.value;
    }

}
