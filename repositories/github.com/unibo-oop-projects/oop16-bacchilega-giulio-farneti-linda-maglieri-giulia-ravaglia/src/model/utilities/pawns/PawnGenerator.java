package model.utilities.pawns;

import java.lang.reflect.Constructor;
import java.util.Optional;

import utilities.Pair;
import utilities.Players;

/**
 * @author : Giulio Bacchilega
 */
public class PawnGenerator implements Generator {

    /**
     * Contructor of this class.
     * @param name the type of this pawn
     * @param player the player owner of this pawn
     * @param actualPosition the current position of this pawn
     * @return Pawn
     */
    public Pawn build(final PawnTypes name, final Players player, final Pair<Integer, Integer> actualPosition) {
        Optional<Pawn> pawn = Optional.empty();
        try {
            Constructor<?> c = Class.forName("model.utilities.pawns." + name.toString()).getConstructor(PawnTypes.class, Players.class, Pair.class);
            pawn = Optional.of((Pawn) c.newInstance(name, player, actualPosition));
        } catch (Exception e) {
            e.printStackTrace();
        } 

        if (pawn.isPresent()) {
            pawn.get().setAfterCreation();
        }
        return pawn.get();
    }
}
