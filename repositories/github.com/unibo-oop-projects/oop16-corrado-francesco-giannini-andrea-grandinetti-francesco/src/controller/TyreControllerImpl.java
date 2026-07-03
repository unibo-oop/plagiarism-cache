package controller;

import java.util.List;

import utility.TyreType;

/**
 * Class that implements TyreController interface.
 */
public class TyreControllerImpl implements TyreController {

    private final List<TyreType> tyresAvailable;

    /**
     * Constructor.
     * @param cir the circuit in which the game is set.
     */
    public TyreControllerImpl(final CircuitPlayable cir) {
        this.tyresAvailable = cir.getTyresAvailable();
    }

    @Override
    public boolean isTheWrongTyre(final TyreType tyre) {
        return !this.tyresAvailable.contains(tyre);
    }

    @Override
    public boolean hasToBeDisqualified(final List<TyreType> list) {
        final TyreType oldTyre = list.get(0);
        if (this.isTheWrongTyre(oldTyre)) {
            return true;
        }
        for (final TyreType tyre : list) {
            if (!tyre.equals(oldTyre)) {
                return false;
            } else if (this.isTheWrongTyre(tyre)) {
                return true;
            }
        }
        return true;
    }

    @Override
    public List<TyreType> getTyresPermitted() {
        return this.tyresAvailable;
    }

}
