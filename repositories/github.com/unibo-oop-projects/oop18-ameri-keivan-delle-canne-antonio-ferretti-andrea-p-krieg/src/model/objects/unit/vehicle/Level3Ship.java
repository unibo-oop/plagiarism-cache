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
 * The Level3Ship extends ShipImpl class and represent the third level of ship.
 * The Level3Ship, is a ship and it is similar to Level2Ship, but it has more
 * strength.
 */
public class Level3Ship extends ShipImpl {

    private static final String NAME = "Ship level 3";
    private static final int STRENGTH = 25;
    private static final int HP = 40;
    private static final int MOV_RANGE = 2;
    private static final int ATT_RANGE = 3;
    private static final int POSSIBLE_ATT = 1;
    private static final boolean CAN_MOVE_AFTER_ATTACK = false;
    private static final boolean MOVE_ON_KILL = false;
    private static final int UNLOCK_GOLD_COST = 600;
    private static final int UNLOCK_WOOD_COST = 700;

    /**
     * Level3Ship constructor.
     * 
     * @param passenger is the unit that ride the vehicle.
     */
    public Level3Ship(final Optional<Unit> passenger) {
        super(NAME, STRENGTH, HP, MOV_RANGE, ATT_RANGE, POSSIBLE_ATT, CAN_MOVE_AFTER_ATTACK, MOVE_ON_KILL,
                new BasicCostImpl(Optional.of(UNLOCK_GOLD_COST), Optional.of(UNLOCK_WOOD_COST), Optional.empty()), passenger);
    }

    /** {@inheritDoc} **/
    @Override
    public Set<Ability> getAbilities() {
        return new HashSet<Ability>(Arrays.asList(BasicAbilities.WALKONWATER));
    }

}
