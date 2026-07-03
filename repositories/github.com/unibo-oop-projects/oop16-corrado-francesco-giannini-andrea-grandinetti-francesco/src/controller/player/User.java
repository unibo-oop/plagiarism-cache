package controller.player;

import utility.Driver;

/**
 * 
 */
public class User extends PlayerImpl {

    private final String name;

    /**
     * Constructor.
     * @param name user's name
     * @param driver the driver the user wants to play with
     */
    public User(final Driver driver, final String name) {
        super(driver);
        this.name = name;
    }

    /**
     * Method to return the user's name.
     * @return the user's name
     */
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
