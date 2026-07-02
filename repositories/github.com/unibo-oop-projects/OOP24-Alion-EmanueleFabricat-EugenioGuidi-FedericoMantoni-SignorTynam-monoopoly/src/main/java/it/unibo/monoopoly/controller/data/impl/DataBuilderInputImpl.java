package it.unibo.monoopoly.controller.data.impl;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.controller.data.api.DataBuilderInput;

/**
 * Implementation of the DataBuilderInput.
 */
public class DataBuilderInputImpl implements DataBuilderInput {
    private Optional<Event> event = Optional.empty();
    private Optional<Integer> valueToPAy = Optional.empty();
    private Optional<Pair<Integer, Integer>> dices = Optional.empty();
    private Optional<Boolean> enabled = Optional.empty();
    private Optional<Map<Integer, Integer>> cellMap = Optional.empty();
    private Optional<String> text = Optional.empty();

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public DataBuilderInput cellMap(final Map<Integer, Integer> map) {
        this.cellMap = Optional.of(map);
        return this;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public DataBuilderInput dices(final Pair<Integer, Integer> dices) {
        this.dices = Optional.of(dices);
        return this;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public DataBuilderInput event(final Event event) {
        this.event = Optional.of(event);
        return this;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public DataBuilderInput enabled(final boolean mode) {
        this.enabled = Optional.of(mode);
        return this;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public DataBuilderInput text(final String text) {
        this.text = Optional.of(text);
        return this;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public DataBuilderInput valueToPay(final Integer value) {
        this.valueToPAy = Optional.of(value);
        return this;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public DataInput build() {
        return new DataInput(this.cellMap, this.dices, this.event, this.enabled, this.text, this.valueToPAy);
    }

}
