package color.filter;

import java.util.Arrays;
import java.util.Optional;

/**
 *Describes all types of color filters in the program.
 *
 */
public enum Filters {
    /**
     *Describes the filter that returns the color of the cells according to their age.
     */
    AGE("Age", 0), 
    /**
     *Describes the filter that returns the color of the cells based on their energy.
     */
    ENERGY("Energy", 1), 
    /**
     *Describes the filter that returns the color of the cells according to their nutrition.
     */
    NUTRITION("Nutrition", 2);

    private String name;
    private int value;
    Filters(final String name, final int value) { 
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }

    /**
     * Calculate the enum Filters corresponding to name.
     * @param name containing the name of the filter you want to search for
     * @return enum Filter corresponding to the name inserted
     * @throws IllegalArgumentException if the name entered does not correspond to any existing enum Filters
     */
    public static Filters getEnum(final String name) {
       Optional<Filters> filter = Arrays.asList(Filters.values()).stream().filter(x -> x.getName().equals(name)).findFirst();
       if (filter.isPresent()) {
           return filter.get();
       } else {
           throw new IllegalArgumentException("IL NOME INSERITO NON CORRISPONDE A NESSUN ENUM");
       }
    }

    /**
     * Calculate the enum Filters corresponding to value.
     * @param value containing the int value of the filter you want to search for
     * @return enum Filter corresponding to the value inserted
     * @throws IllegalArgumentException if the value entered does not correspond to any existing enum Filters
     */
    public static Filters getEnum(final int value) {
        Optional<Filters> filter = Arrays.asList(Filters.values()).stream().filter(x -> x.getValue() == value).findFirst();
        if (filter.isPresent()) {
            return filter.get();
        } else {
            throw new IllegalArgumentException("IL VALORE INSERITO NON CORRISPONDE A NESSUN ENUM");
        }
    }
}
