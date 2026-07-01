package it.unibo.cluedolite.model.accuseandsuspect.impl;

import java.util.List;

import it.unibo.cluedolite.model.accuseandsuspect.api.InterfaceAccuseManager;
import it.unibo.cluedolite.model.creationcards.impl.AbstractCard;
import it.unibo.cluedolite.model.gamesetup.api.InterfaceSecretSolution;
import it.unibo.cluedolite.model.accuseandsuspect.api.InterfaceSuspicion;

/**
 * Manages the logic for formal accusations in the CluedoLite game.
 * It checks if a player's accusation matches the secret solution.
 * An accusation reuses {@link Suspicion} as its data structure,
 * since it is conceptually a suspicion verified against the solution.
 */
public final class AccuseManager implements InterfaceAccuseManager {

    private final InterfaceSecretSolution secretSolution;

    /**
     * Constructs an AccuseManager with the given secret solution.
     *
     * @param secretSolution the secret solution to check accusations against
     */
    public AccuseManager(final InterfaceSecretSolution secretSolution) {
        this.secretSolution = secretSolution;
    }

    @Override
    public boolean checkAccuse(final InterfaceSuspicion suspicion) {
        final List<AbstractCard> solution = secretSolution.getSolution();
        return solution.contains(suspicion.getCharacter())
            && solution.contains(suspicion.getWeapon())
            && solution.contains(suspicion.getRoom());
    }
}
