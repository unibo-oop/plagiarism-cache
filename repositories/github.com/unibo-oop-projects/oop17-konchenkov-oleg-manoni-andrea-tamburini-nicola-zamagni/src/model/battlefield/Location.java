package model.battlefield;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 *
 * @author Andrea Manoni
 *
 */
public class Location implements BattlefieldComponent {
    private final String name;
    private final Vector2D gravity;
    private final int width;
    private final int heigth;
    private final int wavelength;
    private final List<BattlefieldComponent> fluids = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param name
     *            name
     * @param gravity
     *            gravity
     * @param fluids
     *            fluids
     * @param width
     *            width
     * @param heigth
     *            heigth
     * @param wavelength
     *            wavelength
     */
    public Location(final String name, final Vector2D gravity, final int width, final int heigth, final int wavelength,
            final List<BattlefieldComponent> fluids) {
        this.name = name;
        this.gravity = gravity;
        this.width = width;
        this.heigth = heigth;
        this.wavelength = wavelength;
        this.fluids.addAll(fluids);

    }

    @Override
    public final String getName() {
        return name;
    }

    /**
     * Gets gravity of the location.
     *
     * @return gravity
     */
    public final Vector2D getGravity() {
        return gravity;
    }

    /**
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return heigth
     */
    public int getHeigth() {
        return heigth;
    }

    /**
     *
     * @return wavelength
     */
    public int getWavelength() {
        return wavelength;
    }

    /**
     *
     * @return list fo childs of the component
     */
    public final List<BattlefieldComponent> getChild() {
        return Collections.unmodifiableList(fluids);
    }

    /**
     * Add a component to the list of Childs.
     *
     * @param comp
     *            comp
     */
    public void addComponent(final BattlefieldComponent comp) {
        fluids.add(comp);
    }

    /**
     * Remove a component to the list of Childs.
     *
     * @param comp
     *            comp
     */
    public void removeComponent(final BattlefieldComponent comp) {
        fluids.remove(comp);
    }
}
