package casim.ui.utils.statecolormapper;

import casim.model.bryansbrain.BryansBrainCellState;
import casim.model.codi.cell.CoDiCellState;
import casim.model.gameoflife.GameOfLifeState;
import casim.model.langtonsant.LangtonsAntCellState;
import casim.model.rule110.Rule110CellState;
import casim.model.wator.WatorCellState;

/**
 * Factory for {@link StateColorMapper} configuration.
 */
public interface StateColorMapperFactory {

    /**
     * Return the {@link StateColorMapper} for CoDi automaton.
     * 
     * @return the {@link StateColorMapper} for CoDi automaton.
     */
    StateColorMapper<CoDiCellState> getCoDiStateColorMapper();

    /**
     * Returns the {@link StateColorMapper} for Langton's Ant automaton.
     * 
     * @return the {@link StateColorMapper} for Langton's Ant automaton.
     */
    StateColorMapper<LangtonsAntCellState> getLangtonsAntStateColorMapper();

    /**
     * Returns the {@link StateColorMapper} for Wator automaton.
     * 
     * @return the {@link StateColorMapper} for Wator automaton.
     */
    StateColorMapper<WatorCellState> getWatorStateColorMapper();

    /**
     * Returns the {@link StateColorMapper} for Game of life automaton.
     * 
     * @return the {@link StateColorMapper} for Game of life automaton.
     */
    StateColorMapper<GameOfLifeState> getGameOfLifeStateColorMapper();

    /**
     * Returns the {@link StateColorMapper} for Bryan's Brain automaton.
     * 
     * @return the {@link StateColorMapper} for Bryan's Brain automaton.
     */
    StateColorMapper<BryansBrainCellState> getBryansBrainStateColorMapper();

    /**
     * Returns the {@link StateColorMapper} for Rule 110 automaton.
     * 
     * @return the {@link StateColorMapper} for Rule 110 automaton.
     */
    StateColorMapper<Rule110CellState> getRule110StateColorMapper();

}
