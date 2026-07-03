package controller.player;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import utility.Direction;
import utility.Driver;
import utility.TyreType;

/**
 * 
 */
public class AI extends PlayerImpl {

    private static final int DEGRADE_LIMIT = 60;
    private static final int MAX_DEGRADE_LIMIT = 85;
    private final List<TyreType> tyresAvailable;
    private final TyreType initialType;
    private final Random generator = new Random();
    private boolean hasToChangeTyre = true;

    /**
     * Constructor.
     * @param driver the driver the AI is using
     * @param tyres the list of tyres that the AI could use during the race
     */
    public AI(final Driver driver, final List<TyreType> tyres) {
        super(driver);
        this.tyresAvailable = tyres;
        final int num = this.generator.nextInt(this.tyresAvailable.size());
        this.initialType = this.tyresAvailable.get(num);
        this.getPlayerStints().add(this.initialType);
    }

    /**
     * Method to let "decide" to the AI if it has to change tyres or not.
     * @param degrade the current degrade of the tyres used by the AI
     * @return an optional containing the tyre the AI wants to use
     */
    public Optional<TyreType> changeTyre(final double degrade) {
        Optional<TyreType> tyre = Optional.empty();
        if (degrade >= DEGRADE_LIMIT) {
            final boolean box = this.generator.nextBoolean();
            if (box || degrade >= MAX_DEGRADE_LIMIT) {
                int num = this.generator.nextInt(this.tyresAvailable.size());
                TyreType randomTyre = this.tyresAvailable.get(num);
                while (hasToChangeTyre) {
                    num = this.generator.nextInt(this.tyresAvailable.size());
                    randomTyre = this.tyresAvailable.get(num);
                    hasToChangeTyre = (randomTyre == this.getInitialTyre());
                } 
                tyre = Optional.of(randomTyre);
            }
        }
        return tyre;
    }

    /**
     * Getter. 
     * @return the initial tyre that the AI will use
     */
    public TyreType getInitialTyre() {
        return this.initialType;
    }

    /**
     * Method to get a possible direction that the AI "wants" to take.
     * @return the direction chosen
     */
    public Direction getDirection() {
        final int dir = this.generator.nextInt(3);
        return Direction.values()[dir];
    }

}
