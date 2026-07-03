package user.enums;

/**
 * This Enum contains the class name of the various objects of the game.
 */
public enum Objects {

    /**
     * This fields represents the friend ships of the game.
     */
    PLAYER_SHIP("game.ships.friends.ObjPlayerShip"), ALLY_SHIP("game.ships.friends.ObjAllyShip"),

    /**
     * This fields represents the enemy ships of the game.
     */
    ENEMY_SHIP("game.ships.enemies.ObjEnemyShip"), BOSS_SHIP("game.ships.enemies.ObjBossShip"),

    /**
     * This fields represents the various bullets of the game.
     */
    FRIEND_BULLET("game.bullets.ObjFriendBullet"), ENEMY_BULLET("game.bullets.ObjEnemyBullet"),

    /**
     * This fields represents the objects related to the visual effects.
     */
    EFFECT_EMITTER("game.effects.ObjEffectEmitterImpl"), EFFECT("game.effects.ObjEffect"), FADING_ELEMENT("game.effects.ObjFadingElement"), PARTICLE("game.effects.ObjParticle"), SHOCKWAVE("game.effects.ObjShockwave"),

    /**
     * This field represents the object that controls the view.
     */
    VIEW_CONTROLLER("game.ObjViewController");

    private String value;

    Objects(final String value) {
        this.value = value;
    }

    /**
     * This method returns the class name of the desired object.
     * 
     * @return the class name of the requested object
     */
    public String getValue() {
        return this.value;
    }

}
