package model.achievements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.construction.ConstructionType;
import model.demand.DemandFactory;
import model.demand.DemandFactoryImpl;
import model.resources.ResourceType;


/**
 * This class is used to create the achievements at the start of the game.
 */
public final class AchievementCreatorUtils {

    private AchievementCreatorUtils() {
    }

    /**
     * The method create the reachable achievement during the game.
     * @return
     *          list of reachable achievements
     */
    public static List<Achievement> createListOfAchievements() {
        final List<Achievement> achievements = new ArrayList<>();
        final DemandFactory demandCreator = new DemandFactoryImpl();
        final AchievementFactory achievementCreator = new AchievementFactoryImpl();
        final Map<ResourceType, Integer> resTmp = new HashMap<>();
        final Map<ConstructionType, Integer> bldTmp = new HashMap<>();

        /*
         * Creation of "Città popolata" achievement
         */
        resTmp.put(ResourceType.PEOPLE, Integer.valueOf(100));
        achievements.add(achievementCreator.createAchievement("Città popolata",
                demandCreator.createDemandOfResources(resTmp.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))),
                AchievementType.RESOURCES));
        /*
         * Creation of "Servizi per tutti!" achievement
         */
        bldTmp.put(ConstructionType.OSPEDALE, Integer.valueOf(1));
        bldTmp.put(ConstructionType.POLIZIA, Integer.valueOf(1));
        bldTmp.put(ConstructionType.POMPIERI, Integer.valueOf(1));
        achievements.add(achievementCreator.createAchievement("Servizi per tutti!",
                demandCreator.createDemandOfBuildings(bldTmp.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))),
                AchievementType.BUILDINGS));

        /*
         * Creation of "Sano come un pesce" achievement
         */
        bldTmp.clear();
        bldTmp.put(ConstructionType.OSPEDALE, Integer.valueOf(1));
        achievements.add(achievementCreator.createAchievement("Sano come un pesce",
                demandCreator.createDemandOfBuildings(bldTmp.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))),
                AchievementType.BUILDINGS));

        /*
         * Creation of "Città sicura" achievement
         */
        bldTmp.clear();
        bldTmp.put(ConstructionType.POLIZIA, Integer.valueOf(1));
        achievements.add(achievementCreator.createAchievement("Città sicura",
                demandCreator.createDemandOfBuildings(bldTmp.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))),
                AchievementType.BUILDINGS));

        /*
         * Creation of "Benvenuti a Las Vegas!" achievement
         */
        bldTmp.clear();
        bldTmp.put(ConstructionType.CASINO, Integer.valueOf(3));
        achievements.add(achievementCreator.createAchievement("Benvenuti a Las Vegas!",
                demandCreator.createDemandOfBuildings(bldTmp.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))),
                AchievementType.BUILDINGS));

        return achievements;
    }
}
