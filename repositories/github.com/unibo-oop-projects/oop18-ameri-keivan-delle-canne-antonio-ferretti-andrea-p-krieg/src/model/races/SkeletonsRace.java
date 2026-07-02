package model.races;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.BasicCostImpl;
import model.Cost;
import model.objects.unit.UnitType;

/**
 * The SkeletonsRace is a class that extends RaceImpl. It represent the
 * skeletons race. It has units from the low hp statistics but with low price.
 * However, distance units will have high strength, while close units will have
 * less strength.
 */
public class SkeletonsRace extends RaceImpl {

    private static final int BASIC_CLOSE_FIGHTER_STR = -5;
    private static final int BASIC_CLOSE_FIGHTER_HP = -10;
    private static final int BASIC_CLOSE_FIGHTER_GOLD = -40;
    private static final int BASIC_CLOSE_FIGHTER_WOOD = -50;

    private static final int BASIC_DISTANCE_FIGHTER_STR = +3;
    private static final int BASIC_DISTANCE_FIGHTER_HP = -9;
    private static final int BASIC_DISTANCE_FIGHTER_GOLD = -10;
    private static final int BASIC_DISTANCE_FIGHTER_WOOD = -10;

    private static final int NORMAL_CLOSE_FIGHTER_STR = -5;
    private static final int NORMAL_CLOSE_FIGHTER_HP = -10;
    private static final int NORMAL_CLOSE_FIGHTER_GOLD = -40;
    private static final int NORMAL_CLOSE_FIGHTER_WOOD = -40;
    private static final int NORMAL_CLOSE_FIGHTER_POP = -1;

    private static final int NORMAL_DISTANCE_FIGHTER_STR = +10;
    private static final int NORMAL_DISTANCE_FIGHTER_GOLD = +50;

    private static final int HERO_CLOSE_FIGHTER_STR = -15;
    private static final int HERO_CLOSE_FIGHTER_HP = -5;
    private static final int HERO_CLOSE_FIGHTER_POSS_ATT = 2;
    private static final int HERO_CLOSE_FIGHTER_GOLD = -30;
    private static final int HERO_CLOSE_FIGHTER_WOOD = -30;

    private static final int HERO_DISTANCE_FIGHTER_STR = +10;
    private static final int HERO_DISTANCE_FIGHTER_HP = -8;
    private static final int HERO_DISTANCE_FIGHTER_MOV_RANGE = +1;
    private static final int HERO_DISTANCE_FIGHTER_GOLD = -30;
    private static final int HERO_DISTANCE_FIGHTER_WOOD = -20;

    /**
     * Skeletons race constructor.
     */
    public SkeletonsRace() {
        super("Skeletons", getBoostMap(), getBoostCostMap());
    }

    /**
     * This method is used to create the stats boost map. The order for the boost
     * is: strength boost, hp, movement range, attack range, possible attacks.
     * 
     * @return boostMap the stats boost map.
     */
    private static Map<UnitType, List<Optional<Integer>>> getBoostMap() {
        final Map<UnitType, List<Optional<Integer>>> boostMap = new HashMap<>();
        boostMap.put(UnitType.CLOSE_BASIC, Arrays.asList(Optional.of(BASIC_CLOSE_FIGHTER_STR),
                Optional.of(BASIC_CLOSE_FIGHTER_HP), Optional.empty(), Optional.empty(), Optional.empty()));
        boostMap.put(UnitType.DISTANCE_BASIC, Arrays.asList(Optional.of(BASIC_DISTANCE_FIGHTER_STR),
                Optional.of(BASIC_DISTANCE_FIGHTER_HP), Optional.empty(), Optional.empty(), Optional.empty()));
        boostMap.put(UnitType.CLOSE_NORMAL, Arrays.asList(Optional.of(NORMAL_CLOSE_FIGHTER_STR),
                Optional.of(NORMAL_CLOSE_FIGHTER_HP), Optional.empty(), Optional.empty(), Optional.empty()));
        boostMap.put(UnitType.DISTANCE_NORMAL, Arrays.asList(Optional.of(NORMAL_DISTANCE_FIGHTER_STR), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty()));
        boostMap.put(UnitType.HERO_CLOSE_FIGHTER,
                Arrays.asList(Optional.of(HERO_CLOSE_FIGHTER_STR), Optional.of(HERO_CLOSE_FIGHTER_HP), Optional.empty(),
                        Optional.empty(), Optional.of(HERO_CLOSE_FIGHTER_POSS_ATT)));
        boostMap.put(UnitType.HERO_DISTANCE_FIGHTER,
                Arrays.asList(Optional.of(HERO_DISTANCE_FIGHTER_STR), Optional.of(HERO_DISTANCE_FIGHTER_HP),
                        Optional.of(HERO_DISTANCE_FIGHTER_MOV_RANGE), Optional.empty(), Optional.empty()));
        return boostMap;
    }

    private static Map<UnitType, Cost> getBoostCostMap() {
        final Map<UnitType, Cost> costMap = new HashMap<>();
        costMap.put(UnitType.CLOSE_BASIC, new BasicCostImpl(Optional.of(BASIC_CLOSE_FIGHTER_GOLD),
                Optional.of(BASIC_CLOSE_FIGHTER_WOOD), Optional.empty()));
        costMap.put(UnitType.DISTANCE_BASIC, new BasicCostImpl(Optional.of(BASIC_DISTANCE_FIGHTER_GOLD),
                Optional.of(BASIC_DISTANCE_FIGHTER_WOOD), Optional.empty()));
        costMap.put(UnitType.CLOSE_NORMAL, new BasicCostImpl(Optional.of(NORMAL_CLOSE_FIGHTER_GOLD),
                Optional.of(NORMAL_CLOSE_FIGHTER_WOOD), Optional.of(NORMAL_CLOSE_FIGHTER_POP)));
        costMap.put(UnitType.DISTANCE_NORMAL,
                new BasicCostImpl(Optional.of(NORMAL_DISTANCE_FIGHTER_GOLD), Optional.empty(), Optional.empty()));
        costMap.put(UnitType.HERO_CLOSE_FIGHTER, new BasicCostImpl(Optional.of(HERO_CLOSE_FIGHTER_GOLD),
                Optional.of(HERO_CLOSE_FIGHTER_WOOD), Optional.empty()));
        costMap.put(UnitType.HERO_DISTANCE_FIGHTER, new BasicCostImpl(Optional.of(HERO_DISTANCE_FIGHTER_GOLD),
                Optional.of(HERO_DISTANCE_FIGHTER_WOOD), Optional.empty()));
        return costMap;
    }

}
