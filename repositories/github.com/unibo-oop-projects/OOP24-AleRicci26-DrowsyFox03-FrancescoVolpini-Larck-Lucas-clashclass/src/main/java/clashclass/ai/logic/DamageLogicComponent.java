package clashclass.ai.logic;

import clashclass.ecs.AbstractComponent;

/**
 * Represents a damage logic component.
 */
public class DamageLogicComponent extends AbstractComponent {
    private final CalculateDamageLogic damageLogic;

    /**
     * Constructs the damage logic component.
     *
     * @param damageLogic the damage logic
     */
    public DamageLogicComponent(final CalculateDamageLogic damageLogic) {
        this.damageLogic = damageLogic;
    }

    /**
     * Gets the damage logic.
     *
     * @return the damage logic
     */
    public CalculateDamageLogic getDamageLogic() {
        return damageLogic;
    }
}
