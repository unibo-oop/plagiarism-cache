package it.unibo.monoopoly.model.gameboard.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.Railroad;

/**
 * Implementation of {@link Railroad} interface.
 */
@JsonTypeName("Railroad")
public class RailroadImpl extends AbstractBuyable implements Railroad {

    private static final int BASE_VALUE = 25;

    /**
     * Constructor of a railroad cell.
     * @param name the name of the {@link Railroad}
     * @param cost the cost the {@link Railroad}
     */
    public RailroadImpl(
        @JsonProperty("name")final String name,
        @JsonProperty("cost")final int cost
    ) {
        super(name, cost);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateRentalValue() {
        return BASE_VALUE * (int) Math.pow(2,
            Math.toIntExact(this.getOwner().get().getProperties().stream()
            .filter(Cell::isRailroad).count()) - 1);
    }

}
