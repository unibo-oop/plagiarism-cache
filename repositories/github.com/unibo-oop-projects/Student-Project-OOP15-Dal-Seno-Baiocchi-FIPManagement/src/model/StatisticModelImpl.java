package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Model a StatisticModel
 * @author lucadalseno
 *
 */
public class StatisticModelImpl implements StatisticModel {
    
    Map<Player, Statistics> statMap;
    
    public  StatisticModelImpl() {
        this.statMap = new HashMap<Player, Statistics>();
    }

    @Override
    public void addStatistic(Player p, Statistics s) {
        statMap.put(p, s);
    }

    @Override
    public Statistics getStatistic(Player p) {
        return statMap.get(p);
    }

    @Override
    public void applyStatistic() {
        for(Entry<Player, Statistics> e: statMap.entrySet()){
            e.getKey().addStat(e.getValue());
        }
    }
}