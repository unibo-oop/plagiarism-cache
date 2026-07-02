package it.unibo.monoopoly.controller.data.api;

import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.model.gameboard.api.Cell;

/**
 * The builder of the {@link DataOutput} that contains all information for the
 * Model.
 */
public interface DataBuilderOutput {
    /**
     * Fluent method that insert a boolean to decide if the property has been bought
     * or not, in the {@link DataOutput}.
     * 
     * @param buyProperty to be inserted.
     * @return this.
     */
    DataBuilderOutput buyProperty(boolean buyProperty);

    /**
     * Fluent method that insert a int that represents the index of the chosen
     * {@link Cell}, in the {@link DataOutput}.
     * 
     * @param selectedCell to be inserted.
     * @return this.
     */
    DataBuilderOutput selectedCell(int selectedCell);

    /**
     * Build the {@link DataOutput}.
     * @return product.
     */
    DataOutput build();
}
