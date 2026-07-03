package org.lkyhro.item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemSingleton {

    private static ItemSingleton myInstance;
    @SuppressWarnings("serial")
	private Map<Integer, ConsumableItem> itemList= new HashMap<Integer, ConsumableItem>(){};

    /**
     * Constructive method for the Item Singleton
     */
    private ItemSingleton(){
        this.itemList.put(9,new ConsumableItem("Pozione", "Cura gli HP di 20 unità", 20, ItemType.HEALING, 1,9));
        this.itemList.put(10,new ConsumableItem("SuperPozione", "Cura gli HP di 50 unità", 50, ItemType.HEALING, 2,10));
        this.itemList.put(11,new ConsumableItem("IperPozione", "Cura gli HP di 100 unità", 100, ItemType.HEALING, 3,11));
        this.itemList.put(12,new ConsumableItem("Sasso", "Danneggia il nemico di 15 unità", 15, ItemType.THROWABLE, 1,12));
        this.itemList.put(13,new ConsumableItem("Coltello", "Danneggia il nemico di 30 unità", 30, ItemType.THROWABLE, 2,13));
        this.itemList.put(14,new ConsumableItem("Bomba", "Danneggia il nemico di 80 unità", 80, ItemType.THROWABLE, 3,14));
    }

    /**
     *
     * @return ItemSingleton an instance of the ItemSingleton
     */
    public static ItemSingleton getInstance(){
        if(myInstance==null){
            myInstance=new ItemSingleton();
        }
        return myInstance;
    }

    /**
     *
     * @param id identify number of the item
     * @return ConsumableItem selected from the Singleton by its id
     */
    public ConsumableItem getById(int id){
        return this.itemList.get(id);
    }

    /**
     *
     * @param rarity rarity of the item
     * @return Set of item selected from the Singleton by their rarity
     */
    public Set<ConsumableItem> selectedItems (int rarity){
        Set<ConsumableItem> selectedItems=new HashSet<>();
        for(ConsumableItem c : itemList.values()){
            if(c.getRarity()==rarity){
                selectedItems.add(c);
            }
        }
        return selectedItems;
    }


}
