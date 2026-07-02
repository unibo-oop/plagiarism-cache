package it.unibo.monoopoly.controller.data.impl;

import java.util.Optional;

import it.unibo.monoopoly.controller.data.api.DataBuilderOutput;

/**
 * Implementation of the DataBuilderOutput.
 */
public class DataBuilderOutputImpl implements DataBuilderOutput {
    private Optional<Boolean> buyProperty = Optional.empty();
    private Optional<Integer> selectedCell = Optional.empty();

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public DataBuilderOutput buyProperty(final boolean buy) {
        this.buyProperty = Optional.of(buy);
        return this;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public DataBuilderOutput selectedCell(final int cell) {
        this.selectedCell = Optional.of(cell);
        return this;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public DataOutput build() {
        return new DataOutput(this.buyProperty, this.selectedCell);
    }
}
