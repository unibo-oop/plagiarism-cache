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
 * The ElvesRace is a class that extends RaceImpl. It represent the elves race.
 * They will have units that cost a little more than the average. The close
 * units will have high hp, while the distance units will have a greater
 * attack range.
 */
public class ElvesRace extends RaceImpl {

    private static final int BASIC_CLOSE_FIGHTER_STR = -7;
    private static final int BASIC_CLOSE_FIGHTER_HP = +10;
    private static final int BASIC_CLOSE_FIGHTER_GOLD = +10;
    private static final int BASIC_CLOSE_FIGHTER_WOOD = -10;

    private static final int BASIC_DISTANCE_FIGHTER_STR = +5;
    private static final int BASIC_DISTANCE_FIGHTER_HP = -3;
    private static final int BASIC_DISTANCE_FIGHTER_ATT_RANGE = +1;
    private static final int BASIC_DISTANCE_FIGHTER_GOLD = +10;
    private static final int BASIC_DISTANCE_FIGHTER_WOOD = +30;

    private static final int NORMAL_CLOSE_FIGHTER_STR = -5;
    private static final int NORMAL_CLOSE_FIGHTER_HP = +15;
    private static final int NORMAL_CLOSE_FIGHTER_GOLD = +10;
    private static final int NORMAL_CLOSE_FIGHTER_WOOD = +25;
    private static final int NORMAL_CLOSE_FIGHTER_POP = +1;

    private static final int NORMAL_DISTANCE_FIGHTER_STR = -5;
    private static final int NORMAL_DISTANCE_FIGHTER_ATT_RANGE = +1;
    private static final int NORMAL_DISTANCE_FIGHTER_GOLD = +20;
    private static final int NORMAL_DISTANCE_FIGHTER_WOOD = +20;

    private static final int HERO_CLOSE_FIGHTER_STR = +20;
    private static final int HERO_CLOSE_FIGHTER_HP = +20;
    private static final int HERO_CLOSE_FIGHTER_MOV_RANGE = -2;
    private static final int HERO_CLOSE_FIGHTER_POSS_ATT = -1;
    private static final int HERO_CLOSE_FIGHTER_GOLD = +60;
    private static final int HERO_CLOSE_FIGHTER_WOOD = +60;

    private static final int HERO_DISTANCE_FIGHTER_STR = +5;
    private static final int HERO_DISTANCE_FIGHTER_ATT_RANGE = +2;
    private static final int HERO_DISTANCE_FIGHTER_WOOD = +20;

    /**
     * Elves race constructor.
     */
    public ElvesRace() {
        super("Elves", getStatsBoostMap(), getCostBoostMap());
    }

    /**
     * This method is used to create the stats boost map. The order for the boost
     * is: strength boost, hp, movement range, attack range, possible attacks.
     * 
     * @return boostMap the stats boost map.
     */
    private static Map<UnitType, List<Optional<Integer>>> getStatsBoostMap() {
        final Map<UnitType, List<Optional<Integer>>> boostMap = new HashMap<>();
        boostMap.put(UnitType.CLOSE_BASIC, Arrays.asList(Optional.of(BASIC_CLOSE_FIGHTER_STR),
                Optional.of(BASIC_CLOSE_FIGHTER_HP), Optional.empty(), Optional.empty(), Optional.empty()));
        boostMap.put(UnitType.DISTANCE_BASIC,
                Arrays.asList(Optional.of(BASIC_DISTANCE_FIGHTER_STR), Optional.of(BASIC_DISTANCE_FIGHTER_HP),
                        Optional.empty(), Optional.of(BASIC_DISTANCE_FIGHTER_ATT_RANGE), Optional.empty()));
        boostMap.put(UnitType.CLOSE_NORMAL, Arrays.asList(Optional.of(NORMAL_CLOSE_FIGHTER_STR),
                Optional.of(NORMAL_CLOSE_FIGHTER_HP), Optional.empty(), Optional.empty(), Optional.empty()));
        boostMap.put(UnitType.DISTANCE_NORMAL, Arrays.asList(Optional.of(NORMAL_DISTANCE_FIGHTER_STR), Optional.empty(),
                Optional.empty(), Optional.of(NORMAL_DISTANCE_FIGHTER_ATT_RANGE), Optional.empty()));
        boostMap.put(UnitType.HERO_CLOSE_FIGHTER,
                Arrays.asList(Optional.of(HERO_CLOSE_FIGHTER_STR), Optional.of(HERO_CLOSE_FIGHTER_HP),
                        Optional.of(HERO_CLOSE_FIGHTER_MOV_RANGE), Optional.empty(),
                        Optional.of(HERO_CLOSE_FIGHTER_POSS_ATT)));
        boostMap.put(UnitType.HERO_DISTANCE_FIGHTER, Arrays.asList(Optional.of(HERO_DISTANCE_FIGHTER_STR),
                Optional.empty(), Optional.empty(), Optional.of(HERO_DISTANCE_FIGHTER_ATT_RANGE), Optional.empty()));
        return boostMap;
    }

    private static Map<UnitType, Cost> getCostBoostMap() {
        final Map<UnitType, Cost> costMap = new HashMap<>();
        costMap.put(UnitType.CLOSE_BASIC, new BasicCostImpl(Optional.of(BASIC_CLOSE_FIGHTER_GOLD),
                Optional.of(BASIC_CLOSE_FIGHTER_WOOD), Optional.empty()));
        costMap.put(UnitType.DISTANCE_BASIC, new BasicCostImpl(Optional.of(BASIC_DISTANCE_FIGHTER_GOLD),
                Optional.of(BASIC_DISTANCE_FIGHTER_WOOD), Optional.empty()));
        costMap.put(UnitType.CLOSE_NORMAL, new BasicCostImpl(Optional.of(NORMAL_CLOSE_FIGHTER_GOLD),
                Optional.of(NORMAL_CLOSE_FIGHTER_WOOD), Optional.of(NORMAL_CLOSE_FIGHTER_POP)));
        costMap.put(UnitType.DISTANCE_NORMAL, new BasicCostImpl(Optional.of(NORMAL_DISTANCE_FIGHTER_GOLD),
                Optional.of(NORMAL_DISTANCE_FIGHTER_WOOD), Optional.empty()));
        costMap.put(UnitType.HERO_CLOSE_FIGHTER, new BasicCostImpl(Optional.of(HERO_CLOSE_FIGHTER_GOLD),
                Optional.of(HERO_CLOSE_FIGHTER_WOOD), Optional.empty()));
        costMap.put(UnitType.HERO_DISTANCE_FIGHTER,
                new BasicCostImpl(Optional.empty(), Optional.of(HERO_DISTANCE_FIGHTER_WOOD), Optional.empty()));
        return costMap;
    }
}
