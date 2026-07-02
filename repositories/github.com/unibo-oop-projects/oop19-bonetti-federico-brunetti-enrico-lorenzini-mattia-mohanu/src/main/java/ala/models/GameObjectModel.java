package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * GameObjectModel class.
 * 
 */
public abstract class GameObjectModel {

    //Attributes:
    private double x;
    private double y;
    private double r;

    private double height;
    private double width;
 
    private OBJECTSTYPE type;

    private HitBox hitBox;

    private double damageOnContact;

    //Constructors:
    public GameObjectModel() { }

    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param r
     * @param type
     * @param width
     * @param height
     * @param damageOnContact
     * 
     */
    public GameObjectModel(final double x, final double y, final double r, final OBJECTSTYPE type, final double width, final double height, final double damageOnContact) {
        this.x = x;
        this.y = y;
        this.r = r;

        this.type = type;

        this.width = width;
        this.height = height;

        this.hitBox = new HitBox(this.x, this.x + this.width, this.y, this.y + this.height);

        this.damageOnContact = damageOnContact;
    }

    //Getters&setters:
    public final double getX() {
        return x;
    }

    public final OBJECTSTYPE getType() {
        return type;
    }

    public final void setX(final double x) {
        this.x = x;
    }

    public final double getY() {
        return y;
    }

    public final void setY(final double y) {
        this.y = y;
    }

    public final double getR() {
        return r;
    }

    public final void setR(final double r) {
        this.r = r;
    }

    public final HitBox getHitBox() {
        return hitBox;
    }

    public final double getDamageOnContact() {
        return damageOnContact;
    }

    public final void setDamageOnContact(final double damageOnContact) {
        this.damageOnContact = damageOnContact;
    }

    public final double getHeight() {
        return height;
    }

    public final void setHeight(final double height) {
        this.height = height;
    }

    public final double getWidth() {
        return width;
    }

    public final void setWidth(final double width) {
        this.width = width;
    }

    public final void setHitBox(final HitBox hitBox) {
        this.hitBox = hitBox;
    }
}
