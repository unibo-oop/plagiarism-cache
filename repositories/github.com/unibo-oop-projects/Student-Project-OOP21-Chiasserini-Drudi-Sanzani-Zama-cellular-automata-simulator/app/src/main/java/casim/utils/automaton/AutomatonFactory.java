package casim.utils.automaton;

import casim.model.bryansbrain.BryansBrain;
import casim.model.codi.CoDi;
import casim.model.codi.CoDiConfig;
import casim.model.gameoflife.GameOfLife;
import casim.model.langtonsant.LangtonsAnt;
import casim.model.langtonsant.LangtonsAntConfig;
import casim.model.rule110.Rule110;
import casim.model.wator.Wator;
import casim.utils.automaton.config.BaseConfig;
import casim.utils.automaton.config.WrappingConfig;

/**
 * A factory for the automaton creation.
 */
public interface AutomatonFactory {
    /**
     * Return a new {@link BryansBrain} automaton.
     * 
     * @param config the {@link WrappingConfig} containing the automaton's configuration values.
     * @return a new {@link BryansBrain} automaton.
     */
    BryansBrain getBryansBrainRandom(WrappingConfig config);

    /**
     * Return a new {@link CoDi} automaton.
     * 
     * @param config a {@link CoDiConfig} containing the automaton's configuration values.
     * @return a new {@link CoDi} automaton.
     */
    CoDi getCoDi(CoDiConfig config);

    /**
     * Returns a new {@link LangtonsAnt} automaton.
     * 
     * @param config the {@link LangtonsAntConfig} containing the automaton's configuration values.
     * @return a new {@link LangtonsAnt} automaton.
     */
    LangtonsAnt getLangtonsAnt(LangtonsAntConfig config);

    /**
     * Returns a new {@link Wator} automaton.
     * 
     * @param config the {@link BaseConfig} containing the automaton's configuration values.
     * @return a new {@link Wator} automaton.
     */
    Wator getWator(BaseConfig config);

    /**
     * Returns a new {@link Rule110} automaton.
     * 
     * @param config the {@link BaseConfig} containing the automaton's configuration values.
     * @return a new {@link Rule110} automaton.
     */
    Rule110 getRule110(BaseConfig config);

    /**
     * Returns a new {@link GameOfLife} automaton.
     * 
     * @param config the {@link WrappingConfig} containing the automaton's configuration values.
     * @return a new {@link GameOfLife} automaton.
     */
    GameOfLife getGameOfLife(WrappingConfig config);
}
