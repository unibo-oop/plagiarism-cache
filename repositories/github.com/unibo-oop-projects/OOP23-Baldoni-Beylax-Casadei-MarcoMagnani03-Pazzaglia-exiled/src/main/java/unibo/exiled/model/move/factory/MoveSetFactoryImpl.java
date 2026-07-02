package unibo.exiled.model.move.factory;

import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetImpl;
import unibo.exiled.model.move.MagicMove;

/**
 * The implementation of a factory of MoveSets.
 */
public final class MoveSetFactoryImpl implements MoveSetFactory {

    /**
     * Fills the moveSet with the required moves.
     *
     * @param moves The moves to add to the MoveSet.
     * @return A MoveSet with the moves inserted into it.
     */
    private MoveSet fillMoveSet(final MagicMove... moves) {
        final MoveSet moveSet = new MoveSetImpl();
        for (final MagicMove move : moves) {
            moveSet.addMagicMove(move);
        }
        return moveSet;
    }

    @Override
    public MoveSet defaultNormalMoveSet() {
        return this.fillMoveSet(MagicMove.TACKLE);
    }

    @Override
    public MoveSet defaultFireMoveSet() {
        return this.fillMoveSet(MagicMove.FIREBALL);
    }

    @Override
    public MoveSet defaultGrassMoveSet() {
        return this.fillMoveSet(MagicMove.LEAFBLADE);
    }

    @Override
    public MoveSet defaultBoltMoveSet() {
        return this.fillMoveSet(MagicMove.LIGHTBULB);
    }

    @Override
    public MoveSet defaultWaterMoveSet() {
        return this.fillMoveSet(MagicMove.WATERPISTOL);
    }

    @Override
    public MoveSet whirlerMoveset() {
        return this.fillMoveSet(MagicMove.FLAMEWHIRL, MagicMove.FIREBALL);
    }

    @Override
    public MoveSet boltBossMoves() {
        return this.fillMoveSet(MagicMove.THUNDERSTORM, MagicMove.LOCOMOVOLT);
    }

    @Override
    public MoveSet fireBossMoves() {
        return this.fillMoveSet(MagicMove.INFERNO);
    }

    @Override
    public MoveSet waterBossMoves() {
        return this.fillMoveSet(MagicMove.TSUNAMI);
    }

    @Override
    public MoveSet grassBossMoves() {
        return this.fillMoveSet(MagicMove.LEECHERS);
    }

    @Override
    public MoveSet leafyMoveSet() {
        return this.fillMoveSet(MagicMove.LEAFBLADE, MagicMove.LEAFLATCH);
    }

}
