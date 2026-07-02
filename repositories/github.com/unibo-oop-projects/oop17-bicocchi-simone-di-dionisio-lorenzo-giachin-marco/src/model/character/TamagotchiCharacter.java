package model.character;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import model.container.Box;
import model.container.Inventory;
import model.container.ItemContainer;

/**
 * 
 * 
 * This is a singleton class, it's a single character (for game).
 *
 */
public final class TamagotchiCharacter implements Character, Serializable {

    private static final long serialVersionUID = -302736040080690498L;
    private String name;
    private float age;
    private List<Stats> statList;
    private static TamagotchiCharacter instance;
    private Wallet wallet;
    private ItemContainer inventory;
    private static boolean newCharacter;
    private String url;
    private double factor;
    private static final double INITIAL_FACTOR = 0.001;
    private static final int INITIAL_BALANCE = 100;

    /**
     * constructor creates the inventory with the same categories of stats, only one
     * time
     */
    private TamagotchiCharacter() {
        wallet = new Wallet(INITIAL_BALANCE);
        this.factor = INITIAL_FACTOR;
    }

    /**
     * implementation of Singleton pattern for TamagotchiCharacter.
     * 
     * @return the only instance of TamagotchiCharacter
     */
    public static TamagotchiCharacter getInstance() {
        if (!newCharacter) {
            if (instance == null) {
                instance = new TamagotchiCharacter();
                newCharacter = !newCharacter;
            }
        } else {
            instance = null;
            newCharacter = !newCharacter;
            return TamagotchiCharacter.getInstance();
        }
        return instance;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBalance() {
        return this.wallet.getBalance();
    }

    @Override
    public void setBalance(final int amount) {
        this.wallet.useMoney(amount);
    }

    @Override
    public void addMoney(final int amount) {
        this.wallet.setBalance(this.age, amount);
    }

    @Override
    public double getFactor() {
        return this.factor;
    }

    @Override
    public void setFactor(final double newFactor) {
        this.factor = newFactor;
    }

    @Override
    public void setName(final String newName) {
        this.name = newName;
    }

    @Override
    public float getAge() {
        return this.age;
    }

    @Override
    public void setAge(final double newAge) {
        this.age += (newAge * this.factor);
    }

    @Override
    public void ageUp() {
        this.age += this.factor;
    }

    @Override
    public List<Stats> getList() {
        return this.statList;
    }

    @Override
    public void setList(final List<Stats> newList) {
        this.statList = newList;
    }

    @Override
    public String getListToString() {
        String string = "";
        for (Stats s : this.statList) {
            string = string + s;
        }
        return string;
    }

    @Override
    public Map<String, List<Box>> getItemMap() {
        return this.inventory.getMap();
    }

    @Override
    public Box getMainItem(final String category) {
        return ((Inventory) this.inventory).getMainItem(category);
    }

    @Override
    public void setMainItem(final String category, final Box item) {
        ((Inventory) this.inventory).setMainItem(category, item);
    }

    @Override
    public Map<String, Box> getMainItemMap() {
        return ((Inventory) this.inventory).getMainItemMap();

    }

    @Override
    public void addToInventory(final Box item, final String category) {
        this.inventory.addItemForCategory(item, category);
    }

    @Override
    public void initialize(final Map<String, List<Box>> newMap) {
        this.inventory = new Inventory(newMap);
    }

    @Override
    public String toString() {
        return "TamagotchiCharacter [name=" + name + ", age=" + age + ", statList=" + statList + ", inventory="
                + inventory + ", wallet=" + wallet + "]";
    }

    @Override
    public void setDefaultMainItem(final String category) {
        if (!this.inventory.getMap().get(category).isEmpty()) {
            this.setMainItem(category, this.inventory.getMap().get(category).get(0));
        }
    }

    @Override
    public void setUrl(final String newUrl) {
        this.url = newUrl;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
