package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * GameObjectAliveModel class.
 * 
 */
public abstract class GameObjectAliveModel extends DynamicGameObjectModel {
    //Attributes:
    private double maxHealth;
    private double health;

    //Constructors:
    public GameObjectAliveModel() { }

    /**
    * Constructor.
    * 
    * @param x
    * @param y
    * @param r
    * @param type
    * @param width
    * @param height
    * @param dx
    * @param dy
    * @param dr
    * @param maxHealth
    * @param damageOnContact
    * 
    */
    public GameObjectAliveModel(final double x, final double y, final double r, final OBJECTSTYPE type, final double width, final double height, final double dx, final double dy, final double dr, final double maxHealth, final double damageOnContact) {
        super(x, y, r, type, width, height, dx, dy, dr, damageOnContact);

        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    //Getters&Setters:
    public final double getHealth() {
        return health;
    }
    public final void setHealth(final double health) {
        this.health = health;
    }
    public final double getMaxHealth() {
        return maxHealth;
    }

    public final void setMaxHealth(final double maxHealth) {
        this.maxHealth = maxHealth;
    }

    //Methods:
    /**
     * return that the GameObjectAlive is alive or not.
     * @return boolean
     */
    public boolean isAlive() {
        if (this.getHealth() <= 0) {
            return false;
        }
        return true;
    }
}
