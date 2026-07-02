package vg.model.mystery_box;
/**
 * This class is the superclass of all abilities that are instant.
 */
public abstract class AbstractAbilityInstant extends AbstractAbility implements AbilityInTheBox {
    private static final long serialVersionUID = 1L;
    public AbstractAbilityInstant(final EAbility idAbility) {
        super(idAbility, ETypeAbility.INSTANT);
    }
}
