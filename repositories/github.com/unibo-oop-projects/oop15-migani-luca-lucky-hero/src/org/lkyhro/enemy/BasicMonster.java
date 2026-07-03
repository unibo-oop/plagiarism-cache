package org.lkyhro.enemy;

import org.lkyhro.item.ConsumableItem;
import org.lkyhro.item.ItemSingleton;

import java.util.*;

/**
 * Created by  Migani Luca on 23/09/2015.
 */
public class BasicMonster extends AbstractEnemy {
    private int level;

    /**
     * Constructor method for BasicMonster, given name and level, the other properties are generated randomly
     * according to the level
     * @param name String name of the monster
     * @param lvl int level of the monster
     */
    public BasicMonster(String name, int lvl){
        super(name, randByLevel(lvl), randByLevel(lvl), (lvl*50)/2, lvl*20);
        this.level=lvl;
    }

    private static int randByLevel(int lvl){
        int min=(lvl*10)-10;
        int max=lvl*10;
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    /**
     * Method used to pickup a random item from a pool of item of a certain rarity,
     * which will be dropped by the monster after being defeated.
     * The rarity is influenced by the level of the monster.
     * @return ConsumableItem selected by level of the monster
     */
    @Override
    public ConsumableItem dropItem() {
        ItemSingleton itemList=ItemSingleton.getInstance();
        Set<ConsumableItem> dropSet;
        int rarity=0;
        if(this.level==1){
            rarity=1;
        }else if(this.level==2){
            rarity=2;
        }else if(this.level==3){
            rarity=3;
        }
        dropSet=itemList.selectedItems(rarity);
        int size=dropSet.size();
        int item=new Random().nextInt(size);
        int i=0;
        ConsumableItem drop=null;
        for(ConsumableItem c : dropSet){
            if(i==item){
                drop=c;
            }
            i=i+1;
        }

        return drop;
    }

    /**
     *
     * @return String representation of BasicMonster
     */
    @Override
    public String toString() {
        return "org.lkyhro.enemy.BasicMonster{" +
                "level=" + level +
                "} " + super.toString();
    }
}
