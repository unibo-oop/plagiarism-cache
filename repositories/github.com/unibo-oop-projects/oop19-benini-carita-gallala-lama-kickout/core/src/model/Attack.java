package model;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
/**
 * An entity subclass that defines and contains the characteristics of an attack
 */
public class Attack extends Entity {
    private final Fighter player;
    private float damage;

    /**
     * Creates an attack and positions it based on the Fighter that attacked and defines the characteristics of the attack,
     * such as his Sensor state and his behavior with collisions 
     * @param player
     *              The fighter who created the attack
     * @param width
     *              The attack's width
     * @param height
     *              The attack's height
     * @param damage
     *              The damage of the attack
     */
    public Attack(final Fighter player, final float width, final float height, final float damage) {
        super(player.getBody().getPosition().add(player.getCurrentDirection().equals(Direction.RIGHT) ? width : -width, 0),
        	  width, height, BodyType.DynamicBody, player.getBody().getWorld());
        this.getBody().getFixtureList().first().setSensor(true);
        this.getBody().setUserData(this);
        this.player = player;
        this.damage = damage;
    }

    /**
     * @return the fighter attacking
     */
    public Fighter getPlayer() {
        return this.player;
    }

    /**
     * Triggers the physics-related behavior of the fighter attacked
     * @param target
     *              The fighter attacked
     */
    public void trigger(final Fighter target) {
        target.setHit(this.damage, this.player.getCurrentDirection());
    }
}
