package it.unibo.oop.bbgmm.Entity.Component;

public enum Inventory {
    GUN(10, 50),
    PUNCH(5, 20),
    RPG(30, 100);

    int damage;
    int range;

    Inventory(final int range, final int damage) {
        this.range = range;
        this.damage = damage;
    }
}
