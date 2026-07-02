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
 * The HumansRace is a class that extends RaceImpl. It represent the humans
 * race. The humans race is the race where the various units are the most
 * balanced in terms of statistics.
 */
public class HumansRace extends RaceImpl {

    private static final int BASIC_CLOSE_FIGHTER_HP = +5;
    private static final int BASIC_CLOSE_FIGHTER_WOOD = +10;

    private static final int BASIC_DISTANCE_FIGHTER_STR = +2;

    private static final int NORMAL_CLOSE_FIGHTER_STR = +5;
    private static final int NORMAL_CLOSE_FIGHTER_POSSIBLE_ATT = -1;

    private static final int NORMAL_DISTANCE_FIGHTER_HP = +10;

    private static final int HERO_CLOSE_FIGHTER_HP = +20;
    private static final int HERO_CLOSE_FIGHTER_GOLD = +50;

    private static final int HERO_DISTANCE_FIGHTER_MOV_RANGE = +1;
    private static final int HERO_DISTANCE_FIGHTER_ATT_RANGE = -2;
    private static final int HERO_DISTANCE_FIGHTER_POSS_ATT = +1;

    /**
     * HumansRace constructor.
     */
    public HumansRace() {
        super("Humans", getStatsBoostMap(), getCostBoostMap());
    }

    /**
     * This method is used to create the stats boost map. The order for the boost
     * is: strength boost, hp, movement range, attack range, possible attacks.
     * 
     * @return boostMap the stats boost map.
     */
    private static Map<UnitType, List<Optional<Integer>>> getStatsBoostMap() {
        final Map<UnitType, List<Optional<Integer>>> boostMap = new HashMap<>();
        boostMap.put(UnitType.CLOSE_BASIC, Arrays.asList(Optional.empty(), Optional.of(BASIC_CLOSE_FIGHTER_HP),
                Optional.empty(), Optional.empty(), Optional.empty()));
        boostMap.put(UnitType.DISTANCE_BASIC, Arrays.asList(Optional.of(BASIC_DISTANCE_FIGHTER_STR), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty()));
        boostMap.put(UnitType.CLOSE_NORMAL, Arrays.asList(Optional.of(NORMAL_CLOSE_FIGHTER_STR), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.of(NORMAL_CLOSE_FIGHTER_POSSIBLE_ATT)));
        boostMap.put(UnitType.DISTANCE_NORMAL, Arrays.asList(Optional.empty(), Optional.of(NORMAL_DISTANCE_FIGHTER_HP),
                Optional.empty(), Optional.empty(), Optional.empty()));
        boostMap.put(UnitType.HERO_CLOSE_FIGHTER, Arrays.asList(Optional.empty(), Optional.of(HERO_CLOSE_FIGHTER_HP),
                Optional.empty(), Optional.empty(), Optional.empty()));
        boostMap.put(UnitType.HERO_DISTANCE_FIGHTER,
                Arrays.asList(Optional.empty(), Optional.empty(), Optional.of(HERO_DISTANCE_FIGHTER_MOV_RANGE),
                        Optional.of(HERO_DISTANCE_FIGHTER_ATT_RANGE), Optional.of(HERO_DISTANCE_FIGHTER_POSS_ATT)));
        return boostMap;
    }

    private static Map<UnitType, Cost> getCostBoostMap() {
        final Map<UnitType, Cost> costMap = new HashMap<>();
        costMap.put(UnitType.CLOSE_BASIC,
                new BasicCostImpl(Optional.empty(), Optional.of(BASIC_CLOSE_FIGHTER_WOOD), Optional.empty()));
        costMap.put(UnitType.DISTANCE_BASIC, new BasicCostImpl(Optional.empty(), Optional.empty(), Optional.empty()));
        costMap.put(UnitType.CLOSE_NORMAL, new BasicCostImpl(Optional.empty(), Optional.empty(), Optional.empty()));
        costMap.put(UnitType.DISTANCE_NORMAL, new BasicCostImpl(Optional.empty(), Optional.empty(), Optional.empty()));
        costMap.put(UnitType.HERO_CLOSE_FIGHTER,
                new BasicCostImpl(Optional.of(HERO_CLOSE_FIGHTER_GOLD), Optional.empty(), Optional.empty()));
        costMap.put(UnitType.HERO_DISTANCE_FIGHTER,
                new BasicCostImpl(Optional.empty(), Optional.empty(), Optional.empty()));
        return costMap;
    }

}
