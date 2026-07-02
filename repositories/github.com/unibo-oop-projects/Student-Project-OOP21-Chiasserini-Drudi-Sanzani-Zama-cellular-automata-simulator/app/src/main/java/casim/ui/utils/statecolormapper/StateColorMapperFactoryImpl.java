package casim.ui.utils.statecolormapper;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import casim.model.bryansbrain.BryansBrainCellState;
import casim.model.codi.cell.CoDiCellState;
import casim.model.gameoflife.GameOfLifeState;
import casim.model.langtonsant.LangtonsAntCellState;
import casim.model.rule110.Rule110CellState;
import casim.model.wator.WatorCellState;
import casim.utils.Colors;
import javafx.scene.paint.Color;

/**
 * {@link StateColorMapperFactory} implementation.
 */
public final class StateColorMapperFactoryImpl implements StateColorMapperFactory {

    @Override
    public StateColorMapper<CoDiCellState> getCoDiStateColorMapper() {
        final var colorList = List.of(Colors.BLACK, Colors.AMETHYST, Colors.FUSCIA,
            Colors.CARROT, Colors.ARCTIC, Colors.PARAKEET);
        return this.getColorMapperFromLists(Arrays.asList(CoDiCellState.values()), colorList);
    }

    @Override
    public StateColorMapper<LangtonsAntCellState> getLangtonsAntStateColorMapper() {
        return this.getColorMapperFromLists(
            Arrays.asList(LangtonsAntCellState.values()), 
            List.of(Colors.WHITE, Colors.BLACK));
    }

    @Override
    public StateColorMapper<WatorCellState> getWatorStateColorMapper() {
        final var colorList = List.of(Colors.RED, Colors.GREEN, Colors.BLACK);
        return this.getColorMapperFromLists(Arrays.asList(WatorCellState.values()), colorList);
    }

    @Override
    public StateColorMapper<GameOfLifeState> getGameOfLifeStateColorMapper() {
        final var colorList = List.of(Colors.WHITE, Colors.BLACK);
        return this.getColorMapperFromLists(Arrays.asList(GameOfLifeState.values()), colorList);
    }

    @Override
    public StateColorMapper<BryansBrainCellState> getBryansBrainStateColorMapper() {
        return this.getColorMapperFromLists(
            Arrays.asList(BryansBrainCellState.values()),
            List.of(Colors.WHITE, Colors.LIGHT_BLUE, Colors.BLACK));
    }

    @Override
    public StateColorMapper<Rule110CellState> getRule110StateColorMapper() {
        return this.getColorMapperFromLists(
            Arrays.asList(Rule110CellState.values()),
            List.of(Colors.WHITE, Colors.BLACK));
    }

    private <T> StateColorMapper<T> getColorMapperFromLists(final List<T> states, final List<Color> colors) {
        final var stateToColor = this.zipToMap(states, colors);
        return (state) -> stateToColor.get(state);
    }

    private <T> Map<T, Color> zipToMap(final List<T> values, final List<Color> colors) {
        if (values.size() != colors.size()) {
            throw new IllegalArgumentException();
        }
        final Iterator<T> valuesIterator = values.iterator();
        final Iterator<Color> colorsIterator = colors.iterator();
        return IntStream.range(0, values.size()).boxed()
                .collect(Collectors.toMap(k -> valuesIterator.next(), v -> colorsIterator.next()));
    }
}
