package zombieversity.model.entities.weapon;

import javafx.geometry.Point2D;
import zombieversity.model.entities.AbstractEntity;
import zombieversity.model.entities.EntityType;

/**
 * Abstract representation of a {@link ShortRangeWeapon}.
 */
public abstract class AbstractShortRangeWeapon extends AbstractEntity implements ShortRangeWeapon {

    private static final WeaponType WEAPON_TYPE = WeaponType.SHORT_RANGE;
    private static final EntityType ENTITY_TYPE = EntityType.WEAPON;

    /**
     * @param p2d
     *          Initial position of the weapon.
     */
    public AbstractShortRangeWeapon(final Point2D p2d) {
        super(p2d, ENTITY_TYPE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final WeaponType getWeaponType() {
        return WEAPON_TYPE;
    }

}
