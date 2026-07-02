package it.unibo.monoopoly.controller.data.impl;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.controller.data.api.DataBuilderInput;
import it.unibo.monoopoly.model.gameboard.api.Dices;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * The record built by a {@link DataBuilderInput} that packages the
 * input data to the View.
 * 
 * @param cellMap    could contains the entries player index -> amount of money.
 * @param dices      could contains the {@link Pair} that represents two
 *                   {@link Dices}.
 * @param event      could contains the actual {@link Event} of the
 *                   {@link MainModel}.
 * @param isEnabled       could contains a boolean useful in some {@link ViewState}.
 * @param text       could contains text that need to be passed to the View.
 * @param valueToPay could contains a value that need to be shown.
 */
public record DataInput(
                Optional<Map<Integer, Integer>> cellMap,
                Optional<Pair<Integer, Integer>> dices,
                Optional<Event> event,
                Optional<Boolean> isEnabled,
                Optional<String> text,
                Optional<Integer> valueToPay) {
}
