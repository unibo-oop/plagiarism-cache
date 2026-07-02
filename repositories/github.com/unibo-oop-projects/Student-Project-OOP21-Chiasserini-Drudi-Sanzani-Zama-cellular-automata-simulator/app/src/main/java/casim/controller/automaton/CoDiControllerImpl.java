package casim.controller.automaton;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.codi.CoDi;
import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.CoDiCellState;
import casim.utils.grid.Grid2D;

/**
 * Implementation of {@link CoDi}'s {@link AutomatonControllerImpl}.
 */
public class CoDiControllerImpl extends AutomatonControllerImpl<CoDiCellState, CoDiCell> {

    /**
     * Construct a new {@link CoDiControllerImpl}.
     * 
     * @param automaton the automaton linked to the controller.
     */
    public CoDiControllerImpl(final CoDi automaton) {
        super(automaton);
    }

    /**
     * Do a right shift of {@link CoDi} output layer.
     * 
     * @return the {@link Grid2D} after the shift.
     */
    public Grid2D<CoDiCellState> outputLayerRightShift() {
        return ((CoDi) this.getAutomaton()).outputLayerRightShift().map(AbstractCell::getState);
    }

    /**
     * Do a left shift of {@link CoDi} output layer.
     * 
     * @return the {@link Grid2D} after the shift.
     */
    public Grid2D<CoDiCellState> outputLayerLeftShift() {
        return ((CoDi) this.getAutomaton()).outputLayerLeftShift().map(AbstractCell::getState);
    }

}
