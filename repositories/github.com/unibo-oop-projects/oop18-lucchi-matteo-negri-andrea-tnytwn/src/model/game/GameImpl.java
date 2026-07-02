package model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;
import model.achievements.Achievement;
import model.achievements.AchievementCreatorUtils;
import model.buildingscounter.BuildingsCounter;
import model.buildingscounter.BuildingsCounterImpl;
import model.construction.Construction;
import model.gamemap.GameMap;
import model.gamemap.GameMapImpl;
import model.messages.AchievementsUnlock;
import model.messages.FewWorkersError;
import model.messages.NotEnoughMoneyError;
import model.messages.NotEnoughWorkersError;
import model.messages.SpaceNotFreeError;
import model.resources.ResourceImpl;
import model.resources.Resource;
import model.resources.ResourceType;

/**
 * This class implements the interface Game.
 * The Singleton template is used.
 */
public final class GameImpl implements Game {

    /**
     * The only instance of Singleton GameImpl.
     */
    private static GameImpl instance;

    /**
     * The current map.
     */
    private final GameMap map;

    /**
     * The wealth of the city.
     */
    private final Resource money;

    /**
     * The amount of water units.
     */
    private final Resource water;

    /**
     * The amount of electric energy units.
     */
    private final Resource energy;

    /**
     * The current number of people living in the city.
     */
    private final Resource population;

    /**
     * The current number of free workers in the city.
     */
    private final Resource freeWorkers;

    /**
     * The time passed since the beginning of the game.
     */
    private final Timer time;

    /**
     * The daily money income.
     */
    private SimpleIntegerProperty moneyIncome;

    /**
     * The daily money cost.
     */
    private SimpleIntegerProperty moneyCost;

    /**
     * The daily water income.
     */
    private SimpleIntegerProperty waterIncome;

    /**
     * The daily water cost.
     */
    private SimpleIntegerProperty waterCost;

    /**
     * The daily energy income.
     */
    private SimpleIntegerProperty energyIncome;

    /**
     * The daily energy cost.
     */
    private SimpleIntegerProperty energyCost;

    /**
     * The duration of single day in milliseconds.
     */
    public static final int DAY_DURATION = 60000;

    /**
     * The initial value of the money Resource.
     */
    public static final int START_MONEY = 4000;

    /**
     * The initial value of the water Resource.
     */
    public static final int START_WATER = 100;

    /**
     * The initial value of the energy Resource.
     */
    public static  final int START_ENERGY = 100;

    /**
     * How many days have passed in the current Game.
     */
    private Integer days;

    /**
     * The counter is used to maintain the number of every type of building.
     */
    private final BuildingsCounter counter;

    /**
     * The list of achievements.
     */
    private final List<Achievement> achievements;

    /**
     * The constructor used just at first use.
     */
    private GameImpl() {
        this.money = new ResourceImpl(ResourceType.MONEY, START_MONEY);
        this.water = new ResourceImpl(ResourceType.WATER, START_WATER);
        this.energy = new ResourceImpl(ResourceType.ENERGY, START_ENERGY);
        this.population = new ResourceImpl(ResourceType.PEOPLE, 0);
        this.freeWorkers = new ResourceImpl(ResourceType.PEOPLE, 0);
        this.map = new GameMapImpl();
        this.counter = new BuildingsCounterImpl();
        this.achievements = AchievementCreatorUtils.createListOfAchievements();
        this.moneyIncome = new SimpleIntegerProperty(0);
        this.moneyCost = new SimpleIntegerProperty(0);
        this.waterIncome = new SimpleIntegerProperty(0);
        this.waterCost = new SimpleIntegerProperty(0);
        this.energyIncome = new SimpleIntegerProperty(0);
        this.energyCost = new SimpleIntegerProperty(0);
        this.days = 0;
        this.time = new Timer();
        this.time.scheduleAtFixedRate(new DaysTimerTask(this), 0, DAY_DURATION);
    }

    /**
     * This class is used for the Daily Tasks of the Game.
     */
    public class DaysTimerTask extends TimerTask {

        /**
         * A reference to the current Game.
         */
        private final GameImpl currentGame;

        /**
         * Default constructor.
         * @param cGame
         *      A reference to the current Game.
         */
        DaysTimerTask(final GameImpl cGame) {
            super();
            this.currentGame = cGame;
        }

        /**
         * This method manages all the events that occur in one day of the Game.
         */
        @Override
        public void run() {
            try {
                this.currentGame.money.decrease(this.currentGame.moneyCost.get());
                this.currentGame.money.add(this.currentGame.moneyIncome.get());
                this.currentGame.water.decrease(this.currentGame.waterCost.get());
                this.currentGame.water.add(this.currentGame.waterIncome.get());
                this.currentGame.energy.decrease(this.currentGame.energyCost.get());
                this.currentGame.energy.add(this.currentGame.energyIncome.get());
                this.currentGame.days++;
            } catch (IllegalArgumentException e) {
                this.currentGame.time.cancel();
                Runtime.getRuntime().exit(0);
            }
        }
    }

    /**
     * @return the timer used for the daily updates to the Resources.
     */
    public Timer getTime() {
        return this.time;
    }

    /**
     * At first use creates Game Singleton.
     * @return Game
     *          the Game Singleton.
     */
    public static GameImpl getGameImpl() {
        synchronized (GameImpl.class) {
            if (instance == null) {
                instance = new GameImpl();
            }
        }
        return instance; 
    }

    /*
     * (non-Javadoc)
     * @see model.game.Game#getMap()
     */
    @Override
    public GameMap getMap() {
        return this.map;
    }

    /*
     * (non-Javadoc)
     * @see model.game.Game#getResources()
     */
    @Override
    public List<Resource> getResources() {
        final List<Resource> res = new ArrayList<>();
        res.add(this.money);
        res.add(this.water);
        res.add(this.energy);
        return res;
    }

    /*
     * (non-Javadoc)
     * @see model.game.Game#createBuilding(model.building.Construction, javafx.util.Pair)
     */
    @Override
    public boolean createBuilding(final Construction build, final Pair<Integer, Integer> position) {
        if (this.map.isBuilding(position)) {
            if (this.money.canBeDecreased(build.getType().getCost())) {
                if (build.getType().getPopulationHousingCapacity() < 0
                        && !this.freeWorkers.canBeDecreased(-build.getType().getPopulationHousingCapacity())) {
                    new NotEnoughWorkersError(build.getType().getPopulationHousingCapacity(), this.getFreeWorkers().getValue());
                } else {
                    if (build.getType().getPopulationHousingCapacity() >= 0) {
                        this.population.add(build.getType().getPopulationHousingCapacity());
                        this.freeWorkers.add(build.getType().getPopulationHousingCapacity());
                    } else {
                        this.freeWorkers.decrease(-build.getType().getPopulationHousingCapacity());
                    }
                    this.money.decrease(build.getType().getCost());
                    this.map.addBuilding(build);
                    if (build.getType().getMoneyProduction() >= 0) {
                        this.moneyIncome = new SimpleIntegerProperty(this.moneyIncome.get() + build.getType().getMoneyProduction().intValue());
                    } else {
                        this.moneyCost = new SimpleIntegerProperty(this.moneyIncome.get() - build.getType().getMoneyProduction().intValue());
                    }
                    if (build.getType().getWaterInfluence() > 0) {
                        this.waterIncome = new SimpleIntegerProperty(this.waterIncome.get() + build.getType().getWaterInfluence().intValue());
                    } else {
                        this.waterCost = new SimpleIntegerProperty(this.waterCost.get() - build.getType().getWaterInfluence().intValue());
                    }
                    if (build.getType().getEnergyInfluence() > 0) {
                        this.energyIncome = new SimpleIntegerProperty(this.energyIncome.get() + build.getType().getEnergyInfluence().intValue());
                    } else {
                        this.energyCost = new SimpleIntegerProperty(this.energyCost.get() - build.getType().getEnergyInfluence().intValue());
                    }
                    this.counter.addBuilding(build.getType());
                    for (final Achievement a : this.achievements) {
                        if (!a.isUnlock()) {
                            a.update(this);
                            if (a.isUnlock()) {
                                new AchievementsUnlock(a.getName());
                            }
                        }
                    }
                    return true;
                }
            } else {
                new NotEnoughMoneyError(build.getType().getCost(), this.getResources().get(0).getValue());
            }
        } else {
            new SpaceNotFreeError();
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see model.game.Game#demolishBuilding(model.building.Construction)
     */
    @Override
    public void demolishBuilding(final Construction build) {
        if (build.getType().getPopulationHousingCapacity() >= 0
                && this.freeWorkers.canBeDecreased(build.getType().getPopulationHousingCapacity())) {
            this.population.decrease(build.getType().getPopulationHousingCapacity());
            this.freeWorkers.decrease(build.getType().getPopulationHousingCapacity());
            this.money.add(build.getType().getCost() / 2);
            if (build.getType().getMoneyProduction() > 0) {
                this.moneyIncome.add(-build.getType().getMoneyProduction());
            } else {
                this.moneyCost.add(build.getType().getMoneyProduction());
            }
            if (build.getType().getWaterInfluence() > 0) {
                this.waterIncome.add(-build.getType().getWaterInfluence());
            } else {
                this.waterCost.add(build.getType().getWaterInfluence());
            }
            if (build.getType().getEnergyInfluence() > 0) {
                this.energyIncome.add(-build.getType().getEnergyInfluence());
            } else {
                this.energyCost.add(build.getType().getEnergyInfluence());
            }
            this.map.deleteBuilding(build);
            this.counter.removeBuilding(build.getType());
        } else if (build.getType().getPopulationHousingCapacity() > 0
                && !this.freeWorkers.canBeDecreased(build.getType().getPopulationHousingCapacity())) {
            new FewWorkersError();
        } else if (build.getType().getPopulationHousingCapacity() < 0) {
            this.freeWorkers.add(-build.getType().getPopulationHousingCapacity());
            this.money.add(build.getType().getCost() / 2);
            if (build.getType().getMoneyProduction() > 0) {
                this.moneyIncome.add(-build.getType().getMoneyProduction());
            } else {
                this.moneyCost.add(build.getType().getMoneyProduction());
            }
            if (build.getType().getWaterInfluence() > 0) {
                this.waterIncome.add(-build.getType().getWaterInfluence());
            } else {
                this.waterCost.add(build.getType().getWaterInfluence());
            }
            if (build.getType().getEnergyInfluence() > 0) {
                this.energyIncome.add(-build.getType().getEnergyInfluence());
            } else {
                this.energyCost.add(build.getType().getEnergyInfluence());
            }
            this.map.deleteBuilding(build);
            this.counter.removeBuilding(build.getType());
        }
    }

    /*
     * (non-Javadoc)
     * @see model.game.Game#getDailyIncomes()
     */
    @Override
    public List<Integer> getDailyIncomes() {
        final List<Integer> inc = new ArrayList<>();
        inc.add(this.moneyIncome.get());
        inc.add(this.waterIncome.get());
        inc.add(this.energyIncome.get());
        return inc;
    }

    /*
     * (non-Javadoc)
     * @see model.game.Game#getDailyCosts()
     */
    @Override
    public List<Integer> getDailyCosts() {
        final List<Integer> cos = new ArrayList<>();
        cos.add(this.moneyCost.get());
        cos.add(this.waterCost.get());
        cos.add(this.energyCost.get());
        return cos;
    }

    /*
     * (non-Javadoc)
     * @see model.game.Game#getCityPopulation()
     */
    @Override
    public Resource getCityPopulation() {
        return this.population;
    }

    /*
     * (non-Javadoc)
     * @see model.game.Game#getGameDays()
     */
    @Override
    public Integer getGameDays() {
        return this.days;
    }

    /*
     * (non-Javadoc)
     * @see model.game.Game#getBuildingsCounter()
     */
    @Override
    public BuildingsCounter getBuildingsCounter() {
        return this.counter;
    }

    /*
     * (non-Javadoc)
     * @see model.game.Game#getAchievements()
     */
    @Override
    public List<Achievement> getAchievements() {
        return Collections.unmodifiableList(this.achievements);
    }

    /**
     * @return the Resource freeWorkers, that indicates the number of free workers of the city.
     */
    public Resource getFreeWorkers() {
        return this.freeWorkers;
    }
}
