package it.unibo.monoopoly.controller.data.api;

import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.controller.data.impl.DataInput;
import it.unibo.monoopoly.model.gameboard.api.Dices;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * The builder of the {@link DataInput} that contains all information for the
 * gui.
 */
public interface DataBuilderInput {
    /**
     * Fluent method that insert a {@link Map} {@link Player} index -> amount of
     * money, in the {@link DataInput}.
     * 
     * @param cellMap to be inserted.
     * @return this.
     */
    DataBuilderInput cellMap(Map<Integer, Integer> cellMap);

    /**
     * Fluent method that insert a {@link Pair} of the result of {@link Dices}, in
     * the {@link DataInput}.
     * 
     * @param dices to be inserted.
     * @return this.
     */
    DataBuilderInput dices(Pair<Integer, Integer> dices);

    /**
     * Fluent method that insert the {@link Event} taken from the {@link MainModel},
     * in the {@link DataInput}.
     * 
     * @param event to be inserted.
     * @return this.
     */
    DataBuilderInput event(Event event);

    /**
     * Fluent method that insert a boolean useful to make decision, in the
     * {@link DataInput}.
     * 
     * @param enabled to be inserted.
     * @return this.
     */
    DataBuilderInput enabled(boolean enabled);

    /**
     * Fluent method that insert a {@link String} to set some text, in the
     * {@link DataInput}.
     * 
     * @param text to be inserted.
     * @return this.
     */
    DataBuilderInput text(String text);

    /**
     * Fluent method that insert a {@link Integer} that represents a value to pay,
     * in the {@link DataInput}.
     * 
     * @param valueToPay to be inserted
     * @return this.
     */
    DataBuilderInput valueToPay(Integer valueToPay);

    /**
     * The method to return the built product.
     * 
     * @return product.
     */
    DataInput build();
}
