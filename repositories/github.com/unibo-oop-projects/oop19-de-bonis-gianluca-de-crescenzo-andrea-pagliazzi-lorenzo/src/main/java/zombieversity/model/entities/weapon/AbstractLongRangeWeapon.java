package zombieversity.model.entities.weapon;

import javafx.geometry.Point2D;
import zombieversity.model.entities.AbstractEntity;
import zombieversity.model.entities.EntityType;

/**
 * Abstract representation of a {@link LongRangeWeapon}.
 */
public abstract class AbstractLongRangeWeapon extends AbstractEntity implements LongRangeWeapon {

    private static final WeaponType WEAPON_TYPE = WeaponType.LONG_RANGE;
    private static final EntityType ENTITY_TYPE = EntityType.WEAPON;

    /**
     * @param p2d
     *          Initial position of the weapon.
     */
    public AbstractLongRangeWeapon(final Point2D p2d) {
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
