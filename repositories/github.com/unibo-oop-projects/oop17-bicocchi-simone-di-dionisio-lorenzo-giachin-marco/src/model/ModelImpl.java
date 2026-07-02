package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.character.Stats;
import model.character.Character;
import model.character.TamagotchiCharacter;
import model.container.Box;

import model.container.ItemContainer;
import model.container.Shop;
import model.date.DateManager;
import model.date.DateManagerImpl;
import model.ranking.AbstractCharacter;
import model.ranking.Ranking;

/**
 * realization of interface model.
 * 
 *
 */
public class ModelImpl implements Model {

    private Character character;
    private ItemContainer shop;
    private DateManager dateManager;
    private Ranking ranking;
    private int difficulty;

    /**
     * 
     */

    public ModelImpl() {
        this.character = TamagotchiCharacter.getInstance();
        this.dateManager = new DateManagerImpl();
        this.ranking = new Ranking(new LinkedList<AbstractCharacter>());
    }

    @Override
    public void setCharacterName(final String name) {
        this.character.setName(name);
    }

    @Override
    public void addRanking() {
        this.ranking.addCharacter(new AbstractCharacter(character.getName(), character.getAge()));
    }

    @Override
    public void setShop(final Shop newShop) {
        this.shop = newShop;
    }

    @Override
    public double getDate() {
        return this.dateManager.getTimeToMod();
    }

    @Override
    public void initializeContainers(final Map<String, List<Box>> map) {
        this.shop = new Shop(map);
        this.character.initialize(map);
    }

    @Override
    public boolean buy(final String item) {
        for (Map.Entry<String, List<Box>> entry : this.getShopMap().entrySet()) {
            for (Box b : entry.getValue()) {
                System.out.println(b);
                if (b.getFirst().getName().equals(item) && b.getFirst().getPrice() < this.character.getBalance()) {
                    System.out.println(item);
                    this.character.addToInventory(b, entry.getKey());
                    this.character.setBalance(b.getFirst().getPrice());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean modAllStats(final double value) {
        for (Stats s : this.getListOfStats()) {
            if (!s.modStat(value)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean modStat(final String category) {
        for (Stats s : this.getListOfStats()) {
            if (s.getName().equals(category)) {
                Box box = this.character.getMainItemMap().get(category);
                if (box.getSecond() > 0) {
                    box.decreaseQuantity();
                    return s.modStat(box.getFirst().getPrice());
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public List<Stats> getListOfStats() {
        return this.character.getList();
    }

    @Override
    public String getMainItem(final String category) {
        return this.character.getMainItem(category).getFirst().getUrl();
    }

    @Override
    public List<AbstractCharacter> getRanking() {
        return this.ranking.getList();
    }

    @Override
    public Map<String, List<Box>> getItemMap() {
        return this.character.getItemMap();
    }

    @Override
    public Map<String, List<Box>> getShopMap() {
        return this.shop.getMap();
    }

    @Override
    public int getBalance() {
        return this.character.getBalance();
    }

    @Override
    public boolean setMainItem(final String category, final String item) {
        for (Box box : this.character.getItemMap().get(category)) {
            if (box.getFirst().getName().equals(item)) {
                this.character.setMainItem(category, box);
                return true;
            }
        }
        this.character.setMainItem(category, null);
        return false;
    }

    @Override
    public void checkAndSetMainItem() {
        for (Map.Entry<String, Box> entry : this.character.getMainItemMap().entrySet()) {
            if (entry.getValue() == null) {
                this.character.setDefaultMainItem(entry.getKey());
            }
        }
    }

    @Override
    public String getCharacterUrl() {
        return this.character.getUrl();
    }

    @Override
    public void setCharacterUrl(final String newUrl) {
        this.character.setUrl(newUrl);
    }

    /**
     * 
     * @return the character for this model
     */
    protected TamagotchiCharacter getCharacter() {
        return (TamagotchiCharacter) this.character;
    }

    /**
     * 
     * @param newCharacter
     *            is the new character for this model
     */
    protected void setCharacter(final Character newCharacter) {
        this.character = newCharacter;
    }

    /**
     * 
     * @return the dateManager for this model
     */
    protected DateManager getManager() {
        return this.dateManager;
    }

    /**
     * 
     * @param manager
     *            is the new datemanager for this model
     */
    protected void setManager(final DateManager manager) {
        this.dateManager = manager;
    }

    /**
     * 
     * @param newRanking
     *            is the new datemanager for this model
     */
    protected void setRanking(final Ranking newRanking) {
        this.ranking = newRanking;
    }

    @Override
    public String getName() {
        return this.character.getName();
    }

    @Override
    public void ageUp() {
        this.character.ageUp();
    }

    @Override
    public double getAge() {
        return this.character.getAge();
    }

    @Override
    public void setAge(final double time) {
        this.character.setAge(time);
    }

    @Override
    public void addMoney(final int amount) {
        this.character.addMoney(amount);
    }

    @Override
    public void setDecrementFactor(final double newFactor) {
        this.character.setFactor(newFactor);
    }
    @Override
    public void setDifficulty(final int newDifficulty) {
        this.difficulty = newDifficulty;
    }
    @Override
    public int getDifficulty() {
        return this.difficulty;
    }

}
