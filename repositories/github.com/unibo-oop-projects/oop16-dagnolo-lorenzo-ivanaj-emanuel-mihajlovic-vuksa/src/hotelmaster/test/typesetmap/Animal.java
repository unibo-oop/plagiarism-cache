package hotelmaster.test.typesetmap;

/**
 * Basic superclass.
 */
public class Animal implements Comparable<Animal> {
    private String name;

    Animal(final String name) {
        this.name = name;
    }

    /**
     * Some getter.
     * 
     * @return value
     */
    public String getName() {
        return this.name;
    }

    /**
     * Some setter.
     * 
     * @param name
     *            value
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Overridden toString.
     * 
     * @return string
     */
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Animal)) {
            return false;
        }
        final Animal other = (Animal) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(final Animal arg0) {
        return this.name.compareTo(arg0.name);
    }
}
