package org.lkyhro;

import org.lkyhro.enemy.Enemy;
import org.lkyhro.item.ItemType;
import org.lkyhro.item.Item;

import java.util.*;

/**
 * Created by Migani Luca on 24/09/2015.
 * Class used to handle battles between the hero and his enemies
 */
public class Encounter {
    private Hero hero;
    private Enemy enemy;
    private int bHHp;
    private int bMHP;
    private String actions;
    private boolean critical;
    private EncounterObserver observer;

    /**
     *
     * @param hero the hero used by the player
     * @param enemy an Interface which is used to identify BasicMonsters and Bosses
     */
    public Encounter(Hero hero, Enemy enemy){
        this.hero=hero;
        this.enemy=enemy;
        this.bHHp=hero.getHealthPoint();
        this.bMHP=enemy.getHealthPoints();
        this.actions="Battle Started\n";
        this.critical=false;
    }

    /**
     * Method used to manage the fight part of the battle.
     * It uses attackOrder() to decide which of the two fighters attacks first and for dealing damages,
     * then if one of the two fighters' HP is equal or below zero, it declares the fight ended, by giving the
     * encounterObserver ending condition
     */
    public void fight(){
        actions="";
        attackOrder();
        if(this.bHHp<=0){
            this.bHHp=0;
            this.encounterEnd(EndingCondition.DEFEAT);
        }else if(this.bMHP<=0){
            this.bMHP=0;
            this.encounterEnd(EndingCondition.VICTORY);
        }
    }

    /**
     * Method used to manage the use of items in the battle.
     * First there is a search in the hero's inventory to find the item, if it is an HEALING type some of the hero's hp,
     * indicated by bHHp, will be regenerated, otherwise if it is a THROWABLE type some of the enemy's HP,
     * indicated by BMHp, will be damaged.
     * If the number of that item possessed by the hero, nOwnedObj, is more than 1 it will be decreased by 1,
     * if it is 1 the item will be removed from the inventory.
     * After the use of the object, if the isLucky() method of the hero return false, the enemy will attack
     * @param str name of the item used
     */
    public void useItem(String str){
        Map<Item, Integer> usableItems=this.hero.getInventory();
        int nOwnedObj=-1;
        Item obj=null;
        for(Map.Entry<Item, Integer> entry: usableItems.entrySet()){
            if(entry.getKey().getName().equals(str)){
                obj=entry.getKey();
                nOwnedObj=entry.getValue();
            }
        }
        if(obj==null){
            throw new IllegalArgumentException();
        }
        if(obj.getType()== ItemType.HEALING){
            this.bHHp+=obj.getValue();
            if (bHHp>hero.getHealthPoint()){
                bHHp=hero.getHealthPoint();
            }
            actions=hero.getName()+" cured himself of "+obj.getValue()+" points!";
        }else if(obj.getType()== ItemType.THROWABLE){
            this.bMHP-=obj.getValue();
            if (this.bMHP<=0){
                this.bMHP=0;
                encounterEnd(EndingCondition.VICTORY);
            }
            actions=hero.getName()+" damaged his enemy of "+obj.getValue()+" points!\n";
        }else{
            System.out.printf("error");
        }
        if(nOwnedObj==1){
            usableItems.remove(obj);
        }else if(nOwnedObj>1){
            usableItems.put(obj, nOwnedObj-1);
        }
        this.hero.setInventory(usableItems);
        if(!hero.isLucky()){
            this.bHHp-=this.EnemyAttack();
            if(this.bHHp<=0){
                this.bHHp=0;
                encounterEnd(EndingCondition.DEFEAT);
            }
        }
    }

    /**
     * This method is used to calculate how much damage is caused to the enemy by an hero's attack.
     * Damage is calculated by taking account of hero's attack, monster's defense and hero's luck, which
     * occasionally gives him a chance to deal a critical attack.
     * Actions is a String used to print the actions of the battle.
     * @return int the number of damages dealed by an hero's attack
     */
    private int HeroAttack(){
        int damage;
        if(this.hero.getBattleAttack()>this.enemy.getDefense()){
            if(this.hero.isLucky()){
                damage=(this.hero.getBattleAttack()-this.enemy.getDefense())*2;
                critical=true;
            }else {
                damage = this.hero.getBattleAttack() - this.enemy.getDefense();
            }
        }else if(this.hero.getBattleAttack()==this.enemy.getDefense()){
            if(this.hero.isLucky()){
                damage=this.hero.getBattleAttack();
                critical=true;
            }else{
                damage=this.hero.getBattleAttack()/2;
            }
        }else{
            if(this.hero.isLucky()){
                damage=5;
                critical=true;
            }else{
                damage=1;
            }
        }
        if (critical){
            actions+=this.hero.getName()+" landed a critical attack!\n";
            critical=false;
        }
        actions+=this.hero.getName() + " dealed "+damage+" damages to "+this.enemy.getName()+"\n";
        return damage;
    }

    /**
     *
     * @param observer for the encounter, used in MonsterBattle class
     */
    public void setObserver(EncounterObserver observer) {
        this.observer = observer;
    }

    /**
     * This method is used to calculate how much damage is caused to the hero by an enemy's attack.
     * Damage is calculated by taking account of enemy's attack and hero's defense.
     * Actions is a String used to print the actions of the battle.
     * @return int the number of damages dealed to the hero
     */
    private int EnemyAttack(){
        int damage;
        if(this.enemy.getAttack()>this.hero.getBattleDefense()){
            damage=this.enemy.getAttack()-this.hero.getBattleDefense();
            actions+=this.enemy.getName()+" dealed "+damage+" damages to "+this.hero.getName()+"\n";
            return damage;
        }else if (this.enemy.getAttack()==this.hero.getBattleDefense()){
            damage=this.enemy.getAttack()/2;
            actions+=this.enemy.getName()+" dealed "+damage+" damages to "+this.hero.getName()+"\n";
            return damage;
        }else{
            damage=1;
            actions+=this.enemy.getName()+" dealed "+damage+" damages to "+this.hero.getName()+"\n";
            return damage;
        }
    }

    /**
     * This method handle the consequences of the end of the battle, depending on the cause of the end.
     * The RUN cause will simply end the battle.
     * The VICTORY cause will give to the hero experience and a drop from the enemy, and in the adventure the
     * advancement of the hero.
     * The DEFEAT cause will end the battle, giving no experience or item, and also restarting the adventure for the hero.
     *
     * @param cause condition of an ending battle
     */
    private void encounterEnd(EndingCondition cause){
        switch (cause){
            case RUN:
                actions+="Escaped the battle, you coward!";
                System.out.println("Fuga");
                observer.encounterEnded(cause);
                break;
            case VICTORY:
                this.hero.giveExperience(this.enemy.getGivenExperience());
                Item itemDropped=this.enemy.dropItem();
                actions+="Enemy dropped "+itemDropped.getName()+"!\n";
                actions+="Victory\n";
                this.hero.pickUpItem(itemDropped);
                observer.encounterEnded(cause);
                break;
            case DEFEAT:
                actions+="\nDefeat!";
                System.out.println("Sconfitta");
                observer.encounterEnded(cause);
                break;
        }
    }

    /**
     * Based on the luck of the hero, this method will decide which one of the two contendants of the battle
     * will attack first.
     * If the first attack does not end the battle, the other contendant will also attack.
     */
    private void attackOrder(){
        if(this.hero.isLucky()){
            this.bMHP-=this.HeroAttack();
            if(bMHP>0) {
                this.bHHp -= this.EnemyAttack();
            }
        }else {
            this.bHHp-=this.EnemyAttack();
            if(bHHp>0){
                this.bMHP-=this.HeroAttack();
            }
        }
    }

    /**
     *
     * @return the number of hero's hp during the battle
     */
    public int getbHHp() {
        return bHHp;
    }

    /**
     *
     * @return the number of enemy's hp during the battle
     */
    public int getbMHP() {
        return bMHP;
    }

    /**
     *
     * @return String of the actions of the battle
     */
    public String getActions(){
        return actions;
    }

    /**
     * Method which calls the end of a battle by the RUN cause
     */
    public void run(){
        this.encounterEnd(EndingCondition.RUN);
    }

    /**
     * Enumeration of ending condition
     */
    public enum EndingCondition{
        VICTORY, DEFEAT, RUN
    }
}
