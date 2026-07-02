package spacesurvival.utilities.path;

import spacesurvival.utilities.SoundType;

public enum SoundPath {
    /**
     * Path relative to the menu sound, loop type.
     */
    MENU(MainFolder.SOUND + "/menu.wav", SoundType.LOOP),
    /**
     * Path relative to the game sound, loop type.
     */
    GAME(MainFolder.SOUND + "/game.wav", SoundType.LOOP),
    /**
     * Path relative to the shoot sound, effect type.
     */
    SHOOT(MainFolder.SOUND + "/shoot.wav", SoundType.EFFECT),
    /**
     * Path relative to the enemy shoot sound, effect type.
     */
    ENEMY_SHOOT(MainFolder.SOUND + "/enemyShoot.wav", SoundType.EFFECT),
    /**
     * Path relative to the life up sound, effect type.
     */
    LIFE_UP(MainFolder.SOUND + "/lifeUp.wav", SoundType.EFFECT),
    /**
     * Path relative to the life down sound, effect type.
     */
    LIFE_DOWN(MainFolder.SOUND + "/lifeDown.wav", SoundType.EFFECT),
    /**
     * Path relative to the perk sound, effect type.
     */
    PERK(MainFolder.SOUND + "/perk.wav", SoundType.EFFECT),
    /**
     * Path relative to the asteroid explosion sound, effect type.
     */
    ASTEROID_EXPL(MainFolder.SOUND + "/asteroidExpl.wav", SoundType.EFFECT),
    /**
     * Path relative to the enemy explosion sound, effect type.
     */
    ENEMY_EXPL(MainFolder.SOUND + "/enemyExpl.wav", SoundType.EFFECT),
    /**
     * Path relative to the ship explosion sound, effect type.
     */
    SHIP_EXPL(MainFolder.SOUND + "/shipExpl.wav", SoundType.EFFECT),
    /**
     * Path relative to the boss explosion sound, effect type.
     */
    BOSS_EXPL(MainFolder.SOUND + "/bossExpl.wav", SoundType.EFFECT),
    /**
     * Path relative to the wall collision sound, effect type.
     */
    WALL_COLLISION(MainFolder.SOUND + "/wallCollision.wav", SoundType.EFFECT),
    /**
     * Path relative to the level up sound, effect type.
     */
    LEVEL_UP(MainFolder.SOUND + "/levelUp.wav", SoundType.EFFECT),
    /**
     * Path relative to the game over sound, effect type.
     */
    GAME_OVER(MainFolder.SOUND + "/gameOver.wav", SoundType.EFFECT);


    private final String path;
    private final SoundType type;


    /**
     * Type of command to pass to the playSound method to play the selected sound.
     * 
     * @param path string represents the path of the file
     * @param type SoundType represents the type of sound, loop or effect
     */
    SoundPath(final String path, final SoundType type) {
        this.path = path;
        this.type = type;
    }

    /**
     * Return the path of the current SoundPath.
     * 
     * @return the path to the sound file
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Return the type of the current SoundPath.
     * 
     * @return the type of the sound
     */
    public SoundType getType() {
        return this.type;
    }

}
