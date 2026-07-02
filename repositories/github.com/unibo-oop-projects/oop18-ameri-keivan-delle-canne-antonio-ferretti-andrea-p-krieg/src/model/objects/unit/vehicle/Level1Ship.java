package model.objects.unit.vehicle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import model.BasicCostImpl;
import model.abilities.Ability;
import model.abilities.BasicAbilities;
import model.objects.unit.Unit;

/**
 * The Level1Ship extends ShipImpl class and represent the first level of ship.
 */
public class Level1Ship extends ShipImpl {

    private static final String NAME = "Ship level 1";
    private static final int STRENGTH = 15;
    private static final int HP = 18;
    private static final int MOV_RANGE = 1;
    private static final int ATT_RANGE = 3;
    private static final int POSSIBLE_ATT = 1;
    private static final boolean CAN_MOVE_AFTER_ATTACK = false;
    private static final boolean MOVE_ON_KILL = false;

    /**
     * Level1Ship constructor.
     * 
     * @param passenger is the unit that ride the vehicle.
     */
    public Level1Ship(final Optional<Unit> passenger) {
        super(NAME, STRENGTH, HP, MOV_RANGE, ATT_RANGE, POSSIBLE_ATT, CAN_MOVE_AFTER_ATTACK,
                MOVE_ON_KILL, new BasicCostImpl(), passenger);
    }

    /** {@inheritDoc} **/
    @Override
    public Set<Ability> getAbilities() {
        return new HashSet<Ability>(Arrays.asList(BasicAbilities.WALKONWATER));
    }

}
