package casim.model.codi.cell.builder;

import java.util.EnumMap;
import java.util.Optional;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.CoDiCellState;
import casim.model.codi.utils.CoDiDirection;
import casim.utils.BaseBuilder;

/**
 * Implementation of {@link CoDiCellBuilder}.
 */
public class CoDiCellBuilderImpl implements CoDiCellBuilder {

    private static final String NULL_STATE = "The state cannot be null";
    private static final String NULL_CHROMOSOME = "The chromosome cannot be null";
    private static final String ACTIVATIONCOUNTER_VALUE = "The activation counter value must be greater or equal to zero";
    private static final String NULL_NEIGHBORSPREVIOUSINPUT = "The neighbors previous input cannot be null";
    private static final String ALREADY_BUILT = "Cannot build two times";

    private boolean built;
    private CoDiCellState state;
    private Optional<CoDiDirection> gate;
    private Integer activationCounter;
    private EnumMap<CoDiDirection, Boolean> chromosome;
    private EnumMap<CoDiDirection, Integer> neighborsPreviousInput;

    private final BaseBuilder base = new BaseBuilder();

    /**
     * Construct a {@link CoDiCellBuilder} with initial values.
     */
    public CoDiCellBuilderImpl() {
        this.built = false;
        this.gate = Optional.empty();
    }

    private void alreadyBuiltCheck() {
        this.base.checkValue(this.built, b -> !b, ALREADY_BUILT);
    }

    @Override
    public CoDiCellBuilder gate(final Optional<CoDiDirection> gate) {
        this.alreadyBuiltCheck();
        this.gate = gate;
        return this;
    }

    @Override
    public CoDiCellBuilder state(final CoDiCellState state) {
        this.alreadyBuiltCheck();
        this.state = state;
        return this;
    }

    @Override
    public CoDiCellBuilder chromosome(final EnumMap<CoDiDirection, Boolean> chromosome) {
        this.alreadyBuiltCheck();
        this.chromosome = chromosome;
        return this;
    }

    @Override
    public CoDiCellBuilder activationCounter(final int activationCounter) {
        this.alreadyBuiltCheck();
        this.activationCounter = this.base.checkValue(activationCounter, v -> v >= 0, ACTIVATIONCOUNTER_VALUE);
        return this;
    }

    @Override
    public CoDiCellBuilder neighborsPreviousInput(final EnumMap<CoDiDirection, Integer> neighborsPreviousInput) {
        this.alreadyBuiltCheck();
        this.neighborsPreviousInput = neighborsPreviousInput;
        return this;
    }

    @Override
    public CoDiCell build() { 
        this.alreadyBuiltCheck();
        this.built = true;
        return new CoDiCell(
                this.base.checkNonNullValue(this.state, NULL_STATE), 
                this.activationCounter, 
                this.gate,
                this.base.checkNonNullValue(this.neighborsPreviousInput, NULL_NEIGHBORSPREVIOUSINPUT), 
                this.base.checkNonNullValue(this.chromosome, NULL_CHROMOSOME));
    }

}
