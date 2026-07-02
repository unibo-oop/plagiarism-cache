package it.unibo.monoopoly.controller.data.impl;

import java.util.Optional;

import it.unibo.monoopoly.controller.data.api.DataBuilderOutput;
import it.unibo.monoopoly.model.gameboard.api.Cell;

/**
 * The record built by a {@link DataBuilderOutput} that packages the
 * input data to the Model.
 * 
 * @param buyProperty boolean to decide if the property has been bought or not.
 * @param selectedCell  that represents the index of the chosen {@link Cell}.
 */
public record DataOutput(
        Optional<Boolean> buyProperty,
        Optional<Integer> selectedCell) {
}
