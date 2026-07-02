package model.objects.structures;

import java.util.Optional;

import model.Cost;
import model.player.Player;

/**
 * The CapitalImpl class implements Capital and extends CityImpl. The difference
 * of this class with CityImpl, in addition to the Capital methods it
 * implements, is the amount of population it produces, which will vary
 * according to the level of the capital
 */
public class CapitalImpl extends CityImpl implements Capital {

    private final String name;
    private final double enemyAttackReduction;
    private final int populationBoost;
    private final Cost unlockCost;

    /**
     * CapitalImpl constructor.
     * 
     * @param player               is the player that own the capital;
     * @param name                 is the name of the capital;
     * @param enemyAttackReduction the attack reduction that will be applied to
     *                             attacks that involve an unit that is defending
     *                             his capital;
     * @param populationBoost      it is a boost to be applied to the population
     *                             produced by the capital;
     * @param unlockCost           is the cost to unlock a new capital level.
     */
    public CapitalImpl(final Optional<Player> player, final String name, final double enemyAttackReduction,
            final int populationBoost, final Cost unlockCost) {
        super(player);
        this.name = name;
        this.enemyAttackReduction = enemyAttackReduction;
        this.populationBoost = populationBoost;
        this.unlockCost = unlockCost;
    }

    /** {@inheritDoc} **/
    @Override
    public double getEnemyAttackReduction() {
        return this.enemyAttackReduction;
    }

    /** {@inheritDoc} **/
    @Override
    public int getQuantity() {
        return POPULATION + populationBoost;
    }

    /** {@inheritDoc} **/
    @Override
    public String getDescription() {
        return this.name + "\nOwner: " + getOwnerName() + getExtraInfo();
    }

    private String getExtraInfo() {
        return (getOwner().isPresent()
                ? ("\nPopulation: " + getQuantity() + "\nAttack reduction: " + (100 - getEnemyAttackReduction() * 100)
                        + "%")
                : "");
    }

    /** {@inheritDoc} **/
    @Override
    public Cost getUnlockCost() {
        return this.unlockCost;
    }

    /** {@inheritDoc} **/
    @Override
    public String getUnlockCostToString() {
        return this.unlockCost.toString();
    }

}
