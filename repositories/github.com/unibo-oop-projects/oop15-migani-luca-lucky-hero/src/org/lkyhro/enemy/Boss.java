package org.lkyhro.enemy;

import org.lkyhro.item.Equipment;
import org.lkyhro.item.EquipmentSingleton;

import java.util.Random;
import java.util.Set;

/**
 * Created by Migani Luca on 11/01/2016.
 */
public class Boss extends AbstractEnemy {

    private int bossLevel;

    /**
     * Constructor method for Boss
     * @param name String name of the boss
     * @param attack int attack value of the boss
     * @param defense int defense value of the boss
     * @param healthPoints int amount of boss' hp
     * @param givenExperience int amount of experience given by defeating the boss
     * @param bossLevel int level of the boss
     */
    public Boss(String name ,int attack, int defense, int healthPoints, int givenExperience, int bossLevel){
        super(name, attack, defense ,healthPoints, givenExperience);
        this.bossLevel=bossLevel;
    }

    /**
     * Method used for randomly pick up an Equipment from a pool of equipments of a certain rarity.
     * The rarity is influenced by the BossLevel.
     * @return Equipment randomly selected from a pool of equipments
     */
    @Override
    public Equipment dropItem() {
        EquipmentSingleton equipmentList=EquipmentSingleton.getInstance();
        Set<Equipment> dropSet=equipmentList.selectedItems(bossLevel);
        int item=new Random().nextInt(dropSet.size());
        int i=0;
        Equipment drop=null;
        for(Equipment e : dropSet){
            if(i==item){
                drop=e;
            }
            i=i+1;
        }
        return drop;
    }

    /**
     *
     * @return int Level of the boss
     */
    public int getBossLevel() {
        return bossLevel;
    }

    /**
     *
     * @return String representation of the Boss
     */
    @Override
    public String toString() {
        return "org.lkyhro.enemy.Boss{" +
                "bossGrade=" + bossLevel +
                "} " + super.toString();
    }
}

