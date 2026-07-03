package org.lkyhro.enemy;

import java.util.*;

/**
 * Created by Migani Luca on 24/02/2016.
 */
public class BossSingleton {

    private static BossSingleton myInstance;
    private Set<Boss> bossList=new HashSet<>();

    /**
     * Constructor method for the Singleton of Bosses.
     *
     */
    public BossSingleton(){
        this.bossList.add(new Boss("Ciclope Furioso", 10,15,50,100,1));
        this.bossList.add(new Boss("Servitore Oscuro", 20,20,80,150,2));
        this.bossList.add(new Boss("Re dei Goblin", 30,30,100,200,3));
    }

    /**
     *
     * @return an Instance of BossSingleton
     */
    public static BossSingleton getMyInstance(){
        if(myInstance==null){
            myInstance=new BossSingleton();
        }
        return myInstance;
    }

    /**
     * Randomly select a boss fram the pool of bosses given the level which the monster should be
     * @param lvl int level for select a boss from the pool
     * @return Boss of the level selected
     */
    public Boss selectBoss (int lvl){
        Set<Boss> selectedBosses=new HashSet<>();
        for (Boss b:bossList) {
            if(b.getBossLevel()==lvl){
                selectedBosses.add(b);
            }
        }
        int size=selectedBosses.size();
        int item=new Random().nextInt(size);
        int i=0;
        for(Boss b:selectedBosses){
            if (i==item){
                return b;
            }
            i+=1;
        }
        return null;
    }
}
