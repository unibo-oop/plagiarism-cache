package it.unibo.oop.utilities;

import java.util.Optional;

/**
 * Class that contains the settings of every character in game to calculate the
 * bounds and the speed
 */
public enum CharactersSettings {
    MAIN_CHARACTER(32, 48, new Velocity(7, 11, 5)),
    BASIC_ENEMY(32, 48, new Velocity(5, 5, 10)),
    INVISIBLE_ENEMY(32, 32, new Velocity(9, 9, 5)),
    BULLET(16, 16, new Velocity(20, 30, 1)),
    BONUS(16, 16, null),
    WALL(32, 32, null);

    int characterWidth;
    int characterHeight;
    Optional<Velocity> characterSpeed;

    private CharactersSettings(final int width, final int height, final Velocity speed) {
        this.characterWidth = width;
        this.characterHeight = height;
        this.characterSpeed = Optional.ofNullable(speed);
    }

    public int getWidth() {
        return this.characterWidth;
    }

    public int getHeight() {
        return this.characterHeight;
    }

    public Velocity getSpeed() {
        return this.characterSpeed.get();
    }
}
