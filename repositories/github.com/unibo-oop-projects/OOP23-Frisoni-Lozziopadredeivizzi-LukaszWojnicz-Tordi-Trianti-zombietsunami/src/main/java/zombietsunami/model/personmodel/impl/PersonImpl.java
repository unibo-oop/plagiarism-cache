package zombietsunami.model.personmodel.impl;

import zombietsunami.model.EntityImpl;
import zombietsunami.model.personmodel.api.Person;

/**
 * Class implementing the Person functionalities.
 * 
 * @see zombietsunami.model.personmodel.api.Person
 * @see zombietsunami.model.EntityImpl
 */
public class PersonImpl implements Person {

    private final EntityImpl entity = new EntityImpl();

    /**
     * Gets the X coordinate of the Person.
     */
    @Override
    public int getX() {
        return this.entity.getX();
    }

    /**
     * Gets the Y coordinate of the Person.
     */
    @Override
    public int getY() {
        return this.entity.getY();
    }

    /**
     * Sets the X coordinate of the Person.
     */
    @Override
    public void setX(final int x) {
        this.entity.setX(x);
    }

    /**
     * Sets the Y coordinate of the Person.
     */
    @Override
    public void setY(final int y) {
        this.entity.setY(y);
    }
}
