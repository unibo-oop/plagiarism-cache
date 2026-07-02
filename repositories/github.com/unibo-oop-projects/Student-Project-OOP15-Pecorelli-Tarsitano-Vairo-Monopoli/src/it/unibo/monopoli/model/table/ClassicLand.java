package it.unibo.monopoli.model.table;

import java.awt.Color;

import it.unibo.monopoli.model.mainunits.Owner;

/**
 * This class represents an implementation of {@link ClassicLand}s of Monopoly.
 *
 */
public class ClassicLand extends ClassicOwnership implements Land {

    private final Color color;

    /**
     * Constructs an implementation of {@link ClassicLand} that needs a name, an
     * ID, an {@link Owner} and a {@link Color}.
     * 
     * @param name
     *            - of the {@link ClassicLand}
     * @param id
     *            - of the {@link ClassicLand}
     * @param owner
     *            - {@link Owner} of the {@link ClassicLand}
     * @param color
     *            - {@link Color} of the {@link ClassicLand}
     */
    public ClassicLand(final String name, final int id, final Owner owner, final Color color) {
        super(name, id, owner);
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

}
