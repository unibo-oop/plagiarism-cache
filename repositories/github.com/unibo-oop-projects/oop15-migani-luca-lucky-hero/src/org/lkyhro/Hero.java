package org.lkyhro;

import org.lkyhro.item.Equipment;
import org.lkyhro.item.ItemType;
import org.lkyhro.item.Item;

import java.util.*;

/**
 * Created by Migani Luca on 23/09/2015.
 */
public class Hero {
    private final int LUCKY_POINT = 50;
    private final int LUCK_RANGE = 75;
    private String name;
    private int healthPoint;
    private int attack;
    private int defense;
    private int experience;
    private int luck;
    private int level;
    private int expToNextLvl;
    private int levelExp;
    private int nBossSlayed;
    private Random rand=new Random();
    private Map<Item, Integer> inventory = new HashMap<>();
    private Equipment helm;
    private Equipment armor;
    private Equipment weapon;
    private Equipment shield;
    private Equipment gloves;
    private Equipment boots;

    private HeroObserver observer;

    /**
     * Constructor method used at the moment of a new game
     * @param name  String chosen for the hero
     */
    public Hero(String name){
        this.name=name;
        this.attack=5;
        this.defense=5;
        this.experience=0;
        this.healthPoint=20;
        this.level=1;
        this.luck=5;
        this.levelExp=10;
        this.nBossSlayed=0;
        this.expToNextLvl=this.levelExp-this.experience;
        this.armor=null;
        this.helm=null;
        this.boots=null;
        this.weapon=null;
        this.shield=null;
        this.gloves=null;
    }

    /**
     * Constructor method used at the moment of loading a previous game
     * @param name String name of the hero
     * @param attack int attack value of the hero
     * @param defense int defense value of the hero
     * @param experience int number of current experience possessed by the hero
     * @param healthPoint int number of health points of the hero
     * @param level int level of the hero
     * @param luck int luck value of the hero
     * @param levelExp int basic number for the next level
     * @param nBossSlayed int number of boss monsters killed
     * @param armor Equipment armor
     * @param helm Equipment helm
     * @param boots Equipment boots
     * @param weapon Equipment weapon
     * @param shield Equipment shield
     * @param gloves Equipment gloves
     * @param inventory Map used as inventory of the hero
     *     */
    public Hero(String name, int attack, int defense, int experience, int healthPoint, int level, int luck,
                int levelExp, int nBossSlayed, Equipment armor, Equipment helm, Equipment boots, Equipment weapon,
                Equipment shield, Equipment gloves, Map<Item, Integer> inventory){
        this.name=name;
        this.attack=attack;
        this.defense=defense;
        this.experience=experience;
        this.healthPoint=healthPoint;
        this.level=level;
        this.luck=luck;
        this.levelExp=levelExp;
        this.nBossSlayed=nBossSlayed;
        this.expToNextLvl=this.levelExp-this.experience;
        this.armor=armor;
        this.helm=helm;
        this.boots=boots;
        this.weapon=weapon;
        this.shield=shield;
        this.gloves=gloves;
        this.inventory=new HashMap<>(inventory);
    }

    /**
     * Method used for the regulation of level up by experience given from battles.
     * If the expPoints are enough to level up, a levelUpCounter, which will be given to a observer afterwards,
     * is increased, the number of expPoints is decreased by the number of experience that is necessary to level up,
     * named expToNextLevel.
     * levelExp is the base number for the next level, subtracted from this the current number of exp possessed by
     * the hero you are given the expToNextLevel variable.
     *
     *
     * @param expPoints number of experience given by defeating an enemy
     */
    public void giveExperience(int expPoints){
        int levelUpCounter=0;
        for(;expPoints>0;){
            if(expPoints>=this.expToNextLvl){
                expPoints-=expToNextLvl;
                levelUpCounter++;
                this.experience = this.levelExp;
                this.levelExp = (this.levelExp * 3) / 2;
                this.expToNextLvl = this.levelExp - this.experience;
            }else if(expPoints<this.expToNextLvl){
                this.experience+=expPoints;
                this.expToNextLvl-=expPoints;
                expPoints=0;
            }
        }
        if(levelUpCounter>0){
            observer.levelUp(levelUpCounter);
        }
    }

    /**
     * Add an item, dropped from an encounter, to the inventory of the hero.
     * if there is already an item with the same id in the inventory, the value of the item in the map is increased,
     * otherwise the new item is simply putted in the map
     * @param droppedItem item dropped by an enemy
     */
    public void pickUpItem(Item droppedItem){
        if(this.inventory.containsKey(droppedItem)){
            int value=this.inventory.get(droppedItem);
            value++;
            this.inventory.put(droppedItem, value);
        }else{
            this.inventory.put(droppedItem, 1);
        }
    }

    /**
     *  Delete a certain item from the inventory. If there is only one copy of that item,
     *  it will be deleted from the map, otherwise its value will be decremented by one.
     *  If the item is an Equipment, this method will check if the Equipment is attached to the Hero
     *  and delete its effects.
     *
     * @param item item chosen to be deleted
     */
    public void deleteItem(Item item){
        if(inventory.containsKey(item)){
            int value=inventory.get(item);
            if (value>1){
                value--;
                inventory.put(item,value);
            }else{
                inventory.remove(item);
                if (item.getType()!= ItemType.HEALING && item.getType()!= ItemType.THROWABLE){
                    switch (item.getType()) {
                        case HELM:
                            if (this.helm!=null && this.helm.getName().equals(item.getName())){
                                this.helm=null;
                            }
                        case BOOTS:
                            if (this.boots!=null && this.boots.getName().equals(item.getName())){
                                this.boots=null;
                            }
                            break;
                        case GLOVES:
                            if (this.gloves!=null && this.gloves.getName().equals(item.getName())){
                                this.gloves=null;
                            }
                            break;
                        case ARMOR:
                            if (this.armor!=null && this.armor.getName().equals(item.getName())){
                                this.armor=null;
                            }
                            break;
                        case SHIELD:
                            if (this.shield!=null && this.shield.getName().equals(item.getName())){
                                this.shield=null;
                            }
                            break;
                        case WEAPON:
                            if (this.weapon!=null && this.weapon.getName().equals(item.getName())){
                                this.weapon=null;
                            }
                            break;
					case HEALING:
						break;
					case THROWABLE:
						break;
                    }
                }
            }
        }
    }

    /**
     * Equip a certain equipment from the inventory.
     * First it checks if the equipment passed is effectively an equipment,
     * then it simply equip it to the hero.
     *
     * @param equipment equipment chosen to be equipped
     */
    public void equipItem(Equipment equipment){
        if (equipment.getType()!= ItemType.HEALING && equipment.getType()!= ItemType.THROWABLE){
            switch (equipment.getType()) {
                case HELM:
                    this.helm = equipment;
                    break;
                case BOOTS:
                    this.boots = equipment;
                    break;
                case GLOVES:
                    this.gloves = equipment;
                    break;
                case ARMOR:
                    this.armor = equipment;
                    break;
                case SHIELD:
                    this.shield = equipment;
                    break;
                case WEAPON:
                    this.weapon = equipment;
                    break;
                case HEALING:
                	break;
                case THROWABLE:
                	break;
            }
        }
    }

    /**
     * Simply increase the number of boss killed.
     */
    public void bossSlayed(){
        this.nBossSlayed++;
    }

    /**
     * Method used to increase the capability of the Hero.
     * It is invoked by an Observer in the MonsterBattle class.
     * @param str String passed to decide which capability increase
     */
    public void levelUp(String str){
        this.level++;
        this.healthPoint += 5;
        switch (str) {
            case "atk":
                this.attack += 2;
                break;
            case "def":
                this.defense += 2;
                break;
            case "luk":
                this.luck++;
                break;
            }
    }

    /**
     *
     * @return basic attack of the hero, without counting equipment addition
     */
    public int getAttack(){
        return this.attack;
    }

    /**
     *
     * @return int value of attack of the hero plus eventual addition from the equipment
     */
    public int getBattleAttack(){
        int battleAttack=this.attack;
        if (this.weapon!=null){
            battleAttack+=this.weapon.getValue();
        }
        return battleAttack;
    }

    /**
     *
     * @return basic defense of the hero, without counting equipment addition
     */
    public int getDefense() {
        return defense;
    }

    /**
     *
     * @return int value of defense of the hero plus eventual addition from the equipment
     */
    public int getBattleDefense(){
        int battleDefense=this.defense;
        if (this.helm!=null){
            battleDefense+=this.helm.getValue();
        }
        if (this.armor!=null){
            battleDefense+=this.armor.getValue();
        }
        if (this.gloves!=null){
            battleDefense+=this.gloves.getValue();
        }
        if (this.boots!=null){
            battleDefense+=this.boots.getValue();
        }
        if (this.shield!=null){
            battleDefense+=this.shield.getValue();
        }
        return battleDefense;
    }

    /**
     *
     * @return current number of Experience possessed by the hero
     */
    public int getExperience() {
        return experience;
    }

    /**
     *
     * @return current level of the hero
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @return number of experience needed for the next level up,
     *  calculated by subtracting from levelExp the current experience of the hero
     */
    public int getExpToNextLvl() {
        return expToNextLvl;
    }

    /**
     *
     * @return basic quantity of experience for the next level
     */
    public int getLevelExp() {
        return levelExp;
    }

    /**
     *
     * @return name of the hero
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return number of total health points of the hero
     */
    public int getHealthPoint() {
        return healthPoint;
    }

    /**
     *
     * @return value of luck possessed by the hero
     */
    public int getLuck() {
        return luck;
    }

    /**
     *
     * @return number of boss monsters defeated
     */
    public int getnBossSlayed(){
        return nBossSlayed;
    }

    /**
     *
     * @return Equipment of type ARMOR equipped by the hero
     */
    public Equipment getArmor() {
        return armor;
    }

    /**
     *
     * @return Equipment of type HELM equipped by the hero
     */
    public Equipment getHelm() {
        return helm;
    }

    /**
     *
     * @return Equipment of type WEAPON equipped by the hero
     */
    public Equipment getWeapon() {
        return weapon;
    }

    /**
     *
     * @return Equipment of type SHIELD equipped by the hero
     */
    public Equipment getShield() {
        return shield;
    }

    /**
     *
     * @return Equipment of type GLOVES equipped by the hero
     */
    public Equipment getGloves() {
        return gloves;
    }

    /**
     *
     * @return Equipment of type BOOTS equipped by the hero
     */
    public Equipment getBoots() {
        return boots;
    }

    /**
     *
     * @return a copy of the map used as inventory for the hero
     */
    public Map<Item, Integer> getInventory() {
        return new HashMap<>(inventory);
    }

    /**
     *
     * @param inventory used to update hero's inventory after using or deleting an object
     */
    public void setInventory(Map<Item, Integer> inventory) {
        this.inventory = new HashMap<>(inventory);
    }

    /**
     *
     * @return a boolean based on hero's luck
     */
    public boolean isLucky(){
        int result=rand.nextInt(LUCK_RANGE)+rand.nextInt(luck)+1;
        return result >= LUCKY_POINT;
    }

    /**
     *
     * @param observer sets an observer used by the class MonsterBattle
     */
    public void setObserver(HeroObserver observer){

        this.observer = observer;
    }

    /**
     *
     * @return a String representation of the hero
     */
    public String toString(){
        return "org.lkyhro.Hero: [Nome: "+this.name+", hp: "+this.healthPoint+", atk: "+this.attack+", def: "+this.defense+
                ", luk: "+this.luck+", exp: "+this.experience+", level:"+this.level+", exptnl: "+this.expToNextLvl+
                ", levelExp: "+this.levelExp+", inventario:"+this.inventory.toString()+"]";
    }
}
