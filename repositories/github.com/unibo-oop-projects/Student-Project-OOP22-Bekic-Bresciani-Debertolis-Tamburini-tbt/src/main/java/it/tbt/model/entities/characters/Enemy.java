package it.tbt.model.entities.characters;

/**
 * Generic Enemy.
 */
public class Enemy extends CharacterImpl {
    private static final long serialVersionUID = 398686380391405543L;

    /**
     * Default Constructor.
     * @param name
     * @param health
     * @param attack
     * @param speed
     */
    public Enemy(
        final String name,
        final int health,
        final int attack,
        final int speed
    ) {
        super(name, health, attack, speed);
    }
}
