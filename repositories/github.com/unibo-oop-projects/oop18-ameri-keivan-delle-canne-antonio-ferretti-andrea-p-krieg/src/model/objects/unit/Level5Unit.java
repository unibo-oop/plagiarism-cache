package model.objects.unit;

import java.util.Optional;

import model.BasicCostImpl;
import model.player.Player;

/**
 * The Level5Unit class represents the fifth level unit. That unit is a
 * CLOSE_NORMAL unit, so it fight at close range. It have an unlock
 * cost. If it has an owner, the other attributes and values of
 * this unit will vary according to its race. If it hasn't an owner, it's a
 * neutral unit and so it has basic attributes and values.
 */
public class Level5Unit extends UnitImpl {

    private static final UnitType TYPE = UnitType.CLOSE_NORMAL;
    private static final String NAME = "Elite Soldier";
    private static final int STRENGTH = 30;
    private static final int HP = 35;
    private static final int MOV_RANGE = 2;
    private static final int ATT_RANGE = 1;
    private static final int POSSIBLE_ATT = 2;
    private static final int GOLD_COST = 220;
    private static final int WOOD_COST = 220;
    private static final int POPULATION_COST = 3;
    private static final int UNLOCK_GOLD_COST = 650;
    private static final int UNLOCK_WOOD_COST = 500;
    private static final boolean CAN_MOVE_AFTER_ATTACK = true;
    private static final boolean MOVE_ON_KILL = true;

    /**
     * Constructor.
     * 
     * @param owner is the unit's owner.
     */
    public Level5Unit(final Player owner) {
        super(Optional.of(owner), NAME, STRENGTH + owner.getRace().getStrBoost(TYPE),
                HP + owner.getRace().getHpBoost(TYPE), MOV_RANGE + owner.getRace().getMovRangeBoost(TYPE),
                ATT_RANGE + owner.getRace().getAttRangeBoost(TYPE),
                POSSIBLE_ATT + owner.getRace().getPossibleAttBoost(TYPE), CAN_MOVE_AFTER_ATTACK, MOVE_ON_KILL,
                new BasicCostImpl(Optional.of(GOLD_COST), Optional.of(WOOD_COST), Optional.of(POPULATION_COST))
                        .mergeBasicCost(owner.getRace().getCostBoost(TYPE)),
                new BasicCostImpl(Optional.of(UNLOCK_GOLD_COST), Optional.of(UNLOCK_WOOD_COST), Optional.empty()),
                TYPE);
    }

    /**
     * Constructor.
     * It's use to create an neutral unit. A neutral unit has basic
     * attributes and it doesn't have an unlock cost.
     */
    public Level5Unit() {
        super(Optional.empty(), NAME, STRENGTH, HP, MOV_RANGE, ATT_RANGE, POSSIBLE_ATT, CAN_MOVE_AFTER_ATTACK,
                MOVE_ON_KILL, new BasicCostImpl(),
                new BasicCostImpl(Optional.of(UNLOCK_GOLD_COST), Optional.of(UNLOCK_WOOD_COST), Optional.empty()),
                TYPE);
    }

}
