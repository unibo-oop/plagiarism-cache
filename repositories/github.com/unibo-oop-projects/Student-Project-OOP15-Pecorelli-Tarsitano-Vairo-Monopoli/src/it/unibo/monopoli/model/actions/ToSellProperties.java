package it.unibo.monopoli.model.actions;

import java.util.Objects;

import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandContract;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent one of the {@link MoneyAction}s of the game. This one
 * allows to sell properties: {@link Ownership}s and/or {@link Building}s.
 *
 */
public final class ToSellProperties extends ToBuyAndSellProperties {

    private final Ownership ownership;
    private final Bank bank;

    private ToSellProperties(final int amount, final Ownership ownership, final Bank bank) {
        super(amount);
        this.ownership = ownership;
        this.bank = bank;
    }

    private ToSellProperties(final int amount, final Land land, final Building building, final Bank bank) {
        super(amount, building);
        this.ownership = land;
        this.bank = bank;
    }

    /**
     * This method that creates a new instance of this class. It serves for
     * selling an {@link Ownership}.
     * 
     * @param amount
     *            - the amount of the {@link Ownership}.
     * @param ownership
     *            - the {@link Ownership} to sell
     * @param bank
     *            - the {@link Bank} to witch you sell the property
     * @return an instance of this class
     * @throws NullPointerException
     *             - if instead of {@link Ownership} and/or {@link Bank} there
     *             are some null
     */
    public static ToSellProperties sellAOwnership(final int amount, final Ownership ownership, final Bank bank) {
        return new ToSellProperties(amount, Objects.requireNonNull(ownership), Objects.requireNonNull(bank));
    }

    /**
     * This method that creates a new instance of this class. It serves for
     * selling a {@link Building}.
     * 
     * @param land
     *            - the {@link Land} on which the {@link Building} was built
     * @param building
     *            - the {@link Building} to sell
     * @param bank
     *            - the {@link Bank} to witch you sell the property
     * @return an instance of this class
     * @throws NullPointerException
     *             - if instead of {@link Land} and/or {@link Building} and/or
     *             {@link Bank} there are some null
     */
    public static ToSellProperties sellABuilding(final Land land, final Building building, final Bank bank) {
        return new ToSellProperties(((LandContract) land.getContract()).getCostForEachBuilding(),
                Objects.requireNonNull(land), Objects.requireNonNull(building), Objects.requireNonNull(bank));
    }

    @Override
    protected void whatToDoWithBuilding(final Building building) {
        if (((LandGroup) this.ownership.getGroup()).getBuildings().contains(building)) {
            ((LandGroup) this.ownership.getGroup()).removeBuilding(building);
            this.bank.addBuilding(building);
        } else {
            throw new IllegalArgumentException("You can't sell a building tht you don't have");
        }
    }

    @Override
    protected void whatToDoWithOwnership(final Player player) {
        if (this.ownership.getGroup() instanceof LandGroup
                && !((LandGroup) this.ownership.getGroup()).getBuildings().isEmpty()) {
            throw new IllegalArgumentException("You can't sell an ownership if in its group there are buildings");
        } else if (this.ownership.isMortgaged()) {
            throw new IllegalArgumentException("You can't sell a mortgaged ownership");
        }
        player.removeOwnership(this.ownership);
        this.bank.addOwnership(this.ownership);
        this.ownership.setOwner(bank);
    }

}
