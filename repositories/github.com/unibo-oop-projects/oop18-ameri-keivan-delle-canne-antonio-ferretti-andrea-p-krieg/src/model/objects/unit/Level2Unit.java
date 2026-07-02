package model.objects.unit;

import java.util.Optional;

import model.BasicCostImpl;
import model.player.Player;

/**
 * The Level2Unit class represents the second level unit. That unit is a
 * DISTANCE_BASIC unit, so it fight from a distance. As a basic unit, it doesn't
 * have an unlock cost. If it has an owner, the other attributes and values of
 * this unit will vary according to its race. If it hasn't an owner, it's a
 * neutral unit and so it has basic attributes and values.
 */
public class Level2Unit extends UnitImpl {

    private static final UnitType TYPE = UnitType.DISTANCE_BASIC;
    private static final String NAME = "Basic Distance Soldier";
    private static final int STRENGTH = 15;
    private static final int HP = 15;
    private static final int MOV_RANGE = 1;
    private static final int ATT_RANGE = 2;
    private static final int POSSIBLE_ATT = 1;
    private static final int GOLD_COST = 80;
    private static final int WOOD_COST = 80;
    private static final int POPULATION_COST = 1;
    private static final boolean CAN_MOVE_AFTER_ATTACK = false;
    private static final boolean MOVE_ON_KILL = false;

    /**
     * Constructor.
     * 
     * @param owner is the unit's owner.
     */
    public Level2Unit(final Player owner) {
        super(Optional.of(owner), NAME, STRENGTH + owner.getRace().getStrBoost(TYPE),
                HP + owner.getRace().getHpBoost(TYPE), MOV_RANGE + owner.getRace().getMovRangeBoost(TYPE),
                ATT_RANGE + owner.getRace().getAttRangeBoost(TYPE),
                POSSIBLE_ATT + owner.getRace().getPossibleAttBoost(TYPE), CAN_MOVE_AFTER_ATTACK, MOVE_ON_KILL,
                new BasicCostImpl(Optional.of(GOLD_COST), Optional.of(WOOD_COST), Optional.of(POPULATION_COST))
                        .mergeBasicCost(owner.getRace().getCostBoost(TYPE)),
                new BasicCostImpl(), TYPE);
    }

    /**
     * Constructor.
     * It's use to create an neutral unit. A neutral unit has basic
     * attributes and it doesn't have an unlock cost.
     */
    public Level2Unit() {
        super(Optional.empty(), NAME, STRENGTH, HP, MOV_RANGE, ATT_RANGE, POSSIBLE_ATT, CAN_MOVE_AFTER_ATTACK,
                MOVE_ON_KILL, new BasicCostImpl(), new BasicCostImpl(), TYPE);
    }

}
