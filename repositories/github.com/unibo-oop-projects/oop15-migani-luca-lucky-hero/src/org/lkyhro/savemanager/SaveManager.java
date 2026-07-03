package org.lkyhro.savemanager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.lkyhro.item.EquipmentSingleton;
import org.lkyhro.Hero;
import org.lkyhro.item.Item;
import org.lkyhro.item.ItemSingleton;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luca on 18/02/2016.
 */
public class SaveManager implements ISaveManager {

    private static final String NAME="name";
    private static final String HP="healthpoints";
    private static final String ATTACK="attack";
    private static final String DEFENSE="defense";
    private static final String EXP="experience";
    private static final String LUCK="luck";
    private static final String LVL="level";
    private static final String EXPTONLVL="exptonextlevel";
    private static final String LVLEXP="levelexp";
    private static final String NBOSSSLAYED="nbossslayed";
    private static final String EQUIPMENT="equipment";
    private static final String HELM="helm";
    private static final String ARMOR="armor";
    private static final String WEAPON="weapon";
    private static final String SHIELD="shield";
    private static final String GLOVES="gloves";
    private static final String BOOTS="boots";
    private static final String INVENTORY="inventory";
    private final String fileName;

    /**
     * Constructor method for SaveManager
     * @param fileName name of the saving file
     */
    public SaveManager(String fileName){
        this.fileName=fileName;
    }

    /**
     * Method used to save the hero's progress in a file.
     * The hero and his proprieties will be saved in a JSONObject and written in a file,
     * it will contain a JSONObject for his Equipment while the inventory will be saved in a JSONArray
     * @param hero Hero used by the player
     * @throws IOException if there's some problem with writing the saving file
     */
    @Override
    public void saveGame(Hero hero) throws IOException {
        JSONObject obj=new JSONObject();
        obj.put(NAME, hero.getName());
        obj.put(HP, hero.getHealthPoint());
        obj.put(ATTACK, hero.getAttack());
        obj.put(DEFENSE, hero.getDefense());
        obj.put(EXP, hero.getExperience());
        obj.put(LUCK, hero.getLuck());
        obj.put(LVL, hero.getLevel());
        obj.put(EXPTONLVL, hero.getExpToNextLvl());
        obj.put(LVLEXP, hero.getLevelExp());
        obj.put(NBOSSSLAYED, hero.getnBossSlayed());
        JSONObject equipmentObj=new JSONObject();
        if (hero.getHelm()== null) {
            equipmentObj.put(HELM, -1);
        }else{
            equipmentObj.put(HELM, hero.getHelm().getId());
        }
        if (hero.getArmor()== null){
            equipmentObj.put(ARMOR, -1);
        }else{
            equipmentObj.put(ARMOR, hero.getArmor().getId());
        }
        if (hero.getWeapon()== null){
            equipmentObj.put(WEAPON, -1);
        }else{
            equipmentObj.put(WEAPON, hero.getWeapon().getId());
        }
        if(hero.getShield()== null){
            equipmentObj.put(SHIELD, -1);
        }else{
            equipmentObj.put(SHIELD, hero.getShield().getId());
        }
        if(hero.getGloves()==null){
            equipmentObj.put(GLOVES, -1);
        }else{
            equipmentObj.put(GLOVES, hero.getGloves().getId());
        }
        if(hero.getBoots()==null){
            equipmentObj.put(BOOTS, -1);
        }else{
            equipmentObj.put(BOOTS, hero.getBoots().getId());
        }
        obj.put(EQUIPMENT, equipmentObj);
        JSONArray inventory=new JSONArray();
        Map<Item, Integer> inventoryCopy=hero.getInventory();
        for (Map.Entry<Item, Integer> itemAndCount : inventoryCopy.entrySet()) {
            for(int i=0; i<itemAndCount.getValue(); i++){
                inventory.put(itemAndCount.getKey().getId());
            }
        }
        obj.put(INVENTORY, inventory);
        FileOutputStream outputStream=new FileOutputStream(new File(fileName));
        outputStream.write(obj.toString().getBytes(Charset.forName("UTF-8")));
        outputStream.close();
    }

    /**
     * This method will load from a file hero's data from a previous game,
     * by reading the file with a FileInputStream and loading in a JSONObject,
     * that will be read and re-create the hero and his characteristics
     * @return Hero used by the player, with his characteristic loaded from a previous game
     * @throws IOException if there's some problem with reading the saving file
     */
    @Override
    public Hero loadGame() throws IOException {
        FileInputStream fileInputStream=new FileInputStream(new File(fileName));
        byte[] fileText=new byte[(int)new File(fileName).length()];
        fileInputStream.read(fileText);
        fileInputStream.close();
        JSONObject readObj=new JSONObject(new String(fileText, Charset.forName("UTF-8")));
        JSONObject equipmentObj=readObj.getJSONObject(EQUIPMENT);
        Map<Item, Integer> inventoryLoaded=new HashMap<>();
        JSONArray inventory=readObj.getJSONArray(INVENTORY);
        for (int i=0; i<inventory.length(); i++){
            if(ItemSingleton.getInstance().getById(inventory.getInt(i))==null){
                inventoryLoaded.compute(EquipmentSingleton.getInstance().getEquipmentById(inventory.getInt(i)),
                        (x, y)-> y==null?1:y+1);
            }else {
                inventoryLoaded.compute(ItemSingleton.getInstance().getById(inventory.getInt(i)),
                        (x, y) -> y == null ? 1 : y + 1);
            }
        }
        return new Hero(readObj.getString(NAME), readObj.getInt(ATTACK), readObj.getInt(DEFENSE),
                            readObj.getInt(EXP), readObj.getInt(HP), readObj.getInt(LVL), readObj.getInt(LUCK),
                            readObj.getInt(LVLEXP), readObj.getInt(NBOSSSLAYED),
                            EquipmentSingleton.getInstance().getEquipmentById(equipmentObj.getInt(ARMOR)),
                            EquipmentSingleton.getInstance().getEquipmentById(equipmentObj.getInt(HELM)),
                            EquipmentSingleton.getInstance().getEquipmentById(equipmentObj.getInt(BOOTS)),
                            EquipmentSingleton.getInstance().getEquipmentById(equipmentObj.getInt(WEAPON)),
                            EquipmentSingleton.getInstance().getEquipmentById(equipmentObj.getInt(SHIELD)),
                            EquipmentSingleton.getInstance().getEquipmentById(equipmentObj.getInt(GLOVES)),
                            inventoryLoaded);
    }
}
