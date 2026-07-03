package org.lkyhro.item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Migani Luca on 13/01/2016.
 */
public class EquipmentSingleton {

    private static EquipmentSingleton myInstance;
    private Map<Integer, Equipment> equipmentList = new HashMap<>();

    /**
     * Constructor method for the Singleton of Equipment
     */
    private EquipmentSingleton () {
        this.equipmentList.put(1,new Equipment("Pugnale di Cristallo", "Attacco +15", 15, ItemType.WEAPON, 1,1));
        this.equipmentList.put(2,new Equipment("Spada del Dominatore", "Attacco +30",30, ItemType.WEAPON, 2,2));
        this.equipmentList.put(3,new Equipment("Ascia da Battaglia", "Attacco +50", 50, ItemType.WEAPON, 3,3));
        this.equipmentList.put(4,new Equipment("Scudo di legno", "Difesa +10", 10, ItemType.SHIELD, 1,4));
        this.equipmentList.put(5,new Equipment("Armatura del Vile", "Difesa +100", 100, ItemType.ARMOR, 3,5));
        this.equipmentList.put(6,new Equipment("Guanti rinforzati", "Difesa +5", 5, ItemType.GLOVES, 1,6));
        this.equipmentList.put(7,new Equipment("Gambali da Cavaliere","Difesa +10", 10, ItemType.BOOTS, 2,7));
        this.equipmentList.put(8,new Equipment("Corona del Re degli Scheletri", "Difesa +15", 15, ItemType.HELM, 2,8));
        this.equipmentList.put(15,new Equipment("Spada Corta", "Attacco +5", 5, ItemType.WEAPON,1,15));
        this.equipmentList.put(16,new Equipment("Stracci Sporchi", "Difesa +3", 3, ItemType.ARMOR,1,16));
        this.equipmentList.put(17,new Equipment("Guanti Semplici", "Difesa +2", 2, ItemType.GLOVES,1,17));
        this.equipmentList.put(18,new Equipment("Stivali", "Difesa +2",2, ItemType.BOOTS,1,18));
        this.equipmentList.put(19,new Equipment("Pentola in rame", "Difesa +2",2, ItemType.HELM,1,19));

    }

    /**
     *
     * @return EquipmentSingleton an instance of EquipmentSingleton
     */
    public static EquipmentSingleton getInstance(){
        if(myInstance==null){
            myInstance=new EquipmentSingleton();
        }
        return myInstance;
    }

    /**
     *
     * @param id identify number of the Equipment
     * @return Equipment selected from the singleton by its id
     */
    public Equipment getEquipmentById(int id){
        if(id==-1){
            return null;
        }
        return this.equipmentList.get(id);
    }

    /**
     *
     * @param rarity rarity of the equipment
     * @return Set of selected Equipments by their rarity
     */
    public Set<Equipment> selectedItems (int rarity){
        Set<Equipment> selectedItems=new HashSet<>();
        for(Equipment e : equipmentList.values()){
            if(e.getRarity()==rarity){
                selectedItems.add(e);
            }
        }
        return selectedItems;
    }
}
