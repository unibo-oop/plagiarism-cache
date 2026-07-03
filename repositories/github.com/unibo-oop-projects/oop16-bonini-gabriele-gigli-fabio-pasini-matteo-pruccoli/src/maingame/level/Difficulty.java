package maingame.level;

/**
 * Contiene gli enum per identificare il livello di difficoltà.
 */
public enum Difficulty {
    /**
     * Facile.
     */
    EASY("Easy", 10),
    /**
     * Difficile.
     */
    HARD("Hard", 20);

    private final String diff;
    private final int damage;

    Difficulty(final String toString, final int damage) {
        this.diff = toString;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return this.diff;
    }

    /**
     * Ritorna il danno relativo alla difficoltà.
     * 
     * @return Danno dei nemici.
     */
    public int getDamage() {
        return this.damage;
    }
}
