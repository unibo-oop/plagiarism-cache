package model.objects.unit;

import java.util.Optional;

import model.BasicCostImpl;
import model.player.Player;

/**
 * The Level1Unit class represents the first level unit. That unit is a
 * CLOSE_BASIC unit, so it fight at close range. As a basic unit, it doesn't
 * have an unlock cost. If it has an owner, the other attributes and values of
 * this unit will vary according to its race. If it hasn't an owner, it's a
 * neutral unit and so it has basic attributes and values.
 */
public class Level1Unit extends UnitImpl {

    private static final UnitType TYPE = UnitType.CLOSE_BASIC;
    private static final String NAME = "Basic Soldier";
    private static final int STRENGTH = 20;
    private static final int HP = 20;
    private static final int MOV_RANGE = 1;
    private static final int ATT_RANGE = 1;
    private static final int POSSIBLE_ATT = 1;
    private static final int GOLD_COST = 100;
    private static final int WOOD_COST = 100;
    private static final int POPULATION_COST = 1;
    private static final boolean CAN_MOVE_AFTER_ATTACK = false;
    private static final boolean MOVE_ON_KILL = true;

    /**
     * Constructor.
     * 
     * @param owner is the unit's owner.
     */
    public Level1Unit(final Player owner) {
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
    public Level1Unit() {
        super(Optional.empty(), NAME, STRENGTH, HP, MOV_RANGE, ATT_RANGE, POSSIBLE_ATT, CAN_MOVE_AFTER_ATTACK,
                MOVE_ON_KILL, new BasicCostImpl(), new BasicCostImpl(), TYPE);
    }

}
