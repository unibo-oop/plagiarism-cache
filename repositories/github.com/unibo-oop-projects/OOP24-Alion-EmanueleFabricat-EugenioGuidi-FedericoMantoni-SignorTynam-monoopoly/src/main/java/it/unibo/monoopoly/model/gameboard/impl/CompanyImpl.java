package it.unibo.monoopoly.model.gameboard.impl;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.Company;
import it.unibo.monoopoly.model.gameboard.api.Dices;

/**
 * Implements the {@link Company} interface.
 */
@JsonTypeName("Company")
public class CompanyImpl extends AbstractBuyable implements Company {

    private static final int MULTIPLIER_1 = 4;
    private static final int MULTIPLIER_2 = 10;

    private final Dices dice;
    private Optional<Integer> actualRentalValue;

    /**
     * Constructor of a company cell.
     * @param name the name of the cell
     * @param cost the cost of the cell
     */
    public CompanyImpl(
        @JsonProperty("name")final String name,
        @JsonProperty("cost")final int cost
    ) {
        super(name, cost);
        this.dice = new DicesImpl();
        this.actualRentalValue = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateRentalValue() {
        return this.actualRentalValue.orElseThrow(() ->
            new IllegalStateException("Rental value need to be first calculated for Companies"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollAndCalculate() {
        if (!isAvailable()) {
            this.dice.rollDices();
            final int multiplier = this.hasAnotherCompany() ? MULTIPLIER_2 : MULTIPLIER_1;
            this.actualRentalValue = Optional.of(multiplier * this.dice.getResult());
        } else {
            throw new IllegalStateException("The property must be owned by a player");
        }

    }

    private boolean hasAnotherCompany() {
        return Math.toIntExact(
            this.getOwner().get().getProperties().stream()
            .filter(Cell::isCompany).count()) == 2;
    }

}
