package model.skilltree;

import model.Cost;

/**
 * The SkillTreeAttributeAbstract is an abstract class that implements
 * SkillTreeAttribute.
 */
public abstract class SkillTreeAttributeAbstract implements SkillTreeAttribute {

    private int currentValue;

    /**
     * SkillTreeAbstract constructor.
     * 
     * @param currentLevel is the initial value of the attribute.
     */
    public SkillTreeAttributeAbstract(final int currentLevel) {
        this.currentValue = currentLevel;
    }

    /** {@inheritDoc} **/
    @Override
    public void upgrade() {
        verify();
        this.currentValue++;
    }

    /** {@inheritDoc} **/
    @Override
    public String getCostToString() {
        return getCost().toString();
    }

    /** {@inheritDoc} **/
    @Override
    public abstract String getAttributeName();

    /** {@inheritDoc} **/
    @Override
    public abstract boolean canUpgrade();

    /** {@inheritDoc} **/
    @Override
    public abstract Cost getCost();

    /**
<<<<<<< HEAD
     * if it is not possible to upgrade the attribute, it throw an Exception.
     * 
     * @throws IllegalStateException
=======
     * This method can be use for verify if isn't possible to upgrade the attribute.
     * 
     * @throws IllegalStateException if the attribute can't be upgrade.
>>>>>>> aa591cf50dca86a64e7984c950d30b6ff019a4d2
     */
    protected void verify() {
        if (!canUpgrade()) {
            throw new IllegalStateException();
        }
    }

    /**
     * This method can be use to get the current attribute level.
     * 
     * @return the current level of the attribute
     */
    protected int getCurrentValue() {
        return this.currentValue;
    }

    /**
     * This method can be use to set the current attribute level.
     * 
     * @param newValue is the new value for the attribute.
     */
    protected void setCurrentValue(final int newValue) {
        this.currentValue = newValue;

    }
}
